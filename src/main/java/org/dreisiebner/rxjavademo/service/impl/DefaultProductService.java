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

import org.dreisiebner.rxjavademo.domain.Product;
import org.dreisiebner.rxjavademo.util.Helper;
import org.dreisiebner.rxjavademo.service.ProductService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultProductService implements ProductService {

    @Override
    public Product getProductForId(String productId) {
        Helper.debug("[S] get product", productId);
        try {
            Helper.sleep();
            return new Product(productId, "Product " + productId, "Manfacturer producting " + productId, "http://www.image.com/" + productId + ".jpg");
        }
        finally {
            Helper.debug("[E] get product", productId);
        }
    }
}
