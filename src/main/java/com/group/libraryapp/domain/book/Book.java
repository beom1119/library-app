package com.group.libraryapp.domain.book;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;


    protected Book()
    {

    }


    public Book(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("잘못된 이름이 들어왔습니다.");
        }
       this.name = name;
    }

}
