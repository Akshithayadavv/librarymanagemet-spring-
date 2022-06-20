package com.libraryPro.service;

import com.libraryPro.entity.Books;
import com.libraryPro.entity.Users;
import com.libraryPro.repository.BooksRepository;
import com.libraryPro.repository.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsersServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    public void whenFindById_thenReturnUser() {
        // given
        Users user = new Users(1, "Harry", "qwer", "STUDENT");
        Books book = new Books(1, "Harry Potter", user);
        entityManager.flush();
        entityManager.persist(book);
        entityManager.flush();

        // when

        Optional<Users> found = usersRepository.findById(user.getId());

        // then
        assertThat(found.get().getId())
                .isEqualTo(user.getId());
    }


}
