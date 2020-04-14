package com.example.idmansour.lvoiture;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.ClassFixeInfo;
import com.example.idmansour.lvoiture.outile_dev.Traitment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IDMANSOUR on 30/03/2018.
 */

public class FragmentRechercherActivity extends Fragment {

    String format_;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Button btn_rech;
    private TextView txt_date2;
    private TextView txt_date1;
    TextView txt_heure1;
    TextView txt_heure2;
    CheckBox checkBox_avec_conducteur;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rechercher, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Rechercher une voiture");
        btn_rech = (Button) view.findViewById(R.id.btn_rechercher_recherchevoiture);
        txt_date1 = (TextView) view.findViewById(R.id.txt_datedepart_recherche);
        txt_date2 = (TextView) view.findViewById(R.id.txt_datearrive_recherche);
        txt_heure1 = (TextView) view.findViewById(R.id.txt_houredepart_recherche);
        txt_heure2 = (TextView) view.findViewById(R.id.txt_hourearrive_recherche);
        checkBox_avec_conducteur = (CheckBox) view.findViewById(R.id.check_avec_conducteur);
        //////////////////////////////CHARGEMENT DE LA DATE/////////////////////////
        new Ifo_Date().execute();
        btn_rech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                try {
                    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(txt_date1.getText().toString());
                    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(txt_date2.getText().toString());
                    if (date1.after(date2)) {
                        Toast.makeText(Traitment.getContext_mainActivity(), "Date Invalide\nla date départ doit etre inférieur à la date Arrivé", Toast.LENGTH_SHORT).show();
                        Log.d("date 1 ", txt_date1.getText().toString());
                        Log.d("date 2 ", txt_date2.getText().toString());
                    } else {
                        ClassFixeInfo.DateD = txt_date1.getText().toString();
                        ClassFixeInfo.DateF = txt_date2.getText().toString();
                        ClassFixeInfo.HeureD = txt_heure1.getText().toString();
                        ClassFixeInfo.HeureF = txt_heure2.getText().toString();
                        ClassFixeInfo.avecConducteur = "0";
                        ClassFixeInfo.operationDonnee = " voiture(s) disponible(s)";// change Titre de AlertDialog
                        if(checkBox_avec_conducteur.isChecked())
                        {
                            ClassFixeInfo.avecConducteur = "1";
                        }
                        ClassFixeInfo.Tri = "";
                        ClassFixeInfo.order = "";
                        ClassFixeInfo.Filtrer = "";

                        if(Traitment.checkInternetConnection()){
                            android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                            fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentConsulter_Activity()).commit();
                        }else {
                            Toast.makeText(Traitment.getContext_mainActivity(),R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (Exception ex) {
                    Toast.makeText(Traitment.getContext_mainActivity(), "Erreur: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*txt_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker_Dialog(view);
            }
        });*/
        txt_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker_Dialog(view);
            }
        });
        txt_heure1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker_Dialog(view);
            }
        });
        /*txt_heure2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker_Dialog(view);
            }
        });*/
        return view;
    }

    public void TimePicker_Dialog(View view) {
        final TextView textView = (TextView) view;
        format_ = "";
        Calendar calendar = Calendar.getInstance();
        int Heure = calendar.get(Calendar.HOUR);
        int Minute = calendar.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(Traitment.getContext_mainActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int Heure, int Minute) {
                if (Heure < 10)
                    format_ = "0" + Heure;
                else
                    format_ = "" + Heure;
                if (Minute < 10)
                    format_ += ":0" + Minute;
                else
                    format_ += ":" + Minute;
                textView.setText(format_);
                txt_heure2.setText(textView.getText().toString());
            }
        }, Heure, Minute, true);
        timePickerDialog.show();
    }

    public void DatePicker_Dialog(final View view) {
        final TextView textView = (TextView) view;
        Calendar calendar = Calendar.getInstance();
        format_ = "";
        int jour = calendar.get(Calendar.DAY_OF_MONTH);
        int moin = calendar.get(Calendar.MONTH);
        int annee = calendar.get(Calendar.YEAR);
        datePickerDialog = new DatePickerDialog(Traitment.getContext_mainActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int annee, int moin, int jour) {
                format_ =   annee+"-";
                if (moin < 10)
                    format_ += "0" + moin;
                else
                    format_ += "" + moin;
                if (jour < 10)
                    format_ += "-0" + jour;
                else
                    format_ += "-" + jour;
                textView.setText(format_);

                Log.d("onDateSet2 ", "onDateSet: " + textView.getText().toString());
            }
        }, annee, moin, jour);
        datePickerDialog.show();
    }


    public class Ifo_Date extends AsyncTask<String, Void, String> {
       // private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
          /*  progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
            progressDialog.setTitle("En Coure ....");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("CHARGEMENT");
            progressDialog.show();*/
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

           // String date = strings[0];//date
            //String heure = strings[1];//heure
            String link;
            String data;
            BufferedReader bufferedReader;
            String result;
            try {
                link = "http://192.168.1.100/gestionVoiture/getdate.php";
                URL url = new URL(link);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                result = bufferedReader.readLine();
                return result;
            } catch (Exception e) {
                return new String("????" + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String result) {
            String jsonStr = result;
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    String datetext = jsonObj.getString("date");
                    //String heureetext = jsonObj.getString("heure");
                    txt_date1.setText(datetext);
                    //txt_heure1.setText(heureetext);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Traitment.getContext_mainActivity(), "Error parsing JSON data." + jsonStr, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Traitment.getContext_mainActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
