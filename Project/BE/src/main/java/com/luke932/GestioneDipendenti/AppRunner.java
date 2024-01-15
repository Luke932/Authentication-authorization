package com.luke932.GestioneDipendenti;

import com.luke932.GestioneDipendenti.entity.Ruolo;
import com.luke932.GestioneDipendenti.service.RuoloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final RuoloService roleS;

    public AppRunner(RuoloService roleS){
        this.roleS=roleS;
    }

    @Override
    public void run(String... args) throws Exception {
        /*Ruolo roleUser = new Ruolo("USER", true, false);
        Ruolo roleAdmin = new Ruolo("ADMIN", false, true );
        roleS.saveRuolo(roleUser);
        roleS.saveRuolo(roleAdmin);*/
    }
}
