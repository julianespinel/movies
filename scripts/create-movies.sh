curl -d '{
    "imdbId": "tt1375666",
    "title": "Inception",
    "runtimeInMinutes": 148,
    "releaseDate": "2010-07-16T00:00",
    "filmRating": "PG-13",
    "genre": "Action, Adventure, Sci-Fi",
    "director": "Christopher Nolan",
    "plot": "A thief, who steals corporate secrets through the use of dream-sharing technology, is given the inverse task of planting an idea into the mind of a CEO.",
    "metascore": 74,
    "imdbRating": 8.8,
    "imdbVotes": 1709537
}' \
  -H "Content-Type: application/json" \
  -X POST http://localhost:9001/movies/api/movies

curl -d '{
    "imdbId": "tt1853728",
    "title": "Django Unchained",
    "runtimeInMinutes": 265,
    "releaseDate": "2012-12-25T00:00",
    "filmRating": "R",
    "genre": "Drama, Western",
    "director": "Quentin Tarantino",
    "plot": "With the help of a German bounty hunter, a freed slave sets out to rescue his wife from a brutal Mississippi plantation owner.",
    "metascore": 81,
    "imdbRating": 8.4,
    "imdbVotes": 1128132
}' \
  -H "Content-Type: application/json" \
  -X POST http://localhost:9001/movies/api/movies

curl -d '{
    "imdbId": "tt2543164",
    "title": "Arrival",
    "runtimeInMinutes": 116,
    "releaseDate": "2016-11-11T00:00",
    "filmRating": "PG-13",
    "genre": "Drama, Mystery, Sci-Fi",
    "director": "Denis Villeneuve",
    "plot": "A linguist is recruited by the military to communicate with alien lifeforms after twelve mysterious spacecrafts land around the world.",
    "metascore": 81,
    "imdbRating": 7.9,
    "imdbVotes": 446258
}' \
  -H "Content-Type: application/json" \
  -X POST http://localhost:9001/movies/api/movies
