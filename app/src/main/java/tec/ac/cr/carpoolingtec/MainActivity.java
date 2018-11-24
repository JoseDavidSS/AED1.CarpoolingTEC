package tec.ac.cr.carpoolingtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import tec.ac.cr.carpoolingtec.Logic.MainBrain;
import tec.ac.cr.carpoolingtec.Logic.TemporalHolder;
import tec.ac.cr.carpoolingtec.Server.Connect;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToMainMenu(View v){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}
