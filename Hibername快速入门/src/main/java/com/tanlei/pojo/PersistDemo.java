package com.tanlei.pojo;

import com.tanlei.utils.DetaUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-11 14:18
 */
public class PersistDemo {

    public static void main(String[] args) {

        SessionFactory factory=HibernateUtils.getSessionFactory();

        Session session=factory.openSession();
        Department department=null;
        Employee emp=null;

        try {
            session.getTransaction().begin();

            Long maxEmpId = DetaUtils.getMaxEmpId(session);
            Long empId = maxEmpId + 1;

            // Get Persistent object.
            department = DetaUtils.findDepartment(session, "D09");

            // Create transient object
            emp = new Employee();
            emp.setEmpId(empId);
            emp.setEmpNo("E" + empId);
            emp.setEmpName("Name " + empId);
            emp.setJob("Coder");
            emp.setSalary(1000f);
            emp.setManager(null);
            emp.setHideDate(new Date());
            emp.setDepartment(department);

            // Using persist(..)
            // Now 'emp' is managed by Hibernate.
            // it has Persistent status.
            // No action at this time with DB.
            session.persist(emp);


            // At this step the data is pushed to the DB.
            // Execute Insert statement.
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        // After the session is closed (commit, rollback, close)
        // Objects 'emp', 'dept' became the Detached objects.
        // It is no longer in the control of the session.
        System.out.println("Emp No: " + emp.getEmpNo());
    }
}
