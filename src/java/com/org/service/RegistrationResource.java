package com.org.service;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ejb.EJB;
import com.org.controller.RegistrationController;
import javax.ws.rs.core.Response;
import com.org.entity.Employee;
import com.org.entity.Registration;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("registration")
public class RegistrationResource {

    @Context
    private UriInfo context;

    @EJB
    private RegistrationController rc;

    public RegistrationResource() {

    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("id") String employeeID, @FormParam("username") String email,
            @FormParam("password") String password, @Context HttpServletResponse httpServetResponse) {

        try {
            //byte[] salt = RegistrationController.getSalt();
            Registration registration = new Registration();
            registration.setEmail(email);
            registration.setPassword(rc.encryptPassword(password));
            registration.setEmployeeID(new Employee(employeeID));

            boolean result = rc.create(registration);

            if (result == true) {
                return Response.ok().entity("Entity successfully CREATED").build();
            }
            return Response.ok().entity("Entity NOT successfully CREATED").build();

        } catch (SQLException ex) {
            return Response.ok().entity(ex.getCause()).build();
        }

    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("id") String employeeID, @FormParam("username") String email,
            @FormParam("password") String password, @Context HttpServletResponse httpServetResponse) {

        try {
            // method used to retrieve registration id
            List<Registration> users = rc.findAll();
            if (users.isEmpty()) {
                throw new EntityNotFoundException("The Entity you are trying to retrieve is EMPTY");
            }
            Map<String, Integer> collection = new HashMap<>();
            for (Registration register : users) {
                collection.put(register.getEmail(), register.getRegID());
            }
            int id = 0;
            if (collection.containsKey(email) && collection.get(email) != 0) {
                id = collection.get(email);

            } else {
                throw new SQLException("The value you trying to retrieve is not available");
            }

            Registration registration = rc.find(id);
            registration.setEmail(email);
            registration.setPassword(rc.encryptPassword(password));
            registration.setEmployeeID(new Employee(employeeID));
            boolean result = rc.update(registration);

            if (result) {
                return Response.ok().entity("Entity successfully UPDATED ").build();
            }
            return Response.ok().entity("Entity NOT successfully UPDATED").build();

        } catch (SQLException ex) {
            return Response.ok().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAllEntity() throws JSONException {
        List<Registration> list = rc.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("Entity list is empty");
        }
        JSONArray array = new JSONArray();
        for (Registration registration : list) {
            JSONObject json = new JSONObject();
            json.put("EmployeeID", registration.getEmployeeID().getEmployeeID());
            json.put("Email", registration.getEmail());
            json.put("Password", registration.getPassword());
            array.put(json);

        }
        return array.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String findEntity(@PathParam("id") String email) throws JSONException {
        // method used to retrieve registration id
        List<Registration> users = rc.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("The Entity you are trying to retrieve is EMPTY");
        }
        Map<String, Integer> collection = new HashMap<>();
        for (Registration register : users) {
            collection.put(register.getEmail(), register.getRegID());
        }
        int id = 0;
        if (collection.containsKey(email)) {
            id = collection.get(email);
        }

        Registration registration = rc.find(id);
        if (registration == null) {
            throw new RuntimeException("Empty Entity");
        }
        JSONObject json = new JSONObject();
        json.put("EmployeeID", registration.getEmployeeID().getEmployeeID());
        json.put("Email", registration.getEmail());
        json.put("Password", registration.getPassword());

        return json.toString();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") String email) {

        // method used to retrieve registration id
        List<Registration> users = rc.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("The Entity you are trying to retrieve is EMPTY");
        }
        Map<String, Integer> collection = new HashMap<>();
        for (Registration register : users) {
            collection.put(register.getEmail(), register.getRegID());
        }
        int id = 0;
        if (collection.containsKey(email)) {
            id = collection.get(email);
        }

        boolean result = rc.remove(id);
        if (result) {
            return Response.ok().entity("Entity successfully REMOVE").build();
        }
        return Response.ok().entity("Problem REMOVING entity").build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        javax.persistence.criteria.CriteriaQuery cq = rc.getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Registration> rt = cq.from(Registration.class);
        cq.select(rc.getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = rc.getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
