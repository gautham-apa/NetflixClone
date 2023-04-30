package com.hari.netflix.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public abstract class DAO {
    @PersistenceContext
    private EntityManager entityManager;

    private final Logger log = Logger.getAnonymousLogger();
    private final ThreadLocal sessionThread = new ThreadLocal();

    protected DAO() {}

    public EntityManager getManager() {
        EntityManager session = (EntityManager) sessionThread.get();
        if (session == null) {
            session = entityManager;
            sessionThread.set(entityManager);
        }
        return session;
    }

//    protected void begin() {
//        getManager().getTransaction().begin();
//    }
//
//    protected void commit() {
//        getManager().getTransaction().commit();
//    }
//
//    protected void rollback() {
//        try {
//            getManager().getTransaction().rollback();
//        } catch (RollbackException e) {
//            log.log(Level.WARNING, "Cannot rollback", e);
//        }
//        try {
//            getManager().close();
//        } catch (Exception e) {
//            log.log(Level.WARNING, "Cannot close", e);
//        }
//        sessionThread.set(null);
//    }
//
    public void close() {
        getManager().close();
        sessionThread.set(null);
    }
}


