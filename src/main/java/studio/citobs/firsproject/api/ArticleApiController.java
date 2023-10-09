package studio.citobs.firsproject.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.citobs.firsproject.dto.ArticleForm;
import studio.citobs.firsproject.entity.Article;
import studio.citobs.firsproject.repository.ArticleRepository;
import studio.citobs.firsproject.service.ArticleService;

import java.util.List;

@Slf4j
@RestController //restapi 용 컨트롤러 데이터를 json형태로 반환
public class ArticleApiController {

    //트랜잭션만들기
    @Autowired
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

        //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

        //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        //1.수정용 엔티티 생성
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
   }


    //트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

//    @Autowired
//    private ArticleRepository articleReository;
//
//    //GET
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//
//        return articleReository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable Long id) {
//        return articleReository.findById(id).orElse(null);
//    }
//
//
//    //POST
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleReository.save(article);
//    }
//
//
//    //PATCH
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        //1.수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article:{}", id, article.toString());
//
//        //2. 대상 엔티티를 조회
//        Article target = articleReository.findById(id).orElse(null);
//
//
//        //3. 잘못된 요청처리(대상이 없거나, id가 다른 경우)
//        if(target == null || id != article.getId()) {
//            //400, 잘못된 경우
//            log.info("잘못된 요청 id: {}, article:{}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//
//        //4. 업데이트 및 정상 응답(200)
//        target.patch(article);
//        Article updated = articleReository.save(article);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//
//    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        //대상찾기
//        Article target = articleReository.findById(id).orElse(null);
//
//        //잘못된 요청 처리
//        if(target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //대상삭제
//        articleReository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//   }
//
//}
