package info.androidhive.saluDate.ConexionService;

import retrofit.http.Query;
import retrofit2.Call;
import retrofit.http.GET   ;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by gustavo on 18/05/17.
 */

public interface PersonService {
    public static final String BASE_URL = "http://34.209.167.194:8080/";

    @POST("/patient-api")
    Call<Person> login(@Body User loginBody);

}

