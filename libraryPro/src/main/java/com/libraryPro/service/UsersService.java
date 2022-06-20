package com.libraryPro.service;

//import com.libraryPro.entity.Author;

import com.libraryPro.entity.Users;
import com.libraryPro.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public List<Users> findAll();

    public Users findById(int theId);

    public void save(Users theUsers);

    public void deleteById(int theId);


    Optional<Users> getUsers(Integer usersId);
}
