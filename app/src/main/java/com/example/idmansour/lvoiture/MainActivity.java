package com.example.idmansour.lvoiture;

/**
 * Created by IDMANSOUR on 16/03/2018.
 */

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.idmansour.lvoiture.outile_dev.Traitment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle(R.string.txt_Accueil);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Traitment.setContext_mainActivity(MainActivity.this);

        /*
        *  First app lancer
        * */
        String ab = getIntent().getStringExtra("message");
        if(ab == null){
            ab="0";
        }
        if(!ab.equals("1")){
            ab="0";
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_activity_main, new FragmentAccueilActivity()).commit();
        }else {
            ab="0";
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_activity_main, new FragmentDetailConsulterActivity()).commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here

        /*
        * case R.id.menu_mesreservation:
                    android.support.v4.app.FragmentManager fragmentManager2 = getSupportFragmentManager();
                    fragmentManager2.beginTransaction().replace(R.id.content_activity_main, new FragmentRechercherActivity()).commit();
                    break;*/
        int id = item.getItemId();
        try {
            switch (id) {
                case R.id.menu_accueil:
                    getSupportActionBar().setTitle(R.string.txt_Accueil);
                    android.support.v4.app.FragmentManager fragmentManager1 = getSupportFragmentManager();
                    fragmentManager1.beginTransaction().replace(R.id.content_activity_main, new FragmentAccueilActivity()).commit();
                    break;
                case R.id.menu_consulter:
                    getSupportActionBar().setTitle(R.string.txt_Consulter);
                    android.support.v4.app.FragmentManager fragmentManager3 = getSupportFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.content_activity_main, new FragmentRechercherActivity()).commit();
                    break;
                case R.id.menu_inscrire:
                    getSupportActionBar().setTitle(R.string.inscrire);
                    android.support.v4.app.FragmentManager fragmentManager4 = getSupportFragmentManager();
                    fragmentManager4.beginTransaction().replace(R.id.content_activity_main, new FragmentInscreptionActivity()).commit();
                    break;

            }
        } catch (Exception e) {
            Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
