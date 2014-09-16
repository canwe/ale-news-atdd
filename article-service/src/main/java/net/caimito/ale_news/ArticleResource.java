package net.caimito.ale_news;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.caimito.ale_news.article_service.ArticleMetadata;

@Path("article")
public class ArticleResource {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public ArticleMetadata getArticle() {
		ArticleMetadata m = new ArticleMetadata() ;
		m.author = "Stephan" ;
		m.location = "http://localhost" ;
		m.title = "Something" ;
		return m;
	}
	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getHello() {
        return "Hello World" ;
    }

}
