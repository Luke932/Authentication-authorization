package com.luke932.GestioneDipendenti.serviceimp;

import com.luke932.GestioneDipendenti.entity.Ruolo;
import com.luke932.GestioneDipendenti.entity.Utenti;
import com.luke932.GestioneDipendenti.exceptions.BadRequestException;
import com.luke932.GestioneDipendenti.exceptions.ItemNotFoundException;
import com.luke932.GestioneDipendenti.exceptions.NotFoundException;
import com.luke932.GestioneDipendenti.payloads.UtenteSavePayload;
import com.luke932.GestioneDipendenti.payloads.UtenteUpdatePayload;
import com.luke932.GestioneDipendenti.repository.RuoloRepository;
import com.luke932.GestioneDipendenti.repository.UtentiRepository;
import com.luke932.GestioneDipendenti.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UtentiServiceImp implements UtentiService {

    private final UtentiRepository utentiR;
    private final RuoloRepository ruoloR;
    @Autowired
    public UtentiServiceImp(UtentiRepository utentiR, RuoloRepository ruoloR) {
        this.utentiR = utentiR;
        this.ruoloR = ruoloR;
    }

    @Override
    public Utenti createUser(UtenteSavePayload body) {
        utentiR.findByEmail(body.getEmail()).ifPresent(utente -> {
            throw new BadRequestException("L'email è stata già utilizzata");
        });

        Ruolo userRole = ruoloR.findByNome("USER");
        if (userRole == null){
            throw new NotFoundException("Ruolo 'USER' non trovato nel database");
        }

        Utenti newUser = new Utenti(body.getNome(), body.getCognome(), body.getEta(), body.getEmail());

        // Assicurati che la password non sia nulla prima di impostarla
        if (body.getPassword() != null) {
            newUser.setPassword(body.getPassword());
        }

        newUser.setRuolo(userRole);
        return utentiR.save(newUser);
    }


    @Override
    public Utenti createAdmin(UtenteSavePayload body) {
        utentiR.findByEmail(body.getEmail()).ifPresent(utente -> {
            throw new BadRequestException("L'email è stata già utilizzata");
        });
        Ruolo adminRole = ruoloR.findByNome("ADMIN");
        if (adminRole == null){
            throw new NotFoundException("Ruolo 'ADMIN' non trovato nel database");
        }
        Utenti newAdmin = new Utenti(body.getNome(), body.getCognome(), body.getEta(), body.getEmail());
        newAdmin.setRuolo(adminRole);
        return utentiR.save(newAdmin);
    }

    @Override
    public Utenti findByEmail(String email) {
        return utentiR.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con" + email + "non  trovato"));
    }

    @Override
    public Utenti saveUtenti(Utenti body) {
        if (body.getRuolo().getNome() == null || body.getRuolo() == null){
            Ruolo userRuolo = ruoloR.findByNome("USER");
            if(userRuolo == null){
                throw new NotFoundException("Ruolo 'USER' non trovato nel database");
            }
            body.setRuolo(userRuolo);
        }
        return utentiR.save(body);
    }

    @Override
    public Utenti findByRuolo(String ruolo) {
        return utentiR.findByNome(ruolo);
    }

    @Override
    public List<Utenti> findAll() {
        return utentiR.findAll();
    }

    @Override
    public Page<Utenti> findForPage(int page, int size, String sort) {
        Pageable pag = PageRequest.of(page,size, Sort.by(sort));
        return utentiR.findAll(pag);
    }

    @Override
    public Utenti findById(UUID id) throws ItemNotFoundException {
        return utentiR.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public Utenti updateUtenti(UUID id, UtenteUpdatePayload body) {
        Utenti found = this.findById(id);
        String nameRole = body.getNomeRuolo();
        System.out.println("Nome ruolo nel payload " + nameRole);
        Ruolo role = ruoloR.findByNome(nameRole);
        if(role != null){
            found.setNome(body.getNome());
            found.setCognome(body.getCognome());
            found.setEta(body.getEta());
            found.setEmail(body.getEmail());
            found.setPassword(body.getPassword());
            found.setRuolo(role);
            return utentiR.save(found);
        } else {
            throw new RuntimeException("Ruolo non trovato: " + body.getNomeRuolo());
        }
    }

    @Override
    public void deleteUtente(UUID id) throws ItemNotFoundException {
        Utenti found = this.findById(id);
        utentiR.delete(found);
    }
}
