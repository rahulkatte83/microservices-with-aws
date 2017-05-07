USE desichef;

CREATE TABLE IF NOT EXISTS desichef.users (
  id INT NOT NULL AUTO_INCREMENT,  
  address VARCHAR(40),
  city VARCHAR(40),
  name VARCHAR(40),
  phone_no VARCHAR(12),
  PRIMARY KEY (id));
  
CREATE TABLE IF NOT EXISTS desichef.registration (
  id INT NOT NULL AUTO_INCREMENT,
  item VARCHAR(30),
  user_id INT,
  provider_id INT,
  PRIMARY KEY (id),
  CONSTRAINT FK_PROVIDER
    FOREIGN KEY (provider_id)
    REFERENCES desichef.users (id));    
    
CREATE TABLE IF NOT EXISTS desichef.itemorders (
  id INT NOT NULL AUTO_INCREMENT,
  customer_id INT,
  item_id INT,
  PRIMARY KEY (id),
  CONSTRAINT FK_PROVIDER_ITEM_ORDER
    FOREIGN KEY (item_id)
    REFERENCES desichef.registration (id),
  CONSTRAINT FK_USER_ITEM
  	FOREIGN KEY (customer_id)
  	REFERENCES desichef.users (id));