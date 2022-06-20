package com.libraryPro.config;

import com.libraryPro.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UsersUserDetails implements UserDetails {

    private Users users;
    public UsersUserDetails(Users users) {
        this.users = users;
    }

    public boolean hasRole(String authority) {

           System.out.println("Hello");
        String role=users.getAuthority();

       System.out.println(role);

        if(role.equals("ROLE_ADMIN"))
        return true;
        return false;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String getUsername() {
        return users.getUsername();
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
}

