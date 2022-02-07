package ca.wonderfish.filmstreamingapi.exceptions;

public class FilmIdExceptionResponse {
    private String filmId;

    public FilmIdExceptionResponse(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }
}
