
package com.org.service;

import java.util.Set;
import javax.ws.rs.core.Application;


@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.org.service.DepartmentResource.class);
        resources.add(com.org.service.EmployeeResource.class);
        resources.add(com.org.service.PayrollResource.class);
        resources.add(com.org.service.RegistrationResource.class);
    }
    
}
