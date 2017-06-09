package info.androidhive.saluDate.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import info.androidhive.materialtabs.R;

import static info.androidhive.materialtabs.R.id.textView;

/**
 * Created by eduardo on 5/1/17.
 */

public class FichaMedicaActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Switch switch_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_medica);

        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        switch_info = (Switch)findViewById(R.id.switch_info);
        switch_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Toast.makeText(FichaMedicaActivity.this, getResources().getString(R.string.switch_info_true), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FichaMedicaActivity.this, getResources().getString(R.string.switch_info_false), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
