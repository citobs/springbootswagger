package studio.citobs.firsproject.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import studio.citobs.firsproject.dto.ArticleForm;
import studio.citobs.firsproject.entity.Article;
import studio.citobs.firsproject.repository.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {


    @Autowired //스프링부투가 미리 생성해놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());
//        System.out.println(form.toString());

        //db에 저장

        //1.Dto를 변환 Entitity!
        Article article = form.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString()); --> loggin으로 대체

        //2. Repository에게 Entity를 DB안에 저장하게 함

        Article saved = articleRepository.save(article);
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        //1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //2. 가져온 데이터를 모델에 등록
        model.addAttribute("article",articleEntity);

        //3.보여줄 페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 article을 가져온다
        List<Article> aritcleEntityList = articleRepository.findAll();

        //2. 가져온 article 묶음을 뷰로 전달
        model.addAttribute("articleList",aritcleEntityList);

        //3. 뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터를 가져오기!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터를 등록
        model.addAttribute("article", articleEntity);

        // 뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        //1.DTO를 엔티티로 변환한다!
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());

        //2.엔티티를 DB로 저장한다.
        //2-1 : DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 기존데이터가 있다면 갑을 갱신한다.
          if(target != null){
              articleRepository.save(articleEntity); //엔티티가 DB로 갱신됨
          }

        //3. 수정 결과 페이지로 리다이렉트 한다.

        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public  String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔다.");

        //1. 삭제 대상을 꺼낸다
        Article target= articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        //2. 대상을 삭제한다.
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었다.");
        }
        //3. 결과 페이지로 리다이렉트 한다.

        return  "redirect:/articles";
    }
}
