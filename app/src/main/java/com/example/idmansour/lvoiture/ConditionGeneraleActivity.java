package com.example.idmansour.lvoiture;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.idmansour.lvoiture.outile_dev.Traitment;

import static com.example.idmansour.lvoiture.R.*;

public class ConditionGeneraleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_condition_generale);
        try {
            getSupportActionBar().setTitle(R.string.txtConditionGeneral);
        } catch (Exception e) {
            Toast.makeText(this, "Erreur : \n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rollback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == id.menu_retour){
            if(Traitment.checkInternetConnection()){
                Intent intent = new Intent(ConditionGeneraleActivity.this,MainActivity.class);
                intent.putExtra("message","1");
                startActivity(intent);
            }else {
                Toast.makeText(Traitment.getContext_mainActivity(),R.string.erreurConnection, Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    public void methodeDropDown(View view) {
        try {
            LinearLayout container = (LinearLayout) findViewById(id.groupe_layout_conditionGen);
            TextView nextView = (TextView) container.getChildAt(container.indexOfChild(view) + 1);
            TextView beforeView = (TextView) view;
            if (nextView.getVisibility() == View.VISIBLE) {
                nextView.setVisibility(View.GONE);
                beforeView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_black_24dp, 0);
            } else {
                nextView.setVisibility(View.VISIBLE);
                beforeView.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable.ic_arrow_drop_up_black_24dp, 0);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Erreur : \n" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
