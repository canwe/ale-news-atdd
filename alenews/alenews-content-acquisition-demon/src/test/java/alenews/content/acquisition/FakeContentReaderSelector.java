package alenews.content.acquisition;

public class FakeContentReaderSelector extends ContentReaderSelector {

    @Override
    public ContentReader selectReader(ContentType contentType) {
        return new FakeContentReader() ;
    }
}
