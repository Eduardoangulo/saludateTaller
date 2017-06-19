package info.androidhive.saluDate.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.Mail;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.pacientes;

/**
 * Created by eduardo on 6/18/17.
 */

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    private Activity c;
    private Button aceptar, cancelar;
    private EditText txtCorreo;
    private Boolean encontrado=false;
    private int id_encontrado = -1;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_password);
        aceptar = (Button) findViewById(R.id.btn_aceptar);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        aceptar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_aceptar:
                comprobarCorreo();
                break;
            case R.id.btn_cancelar:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    private void comprobarCorreo() {
        try
        {
            if (pacientes != null){
                for (int i = 0; i < pacientes.size(); i++) {
                    if (pacientes.get(i).getPerson().getUser().getEmail().equals(txtCorreo.getText().toString()) && !encontrado) {
                        id_encontrado=i;
                        encontrado=true;
                    }
                }
                if(encontrado)
                {
                    sendEmail();
                }
                else
                {
                    Toast.makeText(c.getApplicationContext(), "Correo no encontrado" , Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void sendEmail(){
        Mail m = new Mail("eduardoanguloluna@gmail.com", "password");

        String[] toArr = {pacientes.get(id_encontrado).getPerson().getUser().getEmail()};
        m.set_to(toArr);
        m.set_from("eduardoanguloluna@gmail.com");
        m.set_subject("Restablecimiento de contraseña");
        m.setBody("Su contraseña es "+pacientes.get(id_encontrado).getPerson().getUser().getPassword()+". Sírvase a conectarse a SaluDate");

        try {
            m.addAttachment(Environment.getDataDirectory().getPath());

            if(m.send()) {
                Toast.makeText(c.getApplicationContext(), "Correo enviado correctamente", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(c.getApplicationContext(), "Correo no enviado", Toast.LENGTH_LONG).show();
            }
        } catch(Exception e) {
            Toast.makeText(c.getApplicationContext(), "There was a problem sending the email.", Toast.LENGTH_LONG).show();
            Log.e("Saludate", "Could not send email", e);
        }
    }
}