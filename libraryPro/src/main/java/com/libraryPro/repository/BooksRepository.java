package com.libraryPro.repository;

import com.libraryPro.entity.Books;
import com.libraryPro.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {
//    @Query("Select b from Book b where b.book_name LIKE %?1%"
//            + "OR b.isbn LIKE %?1%"
//            + "OR b.serial_name LIKE %?1%"
//            +"OR b.books_author LIKE %?1%")
//    List<Books> findByName(String keyword);
@Query(value="SELECT * FROM books a WHERE a.borrowed_by = ?1",nativeQuery = true)

    public List<Books> findByUserId(String userId);
}
