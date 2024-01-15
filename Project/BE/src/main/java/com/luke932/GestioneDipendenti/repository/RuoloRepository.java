package com.luke932.GestioneDipendenti.repository;

import com.luke932.GestioneDipendenti.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {
    Ruolo findByNome(String nome);

    Optional<Ruolo> findById(UUID id);
}
