package ca.wonderfish.filmstreamingapi.exceptions;

public class UserNameExistsExceptionResponse {

    private String username;

    public UserNameExistsExceptionResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
