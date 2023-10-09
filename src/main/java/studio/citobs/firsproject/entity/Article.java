package studio.citobs.firsproject.entity;


import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;

import javax.persistence.*;

@Entity //DB가 해당 객체를 인식하게
@ToString
@AllArgsConstructor
@NoArgsConstructor //디폴트 생성자 추가
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 ID값을 자동으로 겹치지않게!
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}