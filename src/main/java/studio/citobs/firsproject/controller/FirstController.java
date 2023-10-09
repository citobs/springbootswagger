package studio.citobs.firsproject.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller // 콘트롤러 설정
public class FirstController {
    //mustach 연결

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","ds");
        return "greetings"; //templatefile 찾아준다 자동으로
    }

    @GetMapping("/bye")
    public  String SeeYouNext(Model model){
        model.addAttribute("username","ci");
        return "goodbye";
    }
}
