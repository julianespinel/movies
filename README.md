# movies [![Build Status](https://travis-ci.org/julianespinel/movies.svg?branch=master)](https://travis-ci.org/julianespinel/movies)

Movies is a basic microservice built using Dropwizard. It was created with educational purposes, to be used as a resource for this meetup: [How to build, test and deploy a microservice?](http://www.meetup.com/Bogota-JVM-Meetup/events/222159221)

## Installation

To install the movies microservice just follow these 4 simple steps: 

1. git clone git@github.com:julianespinel/movies.git
2. cd movies/scripts
3. sh build.sh
4. sh start-develop.sh
5. sh create-movies.sh

## Usage

To check if the the microservice is up and running go to `http://localhost:9001/movies/admin/ping`. 
If you receive `pong` as response then it is running.

With the movies microservice you are able to execute the following acitons: 

1. Create a movie
2. Find a specific movie
3. Find a set of movies
4. Update a movie
5. Delete a movie

## API

### 1. Create a movie

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

### 2. Find a specific movie

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

### 3. Find a set of movies

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

### 4. Update a movie

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

### 5. Delete a movie

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
