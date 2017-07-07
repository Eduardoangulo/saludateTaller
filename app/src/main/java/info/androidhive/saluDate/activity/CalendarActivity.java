package info.androidhive.saluDate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.CalendarCustomView;

/**
 * Created by eduardo on 7/6/17.
 */
public class CalendarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    CalendarView calendarView;
    TextView dateDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        CalendarCustomView mView = (CalendarCustomView)findViewById(R.id.custom_calendar);

    }
}