package info.androidhive.saluDate.ConexionService;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gustavo on 18/05/17.
 */

public interface PersonService {
    @GET("?format=json")
    Call<ArrayList<person>> obtenerListaPersonas();

}

