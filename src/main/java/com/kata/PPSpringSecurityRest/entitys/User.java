package com.kata.PPSpringSecurityRest.entitys;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "usertable")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "userlastname")
    private String userLastName;

    @Column(name = "userage")
    private String userAge;

    @Column(name = "useremail")
    private String userEmail;

    @Column(name = "userpassword")
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public void addRoleToUser(Role role) {
        if (roles == null) {
            roles = new HashSet<>();
        }
        roles.add(role);
    }

    public User() {
    }

        public User(String userName, String userLastName, String userAge, String userEmail, String userPassword) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public User(String userName, String userLastName, String userAge, String userEmail, String userPassword, Set<Role> roles) {
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public String getRolesString() {
        StringBuilder str = new StringBuilder();
        for (Role role : roles) {
            str.append(role.getRoleName());
            str.append(" ");
        }
        return (str.length() > 0) ? str.deleteCharAt(str.length() - 1).toString()
                : "";
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserAge() {
        return userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }
}
