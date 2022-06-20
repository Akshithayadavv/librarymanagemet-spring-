package com.libraryPro.service;

import com.libraryPro.entity.Users;
import com.libraryPro.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UsersServiceImpl implements UsersService {
    final UsersRepository usersRepository;
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(int theId) {
        Optional<Users> result = usersRepository.findById(theId);
        Users theUsers = null;
        if (result.isPresent()) {
            theUsers = result.get();
        }
        else {
            // we didn't find the book
            throw new RuntimeException("Did not find users id - " + theId);
        }
        return theUsers;
    }

    @Override
    public void save(Users theUsers) {
        usersRepository.save(theUsers);
    }

    @Override
    public void deleteById(int theId) {
        usersRepository.deleteById(theId);
    }


    @Override
    public Optional<Users> getUsers(Integer usersId) {
        return usersRepository.findById(usersId);
    }
}
