package com.agencja.service;

import com.agencja.domain.Zespol;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ZespolManagerImpl implements ZespolManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
/*
    @Override
    public void addZespol(Zespol zespol){
        zespol.setIdZespol(null);
        sessionFactory.getCurrentSession().persist(zespol);
    }
*/
    @Override
    public Zespol addZespol(Zespol zespol) {

        long idZespol = ((Long)sessionFactory.getCurrentSession().save(zespol)).longValue();
        zespol.setIdZespol(idZespol);
        return zespol;
    }

    @Override
    public List<Zespol> getAllZespols(){
        return sessionFactory.getCurrentSession().getNamedQuery("zespol.getAll").list();
    }

    @Override
    public Zespol getZespolByID(Long id){
        return (Zespol) sessionFactory.getCurrentSession().getNamedQuery("zespol.getByID").setLong("idZespol",id).uniqueResult();
    }

    @Override
    public void deleteZespol(Zespol zespol) {
        zespol = (Zespol) sessionFactory.getCurrentSession().get(Zespol.class, zespol.getIdZespol());

        /*
        // lazy loading here
        for (Car car : person.getCars()) {
            car.setSold(false);
            sessionFactory.getCurrentSession().update(car);
        }
        */
        sessionFactory.getCurrentSession().delete(zespol);
    }

    @Override
    public void updateZespol(Zespol zespol){
        sessionFactory.getCurrentSession().update(zespol);
    }

}
