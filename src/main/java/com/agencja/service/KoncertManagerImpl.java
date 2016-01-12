package com.agencja.service;


import com.agencja.domain.Koncert;
import com.agencja.domain.Zespol;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class KoncertManagerImpl implements KoncertManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addKoncert(Koncert koncert){
        koncert.setIdKoncert(null);
        sessionFactory.getCurrentSession().persist(koncert);
    }
    @Override
    public List<Koncert> getAllKoncerts(){
        return sessionFactory.getCurrentSession().getNamedQuery("koncert.getAll").list();

    }
    @Override
    public Koncert getKoncertByID(Long id){
        return (Koncert) sessionFactory.getCurrentSession().getNamedQuery("koncert.getByID").setLong("idKoncert",id).uniqueResult();

    }
    @Override
    public void deleteKoncert(Koncert koncert){
        koncert = (Koncert) sessionFactory.getCurrentSession().get(Koncert.class, koncert.getIdKoncert());

        /*
        // lazy loading here
        for (Car car : person.getCars()) {
            car.setSold(false);
            sessionFactory.getCurrentSession().update(car);
        }
        */
        sessionFactory.getCurrentSession().delete(koncert);
    }
    @Override
    public void updateKoncert(Koncert koncert){
        sessionFactory.getCurrentSession().update(koncert);

    }

}
