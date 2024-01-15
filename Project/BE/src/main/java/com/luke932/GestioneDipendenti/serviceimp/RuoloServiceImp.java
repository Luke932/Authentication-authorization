package com.luke932.GestioneDipendenti.serviceimp;

import com.luke932.GestioneDipendenti.entity.Ruolo;
import com.luke932.GestioneDipendenti.exceptions.RuoloNotFoundException;
import com.luke932.GestioneDipendenti.repository.RuoloRepository;
import com.luke932.GestioneDipendenti.service.RuoloService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class RuoloServiceImp implements RuoloService {
    private final RuoloRepository ruoloR;

    public RuoloServiceImp(RuoloRepository ruoloR){
        this.ruoloR = ruoloR;
    }
    @Override
    public Ruolo saveRuolo(Ruolo body) {
        Ruolo newRole = new Ruolo(body.getNome(),body.getRuoloUser(),body.getRuoloAdmin());
        return ruoloR.save(newRole);
    }

    @Override
    public List<Ruolo> findAllRuoli() {
        return ruoloR.findAll();
    }

    @Override
    public Ruolo findById(UUID id) throws RuoloNotFoundException {
        return ruoloR.findById(id)
                .orElseThrow(() -> new RuoloNotFoundException(id));
    }

    @Override
    public Ruolo findByNome(String ruolo) throws RuoloNotFoundException {
        return ruoloR.findByNome(ruolo);
    }

    @Override
    public Ruolo findByIdAndUpdate(UUID id, Ruolo body) throws RuoloNotFoundException {
        Ruolo found = this.findById(id);
        found.setNome(body.getNome());
        found.setRuoloAdmin(body.getRuoloAdmin());
        found.setRuoloUser(body.getRuoloUser());
        return ruoloR.save(found);
    }

    @Override
    public void deleteRuolo(UUID id) throws RuoloNotFoundException {
        Ruolo found = this.findById(id);
        ruoloR.delete(found);
    }
}
