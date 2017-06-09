package info.androidhive.saluDate.ConexionService;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gustavo on 24/05/17.
 */

public class User {
    @SerializedName("user_id")
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
