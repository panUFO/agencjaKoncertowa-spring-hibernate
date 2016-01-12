package com.agencja.service;


import com.agencja.domain.Klub;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class KlubManagerImpl implements KlubManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addKlub(Klub klub){
        klub.setIdKlub(null);
        sessionFactory.getCurrentSession().persist(klub);
    }
    public List<Klub> getAllKlubs(){
        return sessionFactory.getCurrentSession().getNamedQuery("klub.getAll").list();

    }
    public Klub getKlubByID(Long id){
        return (Klub) sessionFactory.getCurrentSession().getNamedQuery("klub.getByID").setLong("idKlub",id).uniqueResult();

    }

    public void updateKlub(Klub klub){
        sessionFactory.getCurrentSession().update(klub);

    }
    public void deleteKlub(Klub klub){
        klub = (Klub) sessionFactory.getCurrentSession().get(Klub.class, klub.getIdKlub());

        /*
        // lazy loading here
        for (Car car : person.getCars()) {
            car.setSold(false);
            sessionFactory.getCurrentSession().update(car);
        }
        */
        sessionFactory.getCurrentSession().delete(klub);

    }
}
