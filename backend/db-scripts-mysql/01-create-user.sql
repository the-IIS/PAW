CREATE USER 'trelloapp'@'localhost' IDENTIFIED BY 'trelloapp';

GRANT ALL PRIVILEGES ON * . * TO 'trelloapp'@'localhost';

ALTER USER 'trelloapp'@'localhost' IDENTIFIED WITH mysql_native_password BY 'trelloapp';