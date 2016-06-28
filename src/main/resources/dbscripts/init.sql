

drop table users;

CREATE TABLE users (
username VARCHAR(50) NOT NULL PRIMARY KEY,
password VARCHAR(50) NOT NULL,
enabled BIT NOT NULL
);
  
CREATE TABLE user_roles (
user_role_id INTEGER  IDENTITY PRIMARY KEY,
username varchar(45) NOT NULL,
role varchar(45) NOT NULL
);

CREATE UNIQUE INDEX uni_username_role ON user_roles (role, username);

ALTER TABLE user_roles ADD CONSTRAINT fk_username_idx foreign key (username) REFERENCES users(username);
  
INSERT INTO users(username,password,enabled) VALUES ('mkyong','123456', true);
INSERT INTO users(username,password,enabled) VALUES ('alex','123456', true);
INSERT INTO users(username,password,enabled) VALUES ('naveen','123456', true);

INSERT INTO user_roles (username, role) VALUES ('naveen', 'ROLE_MANAGER');
INSERT INTO user_roles (username, role) VALUES ('naveen', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('alex', 'ROLE_USER');
