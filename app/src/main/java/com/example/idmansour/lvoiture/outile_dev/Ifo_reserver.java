package com.example.idmansour.lvoiture.outile_dev;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by IDMANSOUR on 12/04/2018.
 */

public class Ifo_reserver extends AsyncTask <String, Void, String> {
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
        progressDialog.setTitle("Vérification voiture");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("en cours de traitement...");
        progressDialog.show();
        //super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String dated = strings[0];//
        String heured = strings[1];//
        String datef = strings[2];//
        String heuref = strings[3];//
        String avecconducteur = strings[4];//
        String immatricule = strings[5];//
        String login = strings[6];//
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?code=" + URLEncoder.encode("projetstage", "UTF-8");
            data += "&dated=" + URLEncoder.encode(dated, "UTF-8");
            data += "&heured=" + URLEncoder.encode(heured, "UTF-8");
            data += "&datef=" + URLEncoder.encode(datef, "UTF-8");
            data += "&heuref=" + URLEncoder.encode(heuref, "UTF-8");
            data += "&avecconducteur=" + URLEncoder.encode(avecconducteur, "UTF-8");
            data += "&immatricule=" + URLEncoder.encode(immatricule, "UTF-8");
            data += "&login=" + URLEncoder.encode(login, "UTF-8");
            link = "http://192.168.1.100/gestionVoiture/reserver.php"+data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception do In background: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                String query_result = jsonObj.getString("message");
                Toast.makeText(Traitment.getContext_mainActivity(), "Message : \n"+query_result, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Traitment.getContext_mainActivity(), "Error parsing JSON data." + jsonStr, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Traitment.getContext_mainActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}
