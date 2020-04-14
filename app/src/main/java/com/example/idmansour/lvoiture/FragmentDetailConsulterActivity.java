package com.example.idmansour.lvoiture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.ClassFixeInfo;
import com.example.idmansour.lvoiture.outile_dev.Ifo_connect_client;
import com.example.idmansour.lvoiture.outile_dev.Traitment;
import com.example.idmansour.lvoiture.outile_dev.Voiture;
import com.squareup.picasso.Picasso;

/**
 * Created by IDMANSOUR on 30/03/2018.
 */

public class FragmentDetailConsulterActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.activity_detail_consulter, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Détail");

        /////////////////////////////////////////////////////////////////////
        //////////////////////       CHARGEMENT DES DONNENS :       ////////
        ////////////////////////////////////////////////////////////////////

        Voiture voiture = ClassFixeInfo.VoitureItemOnClick;
        ClassFixeInfo.immatricule = voiture.getImmatricule();
        TextView nomVoiture = (TextView) view1.findViewById(R.id.txt_nom_voiture_detail);
        TextView climaVoiture = (TextView) view1.findViewById(R.id.txt_clima_detail);
        TextView nb_personneVoiture = (TextView) view1.findViewById(R.id.txt_nb_personne_detail);
        TextView nb_portVoiture = (TextView) view1.findViewById(R.id.txt_nb_porte_detail);
        ImageView photoVoiture = (ImageView) view1.findViewById(R.id.image_voiture_datail);
        TextView prixVoitureH = (TextView) view1.findViewById(R.id.txt_prix_detail_voiture);
        TextView prixVoiture = (TextView) view1.findViewById(R.id.txt_prix_listview_detail);
        TextView noteVoiture = (TextView) view1.findViewById(R.id.txt_note_detail);
        final TextView nb_options_Voiture = (TextView) view1.findViewById(R.id.txt_nb_total_carateristique);
        TextView btn_plus_info_detailvoiture = (TextView) view1.findViewById(R.id.btn_plus_info_detailvoiture);
        Button btn_ajouter_maps_voiture = (Button) view1.findViewById(R.id.btn_ajouter_maps_voiture);
        Button btn_commander = (Button) view1.findViewById(R.id.btn_suivant_detail);

        if (Traitment.checkInternetConnection()) { // INTERNET
            nomVoiture.setText(voiture.getNom() + " ou équivalent");
            //inscriptionVoiture.setText("");
            nb_personneVoiture.setText(voiture.getPersonne_nb() + " persson..");
            nb_portVoiture.setText(voiture.getPorte_nb() + " port..");
            if (voiture.getEtat_climatiseur().equals("1"))
                climaVoiture.setText("avec");
            else
                climaVoiture.setText("non");
            Picasso.with(Traitment.getContext_mainActivity()).load("http://192.168.1.100/gestionVoiture/" + voiture.getPhoto()).into(photoVoiture);
            prixVoiture.setText(voiture.getPrix() + "MAD/jour");
            prixVoitureH.setText("Total: " + voiture.getPrix() + " MAD");
            noteVoiture.setText(" Note : " + voiture.getNote() + "/10");
            nb_options_Voiture.setText("+" + voiture.getNombre_options() + " option(s)");
        } else {
            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
        }

        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON RETOUR :                ////////
        ////////////////////////////////////////////////////////////////////

        prixVoitureH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Traitment.checkInternetConnection()) {
                    android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentConsulter_Activity()).commit();
                } else {
                    Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                }
            }
        });

        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON PLUS INFORMATION GENERALES /////
        ////////////////////////////////////////////////////////////////////

        btn_plus_info_detailvoiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Traitment.getContext_mainActivity(), ConditionGeneraleActivity.class);
                startActivity(intent);
            }
        });

        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON COMMANDER :                /////
        ///////////////////////////////////////////////////////////////////

        btn_commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Traitment.getContext_mainActivity());
                View _view = LayoutInflater.from(Traitment.getContext_mainActivity()).inflate(R.layout.connecter, null);

                final TextView login_txt = (TextView) _view.findViewById(R.id.txt_utilisateur);
                final TextView password_txt = (TextView) _view.findViewById(R.id.txt_mot_de_passe);
                Button btn_connecter = (Button) _view.findViewById(R.id.btn_connecter_page_login);
                //btn on click
                btn_connecter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Traitment.checkInternetConnection()){

                            if(!login_txt.getText().toString().isEmpty()&&!password_txt.getText().toString().isEmpty()){
                                ClassFixeInfo.Id_client = login_txt.getText().toString();
                                ClassFixeInfo.alertConnection_detailCommande.dismiss();
                                new Ifo_connect_client().execute(login_txt.getText().toString(),password_txt.getText().toString());
                                android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                                fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentConsulter_Activity()).commit();
                            }
                        }else{
                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setView(_view);
                android.app.AlertDialog alertDialog = builder.create();
                ClassFixeInfo.alertConnection_detailCommande = alertDialog;
                alertDialog.show();
            }
        });

        /////////////////////////////////////////////////////////////////////
        //////////////////////       BUTTON MAPS :                     /////
        ///////////////////////////////////////////////////////////////////
        if (Traitment.checkInternetConnection()) { // INTERNET
           btn_ajouter_maps_voiture.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                  /* android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                   fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new MapsActivity()).commit();*/
               }
           });
        } else {
            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
        }
        return view1;
    }
}
