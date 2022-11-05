package cinema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TokenReq {
    private final UUID token;


    @JsonCreator
    public TokenReq(@JsonProperty("token") String token) {
        this.token = UUID.fromString(token);
    }

    public UUID getToken() {
        return token;
    }
}
