package outcastfoods.integration.zoho.auth;

import java.time.LocalDateTime;

public class Token {

    private String key;
    private LocalDateTime expiresAt;

    public Token(String key, LocalDateTime expiresAt) {
        this.key = key;
        this.expiresAt = expiresAt;
    }

    public String getKey() {
        return key;
    }

    public String getBearerToken(){
        return "Bearer " + key;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public boolean isValid(){
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(expiresAt);
    }
}
