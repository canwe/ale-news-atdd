CREATE TABLE categories (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE content_category (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  content_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,

  FOREIGN KEY (content_id) REFERENCES content (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
);
