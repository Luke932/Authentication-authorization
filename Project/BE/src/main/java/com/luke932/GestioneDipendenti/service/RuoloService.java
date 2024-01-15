package com.luke932.GestioneDipendenti.service;

import com.luke932.GestioneDipendenti.exceptions.RuoloNotFoundException;
import com.luke932.GestioneDipendenti.entity.Ruolo;

import java.util.List;
import java.util.UUID;

public interface RuoloService {

    public Ruolo saveRuolo(Ruolo body);
    public List<Ruolo> findAllRuoli();
    public Ruolo findById(UUID id) throws RuoloNotFoundException;
    public Ruolo findByNome(String ruolo) throws RuoloNotFoundException;
    public Ruolo findByIdAndUpdate(UUID id, Ruolo body) throws RuoloNotFoundException;
    public void deleteRuolo(UUID id) throws RuoloNotFoundException;

}
