package com.agencja.service;

import com.agencja.domain.Zespol;

import java.util.List;

public interface ZespolManager {

    void addZespol(Zespol zespol);
    List<Zespol> getAllZespols();
    Zespol getZespolByID(Long id);
    void updateZespol(Zespol zespol);
    void deleteZespol(Zespol zespol);
}
