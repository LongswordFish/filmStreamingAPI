package ca.wonderfish.filmstreamingapi.security;

public class SecurityConstant {
    public final static long EXPIRATION_TIME = 600_000;
    public final static String HEADER_STRING = "Authorization";
    public final static String SECRET = "SecretKeyForJWT";
    public final static String TOKEN_PREFIX = "Bearer ";
}
