package com.vz89.hometask.repository.hibernate;

import com.vz89.hometask.model.Account;
import com.vz89.hometask.model.AccountStatus;
import com.vz89.hometask.repository.AccountRepository;
import com.vz89.hometask.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository {
    @Override
    public Account getById(Long id) {
        Session session = null;
        Account account = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            account = session.get(Account.class, id);
        } catch (Exception e) {
            System.out.println("Сan't find Account by id");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        Session session = null;
        List<Account> accounts = new ArrayList<>();

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            CriteriaQuery<Account> criteriaQuery = session.getCriteriaBuilder().createQuery(Account.class);
            criteriaQuery.from(Account.class);

            accounts = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            System.out.println("can't find all accounts");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return accounts;
    }

    @Override
    public Account save(Account account) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            account.setAccountStatus(AccountStatus.ACTIVE);
            session.save(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't save account");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }

    @Override
    public Account update(Account account) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            account.setName(session.get(Account.class, account.getId()).getName());
            session.merge(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update account");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Account account = session.load(Account.class, id);
            session.delete(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("can't update Account");
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
