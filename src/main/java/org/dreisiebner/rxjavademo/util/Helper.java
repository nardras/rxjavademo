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

package org.dreisiebner.rxjavademo.util;

/**
 * @author ndreisiebner@netconomy.net
 */
public class Helper {

    private static final long PROCESSING_TIME = 100;

    public static void sleep() {
        sleep(PROCESSING_TIME);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void debug(String action, Object n) {
        System.out.println(
            System.currentTimeMillis() + " | " + action + " - " + n + " \t[Thread=" + Thread
                .currentThread().getId() + "]");
    }

    public static void debug(String action) {
        System.out.println(
            System.currentTimeMillis() + " | " + action + " \t[Thread=" + Thread.currentThread()
                .getId() + "]");
    }

    public static long getTime(long time) {
        return (System.nanoTime() - time) / 1000000;
    }
}
