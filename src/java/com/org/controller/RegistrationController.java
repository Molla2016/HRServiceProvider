package com.org.controller;

import com.org.entity.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.EntityNotFoundException;
import javax.ejb.Stateful;
import javax.persistence.PersistenceContext;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Stateful
public class RegistrationController {

    @PersistenceContext(unitName = "HRServicesProviderPU")
    private EntityManager em;

    private final int payload = 12;

    public RegistrationController() {

    }

    public EntityManager getEntityManager() {
        return em;
    }

    public List<Registration> findAll() {
        TypedQuery<Registration> tq = em.createNamedQuery("Registration.findAll", Registration.class);
        List<Registration> registration = tq.getResultList();
        if (!registration.isEmpty()) {
            return registration;
        } else {
            throw new EntityNotFoundException("Entity set is empty");
        }
    }

    public Registration find(int id) {

        Registration registration = em.find(Registration.class, id);
        if (registration != null) {
            return registration;
        }
        return null;//throw new EntityNotFoundException("Cannot retrieve null entity "+registration);
    }

    public boolean create(Registration registration) throws SQLException {
        if (registration != null) {
            em.persist(registration);
            return true;
        }
        return false;

    }

    public boolean remove(int id) {
        Registration registration = em.find(Registration.class, id);
        if (registration != null) {
            em.remove(registration);
            return true;
        }
        return false;
    }

    public boolean update(Registration register) throws SQLException {

        if (register != null) {
            em.merge(register);
            return true;
        }
        return false;

    }

    //An algorithm that hashed a password based on BCrypt
    public String encryptPassword(String password) {
        String salt = BCrypt.gensalt(payload);
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
   
   
    // methods that encrpt the password based on SHA-1
    public String getEncrptedPassword(String password, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));

            }
            return generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getCause());
            return "";
        }

    }

    // code that gene brated the salt used in password encryption
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;

    }

    /**
     * protected boolean _resetPassword(String empID,String pass)throws
     * NoSuchAlgorithmException{ byte[] salt = getSalt();
     * //System.out.println(pass); String query = "UPDATE Registration SET
     * password =? WHERE EmployeeID = ?"; try {
     * Class.forName("com.mysql.jdbc.Driver"); Connection con =
     * DriverManager.getConnection(constring, username, password);
     * PreparedStatement ps = con.prepareStatement(query); ps.setString(1,
     * getEncrptedPassword(pass, salt)); ps.setString(2, empID);
     * ps.executeUpdate(); return true; } catch (Exception e) { return false;
     * }**
     */
}
