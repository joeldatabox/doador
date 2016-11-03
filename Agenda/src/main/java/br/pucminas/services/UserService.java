package br.pucminas.services;

import br.pucminas.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by master on 03/11/16.
 */
@Service
public class UserService {
    private static Map<String, User> users = new HashMap();

    static {
        User user = new User();
        user.setUserName("user1");
        user.setPassWord("123");
        user.setEmail("user1@usermail.com");
        user.setRole(User.ROLE_ADMIN);
        user.setActivated(true);
        users.put("user1", user);
    }

    public User findUserByUserName(String userName) {
        return users.get(userName);
    }

    public Boolean authenticate(String userName, String passWord) {
        User user = findUserByUserName(userName);
        if (user != null) {
            return user.getPassWord().equals(passWord);
        }
        return false;
    }
}
