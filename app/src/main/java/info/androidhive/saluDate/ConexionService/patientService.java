package info.androidhive.saluDate.ConexionService;


import java.util.ArrayList;

import info.androidhive.saluDate.classes.MedicalRecord;
import info.androidhive.saluDate.classes.patient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface patientService {
    @GET("patient/patient-api/")
    Call<ArrayList<patient>> obtenerListaPacientes();

    @GET("patient/medical-record-api/{id}/")
    Call<MedicalRecord> obtenerFichaMedica(@Path("id") Integer id);

    @Headers({ "Content-Type: application/json"})
    @PUT("patient/patient-api/{id}/")
    Call<patient> guardarStatus(@Path("id") Integer id, @Body patient p);

}

