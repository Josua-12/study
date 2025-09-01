package com.example.mvcs3simple.simple.mvc.controller;

import com.example.mvcs3simple.simple.mvc.Controller;
import com.example.mvcs3simple.simple.mvc.ModelView;

import java.util.Map;

// 실제 컨트롤러
public class HelloController implements Controller {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // 1. 파라미터에서 데이터 추출 (Servlet API 없이)
        String name = paramMap.get("name");

        // 2. 비즈니스 로직
        String message = "안녕하세요, " + name + "님!";

        // 3. ModelView 생성 (논리적 view 이름만 사용)
        ModelView mv = new ModelView("hello");

        // 4. Model에 데이터 추가
        mv.getModel().put("message", message);
        mv.getModel().put("name", name);

        return mv;
    }
}
