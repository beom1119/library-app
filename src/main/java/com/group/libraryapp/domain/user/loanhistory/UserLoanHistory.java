package com.group.libraryapp.domain.user.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;


    @ManyToOne // 내가 다수이고 너가 한 개이다.
    private User user;

    @Column
    private String bookName;

    @Column
    private boolean isReturn;

    protected UserLoanHistory()
    {

    }

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName; ////cascade
        this.isReturn = false;
    }

    public void doReturn()
    {
        this.isReturn = true;
    }


    public String getBookName() {
        return bookName;
    }
}
