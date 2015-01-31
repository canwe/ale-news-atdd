package net.caimito.ale_news.content.acquisition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContentReaderSelectorTest {

    @Test
    public void selectReader() {
        assertThat(ContentReaderSelector.selectReader(ContentType.HTML), is(instanceOf(HTMLContentReader.class))) ;
    }

}
