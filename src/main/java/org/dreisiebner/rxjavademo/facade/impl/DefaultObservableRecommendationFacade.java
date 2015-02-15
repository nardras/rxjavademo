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

package org.dreisiebner.rxjavademo.facade.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.domain.Recommendation;
import org.dreisiebner.rxjavademo.domain.User;
import org.dreisiebner.rxjavademo.service.impl.DefaultPriceService;
import org.dreisiebner.rxjavademo.service.impl.DefaultRatingService;
import org.dreisiebner.rxjavademo.service.impl.DefaultUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import org.dreisiebner.rxjavademo.domain.Rating;
import org.dreisiebner.rxjavademo.facade.ObservableRecommendationFacade;
import org.dreisiebner.rxjavademo.service.PriceService;
import org.dreisiebner.rxjavademo.service.ProductService;
import org.dreisiebner.rxjavademo.service.RatingService;
import org.dreisiebner.rxjavademo.service.UserService;
import org.dreisiebner.rxjavademo.service.impl.DefaultProductService;
import org.dreisiebner.rxjavademo.service.impl.DefaultRecommendationService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultObservableRecommendationFacade implements ObservableRecommendationFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultObservableRecommendationFacade.class);

    private Executor executor = Executors.newFixedThreadPool(32);

    private DefaultRecommendationService recommendationService = new DefaultRecommendationService();

    private UserService userService = new DefaultUserService();

    private PriceService priceService = new DefaultPriceService();

    private RatingService ratingService = new DefaultRatingService();

    private ProductService productService = new DefaultProductService();

    @Override
    public Observable<User> getUserForId(String userId) {

        return Observable
            .create(s -> {
                executor.execute(() -> {
                    try {
                        s.onNext(userService.getCountryForUser(userId));
                        s.onCompleted();
                    } catch (Exception e) {
                        s.onError(e);
                    }
                });
            });
    }

    @Override
    public Observable<Recommendation> getRecommendationsForUser(String userId) {
        return Observable
            .create(s -> {
                executor.execute(() -> {
                    try {
                        recommendationService.getRecommendationsForUser(userId)
                            .forEach(r -> s.onNext(r));
                        s.onCompleted();
                    } catch (Exception e) {
                        s.onError(e);
                    }
                });
            });
    }

    @Override
    public Observable<Product> getProductForId(String productId) {
        return Observable
            .create(s -> {
                executor.execute(() -> {
                    try {
                        s.onNext(productService.getProductForId(productId));
                        s.onCompleted();
                    } catch (Exception e) {
                        s.onError(e);
                    }
                });
            });
    }

    @Override
    public Observable<Price> getPriceForProductAndCountry(String productId,
        String countryIsoCode) {
        return Observable
            .create(s -> {
                executor.execute(() -> {
                    try {
                        s.onNext(
                            priceService.getPriceForProductAndCountry(productId, countryIsoCode));
                        s.onCompleted();
                    } catch (Exception e) {
                        s.onError(e);
                    }
                });
            });
    }

    @Override
    public Observable<Rating> getRatingForProduct(String productId) {
        return Observable.create(s -> {
            executor.execute(() -> {
                try {
                    s.onNext(ratingService.getRatingForProduct(productId));
                    s.onCompleted();
                } catch (Exception e) {
                    s.onError(e);
                }
            });
        });
    }

}
