package br.pucminas.service.impl;

import br.pucminas.exception.PacienteException;
import br.pucminas.exception.PacienteUnauthorizedException;
import br.pucminas.model.JwtUser;
import br.pucminas.model.User;
import br.pucminas.service.AuthService;
import br.pucminas.service.JwtService;
import br.pucminas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String auth(User user) throws PacienteException{
        Boolean correctCredentials = userService.authenticate(user.getUserName(), user.getPassWord());
        if (correctCredentials) {
            return jwtService.getToken(new JwtUser(user.getUserName(), user.getPassWord()));
        }
        throw new PacienteUnauthorizedException();
    }
}
