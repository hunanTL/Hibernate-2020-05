package com.tanlei.pojo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-09 16:13
 */
public class HibernateUtils {

    public static SessionFactory getSessionFactory(){
        Configuration configuration=new Configuration().configure();
        return configuration.buildSessionFactory();
    }
}
