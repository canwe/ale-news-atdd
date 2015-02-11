package alenews.content.acquisition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContentReaderSelectorTest {

    @Test
    public void selectReader() {
        ContentReaderSelector contentReaderSelector = new ContentReaderSelector() ;

        assertThat(contentReaderSelector.selectReader(ContentType.HTML), is(instanceOf(HTMLContentReader.class))) ;
    }

}
