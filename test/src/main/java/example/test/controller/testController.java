package example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class testController {

    @GetMapping("test")
    public String test(Model model) {
        Map<String, Object> attributes = new HashMap<>(); // 변수 선언 및 초기화
        attributes.put("data", "hello!");
        model.addAllAttributes(attributes);
        return "test";
    }
    /*
    요청 처리 흐름
    1. 웹 브라우저 → 서버 요청
        사용자가 localhost:8080/hello URL로 요청을 보냄.
    2. 스프링 컨트롤러 처리
        helloController가 요청을 받아 return: hello를 반환하고, model(data: hello!!)을 추가.
    3. 뷰 리졸버(ViewResolver) 처리
        컨트롤러가 반환한 hello라는 문자열을 뷰 리졸버가 처리하여 템플릿 파일(test.html)을 찾음.
        기본적으로 resources/templates/test.html 경로에서 해당 파일을 찾음.
    4. 템플릿 엔진 렌더링
        Thymeleaf(템플릿 엔진)가 hello.html을 변환하여 최종 HTML을 생성.
    5. 응답 반환
        변환된 HTML이 웹 브라우저에 전달되어 화면에 표시됨.

    요약: 요청 → 컨트롤러 처리 → 뷰 리졸버 → Thymeleaf 렌더링 → 최종 HTML 응답
     */
}
