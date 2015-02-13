package org.abyeti.productapp;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.List;

public class Functions{
    //method to create a seller entry in the database
    public void insertseller(JSONObject j)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        System.out.println("Inserting data into DB");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Seller s=new Seller();
        s.setName(j.getString("name"));
        s.setLocation(j.getString("location"));
        s.setUsername(j.getString("username"));
        s.setPassword(j.getString("password"));
        Transaction tx = session.beginTransaction();
        session.save(s);
        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        factory.close();
    }
    //method to create a customer entry in the database
    public void insertcustomer(JSONObject j)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        System.out.println("Inserting data into DB");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Customer c=new Customer();
        c.setName(j.getString("name"));
        c.setAddress(j.getString("address"));
        c.setUsername(j.getString("username"));
        c.setPassword(j.getString("password"));
        Transaction tx = session.beginTransaction();
        session.save(c);
        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        factory.close();
    }
    //method to create an order entry in the database
    public void insertorder(JSONObject j)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        System.out.println("Inserting data into DB");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Orders b = new Orders();
        b.setCid(j.getInt("cid"));
        b.setPid(j.getInt("pid"));
        JSONObject jj=retrievedata(j.getInt("pid"));
        b.setPname(jj.getString("name"));
        b.setCategory(jj.getString("category"));
        b.setDescription(jj.getString("description"));
        b.setSid(jj.getInt("sellerid"));
        b.setPrice(jj.getLong("price"));
        deleteproduct(j.getInt("pid"));
        Transaction tx = session.beginTransaction();
        session.save(b);
        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        factory.close();
    }
    //method to create a product entry in the database
    public void insertproduct(JSONObject j)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        System.out.println("Inserting data into DB");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Product b = new Product();
        b.setName(j.getString("name"));
        b.setCategory(j.getString("category"));
        b.setDescription(j.getString("description"));
        b.setPrice(j.getLong("price"));
        b.setSellerid(j.getInt("sellerid"));
        Transaction tx = session.beginTransaction();
        session.save(b);
        System.out.println("Object saved successfully.....!!");
        tx.commit();
        session.close();
        factory.close();
    }
    //method to delete a product in the database
    void deleteproduct(int id)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Product b = (Product)session.get(Product.class,id);
            session.delete(b);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
    //method to return all the products
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
                System.out.print("ID: " +b.getId());
                System.out.print("Name: " +b.getName());
                System.out.print("category: " +b.getCategory());
                System.out.print("Description: " +b.getDescription());
                System.out.print("price: " +b.getPrice());
                System.out.print("Seller: " +b.getSellerid());
                JSONObject job=new JSONObject();
                job.put("id",b.getId());
                job.put("name",b.getName());
                job.put("category",b.getCategory());
                job.put("description",b.getDescription());
                job.put("price",b.getPrice());
                job.put("sellerid",b.getSellerid());
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
    //method to retrieve a product data using its id
    JSONObject retrievedata(int id)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Object o=session.load(Product.class,new Integer(id));
        Product s=(Product)o;
        System.out.println("Loaded object Product name is: "+ s.getName());
        JSONObject data=new JSONObject();
        data.put("id",s.getId());
        data.put("price",s.getPrice());
        data.put("category",s.getCategory());
        data.put("description",s.getDescription());
        data.put("name",s.getName());
        data.put("sellerid",s.getSellerid());
        System.out.println("Object Loaded successfully.....!!");
        session.close();
        factory.close();
        return data;
    }
    //method to return the sold products of a seller(Query from orders table)
    JSONArray returnsold(int id)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        JSONArray j=new JSONArray();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql ="FROM Orders WHERE sid=:id";
            Query query=session.createQuery(hql);
            query.setParameter("id",id);
            List products = query.list();
            int i;i=0;
            for (Iterator iterator =products.iterator(); iterator.hasNext();)
            {
                Orders b = (Orders) iterator.next();
                System.out.print("ID: " +b.getId());
                System.out.print("Name: " +b.getPname());
                System.out.print("category: " +b.getCategory());
                System.out.print("Description: " +b.getDescription());
                System.out.print("price: " +b.getPrice());
                System.out.print("Seller: " +b.getSid());
                JSONObject job=new JSONObject();
                job.put("pid",b.getPid());
                job.put("name",b.getPname());
                job.put("category",b.getCategory());
                job.put("description",b.getDescription());
                job.put("price",b.getPrice());
                job.put("sellerid",b.getSid());
                j.put(i,job);
                i++;
            }
            tx.commit();
        }catch (HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return j;
    }
    //method to return a seller's unsold products(Query from product table)
    JSONArray returnunsold(int id)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        JSONArray j=new JSONArray();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "FROM Product P WHERE P.sellerid=:id";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            List products = query.list();
            int i;i=0;
            for (Iterator iterator =products.iterator(); iterator.hasNext();){
                Product b = (Product) iterator.next();
                System.out.print("ID: " +b.getId());
                System.out.print("Name: " +b.getName());
                System.out.print("category: " +b.getCategory());
                System.out.print("Description: " +b.getDescription());
                System.out.print("price: " +b.getPrice());
                System.out.print("Seller: " +b.getSellerid());
                JSONObject job=new JSONObject();
                job.put("id",b.getId());
                job.put("name",b.getName());
                job.put("category",b.getCategory());
                job.put("description",b.getDescription());
                job.put("price",b.getPrice());
                job.put("sellerid",b.getSellerid());
                j.put(i,job);
                i++;
            }
            tx.commit();
        }catch (HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return j;
    }

    JSONArray vieworder(int id)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        JSONArray j=new JSONArray();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql ="FROM Orders WHERE cid=:id";
            Query query=session.createQuery(hql);
            query.setParameter("id",id);
            List products = query.list();
            int i;i=0;
            for (Iterator iterator =products.iterator(); iterator.hasNext();)
            {
                Orders b = (Orders) iterator.next();
                System.out.print("ID: " +b.getId());
                System.out.print("Name: " +b.getPname());
                System.out.print("category: " +b.getCategory());
                System.out.print("Description: " +b.getDescription());
                System.out.print("price: " +b.getPrice());
                System.out.print("Seller: " +b.getSid());
                JSONObject job=new JSONObject();
                job.put("oid",b.getPid());
                job.put("name",b.getPname());
                job.put("category",b.getCategory());
                job.put("description",b.getDescription());
                job.put("price",b.getPrice());
                job.put("sid",b.getSid());
                j.put(i,job);
                i++;
            }
            tx.commit();
        }catch (HibernateException e)
        {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally
        {
            session.close();
        }
        return j;
    }

    JSONObject loginseller(JSONObject j) {

        JSONObject d = new JSONObject();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Seller WHERE username=:user AND password=:pass";
            Query query = session.createQuery(hql);
            query.setParameter("user", j.getString("username"));
            query.setParameter("pass", j.getString("password"));
            List products = query.list();
            d.put("STATUS", "ERROR");
            for (Iterator iterator = products.iterator(); iterator.hasNext(); ) {
                Seller s = (Seller) iterator.next();
                System.out.print("ID: " + s.getId());
                d.put("id", s.getId());
                d.put("username", s.getUsername());
                d.remove("STATUS");
                d.put("STATUS","SUCCESS");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return d;
    }

    JSONObject logincustomer(JSONObject j) {

        JSONObject d = new JSONObject();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Customer WHERE username=:user AND password=:pass";
            Query query = session.createQuery(hql);
            query.setParameter("user", j.getString("username"));
            query.setParameter("pass", j.getString("password"));
            List products = query.list();
            d.put("STATUS", "ERROR");
            for (Iterator iterator = products.iterator(); iterator.hasNext(); ) {
                Customer s = (Customer) iterator.next();
                System.out.print("ID: " + s.getId());
                d.put("id", s.getId());
                d.put("username", s.getUsername());
                d.remove("STATUS");
                d.put("STATUS","SUCCESS");
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return d;
    }

}


