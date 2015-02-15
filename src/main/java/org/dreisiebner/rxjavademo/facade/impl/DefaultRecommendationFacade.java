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

import java.util.List;

import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.domain.Recommendation;
import org.dreisiebner.rxjavademo.domain.User;
import org.dreisiebner.rxjavademo.facade.RecommendationFacade;
import org.dreisiebner.rxjavademo.service.PriceService;
import org.dreisiebner.rxjavademo.service.UserService;
import org.dreisiebner.rxjavademo.service.impl.DefaultPriceService;
import org.dreisiebner.rxjavademo.service.impl.DefaultRatingService;
import org.dreisiebner.rxjavademo.service.impl.DefaultRecommendationService;
import org.dreisiebner.rxjavademo.service.impl.DefaultUserService;
import org.dreisiebner.rxjavademo.domain.Rating;
import org.dreisiebner.rxjavademo.service.ProductService;
import org.dreisiebner.rxjavademo.service.RatingService;
import org.dreisiebner.rxjavademo.service.impl.DefaultProductService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultRecommendationFacade implements RecommendationFacade
{

    private DefaultRecommendationService
        recommendationService = new DefaultRecommendationService();

    private UserService userService = new DefaultUserService();

    private PriceService priceService = new DefaultPriceService();

    private RatingService ratingService = new DefaultRatingService();

    private ProductService productService = new DefaultProductService();


    @Override
    public User getUserForId(String userId) {
        return userService.getCountryForUser(userId);
    }

    @Override
    public List<Recommendation> getRecommendationsForUser(String userId) {
        return recommendationService.getRecommendationsForUser(userId);
    }

    @Override
    public Product getProductForId(String productId) {
        return productService.getProductForId(productId);
    }

    @Override
    public Price getPriceForProductAndCountry(String productId, String countryIsoCode) {
        return priceService.getPriceForProductAndCountry(productId, countryIsoCode);
    }

    @Override
    public Rating getRatingForProduct(String productId) {
        return ratingService.getRatingForProduct(productId);
    }
}
