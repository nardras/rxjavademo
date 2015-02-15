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

import org.dreisiebner.rxjavademo.util.Helper;
import org.dreisiebner.rxjavademo.domain.Rating;
import org.dreisiebner.rxjavademo.service.RatingService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultRatingService implements RatingService
{
  @Override
  public Rating getRatingForProduct(final String productId) {

    Helper.debug("[S] get rating", productId);
    try {
      Helper.sleep();
      switch (productId) {
        case "1000":
          return new Rating(3, 16);
        case "1010":
          return new Rating(4, 12);
        case "1020":
          return new Rating(5, 29);
        case "1030":
          return new Rating(2, 4);
        case "1040":
          return new Rating(3, 29);
      }

      throw new RuntimeException("product not found");
    }
    finally {
      Helper.debug("[E] get rating", productId);
    }
  }
}
