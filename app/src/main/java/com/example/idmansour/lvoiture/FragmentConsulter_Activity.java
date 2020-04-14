package com.example.idmansour.lvoiture;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.ClassFixeInfo;
import com.example.idmansour.lvoiture.outile_dev.Traitment;
import com.example.idmansour.lvoiture.outile_dev.Voiture;
import com.squareup.picasso.Picasso;

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

public class FragmentConsulter_Activity extends Fragment {
    ListView listView;
    TextView txt_resultat;
    Button btn_tri, btn_filtrer, btn_ok_filtrer, btn_ok_trie;
    RadioButton check_lux, check_moyenne, check_prix, check_note;
    CheckBox check_croissant;
    String operationDonnee;
    String TitreAlert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_consulter, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Consulter les voitures");
        //region code###########################
        try {

            /////////////////////////////////////////////////////////////////////
            //////////////////////       INISIALISER VARIABLE ///////////////////
            ////////////////////////////////////////////////////////////////////

            listView = (ListView) view.findViewById(R.id.liste_voiture_consulter);// liste de voiture
            txt_resultat = (TextView) view.findViewById(R.id.txt_nb_result);//nb voiture trouvé avec tri ou filtrer;
            btn_tri = (Button) view.findViewById(R.id.btn_tri_consulter);// btn tri
            btn_filtrer = (Button) view.findViewById(R.id.btn_filtrer_consulter);//btn filtrer
            TitreAlert = "Rechercher";
            // CONNECTION WITH SERVER

            new Ifo_consulter_voiture().execute(ClassFixeInfo.DateD, ClassFixeInfo.HeureD, ClassFixeInfo.Tri, ClassFixeInfo.order, ClassFixeInfo.Filtrer);// rechercher par web

            /////////////////////////////////////////////////////////////////////
            //////////////////////       BUTTON FILTRER       ///////////////////
            ////////////////////////////////////////////////////////////////////

            btn_filtrer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Traitment.checkInternetConnection()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Traitment.getContext_mainActivity());
                        View _viewDialog = getLayoutInflater().inflate(R.layout.style_filter, null);
                        //region  CODE ALERTDIALOG
                        check_lux = (RadioButton) _viewDialog.findViewById(R.id.QualiteLuxe_radiobtn);
                        check_moyenne = (RadioButton) _viewDialog.findViewById(R.id.QualiteMoyenne_radiobtn);
                        btn_ok_filtrer = (Button) _viewDialog.findViewById(R.id.btn_ok_filtre);
                        TitreAlert = "Filtrer";
                        btn_ok_filtrer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // code btn filtrer
                                ClassFixeInfo.Tri ="";
                                ClassFixeInfo.order = "";
                                ClassFixeInfo.Filtrer = "luxe";
                                ClassFixeInfo.operationDonnee = " voiture (s) disponible (s) Filtrer les voitures par :  ";
                                if (check_moyenne.isChecked()) {
                                    ClassFixeInfo.Filtrer = "moyenne";
                                }
                                ClassFixeInfo.operationDonnee += ClassFixeInfo.Filtrer;
                                ClassFixeInfo.dialogFiltrer.dismiss();
                                android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                                fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentChargement()).commit();
                            }
                        });
                        builder.setView(_viewDialog);
                        AlertDialog dialog = builder.create();
                        ClassFixeInfo.dialogFiltrer = dialog; // Enregistrer dialogAlert
                        dialog.show();
                        //endregion
                    } else {
                        Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            /////////////////////////////////////////////////////////////////////
            //////////////////////       BUTTON TRI       //////////////////////
            ////////////////////////////////////////////////////////////////////

            btn_tri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Traitment.checkInternetConnection()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Traitment.getContext_mainActivity());
                        View _viewDialog = getLayoutInflater().inflate(R.layout.style_trie_consulter, null);
                        //region  CODE ALERTDIALOG
                        check_prix = (RadioButton) _viewDialog.findViewById(R.id.prix_radiobtn);
                        check_note = (RadioButton) _viewDialog.findViewById(R.id.note_radiobtn);
                        check_croissant = (CheckBox) _viewDialog.findViewById(R.id.checkbox_triOrder);
                        btn_ok_trie = (Button) _viewDialog.findViewById(R.id.btn_ok_triee);

                        btn_ok_trie.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // code btn tri
                                ClassFixeInfo.Tri = "prix";
                                ClassFixeInfo.order = "ASC";
                                String or = "Croissante";
                                TitreAlert = "Tri";
                                ClassFixeInfo.operationDonnee = " voiture (s) disponible (s) Trier par :  ";
                                if (check_note.isChecked()) {
                                    ClassFixeInfo.Tri = "note";
                                }
                                if (!check_croissant.isChecked()) {
                                    ClassFixeInfo.order = "DESC";
                                    or = "Déroissante";
                                }
                                ClassFixeInfo.operationDonnee += ClassFixeInfo.Tri + " dans ordre : " + or;

                                ClassFixeInfo.dialogTri.dismiss();
                                android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                                fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentChargement()).commit();
                            }
                        });
                        builder.setView(_viewDialog);
                        AlertDialog dialog = builder.create();
                        ClassFixeInfo.dialogTri = dialog;
                        dialog.show();
                        //endregion
                    } else {
                        Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(Traitment.getContext_mainActivity(), "Erreur Consulter voiture :\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //endregion
        return view;
    }

    /////////////////////////////////////////////////////////////////////
    //////////////////////       CLASS AdapterVoiture       /////////////
    ////////////////////////////////////////////////////////////////////

    public class AdapterVoiture extends BaseAdapter {
        Context context;
        ArrayList<Voiture> voitureArrayList = new ArrayList<Voiture>();

        public AdapterVoiture(Context context, ArrayList<Voiture> voitureArrayList) {
            this.context = context;
            this.voitureArrayList = voitureArrayList;
        }

        @Override
        public int getCount() {
            return voitureArrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return voitureArrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final View view1;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.style_liste_voiture, viewGroup, false);
            }
            view1 = view;
            try {
                final Voiture voiture = voitureArrayList.get(i);
                TextView nomVoiture = (TextView) view1.findViewById(R.id.txt_nom_voiture_listview);
                TextView inscriptionVoiture = (TextView) view1.findViewById(R.id.txt_inscription_listview);
                TextView nb_personneVoiture = (TextView) view1.findViewById(R.id.txt_nb_personne_listview);
                TextView nb_portVoiture = (TextView) view1.findViewById(R.id.txt_nb_porte_listview);
                TextView carburantVoiture = (TextView) view1.findViewById(R.id.txt_typecarburant_listview);
                TextView boitVitesseVoiture = (TextView) view1.findViewById(R.id.txt_manuele_listview);
                final ImageView photoVoiture = (ImageView) view1.findViewById(R.id.img_voiture_listeview);
                TextView prixVoiture = (TextView) view1.findViewById(R.id.txt_prix_listview);
                TextView noteVoiture = (TextView) view1.findViewById(R.id.txt_note_voiture);
                TextView buttonDetail = (TextView) view1.findViewById(R.id.btn_detail_listview);
                nomVoiture.setText(voiture.getNom());
                //inscriptionVoiture.setText("");
                nb_personneVoiture.setText(voiture.getPersonne_nb() + " X personne(s)");
                nb_portVoiture.setText(voiture.getPorte_nb() + " X porte(s)");
                carburantVoiture.setText(voiture.getType_carburant());
                boitVitesseVoiture.setText(voiture.getType_boit_vitesse());
                Log.d("photo", "getView: " + voiture.getPhoto());
                Picasso.with(Traitment.getContext_mainActivity()).load("http://192.168.1.100/gestionVoiture/" + voiture.getPhoto()).into(photoVoiture);
                prixVoiture.setText(voiture.getPrix() + " MAD/jour");
                noteVoiture.setText("Note:" + voiture.getNote() + "/10   ");


                // button détail de voiture
                buttonDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!Traitment.checkInternetConnection()) {// INTERNET CONNECTION
                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                        } else {
                            ClassFixeInfo.VoitureItemOnClick = voitureArrayList.get(i);//voiture à click
                            android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                            fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentDetailConsulterActivity()).commit();
                        }
                    }
                });

                // Click in image
                photoVoiture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!Traitment.checkInternetConnection()) { // INTERNET CONNECTION
                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                        } else {
                            View _viewStyleTaille = getLayoutInflater().inflate(R.layout.style_taille_image, null);
                            AlertDialog.Builder builder = new AlertDialog.Builder(Traitment.getContext_mainActivity());
                            ImageView imageView_taile = (ImageView) _viewStyleTaille.findViewById(R.id.imageDialog);
                            TextView cancel_taile = (TextView) _viewStyleTaille.findViewById(R.id.btn_cancel_alertDialog);
                            Picasso.with(Traitment.getContext_mainActivity()).load("http://192.168.1.100/gestionVoiture/" + voiture.getPhoto()).into(imageView_taile);

                            // cancel  btn
                            cancel_taile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ClassFixeInfo.dialogTailleImageGrand.dismiss();
                                }
                            });
                            builder.setView(_viewStyleTaille);
                            AlertDialog dialog = builder.create();
                            ClassFixeInfo.dialogTailleImageGrand = dialog;
                            dialog.show();
                        }
                    }
                });

            } catch (Exception e) {
                Toast.makeText(context, "Erreur Adapter : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return view;
        }
    }

    /////////////////////////////////////////////////////////////////////
    //////////////////////       CLASS CONSULTER       //////////////////
    ////////////////////////////////////////////////////////////////////


    public class Ifo_consulter_voiture extends AsyncTask<String, Void, String> {
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(Traitment.getContext_mainActivity());
            progressDialog.setTitle(TitreAlert);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("en cours de traitement..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String date = strings[0];//date
            String heure = strings[1];//heure
            String tri = strings[2];//tri
            String order = strings[3];//order de tri (ASC | DESC)
            String filtrer = strings[4];//filtrer
            String link;
            String data;
            BufferedReader bufferedReader;
            String result;
            try {
                data = "?code=" + URLEncoder.encode("projetstage", "UTF-8");
                data += "&date=" + URLEncoder.encode(date, "UTF-8");
                data += "&heure=" + URLEncoder.encode(heure, "UTF-8");
                data += "&tri=" + URLEncoder.encode(tri, "UTF-8");
                data += "&order=" + URLEncoder.encode(order, "UTF-8");
                data += "&filtrer=" + URLEncoder.encode(filtrer, "UTF-8");
                link = "http://192.168.1.100/gestionVoiture/consulter.php" + data;
                Log.d("link : ", "link: " + link);
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
                    if (jsonStr.length() != 4) {//lenght error message("null")
                        JSONObject jsonObj = new JSONObject(jsonStr);
                        ClassFixeInfo.Liste_Array_voiture = new ArrayList<Voiture>();
                        JSONArray query_result = jsonObj.getJSONArray("message");
                        //region  CHARGER LES DONNES
                        for (int i = 0; i < query_result.length(); i++) {
                            JSONObject line_voiture = query_result.getJSONObject(i);
                            String Immatricule, photo, nom, prix, note, personne_nb, climat, vitesse, carbutant, disponible, nb_port, remise, nb_option;
                            Immatricule = line_voiture.getString("Immatricule");
                            photo = line_voiture.getString("photo");
                            nom = line_voiture.getString("nom");
                            prix = line_voiture.getString("prix");
                            note = line_voiture.getString("note");
                            personne_nb = line_voiture.getString("personne_nb");
                            climat = line_voiture.getString("etat_climatiseur");
                            vitesse = line_voiture.getString("type_boit_vitesse");
                            carbutant = line_voiture.getString("type_carburant");
                            disponible = line_voiture.getString("disponible");
                            nb_port = line_voiture.getString("porte_nb");
                            remise = line_voiture.getString("remise");
                            nb_option = line_voiture.getString("nombre_options");
                            ClassFixeInfo.Liste_Array_voiture.add(new Voiture(Immatricule, photo, nom, prix, note, personne_nb, climat, vitesse, carbutant, disponible, nb_port, remise, nb_option));
                        }
                        //endregion
                        AdapterVoiture adapterVoiture = new AdapterVoiture(Traitment.getContext_mainActivity(), ClassFixeInfo.Liste_Array_voiture);
                        listView.setAdapter(adapterVoiture);
                        txt_resultat.setText(query_result.length() + ClassFixeInfo.operationDonnee);
                    } else
                        txt_resultat.setText("Aucun voiture disponible maintenant !!!!!!!");
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Traitment.getContext_mainActivity(), "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Traitment.getContext_mainActivity(), "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}