package com.example.idmansour.lvoiture.outile_dev;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by IDMANSOUR on 18/03/2018.
 */

public class Traitment {

    public static Context context_mainActivity;
    public static void setContext_mainActivity(Context context_mainActivity) {
        Traitment.context_mainActivity = context_mainActivity;
    }
    public static Context getContext_mainActivity() {
        return context_mainActivity;
    }

    public  static boolean checkInternetConnection(){
        ConnectivityManager c = (ConnectivityManager) getContext_mainActivity().getSystemService(CONNECTIVITY_SERVICE);
       if(c!=null){
           NetworkInfo info = c.getActiveNetworkInfo();
           if (info != null && info.isConnected())
           {
               return true;
           }
       }
        return false;
    }
}
