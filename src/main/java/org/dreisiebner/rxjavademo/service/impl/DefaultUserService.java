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
import org.dreisiebner.rxjavademo.domain.User;
import org.dreisiebner.rxjavademo.service.UserService;

/**
 * @author ndreisiebner@netconomy.net
 */
public class DefaultUserService implements UserService
{
  @Override
  public User getCountryForUser(String userId) {

    Helper.debug("[S] get user", userId);
    try {
      Helper.sleep();
      switch (userId) {
        case "user_AT" :
          return new User("user_AT", "Austrian User", "AT");
        case "user_DE" :
          return new User("user_DE", "German User", "DE");
      }

      throw new RuntimeException("unknown user");
    }
    finally {
      Helper.debug("[E] get user", userId);
    }
  }
}
