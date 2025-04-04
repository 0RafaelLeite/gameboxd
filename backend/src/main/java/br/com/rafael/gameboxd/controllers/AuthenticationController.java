package br.com.rafael.gameboxd.controllers;

import br.com.rafael.gameboxd.domain.credential.AuthenticationDTO;
import br.com.rafael.gameboxd.domain.credential.LoginResponseDTO;
import br.com.rafael.gameboxd.domain.credential.RegisterDTO;
import br.com.rafael.gameboxd.domain.credential.User;
import br.com.rafael.gameboxd.repositories.UserRepository;
import br.com.rafael.gameboxd.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        UsernamePasswordAuthenticationToken credential = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        try{
            Authentication auth = this.authenticationManager.authenticate(credential);
            String token = tokenService.generateToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginResponseDTO(token));

        }catch(Exception e){
            return ResponseEntity.badRequest().body(String.format("Something got wrong: %s", e.getMessage()));
        }

    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if (this.userRepository.findByUsername(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
