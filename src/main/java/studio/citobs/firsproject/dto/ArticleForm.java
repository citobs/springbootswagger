package studio.citobs.firsproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import studio.citobs.firsproject.entity.Article;


@ToString
@AllArgsConstructor
//data를 받아올 그릇
public class ArticleForm {

    private long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
