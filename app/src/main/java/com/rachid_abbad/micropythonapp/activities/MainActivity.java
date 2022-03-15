package com.rachid_abbad.micropythonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rachid_abbad.micropythonapp.R;
import com.rachid_abbad.micropythonapp.datas.DataProvider;
import com.rachid_abbad.micropythonapp.models.DataModel;
import com.rachid_abbad.micropythonapp.utils.LoadingDialog;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Switch ledSwitch, alertSwicher;
    TextView temp,humidity;
    DataModel dataModel ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ledSwitch = findViewById(R.id.ledSwitch);
        alertSwicher = findViewById(R.id.alertSwitch);
        temp = findViewById(R.id.temp);
        humidity = findViewById(R.id.humidity);

        ledSwitch.setTextOff("Off");
        ledSwitch.setTextOn("On");
        alertSwicher.setTextOff("Off");
        alertSwicher.setTextOff("On");

        dataModel = DataProvider.getData();

        alertSwicher.setChecked(dataModel.isAlertStat());
        if(dataModel.isAlertStat()){
            alertSwicher.setText("ON");
        }
        else{
            alertSwicher.setText("OFF");
        }

        ledSwitch.setChecked(dataModel.isLedStat());
        if(dataModel.isLedStat()){
            ledSwitch.setText("ON");
        }
        else{
            ledSwitch.setText("OFF");
        }

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                dataModel = DataProvider.getData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        temp.setText(dataModel.getTemp().intValue()+" °C");
                        humidity.setText(dataModel.getHumidity().intValue()+" %");
                    }
                });

            }
        },0,1000);

        ledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this,"Attendre en allumant le led");
                    loadingDialog.show(getSupportFragmentManager(),"");
                    DataProvider.controlLed(1,loadingDialog);
                    ledSwitch.setText("ON");
                }
                else{
                    LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this,"Attendre en éteignant le led");
                    loadingDialog.show(getSupportFragmentManager(),"");
                    DataProvider.controlLed(0,loadingDialog);
                    ledSwitch.setText("OFF");
                }
            }
        });

        alertSwicher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this,"Attendre en allumant le buzzer");
                    loadingDialog.show(getSupportFragmentManager(),"");
                    alertSwicher.setText("ON");
                    DataProvider.controlAlert(1,loadingDialog);
                }
                else{
                    LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this,"Attendre en allumant le buzzer");
                    loadingDialog.show(getSupportFragmentManager(),"");
                    alertSwicher.setText("OFF");
                    DataProvider.controlAlert(0,loadingDialog);
                }
            }
        });


    }
}