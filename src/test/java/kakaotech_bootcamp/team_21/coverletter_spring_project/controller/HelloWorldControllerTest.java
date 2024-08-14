package kakaotech_bootcamp.team_21.coverletter_spring_project.controller;

import kakaotech_bootcamp.team_21.coverletter_spring_project.service.HelloWorldService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloWorldControllerTest {

    @Autowired
    private HelloWorldService helloWorldService;

    @Test
    public void HelloWorldTest() throws Exception {

        //given(전제 상황)
        int a=10;
        int b=5;
        helloWorldService.helloTest();
        //when(특정 상황 이라면?)
        b=a;
        //then(다음 결과를 만족해야한다.)
        Assertions.assertThat(b).isEqualTo(10);

    }

}