package com.luke932.GestioneDipendenti.controller;

import com.luke932.GestioneDipendenti.entity.Utenti;
import com.luke932.GestioneDipendenti.service.UtentiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/utenti")
public class UtentiController {

    private final UtentiService utentiS;

    public UtentiController(UtentiService utentiS){
        this.utentiS = utentiS;
    }

    @GetMapping ("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Utenti getById(@PathVariable UUID id){
        return utentiS.findById(id);
    }
}
