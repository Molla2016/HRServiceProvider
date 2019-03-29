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
import com.org.controller.PayrollController;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import com.org.entity.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("payroll")
public class PayrollResource {

    @Context
    private UriInfo context;

    @EJB
    private PayrollController pc;

    public PayrollResource() {

    }

    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@QueryParam("fullname") String employeeFullName,
            @QueryParam("employeeid") String employeeID, @QueryParam("paystatus") String payStatus,
            @QueryParam("datepaid") String datePaid, @QueryParam("position") String position,
            @QueryParam("netamount") String netAmount, @Context HttpServletResponse httpServetResponse) {

        Map<String, BigDecimal> deductions = pc.getAmountDeducted();
        if (getPosition(position) == -1) {
            throw new RuntimeException("Wong position entered");
        }

        try {
            Payroll payroll = new Payroll();
            payroll.setAmountDeducted((deductions.containsKey(employeeID) ? deductions.get(employeeID) : BigDecimal.ZERO));
            payroll.setAmountPaid(getAmountPaid(position));
            payroll.setDatePaid(getDate(datePaid));
            payroll.setEmployeeID(new Employee(employeeID));
            payroll.setEmployeeName(employeeFullName);
            payroll.setGradelevel(new LevelPosition(getPosition(position)));
            payroll.setNetAmount((deductions.containsKey(employeeID)
                    ? getAmountPaid(position).subtract(deductions.get(employeeID)) : getAmountPaid(position)));
            payroll.setPayStatus(payStatus);

            boolean result = pc.create(payroll);

            if (result == true) {
                return Response.ok().entity("Entity successfully CREATED").build();
            }
            return Response.ok("Entity NOT successfully CREATED").entity(result).build();

        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        } catch (ParseException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }

    }

    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@QueryParam("fullname") String employeeFullName,
            @QueryParam("employeeid") String employeeID, @QueryParam("paystatus") String payStatus,
            @QueryParam("datepaid") String datePaid, @QueryParam("position") String position,
            @QueryParam("netamount") String netAmount, @Context HttpServletResponse httpServetResponse) {

        Map<String, BigDecimal> deductions = pc.getAmountDeducted();
        if (getPosition(position) == -1) {
            throw new RuntimeException("Wong position entered");
        }

        List<Payroll> collections = pc.findAll();
        if (collections.isEmpty()) {
            return Response.ok().entity("Payroll list is empty").build();
        }
        Map<String, Integer> map = new HashMap<>();
        for (Payroll payroll : collections) {
            map.put(payroll.getEmployeeID().getEmployeeID(), payroll.getPayrollID());
        }

        try {
            int id = 0;
            if (map.containsKey(employeeID) && map.get(employeeID) != 0) {
                id = map.get(employeeID);
            }
            Payroll payroll = pc.find(id);
            payroll.setAmountDeducted((deductions.containsKey(employeeID) ? deductions.get(employeeID) : BigDecimal.ZERO));
            payroll.setAmountPaid(getAmountPaid(position));
            payroll.setDatePaid(getDate(datePaid));
            payroll.setEmployeeID(new Employee(employeeID));
            payroll.setEmployeeName(employeeFullName);
            payroll.setGradelevel(new LevelPosition(getPosition(position)));
            payroll.setNetAmount((deductions.containsKey(employeeID)
                    ? getAmountPaid(position).subtract(deductions.get(employeeID)) : getAmountPaid(position)));
            payroll.setPayStatus(payStatus);

            boolean result = pc.update(payroll);

            if (result) {
                return Response.ok().entity("Entity successfully UPDATED ").build();
            }
            return Response.notModified().entity("Entity NOT successfully UPDATED").build();

        } catch (SQLException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        } catch (ParseException ex) {
            return Response.notModified().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String findAll() throws JSONException {
        List<Payroll> list = pc.findAll();
        if (list.isEmpty()) {
            throw new RuntimeException("Empty employee list");
        }
        JSONArray array = new JSONArray();
        for (Payroll payroll : list) {
            JSONObject json = new JSONObject();
            json.put("PayrollID", payroll.getPayrollID());
            json.put("EmployeeFullName", payroll.getEmployeeName());
            json.put("EmployeeID", payroll.getEmployeeID().getEmployeeID());
            json.put("AmountDeducted", payroll.getAmountDeducted());
            json.put("PayStatus", payroll.getPayStatus());
            json.put("DatePaid", payroll.getDatePaid());
            json.put("GradeLevel", payroll.getGradelevel().getLevelType());
            json.put("AmountPaid", payroll.getAmountPaid());
            json.put("NetAmount", payroll.getNetAmount());
            array.put(json);

        }
        return array.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public String find(@PathParam("id") String employeeID) throws JSONException {
        List<Payroll> collections = pc.findAll();
        if (collections.isEmpty()) {
            throw new RuntimeException("Payroll list is empty");
        }
        Map<String, Integer> map = new HashMap<>();
        for (Payroll payroll : collections) {
            map.put(payroll.getEmployeeID().getEmployeeID(), payroll.getPayrollID());
        }

        int ids = 0;
        if (map.containsKey(employeeID) && map.get(employeeID) != 0) {
            ids = map.get(employeeID);
        }
        Payroll payroll = pc.find(ids);
        JSONObject json = new JSONObject();
        json.put("PayrollID", payroll.getPayrollID());
        json.put("EmployeeFullName", payroll.getEmployeeName());
        json.put("EmployeeID", payroll.getEmployeeID().getEmployeeID());
        json.put("AmountDeducted", payroll.getAmountDeducted());
        json.put("PayStatus", payroll.getPayStatus());
        json.put("DatePaid", payroll.getDatePaid());
        json.put("GradeLevel", payroll.getGradelevel().getLevelType());
        json.put("AmountPaid", payroll.getAmountPaid());
        json.put("NetAmount", payroll.getNetAmount());
        return json.toString();
    }

    @DELETE
    @Path("remove/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") String employeeID) {
        List<Payroll> collections = pc.findAll();
        if (collections.isEmpty()) {
            throw new RuntimeException("Payroll list is empty");
        }
        Map<String, Integer> map = new HashMap<>();
        for (Payroll payroll : collections) {
            map.put(payroll.getEmployeeID().getEmployeeID(), payroll.getPayrollID());
        }

        int ids = 0;
        if (map.containsKey(employeeID) && map.get(employeeID) != 0) {
            ids = map.get(employeeID);
        }

        if (pc.getEntityManager().find(Payroll.class, ids) == null) {
            throw new RuntimeException("Entity is empty");//you can use a stuctured error message to achieve this
        }
        boolean result = pc.remove(ids);
        if (result) {
            return Response.ok().entity("Entity successfully REMOVE").build();
        }
        return Response.ok().entity("Problem REMOVING entity").build();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public int countREST() {
        javax.persistence.criteria.CriteriaQuery cq = pc.getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Employee> rt = cq.from(Payroll.class);
        cq.select(pc.getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = pc.getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
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

    public java.sql.Date getDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date dateObject = dateFormat.parse(date);
        return new java.sql.Date(dateObject.getTime());
    }

    public BigDecimal getAmountPaid(String pos) {
        switch (pos.trim()) {
            case "Entry Level":
                return BigDecimal.valueOf(200000.00);
            case "Medium Level":
                return BigDecimal.valueOf(300000.00);
            case "Top Level":
                return BigDecimal.valueOf(500000.00);
            default:
                return BigDecimal.ZERO;
        }

    }

}
