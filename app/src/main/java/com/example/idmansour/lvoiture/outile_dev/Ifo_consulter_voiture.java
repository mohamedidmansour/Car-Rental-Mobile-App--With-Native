package com.example.idmansour.lvoiture.outile_dev;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by IDMANSOUR on 06/04/2018.
 */
public class Ifo_consulter_voiture  {
    /*
    extends AsyncTask<String, Void, String>
    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
        progressDialog.setTitle("Rechercher");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("En Coure.......!!");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String date = strings[0];//date
        String heure = strings[1];//heure
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;
        try {
            data = "?date=" + URLEncoder.encode(date, "UTF-8");
            data += "&heure=" + URLEncoder.encode(heure, "UTF-8");
            link = "http://www.farev-event.com/gv/consulter.php";
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            result = stringBuffer.toString();
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
                ClassFixeInfo.Liste_Array_voiture = new ArrayList<Voiture>();
                JSONArray query_result = jsonObj.getJSONArray("message");

                for (int i = 0; i < query_result.length(); i++) {
                    JSONObject line_voiture = query_result.getJSONObject(i);
                    String Immatricule, photo, nom, prix, personne_nb, climat, vitesse, carbutant, disponible, nb_port, remise, nb_option;
                    Immatricule = line_voiture.getString("Immatricule");
                    photo = line_voiture.getString("photo");
                    nom = line_voiture.getString("nom");
                    prix = line_voiture.getString("prix");
                    personne_nb = line_voiture.getString("personne_nb");
                    climat = line_voiture.getString("etat_climatiseur");
                    vitesse = line_voiture.getString("type_boit_vitesse");
                    carbutant = line_voiture.getString("type_carburant");
                    disponible = line_voiture.getString("disponible");
                    nb_port = line_voiture.getString("porte_nb");
                    remise = line_voiture.getString("remise");
                    nb_option = line_voiture.getString("nombre_options");

                    // ajouter ligne par ligne
                    ClassFixeInfo.Liste_Array_voiture.add(new Voiture(Immatricule, photo, nom, prix, personne_nb, climat, vitesse, carbutant, disponible, nb_port, remise, nb_option));
                }
                ClassFixeInfo.adapterVoiture = new AdapterVoiture(Traitment.getContext_mainActivity(),ClassFixeInfo.Liste_Array_voiture);
                progressDialog.dismiss();
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Traitment.getContext_mainActivity(), "Error parsing JSON data." + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Traitment.getContext_mainActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
    }
*/}

