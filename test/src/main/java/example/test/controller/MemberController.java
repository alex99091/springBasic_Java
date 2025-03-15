package example.test.controller;

import example.test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 해당 어노테이션(Autowired)을 통해서 스프링 컨테이너에있는 memberservice와 연결을 시켜줌
}
