package com.tanlei.pojo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import java.util.List;

/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-09 16:25
 */
public class QueryObjectDemo2 {
    public static void main(String[] args) {
        SessionFactory sessionFactory=HibernateUtils.getSessionFactory();
        Session session=sessionFactory.openSession();

        try{
          session.getTransaction().begin();

            String sql = "Select e from " + Employee.class.getName() + " e " + " where e.department.deptNo=:deptNo ";

            // Create query object.
            Query query =  session.createQuery(sql);

            query.setParameter("deptNo", "D10");

            // Execute query.
            List<Employee> employees = query.list();

            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : " + emp.getEmpName());
            }

            // Commit data
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
