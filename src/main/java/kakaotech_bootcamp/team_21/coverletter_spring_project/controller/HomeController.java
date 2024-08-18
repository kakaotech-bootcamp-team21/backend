package kakaotech_bootcamp.team_21.coverletter_spring_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() { return "index";} //기본 홈 페이지

}
