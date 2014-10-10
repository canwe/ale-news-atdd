package net.caimito.ale_news.article_service;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ArticleId {

	private UUID id ;
	
	public ArticleId(String id) {
		this.id = UUID.fromString(id) ;
	}
	
	private ArticleId() {
	}
	
	public String getId() {
		return id.toString();
	}
	
	public void setId(String id) {
		this.id = UUID.fromString(id);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj) ;
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this) ;
	}

	public static ArticleId generate() {
		return new ArticleId(UUID.randomUUID().toString());
	}

}
