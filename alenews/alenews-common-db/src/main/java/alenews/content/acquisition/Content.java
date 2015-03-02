package alenews.content.acquisition;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Content {
    private String description;
    private String language ;
    private URL sourceLocation;
    private String title ;
    private Date publishedDate ;
    private String author ;
    private List<String> categories ;
    private List<URL> discussionLinks;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE) ;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj) ;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 5, this) ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSourceLocation(URL location) {
        this.sourceLocation = location;
    }

    public URL getSourceLocation() {
        return sourceLocation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getCategories() {
        if (categories == null)
            categories = new ArrayList<>() ;
        
        return categories;
    }

    public void addCategory(String category) {
        if (categories == null)
            categories = new ArrayList<String>() ;

        categories.add(category) ;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void addDiscussionLink(URL link) {
        if (discussionLinks == null)
            discussionLinks = new ArrayList<URL>() ;

        discussionLinks.add(link);
    }

    public List<URL> getDiscussionLinks() {
        if (discussionLinks == null)
            discussionLinks = new ArrayList<URL>() ;

        return discussionLinks;
    }
}