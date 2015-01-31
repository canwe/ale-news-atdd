package net.caimito.ale_news.content.acquisition;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Content {
    private ContentType contentType;
    private String body;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this) ;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
