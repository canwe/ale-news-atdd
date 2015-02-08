package alenews.content.acquisition;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ContentSource implements Serializable {
    private static final long serialVersionUID = 1L ;

    @Id
    @GeneratedValue
    private Long id ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean active;

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

}
