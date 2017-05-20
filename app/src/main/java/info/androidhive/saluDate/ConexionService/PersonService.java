package info.androidhive.saluDate.ConexionService;

import java.util.List;

import retrofit2.http.GET;

/**
 * Created by gustavo on 18/05/17.
 */

public interface PersonService {
    @GET("/jairwsj.json")
    void getUser (retrofit2.Callback<List<POJOperson>> callback);
}

