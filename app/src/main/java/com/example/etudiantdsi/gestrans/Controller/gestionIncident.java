package com.example.etudiantdsi.gestrans.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters.IncidentAdapter;
import com.example.etudiantdsi.gestrans.Model.Incident;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class gestionIncident extends AppCompatActivity {

    private Intent intent,redirection;
    private SharedPreferences pref;
    private Retrofit rf;
    private apiGesTrans api;
    private TextView title;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    private IncidentAdapter myIncidentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Gestion des incidents");
        setContentView(R.layout.activity_gestion_incident);
        try{
            intent = getIntent();
        }catch (Exception e){
            e.getStackTrace();
        }
        title = (TextView) findViewById(R.id.desc) ;
        rf = RetrofitInstance.getRetroInstance();
        api = rf.create(apiGesTrans.class);
        retrofit.Call<List<Incident>> incidentCall = api.getTravelIncidents(intent.getStringExtra("id"));
        incidentCall.enqueue(new Callback<List<Incident>>() {
            @Override
            public void onResponse(Response<List<Incident>> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    List<Incident> listeIncidents = (List<Incident>) response.body();
                    if(listeIncidents.size()==0)
                        title.setText("Pas d'incidents pour ce voyage");
                    else {
                        title.setText("Liste des incidents");
                        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_incident);
                        myLayoutManager = new LinearLayoutManager(gestionIncident.this);
                        myRecyclerView.setLayoutManager(myLayoutManager);
                        myIncidentAdapter = new IncidentAdapter(gestionIncident.this, listeIncidents);
                        myRecyclerView.setAdapter(myIncidentAdapter);
                    }
                }
            }


            @Override
            public void onFailure(Throwable t) {
                t.getStackTrace();
            }
        });

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_incident,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeIcone:
                redirection = new Intent(this,HomePage.class);
                startActivity(redirection);
                return true;
            case R.id.home:
                redirection = new Intent(this,HomePage.class);
                startActivity(redirection);
                return true;
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
            case R.id.addIncident:
                try{
                    intent = getIntent();
                }catch (Exception e){
                    e.getStackTrace();
                }
                redirection = new Intent(this,addIncident.class);
                redirection.putExtra("id",intent.getStringExtra("id"));
                startActivity(redirection);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
