package org.jboss.arquillian.quickstart.resteasy.service.rs;

import org.jboss.arquillian.quickstart.resteasy.application.StockApplication;
import org.jboss.arquillian.quickstart.resteasy.model.Stock;
import org.jboss.arquillian.quickstart.resteasy.service.StockService;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.File;

/**
 * An utility class that creates the test deployments.
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
final class Deployments {

    /**
     * Creates test deployment.
     *
     * @return the test deployment
     */
    public static WebArchive createDeployment(String name) {
        File[] libs = loadLibraries();

        return ShrinkWrap.create(WebArchive.class, name + ".war")
            .addClasses(StockApplication.class, Stock.class, StockService.class, StockServiceResource.class)
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsLibraries(libs);
    }

    /**
     * Loads all required dependencies needed to run the application in the application server.
     *
     * @return the loaded dependencies
     */
    private static File[] loadLibraries() {
        return Maven.resolver().resolve("org.easytesting:fest-assert:1.4", "org.jboss.resteasy:resteasy-jaxrs:3.6.3.Final").withTransitivity().asFile();
    }
}
