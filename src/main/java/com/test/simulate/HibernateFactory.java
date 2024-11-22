package com.test.simulate;

import org.apache.sshd.common.session.SessionContext;

public class HibernateFactory
{
  public Session openSession()
  {
    return new Session();
  }
  static class Session
  {
    void beginTransaction()
    {
      ;
    }
  }
}


class SpringContext
{
  SpringContext(HibernateFactory factory){

  }

  class FactoryWrapper extends HibernateFactory
  {
    HibernateFactory getHibernateFactory()
    {
      return new HibernateFactory();
    }
  }

}
