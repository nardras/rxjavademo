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

package org.dreisiebner.rxjavademo.service.impl;

import java.util.List;

import org.dreisiebner.rxjavademo.domain.Recommendation;

import jersey.repackaged.com.google.common.collect.Lists;
import org.dreisiebner.rxjavademo.util.Helper;
import org.dreisiebner.rxjavademo.service.RecommendationService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultRecommendationService implements RecommendationService {

    @Override
    public List<Recommendation> getRecommendationsForUser(String userId) {

        Helper.debug("[S] get recommendations", userId);
        try {
            Helper.sleep();

            switch (userId) {
                case "user_AT":
                    return getProductsAT();
                case "user_DE":
                    return getProductsDE();
            }

            throw new RuntimeException("unknown user");
        }
        finally {
            Helper.debug("[E] get recommendations", userId);
        }
    }

    private List<Recommendation> getProductsAT() {
        return Lists.newArrayList(
            new Recommendation("1000", 1),
            new Recommendation("1010", 2),
            new Recommendation("1020", 3),
            new Recommendation("1030", 4),
            new Recommendation("1040", 5)
        );
    }

    private List<Recommendation> getProductsDE() {
        return Lists.newArrayList(
            new Recommendation("1030", 1),
            new Recommendation("1040", 2),
            new Recommendation("1000", 3),
            new Recommendation("1010", 4),
            new Recommendation("1020", 5)
        );
    }

}
