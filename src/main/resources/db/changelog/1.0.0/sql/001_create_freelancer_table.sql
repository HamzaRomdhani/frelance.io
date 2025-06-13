CREATE TABLE IF NOT EXISTS freelancer (
                            id UUID PRIMARY KEY NOT NULL ,
                            name VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL UNIQUE,
                            number VARCHAR(20),
                            date DATE,
                            linkedin VARCHAR(255),
                            education VARCHAR(255),
                            profilef VARCHAR(255),
                            charge VARCHAR(100),
                            gender VARCHAR(10),
                            skills TEXT,
                            password VARCHAR(255) NOT NULL
);
