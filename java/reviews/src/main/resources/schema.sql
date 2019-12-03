create table if not exists products(
   product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(40) NOT NULL,
   description VARCHAR(100) NOT NULL
);

create table if not exists reviews(
   review_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(40) NOT NULL,
   description VARCHAR(100) NOT NULL,
   product_id INT,
   CONSTRAINT FK_ProductReview FOREIGN KEY (product_id) REFERENCES products(product_id)
);

create table if not exists comments(
   comment_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(40) NOT NULL,
   description VARCHAR(100) NOT NULL,
   review_id INT,
   CONSTRAINT FK_ReviewComment FOREIGN KEY (review_id) REFERENCES reviews(review_id)
);