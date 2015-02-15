/*********************************************************************
 * The Initial Developer of the content of this file is NETCONOMY.
 * All portions of the code written by NETCONOMY are property of
 * NETCONOMY. All Rights Reserved.
 *
 * NETCONOMY Software & Consulting GmbH
 * Hilmgasse 4, A-8010 Graz (Austria)
 * FN 204360 f, Landesgericht fuer ZRS Graz
 * Tel: +43 (316) 815 544
 * Fax: +43 (316) 815544-99
 * www.netconomy.net
 *
 * (c) 2015 by NETCONOMY Software & Consulting GmbH
 *********************************************************************/

package org.dreisiebner.rxjavademo.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import rx.Observable;
import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.domain.ProductResponse;
import org.dreisiebner.rxjavademo.domain.Rating;
import org.dreisiebner.rxjavademo.domain.Recommendation;
import org.dreisiebner.rxjavademo.domain.Response;
import org.dreisiebner.rxjavademo.domain.User;
import org.dreisiebner.rxjavademo.facade.ObservableRecommendationFacade;
import org.dreisiebner.rxjavademo.facade.impl.DefaultObservableRecommendationFacade;
import org.dreisiebner.rxjavademo.util.Helper;

import static org.dreisiebner.rxjavademo.util.Helper.debug;

/**
 * @author ndreisiebner@netconomy.net
 */
@Path("/rx")
public class ObservableRecommendationResource {

    private ObservableRecommendationFacade facade = new DefaultObservableRecommendationFacade();

    @GET
    @Path("/recommendations/{userId}")
    @Produces("application/json")
    public final void getHome(@Suspended final AsyncResponse asyncResponse, @PathParam("userId") final String userId) {

        debug("[S] request");
        final long time = System.nanoTime();

        /**
         * Since we subscribe to userStream everytime we query a price, we'll use cache operator here, in order to make
         * sure the user is only emitted once (which means that the UserService is only called once).
         */
        final Observable<User> userStream = facade
                .getUserForId(userId) // Observable<User> user
                .cache(); // Observable<User> user

        /**
         * We use the take operator to restrict the amount of recommendations to 4.
         */
        final Observable<Recommendation> recommendationStream = facade
                .getRecommendationsForUser(userId) // Observable<Recommendation> r1, r2, r3, r4, r5, ...
                .take(4); // Observable<Recommendation> r1, r2, r3, r4

        final Observable<ProductResponse> productResponseStream = recommendationStream
                .flatMap(recommendation -> {
                    final Observable<Product> productStream = facade.getProductForId(
                            recommendation.getProductId()); // Observable<Product> product_n

                    final Observable<Rating> ratingStream = facade.getRatingForProduct(
                            recommendation.getProductId()); // Observable<Rating> rating_n

                    final Observable<Price> priceStream = userStream.flatMap(
                            user -> facade.getPriceForProductAndCountry(recommendation.getProductId(),
                                    user.getCountryIsoCode())); // Observable<Price> price_n

                    return Observable.zip(productStream, priceStream, ratingStream,
                            (final Product product, final Price price, final Rating rating) -> {
                                final ProductResponse productResponse = new ProductResponse(product);
                                productResponse.setPriority(recommendation.getPriority());
                                productResponse.setPrice(price);
                                productResponse.setRating(rating);
                                return productResponse;
                            }); // Observable<ProductResponse> productResponse_n
        }); // Observable<ProductResponse> productResponse_1, productResponse_3, productResponse_4, productResponse_2

        /**
         * We want to get from a Observable<ProductResponse> emitting 4 items to Observable<List<ProductResponse>>
         * emitting 1 list containing 4 items.
         *
         * Since we used flatMap to execute calls for products, ratings and prices in parallel, we also have to sort.
         */
         final Observable<List<ProductResponse>> productResponsesStream = productResponseStream.toSortedList();
        // Observable<List<ProductResponse>> productResponseList[productResponse_1, productResponse_2,
        //                                                       productResponse_3, productResponse_4]

        final Observable<Response> responseStream = Observable.zip(productResponsesStream, userStream,
                (productResponses, user) -> {
                    final Response response = new Response();
                    response.setProducts(productResponses);
                    response.setUser(user);
                    response.setTime(Helper.getTime(time));
                    return response;
                }); // Observable<Response> response

        // subscribe to response
        responseStream.subscribe(response -> asyncResponse.resume(response),
                error -> asyncResponse.resume(error),
                () -> debug("[E] request"));
    }
}
