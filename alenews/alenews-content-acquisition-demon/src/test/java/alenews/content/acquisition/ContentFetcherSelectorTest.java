package alenews.content.acquisition;

import alenews.content.analysis.LinkFinder;
import alenews.content.db.ContentService;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ContentFetcherSelectorTest {

    @Test
    public void selectReader() {
        ContentService contentService = mock(ContentService.class) ;

        ContentFetcherSelector contentFetcherSelector = new ContentFetcherSelector(contentService) ;

        assertThat(contentFetcherSelector.selectFetcher(ContentSourceType.RSS), is(instanceOf(RSSContentFetcher.class))) ;
    }

}
