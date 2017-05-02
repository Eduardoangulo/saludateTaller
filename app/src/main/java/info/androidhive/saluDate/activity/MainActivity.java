package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import info.androidhive.materialtabs.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btnSimpleTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }catch (Exception e){
            e.printStackTrace();
        }
        btnSimpleTabs = (Button) findViewById(R.id.btn_menu_principal);
        btnSimpleTabs.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_menu_principal:
                startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                break;
        }
    }
}
