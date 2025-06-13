CREATE TABLE IF NOT EXISTS company (
                         id UUID PRIMARY KEY NOT NULL ,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         number VARCHAR(20) DEFAULT NULL,
                         website VARCHAR(255) DEFAULT NULL,
                         profilec VARCHAR(255) DEFAULT NULL,
                         about TEXT,
                         password VARCHAR(255) NOT NULL
);
