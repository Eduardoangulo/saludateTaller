package info.androidhive.saluDate.ConexionService;


import java.util.ArrayList;

import info.androidhive.saluDate.classes.patient;
import info.androidhive.saluDate.classes.patient_post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by gustavo on 18/05/17.
 */

public interface patientService {
    @GET("patient/patient-api/")
    Call<ArrayList<patient>> obtenerListaPacientes();

    @PUT("patient/patient-api/")
    Call<patient> guardarStatus(@Body patient p);

}

