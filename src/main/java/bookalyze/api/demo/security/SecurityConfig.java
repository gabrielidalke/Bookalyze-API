package bookalyze.api.demo.security;

import bookalyze.api.demo.auth.filter.JwtAuthFilter; // Verifique se este é o pacote correto do seu JwtAuthFilter
import bookalyze.api.demo.auth.service.CustomUserDetailsService; // Verifique se este é o pacote correto do seu CustomUserDetailsService
import lombok.RequiredArgsConstructor; // Import do Lombok, se você o estiver usando

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer; // Para csrf().disable()
import static org.springframework.security.config.Customizer.withDefaults; // Para httpBasic(withDefaults()) ou formLogin(withDefaults())

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Usando Lombok para injeção de dependência via construtor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService customUserDetailsService;

    // Construtor manual (se não usar Lombok @RequiredArgsConstructor)
    // public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomUserDetailsService customUserDetailsService) {
    //     this.jwtAuthFilter = jwtAuthFilter;
    //     this.customUserDetailsService = customUserDetailsService;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desabilita CSRF para APIs REST sem sessão
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura sessão sem estado
                .authorizeHttpRequests(authorize -> authorize
                        // Permite requisições OPTIONS (necessário para CORS preflight requests)
                        .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()
                        // Permite acesso a endpoints de listagem e criação de apartamentos, contatos e reservas
                        // Ajuste estas URLs conforme seus controladores
                        .requestMatchers("/api/apartments/**").permitAll()
                        .requestMatchers("/api/contacts/**").permitAll()
                        .requestMatchers("/api/reservations/**").permitAll()
                        // Permite acesso a endpoints de autenticação (login, registro, etc.)
                        .requestMatchers("/auth/**").permitAll() // Exemplo: /auth/login, /auth/register
                        // Todas as outras requisições exigem autenticação
                        .anyRequest().authenticated()
                )
                // Adiciona o filtro JWT antes do filtro de autenticação de usuário/senha padrão
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Configura o provedor de autenticação
                .authenticationProvider(authenticationProvider());
        // Se você quiser autenticação HTTP Basic para testes, pode adicionar:
        // .httpBasic(withDefaults());
        // Se você quiser login por formulário (para apps web tradicionais), pode adicionar:
        // .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(CustomUserDetailsService); // Usa o seu UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Usa o seu PasswordEncoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // Este é o método correto para obter o AuthenticationManager na nova API
        return config.getAuthenticationManager();
    }
}

