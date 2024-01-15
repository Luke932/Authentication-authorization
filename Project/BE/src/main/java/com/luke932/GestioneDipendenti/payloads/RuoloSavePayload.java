package com.luke932.GestioneDipendenti.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RuoloSavePayload {

    private Boolean ruoloUser;
    private Boolean ruoloAdmin;
    private String nome;

}
