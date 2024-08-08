package kakaotech_bootcamp.team_21.coverletter_spring_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String home() {
        return "index"; //기본 홈 페이지
    }


    @GetMapping(path="/hello-world")
    @ResponseBody//
    public String helloworld() {
        return "Hello World!!!";//Test API
    }
}
