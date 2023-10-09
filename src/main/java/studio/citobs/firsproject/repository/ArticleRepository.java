package studio.citobs.firsproject.repository;

import org.springframework.data.repository.CrudRepository;
import studio.citobs.firsproject.entity.Article;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
}
