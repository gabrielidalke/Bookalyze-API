package bookalyze.api.demo.auth.service;



import bookalyze.api.demo.auth.repository.dto.AuthDTO;
import bookalyze.api.demo.auth.repository.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import bookalyze.api.demo.auth.repository.AuthRepository; // Importe seu AuthRepository

import java.util.Optional; // Importe Optional para lidar com a busca no repositório

@RequiredArgsConstructor // Gera o construtor automaticamente para injetar o AuthRepository
@Service // Indica que esta classe é um serviço Spring
public class AuthService {

    // Injeção de dependência do repositório para acesso ao banco de dados
    private final AuthRepository authRepository;

    /**
     * Lógica para criar um novo usuário no sistema.
     * A senha é salva sem criptografia para simplificação, mas isso é uma falha de segurança.
     *
     * @param authDTO Objeto contendo o nome de usuário e a senha para registro.
     * @return ResponseEntity indicando sucesso (CREATED) ou falha (INTERNAL_SERVER_ERROR).
     */
    public ResponseEntity<Object> createUser(AuthDTO authDTO) {
        try {
            // Verifica se o usuário já existe
            if (authRepository.findByUsername(authDTO.getUsername()).isPresent()) {
                return new ResponseEntity<>("Nome de usuário já existe!", HttpStatus.CONFLICT); // 409 Conflict
            }

            Auth auth = new Auth();
            auth.setUsername(authDTO.getUsername());
            // AVISO DE SEGURANÇA: Senha salva sem criptografia.
            // Em um ambiente de produção, a senha DEVE ser criptografada (ex: com BCryptPasswordEncoder).
            auth.setPassword(authDTO.getPassword());

            authRepository.save(auth); // Salva o novo usuário no banco de dados

            return new ResponseEntity<>("Usuário criado com sucesso!", HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            // Log do erro para depuração
            System.err.println("Erro ao criar usuário: " + e.getMessage());
            return new ResponseEntity<>("Erro ao criar usuário", HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    /**
     * Lógica para autenticar um usuário.
     * Compara a senha fornecida com a senha armazenada (sem criptografia).
     *
     * @param authDTO Objeto contendo as credenciais de login (nome de usuário e senha).
     * @return ResponseEntity indicando sucesso (OK) ou falha (UNAUTHORIZED).
     */
    public ResponseEntity<Object> login(AuthDTO authDTO) {
        // 1. Tenta encontrar o usuário pelo nome de usuário
        Optional<Auth> optionalAuth = authRepository.findByUsername(authDTO.getUsername());

        // 2. Verifica se o usuário foi encontrado
        if (optionalAuth.isEmpty()) {
            // Usuário não encontrado no banco de dados
            System.out.println("Tentativa de login falhou: Usuário '" + authDTO.getUsername() + "' não encontrado.");
            return new ResponseEntity<>("Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }

        Auth foundUser = optionalAuth.get();

        // 3. Compara a senha fornecida com a senha armazenada (sem criptografia)
        // AVISO DE SEGURANÇA: Esta comparação é insegura para produção.
        // Em um ambiente real, você usaria um PasswordEncoder (ex: BCryptPasswordEncoder.matches()).
        if (authDTO.getPassword().equals(foundUser.getPassword())) {
            // Senha corresponde, login bem-sucedido
            System.out.println("Login bem-sucedido para o usuário: " + authDTO.getUsername());
            return new ResponseEntity<>("Login bem-sucedido!", HttpStatus.OK); // 200 OK
        } else {
            // Senha não corresponde
            System.out.println("Tentativa de login falhou: Senha incorreta para o usuário '" + authDTO.getUsername() + "'.");
            return new ResponseEntity<>("Usuário ou senha inválidos.", HttpStatus.UNAUTHORIZED); // 401 Unauthorized
        }
    }
}
