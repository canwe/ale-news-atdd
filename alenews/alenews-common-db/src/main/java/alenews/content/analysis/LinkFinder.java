package alenews.content.analysis;

import alenews.content.acquisition.Content;

public interface LinkFinder {

    Content findDiscussionLinks(Content content);

}