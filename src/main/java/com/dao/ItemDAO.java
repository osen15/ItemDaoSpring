package com.dao;

import com.entity.Item;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class ItemDAO {
    private SessionFactory sessionFactory;

    public void save(Item item) {
        Transaction tr;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(item);
            tr.commit();
        } catch (HibernateException e) {
            System.out.println("save is failed");
            e.printStackTrace();
        }
        System.out.println("Item with id: " + item.getId() + " saved");
    }

    public Item findById(Long id) {
        Item item = null;
        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from Item where id =:id");
            query.setParameter("id", id);
            item = (Item) query.getSingleResult();

        } catch (HibernateException e) {
            System.out.println("find by id is failed");
            e.printStackTrace();
        }
        return item;
    }


    public void update(Item item) throws Exception {
        if (findById(item.getId()) == null)
            throw new Exception("Item with id: " + item.getId() + " not found");
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.update(item);
            tr.commit();
        } catch (HibernateException e) {
            System.out.println("update is failed");
            e.printStackTrace();
            if (tr != null)
                tr.rollback();

        }
        System.out.println("Item with id: " + item.getId() + " updated");
    }

    public void delete(Long id) throws Exception {
        Item item = findById(id);
        if (item == null)
            throw new Exception("Item with id: " + id + " not found");
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.delete(item);
            tr.commit();
        } catch (HibernateException e) {
            System.out.println("delete is failed");
            e.printStackTrace();
            if (tr != null)
                tr.rollback();

        }
        System.out.println("Item with id: " + item.getId() + " deleted");
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;

    }

}
