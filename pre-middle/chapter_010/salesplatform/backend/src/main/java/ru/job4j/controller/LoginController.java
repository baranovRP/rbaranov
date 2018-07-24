package ru.job4j.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.Tokenizer;
import ru.job4j.UserRepository;
import ru.job4j.json.AuthJson;
import ru.job4j.model.User;

import javax.security.sasl.AuthenticationException;

/**
 * Signin servlet.
 */
@RestController
public class LoginController {

    @PostMapping(value = "/login",
        consumes = "application/json;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
    public AuthJson login(@RequestBody User guest) throws AuthenticationException {
        if (!new UserRepository().isCredential(guest.getEmail(), guest.getPassw())) {
            throw new AuthenticationException(
                String.format("User: %s is unknown.", guest.getEmail()));
        }
        User user = new UserRepository().findByEmailPassw(guest.getEmail(), guest.getPassw());
        return new AuthJson(
            new Tokenizer().codeEncodeToken(user.getEmail()), user.getId(), 300L);
    }
}