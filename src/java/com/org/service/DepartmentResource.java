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
import com.org.controller.DepartmentController;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import com.org.entity.*;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Path("department")
public class DepartmentResource {

    @Context
    private UriInfo context;

    @EJB
    private DepartmentController dc;

    
    public DepartmentResource() {

    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@FormParam("id") String departmentID, @FormParam("name") String departmentName,
            @FormParam("description") String departmentDescription, @FormParam("headname") String departmentHeadName,
            @FormParam("address") String departmentAddress, @Context HttpServletResponse httpServetResponse) {
        try {

            Department department = new Department();
            department.setDepartmentAddress(departmentAddress);
            department.setDepartmentDescription(departmentDescription);
            department.setDepartmentHeadName(departmentHeadName);
            department.setDepartmentID(Integer.parseInt(departmentID));
            department.setDepartmentName(departmentName);

            boolean result = dc.create(department);

            if (result == true) {
                return Response.ok().entity("Entity successfully CREATED").build();
            }
            return Response.ok("Entity NOT successfully CREATED").entity(result).build();

        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }

    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@FormParam("id") String departmentID, @FormParam("name") String departmentName,
            @FormParam("description") String departmentDescription, @FormParam("headname") String departmentHeadName,
            @FormParam("address") String departmentAddress, @Context HttpServletResponse httpServetResponse) {

        try {
            Department department = dc.find(Integer.parseInt(departmentID));
            department.setDepartmentAddress(departmentAddress);
            department.setDepartmentDescription(departmentDescription);
            department.setDepartmentHeadName(departmentHeadName);
           // department.setDepartmentID(Integer.parseInt(departmentID));
            department.setDepartmentName(departmentName);

            boolean result = dc.update(department);

            if (result) {
                return Response.ok().entity("Entity successfully UPDATED ").build();
            }
            return Response.notModified().entity("Entity NOT successfully UPDATED").build();

        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() throws JSONException {
        List<Department> list = dc.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("Empty employee list");
        }
        JSONArray array = new JSONArray();
        for (Department department : list) {
            JSONObject json = new JSONObject();
            json.put("DepartmentID", department.getDepartmentID());
            json.put("DepartmentName", department.getDepartmentName());
            json.put("DepartmentDescription", department.getDepartmentDescription());
            json.put("DepartmentHeadName", department.getDepartmentHeadName());
            json.put("DepartmentAddress", department.getDepartmentAddress());
            array.put(json);

        }
        return array.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String find(@PathParam("id") int id) throws JSONException {
        Department department = dc.find(id);
        if (department == null) {
            throw new RuntimeException("Empty Entity");
        }
        JSONObject json = new JSONObject();
        json.put("DepartmentID", department.getDepartmentID());
        json.put("DepartmentName", department.getDepartmentName());
        json.put("DepartmentDescription", department.getDepartmentDescription());
        json.put("DepartmentHeadName", department.getDepartmentHeadName());
        json.put("DepartmentAddress", department.getDepartmentAddress());
        return json.toString();
    }

    @DELETE
    @Path("remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") int id) {
        if (dc.getEntityManager().find(Department.class, id) == null) {
            throw new RuntimeException("Entity is empty");//you can use a stuctured error message to achieve this
        }
        boolean result = dc.remove(id);
        if (result) {
            return Response.ok().entity("Entity successfully REMOVE").build();
        }
        return Response.ok().entity("Problem REMOVING entity").build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        javax.persistence.criteria.CriteriaQuery cq = dc.getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Department> rt = cq.from(Department.class);
        cq.select(dc.getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = dc.getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

   
}
