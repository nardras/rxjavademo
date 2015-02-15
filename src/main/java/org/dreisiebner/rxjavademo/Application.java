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

package org.dreisiebner.rxjavademo;

import org.dreisiebner.rxjavademo.resource.ObservableRecommendationResource;
import org.dreisiebner.rxjavademo.resource.RecommendationResource;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author ndreisiebner@netconomy.net
 */
public class Application extends ResourceConfig {

    static final String BASE_URI = "http://localhost:8080/";

    public Application() {
        register(RecommendationResource.class);
        register(ObservableRecommendationResource.class);
        register(JacksonFeature.class);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        final ResourceConfig rc = new Application();
        final URI endpoint = new URI(BASE_URI);
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(endpoint, rc);

        System.out.println("Press Enter to stop the server. ");
        System.in.read();
        server.shutdownNow();
    }
}
