CREATE TABLE content_source (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  content_type VARCHAR(255) not null,
  location VARCHAR(255) not null,
  active BIT not null
);

insert into content_source (content_type, location, active) values ('RSS', 'http://www.trustartist.com/feed/', true);
