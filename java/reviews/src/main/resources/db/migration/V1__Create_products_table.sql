create table if not exists products(
   product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(40) NOT NULL,
   description VARCHAR(100) NOT NULL
);