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

package org.dreisiebner.rxjavademo.domain;

/**
 * @author ndreisiebner@netconomy.net
 */
public class ProductResponse extends Product implements Comparable<ProductResponse>
{
  private Price price;
  private Rating rating;
  private long priority;

  public ProductResponse(Product product) {
    super(product.getId(), product.getName(), product.getManufacturer(), product.getImageUrl());
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public long getPriority() {
    return priority;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }

  @Override
  public int compareTo(ProductResponse other) {
    return Long.compare(this.getPriority(), other.getPriority());
  }
}
