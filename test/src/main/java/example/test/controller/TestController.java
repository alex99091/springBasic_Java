package example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

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

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        Map<String, Object> attributeName = new HashMap<>(); // 변수 선언 및 초기화
        attributeName.put("name", name);
        model.addAllAttributes(attributeName);
        return "hello-template";
    }
    /*
    동작 과정
    1. 웹 브라우저 요청
        - 사용자가 localhost:8080/hello-static.html 요청을 보냄
    2. 스프링 컨테이너 처리
        - 스프링 컨테이너는 컨트롤러에서 처리할 수 있는 요청인지 확인
        - 관련 컨트롤러가 없으면 다음 단계로 이동
    3. 정적 파일 서빙
        - 요청된 파일이 /resources/static/hello-static.html에 존재하면, 내장 톰캣 서버가 직접 해당 파일을 반환
        - 스프링 컨테이너를 거치지 않고 정적 리소스(static) 경로에서 바로 제공
    4. 웹 브라우저 응답
        - 클라이언트(웹 브라우저)가 hello-static.html 파일을 받아 화면에 표시
    */

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        return "hello " + name; // "hello string"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    /* @ResponseBody 사용 원리 정리
    1. 개요
    @ResponseBody는 HTTP 응답의 BODY에 데이터를 직접 반환하는 어노테이션이다.
    Spring Boot 환경에서 컨트롤러의 메서드가 반환하는 데이터를 JSON 또는 문자열 형태로 변환하여 클라이언트에 전달한다.

    2. 동작 방식
    1)웹 브라우저 요청
        클라이언트가 localhost:8080/hello-api에 HTTP 요청을 보냄.
    2)Spring Boot 컨테이너 처리
        helloController에서 요청을 처리하고, @ResponseBody가 적용된 메서드가 실행됨.
        예제에서 return: hello(name: spring) 형태의 데이터를 반환함.
    3) HttpMessageConverter 적용
        viewResolver 대신 HttpMessageConverter가 동작하여 데이터를 변환.
        반환 데이터의 타입에 따라 적절한 Converter가 사용됨.
        문자 데이터: StringHttpMessageConverter
        객체 데이터: MappingJackson2HttpMessageConverter (JSON 변환)
        기타 데이터: byte 처리 등 다양한 HttpMessageConverter가 기본적으로 등록되어 있음.
    4) 웹 브라우저 응답 수신
        변환된 데이터가 HTTP 응답 BODY에 담겨 클라이언트로 반환됨.
        예제의 경우 {name: spring} 형태의 JSON 응답이 반환됨.
    3. 핵심 요약
        @ResponseBody를 사용하면 view 대신 데이터 자체를 응답한다.
        HttpMessageConverter가 자동으로 데이터 변환을 수행한다.
        기본적으로 문자열 및 JSON 변환이 제공되며, 필요 시 다른 변환기도 활용 가능하다. */
}
