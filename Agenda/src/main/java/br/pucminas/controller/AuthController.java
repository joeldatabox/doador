package br.pucminas.controller;

import br.pucminas.exception.AgendaUnauthorizedException;
import br.pucminas.model.JwtUser;
import br.pucminas.model.User;
import br.pucminas.services.JwtService;
import br.pucminas.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by master on 03/11/16.
 */
@RestController
public class AuthController extends Controller<User> {
    private UserService service;
    private JwtService jwtService;

    public ResponseEntity<?> auth(@RequestBody User user) {
        Boolean correctCredentials = service.authenticate(user.getUserName(), user.getPassWord());
        if (correctCredentials) {
            return ResponseEntity.ok(jwtService.getToken(new JwtUser(user.getUserName(), user.getPassWord())));
        }
        return processException(new AgendaUnauthorizedException());
    }
}
