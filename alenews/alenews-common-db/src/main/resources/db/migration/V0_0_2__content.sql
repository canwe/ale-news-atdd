CREATE TABLE content (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  content_type varchar(255) not null,
  title text not null,
  language varchar(255),
  source_location VARCHAR(255),
  published_date DATETIME,
  author VARCHAR(255),
  body text,
  json TEXT
) ;

CREATE TABLE outgoing_link (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  url VARCHAR(255)
) ;

CREATE TABLE outgoing_link_content (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  outgoing_link_id BIGINT,
  content_id BIGINT
) ;

insert into content_source (content_type, location, active) values ('RSS', 'http://www.stephan-schwab.com/rss.xml', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://agilitystreet.com/feed', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://aviation.saketbivalkar.com/?feed=rss2', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://blog.coachcamp.org/feed/', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://blog.omegazadvisors.com/feed/', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://blog.risingtideharbor.com/feeds/posts/default', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://brodzinski.com/feed', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://cognitive-edge.com/blog/rss', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://cupofjava.de/atom.xml', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://deutsche-startups.feedsportal.com/c/32923/f/531000/index.rss', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://feeds.feedburner.com/MichaelSahotaAgilitrix', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://feeds.feedburner.com/SoftwareProjectManagement', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://newtechusa.net/feed/', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://raibledesigns.com/rd/feed/entries/atom', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://rgalen.com/agile-training-news?format=RSS', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://ronjeffries.com/feed.xml', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://saketbivalkar.com/feed/', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://scottberkun.com/feed/', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://www.infoq.com/feed/process-practices', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://www.kanbanway.com/feed', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://www.martinfowler.com/feed.atom', true);
insert into content_source (content_type, location, active) values ('RSS', 'http://xprogramming.com/feed/atom/', true);
