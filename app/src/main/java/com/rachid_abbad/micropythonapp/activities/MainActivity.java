package com.rachid_abbad.micropythonapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.widget.Toast;

import com.rachid_abbad.micropythonapp.R;
import com.rachid_abbad.micropythonapp.datas.DataProvider;
import com.rachid_abbad.micropythonapp.models.DataModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataModel dataModel = DataProvider.getData();

        DataProvider.controlAlert(0);

        Toast.makeText(this,dataModel.getHumidity()+" - "+dataModel.getTemp(),Toast.LENGTH_LONG).show();
    }

    public void show(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}