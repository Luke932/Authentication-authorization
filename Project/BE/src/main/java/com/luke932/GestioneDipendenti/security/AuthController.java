package com.luke932.GestioneDipendenti.security;

import com.luke932.GestioneDipendenti.entity.Utenti;
import com.luke932.GestioneDipendenti.exceptions.UnauthorizedException;
import com.luke932.GestioneDipendenti.payloads.UtenteLoginPayload;
import com.luke932.GestioneDipendenti.payloads.UtenteLoginSuccessfull;
import com.luke932.GestioneDipendenti.payloads.UtenteSavePayload;
import com.luke932.GestioneDipendenti.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UtentiService utentiS;
    private final JWTTools jwt;
    private final PasswordEncoder byCript;
    @Autowired
    public AuthController(UtentiService utentiS,JWTTools jwt, PasswordEncoder byCript){
        this.utentiS=utentiS;
        this.jwt=jwt;
        this.byCript=byCript;
    }

    @PostMapping("/register/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Utenti user(@RequestBody UtenteSavePayload body) throws IOException {
        String rawPassword = body.getPassword();
        System.out.println("Raw Password: " + rawPassword);

        if (rawPassword != null) {
            body.setPassword(byCript.encode(rawPassword));
            Utenti created = utentiS.createUser(body);
            return created;
        } else {
            throw new IllegalArgumentException("La password non può essere nulla.");
        }
    }



    @PostMapping("/register/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Utenti admin(@RequestBody  UtenteSavePayload body) throws IOException{
        body.setPassword(byCript.encode(body.getPassword()));
        Utenti created = utentiS.createAdmin(body);
        return created;
    }

    @PostMapping("/login")
    public ResponseEntity<UtenteLoginSuccessfull> login(@RequestBody UtenteLoginPayload body)
            throws UnauthorizedException {

        Utenti user = utentiS.findByEmail(body.getEmail());

        if (byCript.matches(body.getPassword(), user.getPassword())) {

            String token = jwt.createToken(user);

            UtenteLoginSuccessfull loginAvvenuto = new UtenteLoginSuccessfull(token);
            return new ResponseEntity<>(loginAvvenuto, HttpStatus.OK);

        } else {

            throw new UnauthorizedException("Credenziali non valide!");
        }
    }
}
