package info.androidhive.saluDate.ConexionService;

import java.util.ArrayList;

import info.androidhive.saluDate.classes.MedicalRecord;
import info.androidhive.saluDate.classes.appointment;
import info.androidhive.saluDate.classes.doctor;
import info.androidhive.saluDate.classes.patient;
import info.androidhive.saluDate.classes.schedule;
import info.androidhive.saluDate.classes.schedule_doctor;
import info.androidhive.saluDate.classes.speciality;
import info.androidhive.saluDate.classes.speciality_doctor;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Luis on 16/06/2017.
 */

public interface appointmentService {
    @GET("doctor/speciality-api/")
    Call<ArrayList<speciality>> obtenerEspecialidades();

    @GET("doctor/schedule-api/")
    Call<ArrayList<schedule>> obtenerHorarios();

    @GET("doctor/doctor-api/")
    Call<ArrayList<doctor>> obtenerDoctores();

    @GET("doctor/speciality-doctor-api/")
    Call<ArrayList<speciality_doctor>> obtenerEspecialidadDoctor();

    @GET("doctor/schedule-doctor-api/")
    Call<ArrayList<schedule_doctor>> obtenerDoctorHorario ();


    //Mostrar citas
    @GET("patient/appointment-api/")
    Call<ArrayList<appointment>> obtenerCitas();

    @Headers({ "Content-Type: application/json"})
    @POST("patient/appointment-api/")
    Call<appointment> crearNuevaCita(@Body appointment appointment);

}
