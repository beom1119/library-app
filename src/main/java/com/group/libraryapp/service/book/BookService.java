package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {


  private final BookRepository bookRepository;
  private final UserLoanHistoryRepository userLoanHistoryRepository;

  private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest bookCreateRequest)
    {

        bookRepository.save(new Book(bookCreateRequest.getName()));
    }


    @Transactional
    public void loanBook(BookLoanRequest request)
    {
        //1. 책 정보를 가져온다.
        bookRepository.findByName(request.getBookName()).orElseThrow(IllegalArgumentException::new);
        //2. 대출 중인 책인지 아닌지 확인
        //3. 만약에 확인했는데 대출 중이라면 예외를 발생시킵니다.
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(request.getBookName(),false))
        {
            throw new IllegalArgumentException("이미 대출되어 있는 책입니다.");
        }
        //유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        user.loanBook(request.getBookName());






    }

    @Transactional
    public void returnBook(BookLoanRequest request) {
        User user = userRepository.findByName(request.getUserName()).orElseThrow(IllegalArgumentException::new);
        UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId(),request.getBookName()).orElseThrow(IllegalArgumentException::new);
        history.doReturn();
    }
}



