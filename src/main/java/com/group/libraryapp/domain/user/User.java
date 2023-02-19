package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    private String name;
    private Integer age;


    @OneToMany(mappedBy = "user")
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();


    protected User()
    {

    }


    public User(String name, Integer age) {

        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다."));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }


    public void updateName(String name)
    {
        this.name=name;
    }


    public void loanBook(String bookName)
    {
        this.userLoanHistories.add(new UserLoanHistory(this,bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream().filter(history -> history.getBookName().equals(bookName)).findFirst().orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }
}
