package com.example.mvcs3simple.simple.mvc.controller;

import com.example.mvcs3simple.simple.mvc.Controller;
import com.example.mvcs3simple.simple.mvc.ModelView;

import java.util.Map;

// 또 다른 컨트롤러
public class CalculatorController implements Controller {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // Servlet API 없이 순수 Java로만 구현
        String num1Str = paramMap.get("num1");
        String num2Str = paramMap.get("num2");

        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);
        int result = num1 + num2;

        ModelView mv = new ModelView("caculator");
        mv.getModel().put("num1", num1);
        mv.getModel().put("num2", num2);
        mv.getModel().put("result", result);

        return mv;
    }
}
