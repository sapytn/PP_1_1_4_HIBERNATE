package jm.task.core.jdbc.util;


import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД
  private static SessionFactory sessionFactory;
  public static SessionFactory getSessionFactory(){
    if (sessionFactory == null) {
      try {
        Configuration configuration = new Configuration();

        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/jdbctest");
        settings.put(Environment.USER, "bestuser");
        settings.put(Environment.PASS, "bestuser");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

        settings.put(Environment.SHOW_SQL, "true");

        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


        configuration.setProperties(settings);

        configuration.addAnnotatedClass(User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        System.out.println("connection OK");

      } catch (HibernateException e) {
        e.printStackTrace();
      }
    }
    return sessionFactory;
  }
}
