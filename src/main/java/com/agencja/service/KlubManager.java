package com.agencja.service;

import com.agencja.domain.Klub;
import java.util.List;

public interface KlubManager{
    void addKlub(Klub klub);
    List<Klub> getAllKlubs();
    Klub getKlubByID(Long id);
    void updateKlub(Klub klub);
    void deleteKlub(Klub klub);

}
