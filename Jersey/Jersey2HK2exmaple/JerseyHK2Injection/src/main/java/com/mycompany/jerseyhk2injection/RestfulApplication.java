package com.mycompany.jerseyhk2injection;

import com.google.inject.Guice;
import org.glassfish.jersey.server.ResourceConfig;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

@ApplicationPath("resources")
public class RestfulApplication extends ResourceConfig {
    @Inject
    public RestfulApplication(ServiceLocator locator) {
        packages("com.mycompany.jerseyhk2injection.resources");
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(locator);
        
        GuiceIntoHK2Bridge guiceBridge = locator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(Guice.createInjector(new GuiceBindingConfiguration() ));
    }
    
}
