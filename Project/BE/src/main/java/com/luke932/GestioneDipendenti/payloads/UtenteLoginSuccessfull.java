package com.luke932.GestioneDipendenti.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UtenteLoginSuccessfull {
    private String token;

    public UtenteLoginSuccessfull(String token) {

        this.token = token;
    }

    @Override
    public String toString() {
        return "UtenteLoginSuccessful [token=" + token + "]";
    }

}
