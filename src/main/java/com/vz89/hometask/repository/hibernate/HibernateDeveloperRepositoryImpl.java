package com.vz89.hometask.repository.hibernate;

import com.vz89.hometask.model.Developer;
import com.vz89.hometask.repository.DeveloperRepository;
import com.vz89.hometask.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.EntityGraph;
import java.util.ArrayList;
import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository {
    @Override
    public Developer getById(Long id) {
        Session session = null;
        Developer developer = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            EntityGraph entityGraph = session.getEntityGraph("account_entity_graph");
            developer = session.createQuery("select d from Developer d where d.id = :id", Developer.class)
                    .setHint("javax.persistence.fetchgraph", entityGraph)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Сan't find Developer by id");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;
    }

    @Override
    public List<Developer> findAll() {

        Session session = null;
        List<Developer> developers = new ArrayList<>();

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            EntityGraph entityGraph = session.getEntityGraph("account_entity_graph");
            developers = session.createQuery("select d from Developer d", Developer.class)
                    .setHint("javax.persistence.fetchgraph", entityGraph)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("can't find all");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developers;
    }

    @Override
    public Developer save(Developer developer) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(developer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't save developer");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;

    }

    @Override
    public Developer update(Developer developer) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(developer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update Developer");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return developer;
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Developer developer = session.load(Developer.class, id);
            session.delete(developer);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update Developer");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
