CREATE TYPE gender AS ENUM ("Female","Male");
CREATE TABLE physicists(ID  SERIAL PRIMARY KEY,name varchar(32) NOT NULL,pc_gender gender NOT NULL);