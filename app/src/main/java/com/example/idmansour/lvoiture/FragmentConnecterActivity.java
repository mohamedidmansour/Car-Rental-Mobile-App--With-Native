package com.example.idmansour.lvoiture;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.Traitment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by IDMANSOUR on 30/03/2018.
 */

public class FragmentConnecterActivity extends Fragment {
    Button btn_connecter;
    EditText login_text,pass_text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.connecter,container,false);
        btn_connecter  = (Button) view.findViewById(R.id.btn_connecter_page_login);
        login_text = (EditText)view.findViewById(R.id.txt_utilisateur);
        pass_text = (EditText)view.findViewById(R.id.txt_mot_de_passe);
        btn_connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new Ifo_connect_client().execute(login_text.getText().toString(),pass_text.getText().toString());
            }
        });
        return view;
    }
    /*
    public class Ifo_connect_client extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
            progressDialog.setTitle("En Coure ....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("CHARGEMENT");
            progressDialog.show();
            super.onPreExecute();
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
                data = "?login=" + URLEncoder.encode(user, "UTF-8");
                data += "&password=" + URLEncoder.encode(pass, "UTF-8");
                link = "http://www.farev-event.com/gv/connecte.php"+data;
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
                    if(query_result.equals("Valide"))
                    {
                        Toast.makeText(Traitment.getContext_mainActivity(), "Bienvenue", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Traitment.getContext_mainActivity(), "Votre mot de passe \n ou login Incorrect", Toast.LENGTH_SHORT).show();
                    }
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
    */
}
