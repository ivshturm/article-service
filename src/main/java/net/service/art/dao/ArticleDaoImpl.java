package net.service.art.dao;

import net.service.art.model.Article;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ivan.
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createArticle(Article article) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(article);
    }

    @Override
    public void updateArticle(Article article) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(article);
    }

    @Override
    public void removeArticle(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Article article = (Article) session.load(Article.class, new Integer(id));

        if (article != null) {
            session.delete(article);
        }
    }

    @Override
    public Article getArticleById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Article article = (Article) session.get(Article.class, new Integer(id));
        return article;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> listArticles() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Article> articleList = session.createQuery("from Article").list();
        return articleList;
    }
}
