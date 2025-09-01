package com.example.mvcs3simple.simple.mvc;

import java.util.Map;
import java.util.Objects;

// 컨트롤러 인터페이스
public interface Controller {
    // 파라미터를 Map으로 받고, ModelView를 반환
    ModelView process(Map<String, String> paramMap);
}
