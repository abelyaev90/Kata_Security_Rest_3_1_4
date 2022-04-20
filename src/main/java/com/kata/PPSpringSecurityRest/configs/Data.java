package com.kata.PPSpringSecurityRest.configs;

import com.kata.PPSpringSecurityRest.entitys.Role;
import com.kata.PPSpringSecurityRest.entitys.User;
import com.kata.PPSpringSecurityRest.service.RoleService;
import com.kata.PPSpringSecurityRest.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Data {

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public Data(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void init() {
        Role roleAdmin = new Role(1L,"ROLE_ADMIN");
        Role roleUser = new Role(2L,"ROLE_USER");

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        User userIvan = new User("ivan","ivanov", "20", "ivan@mail.ru", "123");
        userIvan.setUserPassword(passwordEncoder.encode("123"));

        User userAlex = new User("alex", "alexeev", "10","iaaa@mail.ru", "123");
        userAlex.setUserPassword(passwordEncoder.encode("123"));

        User userTom = new User("tom", "tomov", "11","iaaa@mail.ru", "123");
        userAlex.setUserPassword(passwordEncoder.encode("123"));

        userService.addUser(userIvan, new String[]{"ROLE_ADMIN", "ROLE_USER"});
        userService.addUser(userAlex, new String[] {"ROLE_USER"});
        userService.addUser(userTom, new String[] {"ROLE_USER"});
    }
}
