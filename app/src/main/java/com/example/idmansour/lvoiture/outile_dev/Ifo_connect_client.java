package com.example.idmansour.lvoiture.outile_dev;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by IDMANSOUR on 06/04/2018.
 */

public class Ifo_connect_client extends AsyncTask<String, Void, String> {
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
        progressDialog.setTitle("Vérification Compte");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("en cours de traitement");
        progressDialog.show();
        //super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String user = strings[0];//login
        String pass = strings[1];//password
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;
        try {
            data = "?code=" + URLEncoder.encode("projetstage", "UTF-8");
            data += "&login=" + URLEncoder.encode(user, "UTF-8");
            data += "&password=" + URLEncoder.encode(pass, "UTF-8");
            link = "http://192.168.1.100/gestionVoiture/connecte.php"+data;
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
                progressDialog.dismiss();
                if(query_result.equals("Valide"))
                {
                     new Ifo_reserver().execute(ClassFixeInfo.DateD,ClassFixeInfo.HeureD,ClassFixeInfo.DateF,ClassFixeInfo.HeureF,ClassFixeInfo.avecConducteur,ClassFixeInfo.immatricule,ClassFixeInfo.Id_client);
                }
                else {
                    Toast.makeText(Traitment.getContext_mainActivity(), "Votre mot de passe \n ou login Incorrect", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Traitment.getContext_mainActivity(), "Error parsing JSON data." + jsonStr, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Traitment.getContext_mainActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
}