# TMA Spring Boot Test
This application exposes REST endpoints to create, read, update, and delete players and teams. 

# Thought Process
I started this project with the entities. The Player entity has a many-to-one relationship to Team. I then added the controllers to
handle the routes. I did not separate the business logic into services as they were not too large but in a more complex setting, extracting that logic
and putting it into services would be better. The Player repository has a findByTeamId method because of its dependency with a team.
The Team repository contains three methods that are used to take care of the search endpoint. I used a command line runner to
populate the database with some initial teams and players.

# Software Requirements
1. Maven
2. Java 11 - Have JAVA_HOME environment variable set to use JDK 11

# Build and Run Instructions
1. Navigate to the tma_spring_boot_test directory
2. Run mvn clean install or use the mvnw wrapper
3. Run java -jar target/tma_spring_boot_test-0.0.1-SNAPSHOT.jar
4. The application is fully up when the 4 preloading statements are printed to the terminal.

# How to use the API
With the application running, the following endpoints will be available on localhost:8080

Player weight is in pounds and height is in inches.

Get a JSON Web Token by first sending a POST request to localhost:8080/login with header Content-Type: application/x-www-form-urlencoded
and query parameters username=user and password=1234

Copy the jwt and paste it in HTTP headers 'Authorization': Bearer (jwt)

### GET /v1/teams
Finds all teams

### GET /v1/teams/{id}
Finds one team based on the id in the url

### POST /v1/teams
Creates a new team with a json payload of the structure

```json
{
   "name": "teamName",
   "city": "cityName",
   "mascot": "mascot"
}
```

### PUT /v1/teams/{id}
Updates an existing team or adds the team if it does not already exist

JSON payload
```json
{
   "name": "teamName",
   "city": "cityName",
   "mascot": "mascot"
}
```

### DELETE /v1/teams/{id}
Deletes a team based on the team ID

### GET /v1/teams/search?name=A&city=B&mascot=C
Searches for teams based on the provided queries. Each query is optional. The search will be performed from
left to right depending on what query is first. Returns a list of teams.

### GET /v1/teams/{id}/players
Returns a list of all players on the team matching the id in the url.

### POST /v1/teams/{id}/players
Creates a new player and adds them to the team matching the id in the url.

JSON payload
```json
{
    "team": {
        "name": "Hawks",
        "city": "Atlanta",
        "mascot": "Harry"
    },
    "name": "John Collins",
    "position": "Power Forward",
    "age": 23,
    "height": 81,
    "weight": 200,
    "college": "Wake Forest",
    "salary": 30000
}
```

### PUT /v1/teams/{id}/players/{playerId}
Updates a player on the team ID and the player ID

JSON payload
```json
{
  "team": {
    "name": "Hawks",
    "city": "Atlanta",
    "mascot": "Harry"
  },
  "name": "John Collins",
  "position": "Power Forward",
  "age": 23,
  "height": 81,
  "weight": 200,
  "college": "Wake Forest",
  "salary": 30000
}
```

### DELETE /v1/teams/{id}/players/{playerId}
Deletes a player from a team using the player ID

# Future Improvements
1. Loosen the exact search queries to allow for partial text matching
2. Add rate limiter
3. Provide caching 