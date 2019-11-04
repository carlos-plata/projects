create table if not exists reviews(
   review_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(40) NOT NULL,
   description VARCHAR(100) NOT NULL,
   product_id INT,
   CONSTRAINT FK_ProductReview FOREIGN KEY (product_id) REFERENCES products(product_id)
);