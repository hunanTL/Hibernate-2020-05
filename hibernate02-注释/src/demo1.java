import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * @author <a href="mailto:lei.tan@vtradex.net">谭磊/a>
 * @since 2020-05-08 16:37
 */
public class demo1 {

    @Test
    public  void demo1(){
        //1.加载Hibernate的核心配置文件
        Configuration configuration=new Configuration().configure();
        //如果在Hibernate的核心配置文件没有设置加载哪个映射文件，则可手动加载映射文件
        //configuration.addResource("com/tanlei/pojo/hbm/Customer.hbm.xml");

        //2.创建SessionFactory对象，类似于JDBC中的连接池
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //3. 通过SessionFactory获取到Session对象，类似于JDBC中的Connection
        Session session = sessionFactory.openSession();

        //4. 手动开启事务，（最好是手动开启事务）
        Transaction transaction = session.beginTransaction();

        //5. 编写代码
        Employee e = new Employee();
        e.setFirstName("tan");
        e.setLastName("lei");

        session.save(e);//保存一个用户

        //6.事务提交
        transaction.commit();

        //7.释放资源
        session.close();
        sessionFactory.close();


    }
}
