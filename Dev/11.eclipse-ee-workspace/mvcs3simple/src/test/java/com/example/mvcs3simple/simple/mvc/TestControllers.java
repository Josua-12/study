package com.example.mvcs3simple.simple.mvc;

import com.example.mvcs3simple.simple.mvc.controller.CalculatorController;
import com.example.mvcs3simple.simple.mvc.controller.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

// Servlet 없이 테스트
public class TestControllers {

    private static final Logger log = LoggerFactory.getLogger(TestControllers.class);

    public static void main(String[] args) {
        System.out.println("=== HelloController 테스트 ===");
        HelloController helloController = new HelloController();

        Map<String, String> paramMap1 = new HashMap<>();
        paramMap1.put("name", "이순신");

        ModelView mv1 = helloController.process(paramMap1);
        System.out.println("View 이름 : " + mv1.getViewName());
        System.out.println("Model 데이터 : " + mv1.getModel());

        System.out.println("=== CalculatorController 테스트 ===");
        CalculatorController calculatorController = new CalculatorController();

        Map<String, String> paramMap2 = new HashMap<>();
        paramMap2.put("num1", "10");
        paramMap2.put("num2", "20");

        ModelView mv2 =  calculatorController.process(paramMap2);
        System.out.println("View 이름 : " + mv2.getViewName());
        System.out.println("Model 데이터 : " + mv2.getModel());
    }
}
