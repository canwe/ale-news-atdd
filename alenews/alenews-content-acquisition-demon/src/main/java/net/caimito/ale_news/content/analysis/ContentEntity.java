package net.caimito.ale_news.content.analysis;

import net.caimito.ale_news.content.acquisition.ContentType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "content")
public class ContentEntity implements Serializable {
    private static final long serialVersionUID = 1L ;

    @Id
    @GeneratedValue
    private Long id ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String title ;

    private String language ;

    private String sourceLocation ;

    private Date publishedDate ;

    private String author ;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String body ;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String json ;


    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj) ;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 15, this) ;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
