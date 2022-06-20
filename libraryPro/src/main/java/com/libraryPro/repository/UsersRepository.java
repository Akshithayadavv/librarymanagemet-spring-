package com.libraryPro.repository;


import com.libraryPro.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsersRepository extends JpaRepository<Users,Integer> {
    //no need to any code

  @Query(value="SELECT * FROM users a WHERE a.username = ?1",nativeQuery = true)
    public Users findByName(String userName);
}
