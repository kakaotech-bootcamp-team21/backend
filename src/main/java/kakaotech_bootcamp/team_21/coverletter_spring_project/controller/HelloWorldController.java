package kakaotech_bootcamp.team_21.coverletter_spring_project.controller;

import kakaotech_bootcamp.team_21.coverletter_spring_project.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping("/")
    public String home() {
        return "index"; //기본 홈 페이지
    }


    @GetMapping(path="/hello-world")
    @ResponseBody//
    public String helloworld() {
        helloWorldService.helloTest();
        return "Hello World Welcome!";//Test API
    }
}
