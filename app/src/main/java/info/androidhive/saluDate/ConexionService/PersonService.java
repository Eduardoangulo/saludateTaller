package info.androidhive.saluDate.ConexionService;


import java.util.ArrayList;

import info.androidhive.saluDate.classes.person;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gustavo on 18/05/17.
 */

public interface PersonService {
    @GET("?format=json")
    Call<ArrayList<person>> obtenerListaPersonas();

}

