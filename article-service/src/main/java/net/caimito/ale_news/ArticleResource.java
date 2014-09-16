package net.caimito.ale_news;

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

@Path("article")
public class ArticleResource {

	// todo: fake
	private static ArticleMetadata fakeArticle ;
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{articleId}")
	public ArticleMetadata getArticle(@PathParam("articleId") String articleId) {
		return fakeArticle ;
	}
	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ArticleId storeArticle(ArticleMetadata metadata) {
    	ArticleId id = ArticleId.generate() ;
    	fakeArticle = metadata ;
        fakeArticle.setId(id.getId()) ;
        return id ;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	@Path("/{articleId}")
    public ArticleId updateArticle(@PathParam("articleId") String articleId, ArticleMetadata metadata) {
    	fakeArticle.setAuthor(metadata.getAuthor()) ;
    	fakeArticle.setLocation(metadata.getLocation());
    	fakeArticle.setTitle(metadata.getTitle());

    	return new ArticleId(fakeArticle.getId()) ;
    }
    
    @DELETE
	@Path("/{articleId}")
    public void deleteArticle(@PathParam("articleId") String articleId) {
    	fakeArticle = null ;
    }
    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/fakeCreate")
    public ArticleId fakeStoreArticle(ArticleMetadata metadata) {
    	fakeArticle = metadata ;
        return new ArticleId(fakeArticle.getId()) ;
    }

}
