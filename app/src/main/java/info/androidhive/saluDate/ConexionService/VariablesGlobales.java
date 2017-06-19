package info.androidhive.saluDate.ConexionService;


import java.util.ArrayList;

import info.androidhive.saluDate.classes.patient;
import retrofit2.Retrofit;

/**
 * Created by eduardo on 6/13/17.
 */

public class VariablesGlobales {
    public static String URL_desarrollo = "http://34.209.167.194:8080/";
    public static boolean estado_user;
    public static final String TAG= "SALUDATELOGS";
    public static int LogedID;
    public static ArrayList<patient>pacientes;
    public static patient patient1;
    public static api_connection conexion;
    public static int speciality_img;
}
