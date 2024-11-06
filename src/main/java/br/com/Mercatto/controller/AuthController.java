package br.com.Mercatto.controller;

import br.com.Mercatto.JWT.JwtUtil;
import br.com.Mercatto.dao.UserDao;
import br.com.Mercatto.dto.LoginRequestDTO;
import br.com.Mercatto.dto.ResponseDTO;
import br.com.Mercatto.dto.SignupRequestDTO;
import br.com.Mercatto.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmailId(body.email());

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: "+ body.email());
        }

        if(passwordEncoder.matches(body.password(), user.getSenha())){
            String token = this.jwtUtil.generateToken(user.getName(), user.getRole());
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupRequestDTO body){
        User user = this.repository.findByEmailId(body.email());

        if(user == null){
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setContactNumber(body.number());
            this.repository.save(newUser);

            String token = this.jwtUtil.generateToken(newUser.getName(), newUser.getRole());
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


}
