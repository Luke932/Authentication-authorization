package com.luke932.GestioneDipendenti.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UtenteSavePayload {

    @NotNull(message = "Nome obbligatorio")
    private String nome;
    @NotNull(message = "Cognome obbligatorio")
    private String cognome;
    @NotNull(message = "Età obbligatoria")
    private String eta;
    @NotNull(message = "Email è obbligatoria")
    @Email(message = "Formato e-mail errato")
    private String email;
    @NotNull(message = "Password obbligatoria")
    private String password;
}
