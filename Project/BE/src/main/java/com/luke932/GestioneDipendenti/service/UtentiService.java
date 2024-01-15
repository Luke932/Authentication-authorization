package com.luke932.GestioneDipendenti.service;

import com.luke932.GestioneDipendenti.entity.Utenti;
import com.luke932.GestioneDipendenti.exceptions.ItemNotFoundException;
import com.luke932.GestioneDipendenti.payloads.UtenteSavePayload;
import com.luke932.GestioneDipendenti.payloads.UtenteUpdatePayload;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UtentiService {

    public Utenti createUser(UtenteSavePayload body);

    public Utenti createAdmin(UtenteSavePayload body);

    public Utenti findByEmail(String email);

    public Utenti saveUtenti(Utenti body);

    public Utenti findByRuolo(String ruolo);

    public List<Utenti> findAll();

    public Page<Utenti> findForPage(int page,int size, String sort);

    public Utenti findById(UUID id) throws ItemNotFoundException;

    public Utenti updateUtenti(UUID id, UtenteUpdatePayload body);

    public void deleteUtente(UUID id) throws ItemNotFoundException;
}
