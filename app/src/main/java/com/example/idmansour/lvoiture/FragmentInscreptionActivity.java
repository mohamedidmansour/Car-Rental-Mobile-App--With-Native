package com.example.idmansour.lvoiture;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
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

/**
 * Created by IDMANSOUR on 30/03/2018.
 */

public class FragmentInscreptionActivity extends Fragment {


    /////////////////////////////////////////////////////////////////////
    //////////////////////  DECLARATION DES VARIABLES       ////////////
    ///////////////////////////////////////////////////////////////////


    private TextView cinOuPassport;
    private TextView motPasseComfirmer;
    private TextView nom;
    private TextView adresse;
    private TextView ville;
    private TextView codePostal;
    private TextView email;
    private TextView numTel;
    private TextView login;
    private TextView motPasse;
    private Spinner sexe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view1 = inflater.inflate(R.layout.inscription, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Création compte");

        /////////////////////////////////////////////////////////////////////
        //////////////////////  INISIALISATION DES VARIABLES       /////////
        ///////////////////////////////////////////////////////////////////

        ClassFixeInfo.etap = 1;
        final ProgressBar progressBar = (ProgressBar) view1.findViewById(R.id.progresse_etap);
        final TextView etapText = (TextView) view1.findViewById(R.id.txt_etap_progresse);
        final LinearLayout l1, l2;
        final TextView l3;
        l1 = (LinearLayout) view1.findViewById(R.id.layout_etap1);
        l2 = (LinearLayout) view1.findViewById(R.id.layout_etap2);
        l3 = (TextView) view1.findViewById(R.id.txt_resultat_creation_compte);
        cinOuPassport = (TextView) view1.findViewById(R.id.txt_cin_client);
        nom = (TextView) view1.findViewById(R.id.txt_nom_client);
        adresse = (TextView) view1.findViewById(R.id.txt_adresse_client);
        ville = (TextView) view1.findViewById(R.id.txt_ville_client);
        codePostal = (TextView) view1.findViewById(R.id.txt_codepostal_client);
        email = (TextView) view1.findViewById(R.id.txt_email_client);
        numTel = (TextView) view1.findViewById(R.id.txt_tel_client);
        sexe = (Spinner) view1.findViewById(R.id.sexe_inscrire);
        login = (TextView) view1.findViewById(R.id.txt_login_inscrire);
        motPasse = (TextView) view1.findViewById(R.id.txt_mot_de_passe_inscrire);
        motPasseComfirmer = (TextView) view1.findViewById(R.id.txt_mot_de_passe_confirmation_inscrire_inscrire);
        final Button btn_suivant = (Button) view1.findViewById(R.id.btn_suivant_page_inscrire);
        final Button btn_precedent = (Button) view1.findViewById(R.id.btn_precedent_page_inscrire);
        btn_precedent.setEnabled(false);


        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON SUIVANT       //////////////////
        ///////////////////////////////////////////////////////////////////


        btn_suivant.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                try {
                    btn_precedent.setEnabled(true);
                    if (ClassFixeInfo.etap != 4) {
                        if (isValideChamps(ClassFixeInfo.etap)) {
                            ClassFixeInfo.etap++;
                            // region CODE SOURCE


                            if (ClassFixeInfo.etap == 4) {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Traitment.getContext_mainActivity());
                                builder.setIcon(R.drawable.ic_plusinfo);
                                builder.setTitle("Comfirmation");
                                builder.setMessage("Vouler vous vraiment comfirmer votre inscription");
                                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // Envoyer les informations
                                        if (Traitment.checkInternetConnection())
                                            new Ifo_inscrire_client().execute(cinOuPassport.getText().toString(), nom.getText().toString(), adresse.getText().toString(), ville.getText().toString(), codePostal.getText().toString(), email.getText().toString(), numTel.getText().toString(), sexe.getSelectedItem().toString(), login.getText().toString(), motPasse.getText().toString());
                                        else
                                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                                android.app.AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                                btn_suivant.setEnabled(false);
                            } else if (ClassFixeInfo.etap == 3) {
                                l1.setVisibility(View.GONE);
                                l2.setVisibility(View.GONE);
                                l3.setVisibility(View.VISIBLE);
                                btn_suivant.setText("Comfirmer");
                                etapText.setText("Etape 3/3");
                                String sum1 =
                                        "Cin:\n\n\t\t\t" + cinOuPassport.getText().toString() + "\n"
                                                + "Nom:\n\n\t\t\t" + nom.getText().toString() + "\n"
                                                + "Adresse:\n\n\t\t\t" + adresse.getText().toString() + "\n"
                                                + "Ville:\n\n\t\t\t" + ville.getText().toString() + "\n"
                                                + "Code Postal:\n\n\t\t\t" + codePostal.getText().toString() + "\n"
                                                + "Email:\n\n\t\t\t" + email.getText().toString() + "\n"
                                                + "Tel:\n\n\t\t\t" + numTel.getText().toString() + "\n"
                                                + "Sexe:\n\n\t\t\t" + sexe.getSelectedItem().toString() + "\n"
                                                + "Login:\n\n\t\t\t" + login.getText().toString() + "\n"
                                                + "Mot de passe:\n\n\t\t\t" + motPasse.getText().toString() + "\n";
                                l3.setText(sum1);
                                progressBar.setProgress(progressBar.getProgress() + 50);
                            } else if (ClassFixeInfo.etap == 2) {
                                l1.setVisibility(View.GONE);
                                l2.setVisibility(View.VISIBLE);
                                l3.setVisibility(View.GONE);
                                etapText.setText("Etape 2/3");
                                progressBar.setProgress(progressBar.getProgress() + 50);
                            }
                            //endregion
                        } else {
                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.verifier_les_champs_fome, Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception e) {
                    Toast.makeText(Traitment.getContext_mainActivity(), "E: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON PRECEDENT       ////////////////
        ///////////////////////////////////////////////////////////////////


        btn_precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    btn_suivant.setEnabled(true);
                    btn_suivant.setText("suivant");
                    if (ClassFixeInfo.etap != 1) {
                        ClassFixeInfo.etap--;
                        if (ClassFixeInfo.etap == 1) {
                            btn_precedent.setEnabled(false);
                            l1.setVisibility(View.VISIBLE);
                            l2.setVisibility(View.GONE);
                            l3.setVisibility(View.GONE);
                            etapText.setText("Etape 1/3");
                            progressBar.setProgress(50);
                        } else if (ClassFixeInfo.etap == 2) {
                            l1.setVisibility(View.GONE);
                            l2.setVisibility(View.VISIBLE);
                            l3.setVisibility(View.GONE);
                            etapText.setText("Etape 2/3");
                            progressBar.setProgress(progressBar.getProgress() + 50);
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(Traitment.getContext_mainActivity(), "E: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view1;
    }

    public boolean isValideChamps(int etat) {
        if (etat == 1) {
            return !(cinOuPassport.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || adresse.getText().toString().isEmpty() || ville.getText().toString().isEmpty() || codePostal.getText().toString().isEmpty() || email.getText().toString().isEmpty() || numTel.getText().toString().isEmpty());
        } else {
            return !(login.getText().toString().isEmpty() || motPasse.getText().toString().isEmpty() || motPasseComfirmer.getText().toString().isEmpty() || (!motPasse.getText().toString().equals(motPasseComfirmer.getText().toString())) || sexe.getSelectedItem().toString().equals("--choisir votre sexe--"));
        }
    }

    /////////////////////////////////////////////////////////////////////
    //////////////////////       CLASS INSCRIRE       //////////////////
    ////////////////////////////////////////////////////////////////////

    public class Ifo_inscrire_client extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
            progressDialog.setTitle("Creation compte");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("en cours de traitement...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String cin = strings[0];
            String nom = strings[1];
            String adresse = strings[2];
            String ville = strings[3];
            String codepostal = strings[4];
            String email = strings[5];
            String tel = strings[6];
            String sexe = strings[7];
            String login = strings[8];
            String password = strings[9];
            String link;
            String data;
            BufferedReader bufferedReader;
            String result;
            try {
                data = "?code=" + URLEncoder.encode("projetstage", "UTF-8");
                data += "&cin=" + URLEncoder.encode(cin, "UTF-8");
                data += "&nom=" + URLEncoder.encode(nom, "UTF-8");
                data += "&adresse=" + URLEncoder.encode(adresse, "UTF-8");
                data += "&ville=" + URLEncoder.encode(ville, "UTF-8");
                data += "&codepostal=" + URLEncoder.encode(codepostal, "UTF-8");
                data += "&email=" + URLEncoder.encode(email, "UTF-8");
                data += "&tel=" + URLEncoder.encode(tel, "UTF-8");
                data += "&sexe=" + URLEncoder.encode(sexe, "UTF-8");
                data += "&login=" + URLEncoder.encode(login, "UTF-8");
                data += "&password=" + URLEncoder.encode(password, "UTF-8");
                link = "http://192.168.1.100/gestionVoiture/inscrire.php" + data;
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
                    String query_result = jsonObj.getString("compte");
                    Toast.makeText(Traitment.getContext_mainActivity(), query_result, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentRechercherActivity()).commit();
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
