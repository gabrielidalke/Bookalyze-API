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

@Log4j2 // Anotação do Lombok para logs
@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/auth") // Mapeia todas as requisições para /auth
@RequiredArgsConstructor // Gera um construtor com os campos final (para injeção de dependência)
// Configuração CORS: permite requisições do frontend React em http://localhost:5173
// IMPORTANTE: Se você tiver mais de uma origem (ex: 5174), você precisará mudar para um array:
// @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"})
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    // Injeção de dependência do serviço de autenticação
    @Autowired
    private AuthService authService;

    /**
     * Endpoint para registro de novos usuários.
     * Recebe um AuthDTO no corpo da requisição e delega para o AuthService.
     *
     * @param authDTO Objeto contendo os dados de autenticação (usuário, senha).
     * @return ResponseEntity com o resultado da operação de criação do usuário.
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> create(@Valid @RequestBody AuthDTO authDTO) {
        // Delega a lógica de criação de usuário para o AuthService
        return authService.createUser(authDTO);
    }

    /**
     * Endpoint para login de usuários existentes.
     * Recebe um AuthDTO no corpo da requisição e delega para o AuthService.
     *
     * @param authDTO Objeto contendo as credenciais de login (usuário, senha).
     * @return ResponseEntity com o resultado da operação de login.
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Valid @RequestBody AuthDTO authDTO) {
        // Delega a lógica de login para o AuthService
        return authService.login(authDTO);
    }
}
