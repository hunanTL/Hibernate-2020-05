package com.tanlei.utils;

import com.tanlei.pojo.Department;

import com.tanlei.pojo.Employee;
import org.hibernate.Session;
import org.hibernate.Query;

/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-11 09:02
 */
public class DetaUtils {

    public static Department findDepartment(Session session, String deptNo) {
        String sql = "Select d from " + Department.class.getName() + " d "//
                + " Where d.deptNo = :deptNo";
        Query query =  session.createQuery(sql);
        query.setParameter("deptNo", deptNo);
        return (Department)query.uniqueResult();
    }

    public static Long getMaxEmpId(Session session) {
        String sql = "Select max(e.empId) from " + Employee.class.getName() + " e ";
        Query query =  session.createQuery(sql);
        Number value = (Number) query.uniqueResult();
        if (value == null) {
            return 0L;
        }
        return value.longValue();
    }

    public static Employee findEmployee(Session session, String empNo) {
        String sql = "Select e from " + Employee.class.getName() + " e "//
                + " Where e.empNo = :empNo";
        Query query =  session.createQuery(sql);
        query.setParameter("empNo", empNo);
        return (Employee)query.uniqueResult();
    }

}