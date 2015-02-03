package org.abyeti.productapp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Sucharitha Reddy on 2/2/2015.
 */
public class Functionality {
    void addproduct(Product b1)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Product b = new Product();
        b.setid(b1.getid());
        b.setname(b1.getname());
        b.setcategory(b1.getcategory());
        b.setdescription(b1.getdescription());
        b.setprice(b1.getprice());
        b.setseller(b1.getseller());
        Transaction tx = session.beginTransaction();
        session.save(b);
        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        factory.close();
    }

    JSONArray returnlist()
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        JSONArray j=new JSONArray();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List products = session.createQuery("FROM Product").list();
            int i;i=0;
            for (Iterator iterator =products.iterator(); iterator.hasNext();){
                Product b = (Product) iterator.next();
                System.out.print("ID: " +b.getid());
                System.out.print("Name: " +b.getname());
                System.out.print("category: " +b.getcategory());
                System.out.print("Description: " +b.getdescription());
                System.out.print("price: " +b.getprice());
                System.out.print("Seller: " +b.getseller());
                JSONObject job=new JSONObject();
                job.put("id",b.getid());
                job.put("name",b.getname());
                job.put("category",b.getcategory());
                job.put("description",b.getdescription());
                job.put("price",b.getprice());
                job.put("seller",b.getseller());
                j.put(i,job);
                i++;
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return j;
    }

    JSONObject deleterecord(String id) {
        JSONObject job=new JSONObject();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Product b = (Product)session.get(Product.class,id);
            job.put("id",b.getid());
            job.put("name",b.getname());
            job.put("category",b.getcategory());
            job.put("description",b.getdescription());
            job.put("price",b.getprice());
            job.put("seller",b.getseller());

            session.delete(b);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return job;
    }
}
