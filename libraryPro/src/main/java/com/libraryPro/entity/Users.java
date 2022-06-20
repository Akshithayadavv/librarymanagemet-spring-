package com.libraryPro.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="authority")
    private String authority;



    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "users",
            cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Books> books;

    public Users(){

    }

    public Users(int id, String username, String password, String authority) {
        // this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Users(int id, String username, String password, String authority, List<Books> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.books = books;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
        //System.out.println("in setter" + authority);
    }

    public String getAuthority() {
       // System.out.println("in getter" + authority);
        return authority;
    }



    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }


    public boolean hasRole(String authority) {

        System.out.println(authority);


        String role2 = "ROLE_ADMIN";
        if (role2.equals(authority)){
            System.out.println(authority);
            System.out.println("Ji");
            return true;
        }
       System.out.println(authority);
        return false;
    }

    @Override
    public String toString() {
        return    id + ":" + username;
    }
}
