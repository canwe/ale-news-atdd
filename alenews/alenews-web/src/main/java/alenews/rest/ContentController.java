package alenews.rest;

import alenews.content.acquisition.Content;
import alenews.content.db.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService ;
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public List<Content> listContent() {
        return contentService.findAll() ;
    }

}
