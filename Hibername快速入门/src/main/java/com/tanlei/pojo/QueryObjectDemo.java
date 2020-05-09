package com.tanlei.pojo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import java.util.List;

/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-09 15:35
 */
public class QueryObjectDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.openSession();

        try {

            // All the action with DB via Hibernate
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.
            // Equivalent to the SQL statement:
            // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
            String sql = "Select e from " + Employee.class.getName() + " e "
                    + " order by e.empName, e.empNo ";


            // Create Query object.
            Query query = session.createQuery(sql);


            // Execute query.
            List<Employee> employees = query.list();

            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : "
                        + emp.getEmpName());
            }

            // Commit data.
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }

}