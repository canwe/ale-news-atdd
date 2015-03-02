package alenews.content.analysis;

import alenews.content.acquisition.Content;

import java.net.URL;
import java.util.List;

public interface LinkFinder {

    List<URL> findDiscussionLinks(URL sourceLocation, String sourceTitle);

}
