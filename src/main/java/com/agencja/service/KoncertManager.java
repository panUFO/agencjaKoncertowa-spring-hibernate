package com.agencja.service;


import com.agencja.domain.Koncert;

import java.util.List;

public interface KoncertManager {


    void addKoncert(Koncert koncert);
    List<Koncert> getAllKoncerts();
    Koncert getKoncertByID(Long id);
    void updateKoncert(Koncert koncert);
    void deleteKoncert(Koncert koncert);


}
