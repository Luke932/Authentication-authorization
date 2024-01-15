package com.luke932.GestioneDipendenti.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "Ruoli")
public class Ruolo {

    @Id
    @GeneratedValue
    private UUID id;
    @Column(unique = true)
    private String nome;
    private Boolean ruoloUser;
    private Boolean ruoloAdmin;

    public Ruolo(String nome, Boolean ruoloUser, Boolean ruoloAdmin) {
        super();
        this.nome = nome;
        this.ruoloUser = ruoloUser;
        this.ruoloAdmin = ruoloAdmin;
    }

}
