package com.example.idmansour.lvoiture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.Traitment;

/**
 * Created by IDMANSOUR on 30/03/2018.
 */

public class FragmentAccueilActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_accueil,container,false);
        Button button = view.findViewById(R.id.btn_consulter_accueil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentRechercherActivity()).commit();
                }catch (Exception e){
                    Toast.makeText(Traitment.getContext_mainActivity(), "Erreur : \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
