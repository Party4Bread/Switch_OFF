package com.ezo.switchoff;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.hardware.*;
import android.os.Debug;
import android.util.Log;
import android.widget.*;
import android.view.*;

import java.util.*;

public class Launcher extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mLight;
    private Button gotoMisBtn, leaderboardBtn, settingBtn, gotoMulBtn, achieveBtn;
    private int dim;
    float lx;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        preferences = getSharedPreferences("pref", MODE_PRIVATE);
        // (해당 스프라이트까지의 거리)/7
        dim = 0;
        mSensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gotoMisBtn = (Button) findViewById(R.id.missionBtn);
        leaderboardBtn = (Button) findViewById(R.id.leaderboardBtn);
        settingBtn = (Button) findViewById(R.id.settingBtn);
        gotoMulBtn = (Button) findViewById(R.id.multiBtn);
        achieveBtn = (Button) findViewById(R.id.achieveBtn);

        gotoMisBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Launcher.this,SingleGame.class);
                startActivity(i);
            }
        });
        achieveBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                preferences.edit().clear().apply();
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Launcher.this,ShopActivity.class);
                startActivity(i);
            }
        });
        TimerTask task = new TimerTask() {
            public void run() {
                try {
                    lx = lx;// * 255 / 300+50;
                    lx -= lx > 255 ? lx % 255 : 0;
                    dim += (lx - dim) / 40 ;
                    dim -= dim > 255 ? dim % 255 : 0;
                    runOnUiThread(
                            new Runnable() {
                                public void run() {
                                    gotoMisBtn.setBackgroundColor(Color.rgb(255 - dim, 255 - dim, 255 - dim));
                                    gotoMisBtn.setTextColor(Color.rgb(dim, dim, dim));
                                    gotoMulBtn.setBackgroundColor(Color.rgb(255 - dim, 255 - dim, 255 - dim));
                                    gotoMulBtn.setTextColor(Color.rgb(dim, dim, dim));
                                    settingBtn.setBackgroundColor(Color.argb(dim,102,192,183));
                                    //settingBtn.setTextColor(Color.rgb(dim, dim, dim));
                                    achieveBtn.setBackgroundColor(Color.argb(dim,50,171,159));
                                    //achieveBtn.setTextColor(Color.rgb(dim, dim, dim));
                                    leaderboardBtn.setBackgroundColor(Color.argb(dim,0,150,136));
                                    //leaderboardBtn.setTextColor(Color.rgb(dim, dim, dim));
                                }
                            }
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Timer mTimer = new Timer();
        mTimer.schedule(task, 50, 10);
    }
    protected void onResume() {

        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        lx = event.values[0];
    }
}
