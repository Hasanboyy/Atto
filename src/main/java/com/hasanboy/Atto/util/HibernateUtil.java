package com.hasanboy.Atto.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().
                    configure("hibernate.config.xml").build();
            Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
            sessionFactory = meta.getSessionFactoryBuilder().build();
        }catch (Throwable throwable){
            System.err.println("Enitial SessionFactory creation failed" + throwable);
            throw new ExceptionInInitializerError(throwable);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
