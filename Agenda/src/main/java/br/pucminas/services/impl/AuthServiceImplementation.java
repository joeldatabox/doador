package br.pucminas.services.impl;

import br.pucminas.exception.AgendaException;
import br.pucminas.exception.AgendaUnauthorizedException;
import br.pucminas.model.JwtUser;
import br.pucminas.model.User;
import br.pucminas.services.AuthService;
import br.pucminas.services.JwtService;
import br.pucminas.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by master on 11/11/16.
 */
@Service
public class AuthServiceImplementation implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Override
    public String auth(User user) throws AgendaException {
        Boolean correctCredentials = userService.authenticate(user.getUserName(), user.getPassWord());
        if (correctCredentials) {
            return jwtService.getToken(new JwtUser(user.getUserName(), user.getPassWord()));
        }
        throw new AgendaUnauthorizedException();
    }
}
