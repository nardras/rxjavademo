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
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.domain.ProductResponse;
import org.dreisiebner.rxjavademo.domain.Rating;
import org.dreisiebner.rxjavademo.domain.Response;
import org.dreisiebner.rxjavademo.domain.User;
import org.dreisiebner.rxjavademo.facade.RecommendationFacade;
import org.dreisiebner.rxjavademo.facade.impl.DefaultRecommendationFacade;
import org.dreisiebner.rxjavademo.util.Helper;

/**
 * @author ndreisiebner@netconomy.net
 */
@Path("/sync")
public class RecommendationResource {

    private RecommendationFacade facade = new DefaultRecommendationFacade();

    @GET
    @Path("/recommendations/{userId}")
    @Produces("application/json")
    public final void getHome(@Suspended final AsyncResponse asyncResponse, @PathParam("userId") final String userId) {
        Helper.debug("[S] request");
        final long time = System.nanoTime();

        try {

            final User user = facade.getUserForId(userId);
            final List<ProductResponse> products = facade.getRecommendationsForUser(user.getId())
                .stream()
                .limit(4)
                .map((recommendation) -> {
                    final Product product = facade.getProductForId(recommendation.getProductId());
                    final Price price = facade
                        .getPriceForProductAndCountry(recommendation.getProductId(), user.getCountryIsoCode());
                    final Rating rating = facade.getRatingForProduct(recommendation.getProductId());

                    final ProductResponse productResponse = new ProductResponse(product);
                    productResponse.setPriority(recommendation.getPriority());
                    productResponse.setPrice(price);
                    productResponse.setRating(rating);
                    return productResponse;
                })
                .collect(Collectors.toList());

            final Response response = new Response();
            response.setProducts(products);
            response.setUser(user);
            response.setTime(Helper.getTime(time));

            // initiate successful response
            asyncResponse.resume(response);

            // finished
            Helper.debug("[E] request");

        } catch (Exception e) {
            // initiate error message
            asyncResponse.resume(e);
        }
    }
}
