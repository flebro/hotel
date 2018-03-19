package com.iia.webservices.groupa.hotel;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.*;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
    public ApplicationConfig() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/hotel/rest");
        beanConfig.setResourcePackage("com.iia.webservices.groupa.hotel");
        beanConfig.setScan(true);
    }
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(com.iia.webservices.groupa.hotel.HotelService.class);
        resources.add(com.iia.webservices.groupa.hotel.LoginService.class);
        resources.add(com.iia.webservices.groupa.hotel.ReservationService.class);
        

        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }

}
