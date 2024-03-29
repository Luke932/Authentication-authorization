package com.luke932.GestioneDipendenti.exceptions;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(UUID id) {
        super("Item con id " + id + " non trovato.");
    }
}
