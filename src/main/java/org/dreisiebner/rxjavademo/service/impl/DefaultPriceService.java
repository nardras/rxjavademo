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
import org.dreisiebner.rxjavademo.domain.Price;
import org.dreisiebner.rxjavademo.service.PriceService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultPriceService implements PriceService
{

  public Price getPriceForProductAndCountry(String productId, String countryIsoCode) {

    Helper.debug("[S] get price", productId);
    try {
      Helper.sleep();
      switch (countryIsoCode) {
        case "AT":
          return getPriceForAT(productId);
        case "DE":
          return getPriceForDE(productId);
      }
      throw new RuntimeException("country not supported");
    }
    finally {
      Helper.debug("[E] get price", productId);
    }
  }

  private Price getPriceForAT(String productId)
  {
    switch (productId) {
      case "1000":
        return new Price(89.99, "EUR");
      case "1010":
        return new Price(99.99, "EUR");
      case "1020":
        return new Price(109.99, "EUR");
      case "1030":
        return new Price(119.99, "EUR");
      case "1040":
        return new Price(120.99, "EUR");
    }

    throw new RuntimeException("product not supported");
  }

  private Price getPriceForDE(String productId)
  {
    switch (productId) {
      case "1000":
        return new Price(75.99, "EUR");
      case "1010":
        return new Price(85.99, "EUR");
      case "1020":
        return new Price(95.99, "EUR");
      case "1030":
        return new Price(105.99, "EUR");
      case "1040":
        return new Price(115.99, "EUR");
    }

    throw new RuntimeException("product not supported");
  }
}
