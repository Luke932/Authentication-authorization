package com.luke932.GestioneDipendenti.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtenteLoginPayload {
    String email;
    String password;
}
