package tec.ac.cr.carpoolingtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void goToRiderView(View v){
        Intent intent = new Intent(this, RiderView.class);
        startActivity(intent);
    }

    public void goToDriverView(View v){
        Intent intent = new Intent(this, DriverView.class);
        startActivity(intent);
    }

}
