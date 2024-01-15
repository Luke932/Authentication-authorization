package com.luke932.GestioneDipendenti.repository;

import com.luke932.GestioneDipendenti.entity.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UtentiRepository extends JpaRepository<Utenti, UUID> {

    Optional<Utenti> findByEmail(String email);

    Utenti findByNome(String nome);

    List<Utenti> findByRuoloNome(String nomeRuolo);
}
