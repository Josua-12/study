package com.example.mvcs3simple.simple.mvc;

import java.util.HashMap;
import java.util.Map;

/*
    Model과 View 정보를 함께 담는 객체
 */
public class ModelView {
    private String viewName;       // 논리 view 이름 (예: "hello")
    private Map<String, Object> model = new HashMap<>();   // 데이터 저장소


    public ModelView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, Object> getModel() {
    return model;
    }
}
