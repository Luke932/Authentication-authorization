# Progetto di Autenticazione e Autorizzazione 🌐🔒

## Descrizione 🚀
Questo progetto si concentra sull'implementazione di autenticazione e autorizzazione. I dettagli principali includono l'uso di Spring Boot con Spring Security per la gestione degli utenti e dei ruoli. Gli utenti che si registrano vengono automaticamente assegnati al ruolo USER. Attualmente, la funzionalità per consentire agli ADMIN di creare altri ADMIN non è ancora implementata.

## Tecnologie Utilizzate 💻
- **Backend:** Spring Boot, Spring Security, JSON Web Token (JWT), PostgreSQL
- **Frontend:** Angular, Forms Module, HttpClientModule, Guards

## Caratteristiche Principali 🌟
- **Autenticazione:** Utilizzo di JWT per gestire l'autenticazione, con configurazioni CORS su ogni controller per consentire l'accesso da qualsiasi URL.
- **Ruoli Utente:** Gli utenti registrati vengono assegnati automaticamente al ruolo USER.
- **Database:** Utilizzo di un database relazionale PostgreSQL per la memorizzazione dei dati utente.
- **Frontend:** Framework Angular per il recupero dei dati utente durante il login tramite il token. La registrazione prevede l'hashing delle password.

## Funzionalità Futuro 🚧
- **Creazione di Admin:** Implementare la funzionalità che consente agli ADMIN di creare altri ADMIN.
- **Interceptor:** Configurato l'interceptor per facilitare l'estensione del progetto per effettuare chiamate HTTP ad altre API che richiedono l'autenticazione.

## Utilizzo del Progetto 🛠️
1. **Backend:**
   - Configurare correttamente il database PostgreSQL.
   - Eseguire l'applicazione Spring Boot.

2. **Frontend:**
   - Assicurarsi che Angular sia installato.
   - Eseguire l'applicazione Angular.

## Accesso Negato 🚫
In caso di tentativo di bypassare l'autorizzazione, gli utenti vengono indirizzati a una pagina di accesso negato. Da questa pagina, è possibile tornare alla pagina di login.

---

# Authentication and Authorization Project 🌐🔒

## Description 🚀
This project focuses on implementing authentication and authorization. Key details include the use of Spring Boot with Spring Security for user and role management. Users who register are automatically assigned the USER role. Currently, the functionality to allow ADMINs to create other ADMINs is not yet implemented.

## Technologies Used 💻
- **Backend:** Spring Boot, Spring Security, JSON Web Token (JWT), PostgreSQL
- **Frontend:** Angular, Forms Module, HttpClientModule, Guards

## Key Features 🌟
- **Authentication:** Use of JWT to handle authentication, with CORS configurations on every controller to allow access from any URL.
- **User Roles:** Registered users are automatically assigned the USER role.
- **Database:** Use of PostgreSQL relational database for storing user data.
- **Frontend:** Angular framework for retrieving user data during login through the token. Registration involves password hashing.

## Future Features 🚧
- **Admin Creation:** Implement functionality to allow ADMINs to create other ADMINs.
- **Interceptor:** Configured interceptor for easy project extension to make HTTP calls to other APIs requiring authentication.

## Project Usage 🛠️
1. **Backend:**
   - Properly configure the PostgreSQL database.
   - Run the Spring Boot application.

2. **Frontend:**
   - Ensure Angular is installed.
   - Run the Angular application.

## Access Denied 🚫
In case of an attempt to bypass authorization, users are redirected to an access denied page. From this page, one can return to the login page.
