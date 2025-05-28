package bookalyze.api.demo.auth.controller;

import bookalyze.api.demo.auth.repository.dto.AuthDTO;
import bookalyze.api.demo.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor // Gera o construtor automaticamente com final fields
public class AuthController {

    @Autowired
    private AuthService authService;


    // Registro de novo usuário
    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@Valid @RequestBody AuthDTO authDTO) {
        return authService.createUser(authDTO);
    }

    // Login do usuário
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody AuthDTO authDTO) {
        return authService.login(authDTO);
    }
}
