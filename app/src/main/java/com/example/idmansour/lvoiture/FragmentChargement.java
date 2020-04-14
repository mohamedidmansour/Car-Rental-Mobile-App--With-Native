package com.example.idmansour.lvoiture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by IDMANSOUR on 11/04/2018.
 */

public class FragmentChargement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.chargement,container,false);
        android.support.v4.app.FragmentManager fragmentManager3 = getFragmentManager();
        fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentConsulter_Activity()).commit();
        return _view;
    }
}
