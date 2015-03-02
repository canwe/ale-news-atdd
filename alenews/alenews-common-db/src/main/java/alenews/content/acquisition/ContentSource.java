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
    private ContentSourceType contentSourceType;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private boolean active;

    public void setContentSourceType(ContentSourceType contentSourceType) {
        this.contentSourceType = contentSourceType;
    }

    public ContentSourceType getContentSourceType() {
        return contentSourceType;
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
