package br.com.Mercatto.controller;
import br.com.Mercatto.infra.security.TokenService;
import br.com.Mercatto.model.UserModel;
import br.com.Mercatto.repositories.UserRepository;
import br.com.Mercatto.dto.LoginRequestDTO;
import br.com.Mercatto.dto.ResponseDTO;
import br.com.Mercatto.dto.SignupRequestDTO;
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
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        UserModel userModel = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), userModel.getPassword())) {
            String token = this.tokenService.generateToken(userModel);
            return ResponseEntity.ok(new ResponseDTO(userModel.getName(), token));
        }
        return ResponseEntity.badRequest().body("Erro ao realizar o login");
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDTO body) {
        Optional<UserModel> user = this.repository.findByEmail(body.email());

        if (user.isEmpty()) {
            UserModel newUserModel = new UserModel();

            newUserModel.setPassword(passwordEncoder.encode(body.password()));
            newUserModel.setEmail(body.email());
            newUserModel.setNumber(body.number());
            newUserModel.setName(body.name());
            this.repository.save(newUserModel);

//            String token = this.tokenService.generateToken(newUserModel);
            return ResponseEntity.ok().body("Cadastrado com sucesso");
        }
        return ResponseEntity.badRequest().build();
    }
}
