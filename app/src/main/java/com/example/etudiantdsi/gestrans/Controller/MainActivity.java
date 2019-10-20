package com.example.etudiantdsi.gestrans.Controller;

import com.example.etudiantdsi.gestrans.Model.*;
import com.example.etudiantdsi.gestrans.R;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class

MainActivity extends AppCompatActivity {

    private EditText log;
    private EditText pswd;
    private Button login;
    private Retrofit rf ;
    private apiGesTrans api;
    private SharedPreferences pref;
    private Intent redirection;
    public static final String MY_PREFERENCES = "user_details";
    ///////
    private boolean shouldStopLoop = false;
    private Handler mHandler = new Handler();
    private int nb_voy = 0;
    ///////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        log = (EditText) findViewById(R.id.login);
        pswd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.cx);

        rf = RetrofitInstance.getRetroInstance();
        api =  rf.create(apiGesTrans.class);
        pref = getSharedPreferences(MY_PREFERENCES,MODE_PRIVATE);
        redirection = new Intent(this,HomePage.class);
        /*SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();*/
        if(pref.contains("id") && pref.contains("role")) {
            startActivity(redirection);
            Toast.makeText(MainActivity.this,"Bonjour "+pref.getString("nom","nom")+" "+pref.getString("prenom","prenom"), Toast.LENGTH_LONG).show();
            finish();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(log.getText().toString().trim().equals("") || pswd.getText().toString().trim().equals("")){
                    if(log.getText().toString().trim().equals(""))
                        log.setError("you have to enter your login");
                    if(pswd.getText().toString().trim().equals(""))
                        pswd.setError("you have to enter your password");
                }else{
                    retrofit.Call<User> userCall = api.getUser(log.getText().toString());
                    userCall.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Response<User> response, Retrofit retrofit) {
                            if (response.isSuccess()) {
                                User u = new User();
                                u = (User) response.body();
                                if(u.getEmail()!=null) {
                                    Toast.makeText(MainActivity.this, "Bonjour " + u.getE().getNom() + " " + u.getE().getPrenom(), Toast.LENGTH_LONG).show();
                                    //Toast.makeText(MainActivity.this, u.getEmployee_id()+"", Toast.LENGTH_LONG).show();
                                    SharedPreferences.Editor editor = pref.edit();
                                    //editor.putString("login", String.valueOf(u.getId()));
                                    //editor.putString("password", String.valueOf(u.getPassword()));
                                    editor.putString("id", String.valueOf(u.getId()));
                                    editor.putString("id_employe", u.getEmployee_id()+"");
                                    editor.putString("nom", u.getE().getNom());
                                    editor.putString("prenom", u.getE().getPrenom());
                                    editor.putString("role", u.getRole());
                                    ///
                                    editor.putInt("nb_voy",0);
                                    editor.commit();

                                    startActivity(redirection);
                                    finish();
                                }else {
                                    log.setText("");
                                    pswd.setText("");
                                    Toast.makeText(MainActivity.this, "Verifiez votre login ou mot de passe", Toast.LENGTH_LONG).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            System.out.println(t.getLocalizedMessage());
                        }

                    });
                }
        }});
        /*Notification Runner*////////////////////////////////////////////////////////////////
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                retrofit.Call<List<Travel>> voyageCall = api.getUserTravels(pref.getString("id","id"),pref.getString("id_employe","id_employe"),pref.getString("role","role"));
                voyageCall.enqueue(new Callback<List<Travel>>() {
                    @Override
                    public void onResponse(Response<List<Travel>> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            List<Travel> lstTravel1 = (List<Travel>) response.body();
                            //  Toast.makeText(MainActivity.this,pref.getInt("nb_voy",0)+"  "+lstTravel1.size(),Toast.LENGTH_LONG).show();
                            if(pref.getInt("nb_voy",10) < lstTravel1.size()){
                                redirection = new Intent(MainActivity.this,voyage.class);
                                PendingIntent predirection = PendingIntent.getActivity(MainActivity.this,1,redirection, PendingIntent.FLAG_ONE_SHOT);
                                NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "Go", predirection).build();

                                NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(MainActivity.this)
                                        .setSmallIcon(android.R.drawable.btn_star_big_on)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                        .setContentTitle("Vous avez un nouveau voyage")
                                        .setContentText("Gestrans")
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                        .addAction(R.mipmap.ic_launcher, "Go", predirection);
                                notificationbuilder.setDefaults(
                                        Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE);
                                NotificationManagerCompat m = NotificationManagerCompat.from(MainActivity.this);
                                m.notify(1,notificationbuilder.build());
                            }
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putInt("nb_voy",lstTravel1.size());
                            editor.commit();

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
        mHandler.post(runnable);
        ////////////////////////////////////////////////////////////////////////////////
    }
}



