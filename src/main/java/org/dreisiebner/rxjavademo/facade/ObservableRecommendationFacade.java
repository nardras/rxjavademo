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

package org.dreisiebner.rxjavademo.facade;

import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.domain.Recommendation;
import org.dreisiebner.rxjavademo.domain.User;

import rx.Observable;

import org.dreisiebner.rxjavademo.domain.Rating;

/**
 * @author ndreisiebner@netconomy.net
 */
public interface ObservableRecommendationFacade {

    Observable<User> getUserForId(String userId);

    Observable<Recommendation> getRecommendationsForUser(final String userId);

    Observable<Product> getProductForId(final String productId);

    Observable<Price> getPriceForProductAndCountry(final String productId, final String countryIsoCode);

    Observable<Rating> getRatingForProduct(final String productId);
}
