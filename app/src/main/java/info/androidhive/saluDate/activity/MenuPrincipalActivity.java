package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.api_connection;
import info.androidhive.saluDate.ConexionService.patientService;
import info.androidhive.saluDate.classes.patient;
import info.androidhive.saluDate.fragments.OneFragment;
import info.androidhive.saluDate.fragments.TwoFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.conexion;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.estado_user;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.patient1;

public class MenuPrincipalActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        conexion = new api_connection(getApplicationContext(), TAG, URL_desarrollo);
        verifyStatus();

        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }catch (Exception e){
            e.printStackTrace();
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.snackbar_menuprincipal),Toast.LENGTH_LONG);
                startActivity(new Intent(MenuPrincipalActivity.this, NuevaCitaActivity.class));
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), getResources().getString(R.string.title_first_tab));
        adapter.addFragment(new TwoFragment(),getResources().getString(R.string.title_second_tab));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*switch (item.getItemId()) {
            case R.id.icon_ficha_medica:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_fichamedica),Toast.LENGTH_LONG);
                startActivity(new Intent(MenuPrincipalActivity.this, FichaMedicaActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        switch (item.getItemId()) {
            case R.id.ficha_medica:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.toast_fichamedica),Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuPrincipalActivity.this, FichaMedicaActivity.class));
                return true;
            case R.id.ajustes:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.action_settings),Toast.LENGTH_LONG).show();
                return true;
            case R.id.cerrar_sesion:
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.log_out),Toast.LENGTH_LONG).show();
                LogOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
    private void LogOut()
    {
        patient1.getPerson().setStatus("Desconectado");
        updateStatus(conexion.getRetrofit(),patient1);
    }

    private void updateStatus(Retrofit retrofit, patient p){
        patientService service = retrofit.create(patientService.class);
        service.guardarStatus(p.getId(),p).enqueue(new Callback<patient>() {

            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {
                try
                {
                    if (response.isSuccessful()){
                        Log.i(TAG, " UPDATED PAPU! ID" + response.body().getId());
                        estado_user=false;
                        verifyStatus();
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody().toString());
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<patient> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
    private void verifyStatus(){
        if(!estado_user)
        {
            goLoginScreen();
        }
    };

}
