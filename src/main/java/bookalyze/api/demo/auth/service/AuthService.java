package bookalyze.api.demo.auth.service;

import bookalyze.api.demo.auth.repository.AuthRepository;
import bookalyze.api.demo.auth.repository.dto.AuthDTO;
import bookalyze.api.demo.auth.repository.entity.Auth;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Log4j2
@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<Object> createUser(@Valid AuthDTO authDTO) {
        try {
            byte[] salt = generateSalt();
            String hashedPassword = hashPassword(authDTO.getPassword(), salt);

            Auth auth = new Auth();
            auth.setUsername(authDTO.getUsername());
            auth.setPassword(hashedPassword);
            auth.setSalt(Base64.getEncoder().encodeToString(salt));

            authRepository.save(auth);

            return new ResponseEntity<>("Usuário criado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao criar usuário: ", e);
            return new ResponseEntity<>("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> login(@Valid AuthDTO authDTO) {
        try {
            Auth auth = authRepository.findByUsername(authDTO.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));


            if (auth == null) {
                return new ResponseEntity<>("Usuário não encontrado", HttpStatus.UNAUTHORIZED);
            }

            byte[] salt = Base64.getDecoder().decode(auth.getSalt());
            String hashedInputPassword = hashPassword(authDTO.getPassword(), salt);

            if (!hashedInputPassword.equals(auth.getPassword())) {
                return new ResponseEntity<>("Senha incorreta", HttpStatus.UNAUTHORIZED);
            }

            String token = jwtUtil.generateToken(auth.getUsername());

            return ResponseEntity.ok().body(java.util.Map.of("token", token));
        } catch (Exception e) {
            log.error("Erro ao verificar credenciais: ", e);
            return new ResponseEntity<>("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    private String hashPassword(String password, byte[] salt) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
}

