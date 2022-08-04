package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UserDaoHibernateImpl implements UserDao {

  private final SessionFactory sessionFactory = Util.getSessionFactory();
  private Session session;
  private Transaction transaction;

  public UserDaoHibernateImpl() {

  }


  @Override
  public void createUsersTable() {
    try {
      session = sessionFactory.getCurrentSession();
      transaction = session.beginTransaction();
      session.createSQLQuery("create TABLE IF NOT EXISTS user "
          + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, "
          + "first_name TINYTEXT, "
          + "last_name TINYTEXT, "
          + "age TINYINT UNSIGNED)").executeUpdate();
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
    }

  }

  @Override
  public void dropUsersTable() {
    try {
      session = sessionFactory.getCurrentSession();
      transaction = session.beginTransaction();
      session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
    }
  }

  @Override
  public void saveUser(String name, String lastName, byte age) {
    try {
      session = sessionFactory.getCurrentSession();
      transaction = session.beginTransaction();
      User user = new User(name, lastName, age);
      session.save(user);
      transaction.commit();
      System.out.println("User с именем – " + name + " добавлен в базу данных");
    } catch (HibernateException e) {
      transaction.rollback();
    }
  }

  @Override
  public void removeUserById(long id) {
    try {
      session = sessionFactory.getCurrentSession();
      transaction = session.beginTransaction();
      User user = session.get(User.class, id);
      session.delete(user);
      transaction.commit();
    } catch (HibernateException e) {
      transaction.rollback();
    }
  }

  @Override
  public List<User> getAllUsers() {
    try {
      session = sessionFactory.getCurrentSession();
      transaction = session.beginTransaction();
      List<User> userList = session.createQuery("select a from User a", User.class)
          .getResultList();
      transaction.commit();
      return userList;
    } catch (HibernateException e) {
      transaction.rollback();
      return null;
    }
  }

  @Override
  public void cleanUsersTable() {
    try (Session session = Util.getSessionFactory().getCurrentSession()) {
      session.beginTransaction();
      session.createSQLQuery("delete from user").executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
