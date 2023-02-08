package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import com.group.libraryapp.dto.calculator.request.CalculatorMultiplyRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 진입지정으로 만든다.
public class CalculatorController {

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request)
    {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") //Post Multiply
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) ///J
    {
        return  request.getNumber1()*request.getNumber2();
    }


}
