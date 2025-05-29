package bookalyze.api.demo.auth.service; // Pacote correto

import bookalyze.api.demo.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Anotação para marcar como serviço
public class CustomUserDetailsService implements UserDetailsService { // Certifique-se de que a classe implementa UserDetailsService

    @Autowired
    private UserRepository userRepository; // Injetando o repositório do usuário

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados
        return userRepository.findByUsername(username)
                .map(u -> new User(u.getUsername(), u.getPassword(), u.getAuthorities())) // Aqui mapeamos os dados
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
