//package com.test;
////
//// Source code recreated from a .class file by IntelliJ IDEA
//// (powered by FernFlower decompiler)
////
//
//
//
////import javax.transaction.SystemException;
////import javax.transaction.TransactionManager;
//
//import org.apache.commons.logging.LogFactory;
//import org.hibernate.FlushMode;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.context.spi.CurrentSessionContext;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.engine.transaction.jta.platform.spi.JtaPlatform;
//import org.springframework.transaction.support.TransactionSynchronizationManager;
//
//public class SpringSessionContext implements CurrentSessionContext
//{
//  private final SessionFactoryImplementor sessionFactory;
//  private TransactionManager transactionManager;
//  private CurrentSessionContext jtaSessionContext;
//
//  public SpringSessionContext(SessionFactoryImplementor sessionFactory)
//  {
//    this.sessionFactory = sessionFactory;
//
//    try
//    {
//      JtaPlatform jtaPlatform = (JtaPlatform) sessionFactory.getServiceRegistry().getService(JtaPlatform.class);
//      this.transactionManager = jtaPlatform.retrieveTransactionManager();
//      if (this.transactionManager != null)
//      {
//        this.jtaSessionContext = new SpringJtaSessionContext(sessionFactory);
//      }
//    }
//    catch (Exception var3)
//    {
//      LogFactory.getLog(SpringSessionContext.class)
//        .warn("Could not introspect Hibernate JtaPlatform for SpringJtaSessionContext", var3);
//    }
//
//  }
//
//  public Session currentSession() throws HibernateException
//  {
//    Object value = TransactionSynchronizationManager.getResource(this.sessionFactory);
//    if (value instanceof Session)
//    {
//      return (Session) value;
//    }
//    else if (value instanceof SessionHolder)
//    {
//      SessionHolder sessionHolder = (SessionHolder) value;
//      Session session = sessionHolder.getSession();
//      if (!sessionHolder.isSynchronizedWithTransaction() && TransactionSynchronizationManager.isSynchronizationActive())
//      {
//        TransactionSynchronizationManager.registerSynchronization(new SpringSessionSynchronization(sessionHolder,
//                                                                                                   this.sessionFactory,
//                                                                                                   false));
//        sessionHolder.setSynchronizedWithTransaction(true);
//        FlushMode flushMode = SessionFactoryUtils.getFlushMode(session);
//        if (flushMode.equals(FlushMode.MANUAL) && !TransactionSynchronizationManager.isCurrentTransactionReadOnly())
//        {
//          session.setFlushMode(FlushMode.AUTO);
//          sessionHolder.setPreviousFlushMode(flushMode);
//        }
//      }
//
//      return session;
//    }
//    else
//    {
//      Session session;
//      if (this.transactionManager != null)
//      {
//        try
//        {
//          if (this.transactionManager.getStatus() == 0)
//          {
//            session = this.jtaSessionContext.currentSession();
//            if (TransactionSynchronizationManager.isSynchronizationActive())
//            {
//              TransactionSynchronizationManager.registerSynchronization(new SpringFlushSynchronization(session));
//            }
//
//            return session;
//          }
//        }
//        catch (SystemException var5)
//        {
//          throw new HibernateException("JTA TransactionManager found but status check failed", var5);
//        }
//      }
//
//      if (TransactionSynchronizationManager.isSynchronizationActive())
//      {
//        session = this.sessionFactory.openSession();
//        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly())
//        {
//          session.setFlushMode(FlushMode.MANUAL);
//        }
//
//        SessionHolder sessionHolder = new SessionHolder(session);
//        TransactionSynchronizationManager.registerSynchronization(new SpringSessionSynchronization(sessionHolder,
//                                                                                                   this.sessionFactory,
//                                                                                                   true));
//        TransactionSynchronizationManager.bindResource(this.sessionFactory, sessionHolder);
//        sessionHolder.setSynchronizedWithTransaction(true);
//        return session;
//      }
//      else
//      {
//        throw new HibernateException("Could not obtain transaction-synchronized Session for current thread");
//      }
//    }
//  }
//}
//
