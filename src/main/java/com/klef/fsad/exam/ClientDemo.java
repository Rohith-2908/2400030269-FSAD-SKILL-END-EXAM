package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;

public class ClientDemo {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Shipment shipment = new Shipment(
                "Electronics",
                new Date(),
                "Dispatched",
                "Hyderabad",
                20.5
        );

        session.save(shipment);

        tx.commit();
        session.close();

        System.out.println("Inserted Successfully");

        Session session2 = HibernateUtil.getSessionFactory().openSession();
        Transaction tx2 = session2.beginTransaction();

        int deleteId = shipment.getId(); 

        Query query = session2.createQuery("DELETE FROM Shipment WHERE id = :sid");
        query.setParameter("sid", deleteId);

        int result = query.executeUpdate();

        if (result > 0)
            System.out.println("Deleted Successfully");
        else
            System.out.println("Not Found");

        tx2.commit();
        session2.close();
    }
}