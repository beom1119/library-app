package com.group.libraryapp.domain.user.loanhistory;

import javax.persistence.*;

@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column
    private long userId;

    @Column
    private String bookName;

    @Column
    private boolean isReturn;

    protected UserLoanHistory()
    {

    }

    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn()
    {
        this.isReturn = true;
    }
}
