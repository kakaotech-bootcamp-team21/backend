package kakaotech_bootcamp.team_21.coverletter_spring_project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    private static final Logger logger= LoggerFactory.getLogger(HelloWorldService.class);

    public void helloTest() {
        logger.info("This is a info message");
        logger.debug("This is a debug message");
        logger.error("This is a error message");
    }
}
