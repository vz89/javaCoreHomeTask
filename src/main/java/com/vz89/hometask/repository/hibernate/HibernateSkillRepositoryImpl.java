package com.vz89.hometask.repository.hibernate;

import com.vz89.hometask.model.Skill;
import com.vz89.hometask.repository.SkillRepository;
import com.vz89.hometask.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository {
    @Override
    public Skill getById(Long id) {
        Session session = null;
        Skill skill = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            skill = session.get(Skill.class, id);
        } catch (Exception e) {
            System.out.println("Сan't find Skill by id");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public List<Skill> findAll() {
        Session session = null;
        List<Skill> skills = new ArrayList<>();

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaQuery<Skill> criteriaQuery = session.getCriteriaBuilder().createQuery(Skill.class);
            criteriaQuery.from(Skill.class);

            skills = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            System.out.println("can't find all");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skills;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't save skill");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update Skill");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return skill;
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Skill skill = session.load(Skill.class, id);
            session.delete(skill);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update Skill");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
