# NetflixClone
A simplified video streaming service built with Java Spring Boot

#### Features
1. Authentication using JWT built with Spring Security
2. Database built with Hibernate and MySQL
3. Video streaming API with byte range access
4. Movie catalogue APIs for listing movies and searching
5. A CDN API for thumbnails

#### Running the Project
1. Update the username and password for MySQl database in the application.properties file
2. Import postman collection and environment files from `PostmanCollection` folder
3. Upon registration and login, copy the JWT token and update the jwt environment variable
4. For viewing the video, install [ModHeader](https://modheader.com/) plugin on your browser and add a header Authorization with value as `Bearer <JWT token>` and access the video link on localhost
