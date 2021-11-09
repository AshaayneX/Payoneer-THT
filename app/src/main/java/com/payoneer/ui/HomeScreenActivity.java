package com.payoneer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.payoneer.R;

import dagger.hilt.android.AndroidEntryPoint;
/**
 * Holds navigation component
 */
@AndroidEntryPoint
public class HomeScreenActivity extends AppCompatActivity {
    /** capture time on back button press */
    private long backPressedTime;

    /** toast to be displayed on back button press */
    private Toast backPressToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    /** back button press logic */
    @Override
    public void onBackPressed() {
            if(backPressedTime+2000> System.currentTimeMillis()){
                backPressToast.cancel();
                super.onBackPressed();
                return;
            }else{
              backPressToast =   Toast.makeText(getBaseContext(),getString(R.string.exit_toast),Toast.LENGTH_SHORT);
              backPressToast.show();
            }
            backPressedTime = System.currentTimeMillis();
    }
}
