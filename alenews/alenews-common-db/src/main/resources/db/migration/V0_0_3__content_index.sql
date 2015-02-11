DROP TABLE outgoing_link ;
DROP TABLE outgoing_link_content ;

ALTER TABLE content MODIFY COLUMN source_location VARCHAR(255) NOT NULL  ;
create unique index source_location_idx on content(source_location);
