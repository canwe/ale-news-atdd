CREATE TABLE content_content (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  origin_content_id BIGINT NOT NULL,
  target_content_id BIGINT NOT NULL,

  FOREIGN KEY (origin_content_id) REFERENCES content (id),
  FOREIGN KEY (target_content_id) REFERENCES content (id)
);
