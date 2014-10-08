package net.caimito.ale_news;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.caimito.ale_news.article_service.ArticleId;
import net.caimito.ale_news.article_service.ArticleMetadata;
import org.springframework.beans.factory.annotation.Autowired;

@Path("article")
public class ArticleResource {

    @Autowired
	private ArticleStorage articleStorage ;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{articleId}")
	public ArticleMetadata getArticle(@PathParam("articleId") String articleId) {
		return articleStorage.getStorage().get(articleId) ;
	}
	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ArticleId storeArticle(ArticleMetadata metadata) {
    	ArticleId id = ArticleId.generate() ;
        metadata.setId(id.getId()) ;
        articleStorage.getStorage().put(id.getId(), metadata) ;

        return id ;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	@Path("/{articleId}")
    public ArticleId updateArticle(@PathParam("articleId") String articleId, ArticleMetadata metadata) {
        ArticleMetadata existingArticle = articleStorage.getStorage().get(articleId) ;

        existingArticle.setAuthor(metadata.getAuthor()) ;
        existingArticle.setLocation(metadata.getLocation());
        existingArticle.setTitle(metadata.getTitle());

    	return new ArticleId(existingArticle.getId()) ;
    }
    
    @DELETE
	@Path("/{articleId}")
    public void deleteArticle(@PathParam("articleId") String articleId) {
        articleStorage.getStorage().remove(articleId) ;
    }

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<ArticleMetadata> getAllArticles() {
		return new ArrayList<ArticleMetadata>(articleStorage.getStorage().values()) ;
	}


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/fakeCreate")
    public ArticleId fakeStoreArticle(ArticleMetadata metadata) {
        articleStorage.getStorage().put(metadata.getId(), metadata) ;
        return new ArticleId(metadata.getId()) ;
    }

}
