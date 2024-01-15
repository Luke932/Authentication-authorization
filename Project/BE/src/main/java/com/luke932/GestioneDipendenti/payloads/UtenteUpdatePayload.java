package com.luke932.GestioneDipendenti.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtenteUpdatePayload {

    private String eta;
    private String password;
    private String email;
    private String nome;
    private String cognome;

    private String nomeRuolo;

}
