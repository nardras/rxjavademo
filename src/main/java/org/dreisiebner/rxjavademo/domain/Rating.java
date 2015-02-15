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
public class Rating
{
  private int stars;
  private long numberOfComments;

  public Rating(int stars, long numberOfComments) {
    this.stars = stars;
    this.numberOfComments = numberOfComments;
  }

  public int getStars() {
    return stars;
  }

  public void setStars(int stars) {
    this.stars = stars;
  }

  public long getNumberOfComments() {
    return numberOfComments;
  }

  public void setNumberOfComments(long numberOfComments) {
    this.numberOfComments = numberOfComments;
  }
}
