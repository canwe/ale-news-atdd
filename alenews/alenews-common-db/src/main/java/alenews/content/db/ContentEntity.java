package alenews.content.db;

import alenews.content.acquisition.Content;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "content")
public class ContentEntity implements Serializable {
    private static final long serialVersionUID = 1L ;

    @Id
    @GeneratedValue
    protected Long id ;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String title ;

    private String language ;

    @Column(nullable = false, unique = true)
    private String sourceLocation ;

    private Date publishedDate ;

    private String author ;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description ;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "content_category", joinColumns = { @JoinColumn(name = "content_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<CategoryEntity> categories = new HashSet<>();

    public ContentEntity() {}

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj) ;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(17, 15, this) ;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

}
