package com.example.etudiantdsi.gestrans.Controller;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters.VoyageAdapter;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.Travel;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HomePage extends AppCompatActivity {

    private Intent redirection;
    private SharedPreferences pref;

    boolean shouldStopLoop = false;
    Handler mHandler = new Handler();
    int nb_voyages = 0;
    private Retrofit rf;
    private apiGesTrans api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /*Runnable runnable = new Runnable() {
            @Override
            public void run() {
                rf = RetrofitInstance.getRetroInstance();
                api = rf.create(apiGesTrans.class);
                pref =HomePage.this.getSharedPreferences(MainActivity.MY_PREFERENCES,Context.MODE_PRIVATE);
                retrofit.Call<List<Travel>> voyageCall = api.getUserTravels(pref.getString("id","id"),pref.getString("id_employe","id_employe"),pref.getString("role","role"));
                voyageCall.enqueue(new Callback<List<Travel>>() {
                    @Override
                    public void onResponse(Response<List<Travel>> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            List<Travel> listeTravels = (List<Travel>) response.body();
                            if(nb_voyages < listeTravels.size()){
                                nb_voyages=listeTravels.size();

                                NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(HomePage.this)
                                        .setSmallIcon(android.R.drawable.stat_notify_error)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                        .setContentTitle("Vous avez un nouveau voyage")
                                        .setContentText("Gestrans")
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                notificationbuilder.setDefaults(
                                        Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE);
                                NotificationManagerCompat m = NotificationManagerCompat.from(HomePage.this);
                                m.notify(1,notificationbuilder.build());

                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });


                if (!shouldStopLoop==true) {
                    mHandler.postDelayed(this, 15000);
                }
            }
        };
        mHandler.post(runnable);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.entreprise:
                redirection = new Intent(this,entreprise.class);
                startActivity(redirection);
                return true;
            case R.id.flotte:
                redirection = new Intent(this,flotte.class);
                startActivity(redirection);
                return true;
            case R.id.travels:
                redirection = new Intent(this,voyage.class);
                startActivity(redirection);
                return true;
            case R.id.travelsIcone:
                redirection = new Intent(this,voyage.class);
                startActivity(redirection);
                return true;
            case R.id.logout:
                redirection = new Intent(this,MainActivity.class);
                pref = getSharedPreferences(MainActivity.MY_PREFERENCES,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                redirection = new Intent(this,MainActivity.class);
                startActivity(redirection);
                finishAffinity();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

}
