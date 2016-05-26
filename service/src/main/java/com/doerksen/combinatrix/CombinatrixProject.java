package com.doerksen.combinatrix;

import com.doerksen.combinatrix.resources.UserResource;
import com.doerksen.combinatrix.resources.impl.UserResourceImpl;
import com.doerksen.elasticsearch_service.resources.ElasticResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class CombinatrixProject extends Application<CombinatrixProjectConfiguration> {

    public static void main(String[] args) throws Exception {
        new CombinatrixProject().run(args);
    }

    @Override
    public void run(CombinatrixProjectConfiguration configuration, Environment environment) throws Exception {

        /**
         * Clients for services we need should be created here.
         * Please keep these in alphabetical order.
         */
        WebTarget elasticServiceClient = createClient("http://", configuration.getElasticHost(), configuration.getElasticPort());

        /**
         * Bind the external resources to the appropriate client.
         * Please keep these in alphabetical order.
         */
        ElasticResource test = bindResourceToTarget(ElasticResource.class, elasticServiceClient);

        /**
         * Create resources internal to this service.
         * Please keep these in alphabetical order.
         */
        UserResource uR2 = new UserResourceImpl(test);

        /**
         * Bind/register the internal resource to our own server.
         * Please keep these in alphabetical order.
         */
        environment.jersey().register(uR2);
    }

    public static <T> T bindResourceToTarget(Class<T> resource,
                                             WebTarget target) {
        return WebResourceFactory.newResource(resource, target);
    }

    public static WebTarget createClient(String urlAccessor,
                                         String host,
                                         String port) {
        Client client = ClientBuilder.newClient();
        return client.target(urlAccessor + host + ":" + port);
    }
}
