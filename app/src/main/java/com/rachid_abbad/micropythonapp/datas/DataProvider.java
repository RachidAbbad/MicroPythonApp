package com.rachid_abbad.micropythonapp.datas;

import android.widget.Toast;

import com.rachid_abbad.micropythonapp.activities.MainActivity;
import com.rachid_abbad.micropythonapp.models.DataModel;
import com.rachid_abbad.micropythonapp.utils.Urls;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataProvider {


    public static DataModel getData() {
        DataModel dataModel = new DataModel();

        try {
            URL obj = new URL(Urls.GET_ALL_DATA);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResult = new JSONObject(response.toString());
                dataModel.setHumidity(jsonResult.getDouble("humidity"));
                dataModel.setTemp(jsonResult.getDouble("temp"));

                if (jsonResult.getInt("alertStatus") == 0)
                    dataModel.setAlertStat(false);
                else
                    dataModel.setAlertStat(true);

                if (jsonResult.getInt("led_status") == 0)
                    dataModel.setLedStat(false);
                else
                    dataModel.setLedStat(true);


            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception ex) {

        }


        return dataModel;
    }

    public static void controlLed(int stat) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Urls.CONTROL_LED + stat)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }

    public static void controlAlert(int stat) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Urls.CONTROL_ALERT + stat)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }


}
