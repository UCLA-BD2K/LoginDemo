# LoginDemo By Daniel Yao

Experimental login features w/ security encryption over HTTPS, using ThymeLeaf and Spring Boot.

#### 
This requires your location machine to be running MySQL server on port 3306, which has the following table in a database called demo:

CREATE TABLE users
(
    Username VARCHAR(40) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(45),
    LastName VARCHAR(45),
    Password VARCHAR(45)
);
CREATE UNIQUE INDEX Username_UNIQUE ON users (Username);

####
keystore.p12 is a signed RSA handshake, only use for testing purposes.
To create a new keystore file, check application.properties for instructions
For real production, application.properties should also not be public.

####
After everything is running. Go to https://localhost:8443 to test.