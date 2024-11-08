package br.com.Mercatto.controller;


import br.com.Mercatto.infra.security.TokenService;
import br.com.Mercatto.repositories.UserRepository;
import br.com.Mercatto.dto.LoginRequestDTO;
import br.com.Mercatto.dto.ResponseDTO;
import br.com.Mercatto.dto.SignupRequestDTO;
import br.com.Mercatto.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if(passwordEncoder.matches(body.password(), user.getPassword())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().body("Erro ao realizar o login");
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDTO body){
        Optional<User> user = this.repository.findByEmail(body.email());

        if(user.isEmpty()){
            User newUser = new User();

            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setNumber(body.number());
            newUser.setName(body.name());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
//        return ResponseEntity.badRequest().body("");
        return ResponseEntity.badRequest().build();
    }
}
