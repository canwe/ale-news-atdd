package alenews.content.acquisition;

import java.net.URL;

public class FakeContentReader implements ContentReader {
    @Override
    public Content read(URL location) {
        return new Content();
    }
}
