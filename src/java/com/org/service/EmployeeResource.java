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
import com.org.controller.*;
import java.text.ParseException;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import com.org.entity.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Path("employee")
public class EmployeeResource {

    @Context
    private UriInfo context;

    @EJB
    private EmployeeController ec;

    public EmployeeResource() {

    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@QueryParam("id") String employeeID, @QueryParam("fullname") String employeeFullName,
            @QueryParam("dob") String dob, @QueryParam("age") String employeeAge, @QueryParam("gender") String employeeGender,
            @QueryParam("address") String employeeAddress, @QueryParam("department") String depart,
            @QueryParam("position") String position, @Context HttpServletResponse httpServetResponse) {
       
        
        if (getPosition(position) == -1) {
            throw new RuntimeException("Wong position entered");
        }
        if (getDepartment(depart) == -1) {
            throw new RuntimeException("Wrong Department entered");
        }
        try {
            Employee employee = new Employee();
            employee.setEmployeeAddress(employeeAddress);
            employee.setEmployeeAge(Integer.parseInt(employeeAge));
            employee.setEmployeeDOB(getDate(dob));
            employee.setEmployeeDepartment(new Department(getDepartment(depart)));
            employee.setEmployeeFullName(employeeFullName);
            employee.setEmployeeID(employeeID);
            employee.setEmployeeLevel(new LevelPosition(getPosition(position)));
            employee.setEmployeeGender(employeeGender);

            boolean result = ec.create(employee);

            if (result == true) {
                return Response.ok().entity("Entity successfully CREATED").build();
            }
            return Response.ok("Entity NOT successfully CREATED").entity(result).build();

        } catch (ParseException ex) {
            return Response.notModified().entity("Unable to complete operation").build();
        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }

    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("id") String employeeID, @QueryParam("fullname") String employeeFullName,
            @QueryParam("dob") String dob, @QueryParam("age") String employeeAge, @QueryParam("gender") String employeeGender,
            @QueryParam("address") String employeeAddress, @QueryParam("department") String depart,
            @QueryParam("position") String position, @Context HttpServletResponse httpServetResponse) {
        
        if (getPosition(position) == -1) {
            throw new RuntimeException("Wong position entered");
        }

        if (getDepartment(depart) == -1) {
            throw new RuntimeException("Wrong Department entered");
        }

        try {
            Employee employee = ec.find(employeeID);
            employee.setEmployeeID(employeeID);
            employee.setEmployeeAddress(employeeAddress);
            employee.setEmployeeAge(Integer.parseInt(employeeAge));
            employee.setEmployeeDOB(getDate(dob));
            employee.setEmployeeDepartment(new Department(getDepartment(depart)));
            employee.setEmployeeFullName(employeeFullName);
            employee.setEmployeeLevel(new LevelPosition(getPosition(position)));
            employee.setEmployeeGender(employeeGender);

            boolean result = ec.update(employee);

            if (result) {
                return Response.ok().entity("Entity successfully UPDATED ").build();
            }
            return Response.notModified().entity("Entity NOT successfully UPDATED").build();

        } catch (ParseException ex) {
            return Response.notModified().entity("Unable to complete operation").build();
        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() throws JSONException {
        List<Employee> list = ec.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("Empty employee list");
        }
        JSONArray array = new JSONArray();
        for (Employee employee : list) {
            JSONObject json = new JSONObject();
            json.put("EmployeeID", employee.getEmployeeID());
            json.put("EmployeeFullName", employee.getEmployeeFullName());
            json.put("EmployeeAge", employee.getEmployeeAge());
            json.put("EmployeeDOB", employee.getEmployeeDOB());
            json.put("EmployeeAddress", employee.getEmployeeAddress());
            json.put("EmployeePosition", employee.getEmployeeLevel().getLevelType());
            json.put("EmployeeGender", employee.getEmployeeGender());
            json.put("EmployeeDepartment", employee.getEmployeeDepartment().getDepartmentName());
            array.put(json);
            
        }
        return array.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String find(@PathParam("id") String id) throws JSONException {
        Employee employee = ec.find(id);
        if (employee == null) {
            throw new RuntimeException("Empty Entity");
        }
        JSONObject json = new JSONObject();
        json.put("EmployeeID", employee.getEmployeeID());
        json.put("EmployeeFullName", employee.getEmployeeFullName());
        json.put("EmployeeAge", employee.getEmployeeAge());
        json.put("EmployeeDOB", employee.getEmployeeDOB());
        json.put("EmployeeAddress", employee.getEmployeeAddress());
        json.put("EmployeePosition", employee.getEmployeeLevel().getLevelType());
        json.put("EmployeeGender", employee.getEmployeeGender());
        json.put("EmployeeDepartment", employee.getEmployeeDepartment().getDepartmentName());
        return json.toString();
    }

    @DELETE
    @Path("remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") String id) {
        if (ec.getEntityManager().find(Employee.class, id) == null) {
            throw new RuntimeException("Entity is empty");//you can use a stuctured error message to achieve this
        }
        boolean result = ec.remove(id);
        if (result) {
            return Response.ok().entity("Entity successfully REMOVE").build();
        }
        return Response.ok().entity("Problem REMOVING entity").build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        javax.persistence.criteria.CriteriaQuery cq = ec.getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Employee> rt = cq.from(Employee.class);
        cq.select(ec.getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = ec.getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public java.sql.Date getDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateObject = dateFormat.parse(date);
        return new java.sql.Date(dateObject.getTime());
    }

    public int getPosition(String pos) {
        switch (pos.trim()) {
            case "Entry Level":
                return 1;
            case "Medium Level":
                return 2;
            case "Top Level":
                return 3;
            default:
                return -1;
        }

    }

    public int getDepartment(String depart) {
        switch (depart.trim()) {
            case "Procurement Department":
                return 1;
            case "ICT Department":
                return 2;
            case "Human Resource Department":
                return 3;
            case "Health Care Department":
                return 4;
            case "Finace and Accounting Department":
                return 5;
            default:
                return -1;

        }
    }

}
