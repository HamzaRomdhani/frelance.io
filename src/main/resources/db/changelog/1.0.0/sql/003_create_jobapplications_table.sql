CREATE TABLE IF NOT EXISTS job_applications (
    id UUID PRIMARY KEY NOT NULL ,
    companyname VARCHAR(255) NOT NULL,
    companyemail VARCHAR(255) NOT NULL ,
    position VARCHAR(255) DEFAULT NULL,
    candidatename VARCHAR(255) DEFAULT NULL,
    candidateemail VARCHAR(255) DEFAULT NULL,
    candidateresume VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    cid UUID NOT NULL
    );


