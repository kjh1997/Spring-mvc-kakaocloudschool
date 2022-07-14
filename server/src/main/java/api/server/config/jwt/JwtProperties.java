package api.server.config.jwt;

public interface JwtProperties {
    String SECRET = "조익현"; // 우리 서버만 알고 있는 비밀값
    int EXPIRATION_TIME_Access = 1000 * 60 * 30; // 10일 (1/1000초)
    int EXPIRATION_TIME_Refresh = 1000 * 60 * 60 * 24 * 7; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String HEADER_STRING_Refresh = "Authorization_Refresh";
}
