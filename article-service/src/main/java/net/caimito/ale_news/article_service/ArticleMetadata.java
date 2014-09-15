package net.caimito.ale_news.article_service;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ArticleMetadata {

	public String author;
	public String title;
	public String location;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this) ;
	}
}
