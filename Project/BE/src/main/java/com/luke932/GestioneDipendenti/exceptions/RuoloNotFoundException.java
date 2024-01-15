package com.luke932.GestioneDipendenti.exceptions;

import java.util.UUID;

public class RuoloNotFoundException extends RuntimeException {
    public RuoloNotFoundException(UUID id) {
        super("Ruolo come: " + id + " non trovato");
    }
}
