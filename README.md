# TMA Spring Boot Test
This application exposes REST endpoints to create, read, update, and delete players and teams. 

# Software Requirements
1. Maven
2. Java 11

# Build and Run Instructions
1. Navigate to the tma_spring_boot_test directory
2. Run mvn clean install
3. Run java -jar target/tma_spring_boot_test-0.0.1-SNAPSHOT.jar

# How to use the API
With the application running, the following endpoints will be available on localhost:8080

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
1. Use SSL
2. Consider pulling business logic out of controllers and into services instead
3. Loosen the exact search queries to allow for partial text matching
4. Add rate limiter
5. Provide caching 