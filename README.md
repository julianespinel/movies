# movies

`movies` is a sample exercise to understand how to build basic a microservice using different programming languages.

Any implementation of `movies` should:

1. Respect a set of constraints (see Constraints section below)
1. Follow the same API (see API section below)

The goal of this project is to be able to compare different technologies by offering the same system behavior.

## Implementations

Currently the following implementations are available:

1. [Java](https://github.com/julianespinel/movies-java)

## Constraints

Any implementation of `movies` should respect the following constraints:

1. Have a `README.md` file with at least the following sections:
   1. Installation
   1. Testing
   1. Usage
1. Have code coverage by unit tests greater or equal to 80%
1. Read configuration values from a file
1. Store movies in a database
1. Log to a file

## API

Any implementation of `movies` should offer the following actions: 

1. Check microservice is alive
1. Create a movie
1. Find a specific movie
1. Find a set of movies
1. Update a movie
1. Delete a movie

Each API call is specified below:

### 1. Check microservice is alive

*Request*

Method: `GET`<br>
URL: `http://localhost:9001/movies/admin/ping`<br>

*Response*

```
pong
```

### 2. Create a movie

*Request*

Method: `POST`<br>
URL: `http://localhost:9001/movies/api/movies`<br>
Body:

```json
{
    "imdbId": "tt0133093",
    "title": "The Matrix",
    "runtimeInMinutes": 136,
    "releaseDate": "1999-03-31T00:00",
    "filmRating": "R",
    "genre": "Action, Sci-Fi",
    "director": "The Wachowski brothers",
    "plot": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    "metascore": 73,
    "imdbRating": 8.7,
    "imdbVotes": 1023621
}
```

*Response*

```json
{
  "imdbId": "tt0133093"
}
```

### 3. Find a specific movie

*Request*

Method: `GET`<br>
URL: `http://localhost:9001/movies/api/movies/tt0133093`<br>

*Response*

```json
{
  "imdbId": "tt0133093",
  "title": "The Matrix",
  "runtimeInMinutes": 136,
  "releaseDate": "1999-03-31T00:00",
  "filmRating": "R",
  "genre": "Action, Sci-Fi",
  "director": "The Wachowski brothers",
  "plot": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
  "metascore": 73,
  "imdbRating": 8.7,
  "imdbVotes": 1023621
}
```

### 4. Find a set of movies

*Request*

Method: `GET`<br>
URL: `http://localhost:9001/movies/api/movies?title=Matrix&runtimeInMinutes=130&metascore=6&imdbRating=7.2&imdbVotes=1000`<br>

*Response*

```json
[
  {
    "imdbId": "tt0133093",
    "title": "The Matrix",
    "runtimeInMinutes": 136,
    "releaseDate": "1999-03-31T00:00",
    "filmRating": "R",
    "genre": "Action, Sci-Fi",
    "director": "The Wachowski brothers",
    "plot": "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
    "metascore": 73,
    "imdbRating": 8.7,
    "imdbVotes": 1023621
  }
]
```

### 5. Update a movie

*Request*

Method: `PUT`<br>
URL: `http://localhost:9001/movies/api/movies/tt0133093`<br>
Body:
```json
{
    "imdbId": "tt0133093",
    "title": "The Matrix2",
    "runtimeInMinutes": 136,
    "releaseDate": "1999-03-31T00:00",
    "filmRating": "R",
    "genre": "Action, Sci-Fi",
    "director": "The Wachowski brothers and some others",
    "plot": "A computer hacker meets agent Smith.",
    "metascore": 83,
    "imdbRating": 8.7,
    "imdbVotes": 1023621
}
```

We changed the title, director, plot and the metascore.

*Response*

```json
{
  "imdbId": "tt0133093",
  "title": "The Matrix2",
  "runtimeInMinutes": 136,
  "releaseDate": "1999-03-31T00:00",
  "filmRating": "R",
  "genre": "Action, Sci-Fi",
  "director": "The Wachowski brothers and some others",
  "plot": "A computer hacker meets agent Smith.",
  "metascore": 83,
  "imdbRating": 8.7,
  "imdbVotes": 1023621
}
```

### 6. Delete a movie

*Request*

Method: `DELETE`<br>
URL: `http://localhost:9001/movies/api/movies/tt0133093`<br>

*Response*

```json
{
  "The movie was deleted?": true
}
```

## License

This project is under the MIT License. See the LICENSE file for the full license text.
