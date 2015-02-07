package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.Content;

public interface LinkFinder {

    Content findOutgoingLinks(Content content);

}
