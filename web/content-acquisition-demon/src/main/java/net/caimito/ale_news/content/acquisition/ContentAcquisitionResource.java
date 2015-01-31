package net.caimito.ale_news.content.acquisition;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("source")
public class ContentAcquisitionResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ContentSourceDescription createContentSource(ContentSourceDescription source) {
        source.setSourceId("SOURCE_ID");
        return source ;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{sourceId}/active")
    public ContentSourceDescription modifyContentSource(@PathParam("sourceId") String sourceId) {
        ContentSourceDescription source = new ContentSourceDescription() ;
        source.setSourceId(sourceId);
        source.setActive(true);

        return source ;
    }

}
