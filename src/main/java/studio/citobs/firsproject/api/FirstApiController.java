package studio.citobs.firsproject.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Restapi용 컨트롤러 json을 반환함
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello world";
    }
}
