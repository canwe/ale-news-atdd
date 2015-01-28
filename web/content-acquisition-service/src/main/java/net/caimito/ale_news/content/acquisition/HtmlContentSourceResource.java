package net.caimito.ale_news.content.acquisition;

public class HtmlContentSourceResource {
    private ContentSourceType sourceType;
    private String location;
    private boolean active;
    private String sourceId;

    public void setSourceType(ContentSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public ContentSourceType getSourceType() {
        return sourceType;
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
