package com.example.idmansour.lvoiture.outile_dev;

import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.idmansour.lvoiture.FragmentChargement;
import com.example.idmansour.lvoiture.FragmentDetailConsulterActivity;
import com.example.idmansour.lvoiture.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by IDMANSOUR on 07/04/2018.
 */

public class AdapterVoiture {
    /*
    extends BaseAdapter
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
            if (view == null){
                view = LayoutInflater.from(context).inflate(R.layout.style_liste_voiture,viewGroup,false);
            }
            view1 = view;
            try {
                Voiture voiture = voitureArrayList.get(i);
                TextView nomVoiture =(TextView) view1.findViewById(R.id.txt_nom_voiture_listview);
                TextView inscriptionVoiture =(TextView) view1.findViewById(R.id.txt_inscription_listview);
                TextView nb_personneVoiture =(TextView) view1.findViewById(R.id.txt_nb_personne_listview);
                TextView nb_portVoiture =(TextView) view1.findViewById(R.id.txt_nb_porte_listview);
                TextView carburantVoiture =(TextView) view1.findViewById(R.id.txt_typecarburant_listview);
                TextView boitVitesseVoiture =(TextView) view1.findViewById(R.id.txt_manuele_listview);
                ImageView photoVoiture = (ImageView) view1.findViewById(R.id.img_voiture_listeview);
                TextView prixVoiture =(TextView) view1.findViewById(R.id.txt_prix_listview);
                TextView noteVoiture  = (TextView) view1.findViewById(R.id.txt_note_voiture);
                TextView buttonDetail = (TextView) view1.findViewById(R.id.btn_detail_listview);
                nomVoiture.setText(voiture.getNom());
                //inscriptionVoiture.setText("");
                nb_personneVoiture.setText(voiture.getPersonne_nb()+" X personne(s)");
                nb_portVoiture.setText(voiture.getPorte_nb() + " X porte(s)");
                carburantVoiture.setText(voiture.getType_carburant());
                boitVitesseVoiture.setText(voiture.getType_boit_vitesse());
                Picasso.with(Traitment.getContext_mainActivity()).load("http://www.farev-event.com/iv/"+voiture.getPhoto()).into(photoVoiture);
                prixVoiture.setText(voiture.getPrix() + "MAD/jour");
                noteVoiture.setText(voiture.getNote() + "/10");


                buttonDetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!Traitment.checkInternetConnection()){
                            Toast.makeText(Traitment.getContext_mainActivity(), R.string.erreurConnection, Toast.LENGTH_SHORT).show();
                        }else {
                            ClassFixeInfo.numeroItemOnClick = i;
                        }
                    }
                });
            }catch (Exception e){
                Toast.makeText(context, "Erreur Adapter : "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return view;
    }
    */
}
