package kakaotech_bootcamp.team_21.coverletter_spring_project.controller.api;

import kakaotech_bootcamp.team_21.coverletter_spring_project.domain.testDomain.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldApiController {

    private MessageSource messageSource;

    public HelloWorldApiController(MessageSource messageSource) {
        this.messageSource=messageSource;
    }

    // GET
    // URI - /hello-world
    // @RequestMapping(method-RequestMethod.GET, path="/hello-world")
    @GetMapping(path="/api/hello-world")
    public String helloworld() {
        return "Hello World";
    }

    @GetMapping(path="/api/hello-world-bean")
    public HelloWorldBean helloworldBean() {
        return new HelloWorldBean("Hello World!");
    }

    @GetMapping(path="/api/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloworldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

}


