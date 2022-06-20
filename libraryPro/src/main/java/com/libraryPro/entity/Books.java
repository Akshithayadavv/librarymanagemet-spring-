package com.libraryPro.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;


@Entity
@Table(name="books")
public class Books {

    // define fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

//    @Column(name="borrowed_by")
//    private int borrowed_by;


    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name="borrowed_by")
    private Users users;
    // define constructors

    public Books() {

    }

    public Books(int id, String name, Users users) {
        // this.id = id;
        this.name = name;
        this.users = users;
    }

    public Books(int id, String name) {
        this.id = id;
        this.name = name;
    }


    // define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getBorrowed_by() {
//        return borrowed_by;
//    }
//
//    public void setBorrowed_by(int borrowed_by) {
//        this.borrowed_by = borrowed_by;
//    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
// define tostring


    @Override
    public String toString() {
        return "name" + name;
    }
}










