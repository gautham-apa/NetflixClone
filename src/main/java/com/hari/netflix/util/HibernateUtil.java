//package com.hari.netflix.util;
//
//import java.util.Properties;
//
//import com.hari.netflix.pojo.Comment;
//import com.hari.netflix.pojo.VideoData;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;
//import org.springframework.stereotype.Component;
//
//import com.hari.netflix.pojo.User;
//
//@Component
//public class HibernateUtil {
//
//    private static EntityManagerFactory entityManagerFactory;
//
//    public static EntityManagerFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                // Hibernate settings equivalent to hibernate.cfg.xml's properties
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/netflix?createDatabaseIfNotExist=true");
//                settings.put(Environment.USER, "root");
//                settings.put(Environment.PASS, "test@123");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//                settings.put(Environment.HBM2DDL_AUTO, "create");
//                configuration.setProperties(settings);
//                configuration.addAnnotatedClass(User.class);
//                configuration.addAnnotatedClass(VideoData.class);
//                configuration.addAnnotatedClass(Comment.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
//}
