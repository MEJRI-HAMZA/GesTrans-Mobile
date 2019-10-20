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

import com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters.VoyageAdapter;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.Travel;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class voyage extends AppCompatActivity {

    private Intent redirection;
    private SharedPreferences pref;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    private VoyageAdapter voyageAdapter;
    private Retrofit rf;
    private apiGesTrans api;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Mes voyages");
        setContentView(R.layout.activity_voyage);

        pref =this.getSharedPreferences(MainActivity.MY_PREFERENCES,Context.MODE_PRIVATE);
        //Toast.makeText(this,  pref.getString("id","id")+pref.getString("id_employe","id_employe")+pref.getString("role","role"), Toast.LENGTH_LONG).show();

        desc = (TextView) findViewById(R.id.desc);
        rf = RetrofitInstance.getRetroInstance();
        api = rf.create(apiGesTrans.class);
        retrofit.Call<List<Travel>> voyageCall = api.getUserTravels(pref.getString("id","id"),pref.getString("id_employe","id_employe"),pref.getString("role","role"));
        voyageCall.enqueue(new Callback<List<Travel>>() {
            @Override
            public void onResponse(Response<List<Travel>> response, Retrofit retrofit) {
                if(response.isSuccess()){
                    List<Travel> listeTravels = (List<Travel>) response.body();
                    if(listeTravels.size()==0)
                        desc.setText("Aucun voyage planifié ,verifier plus tard");
                    else {
                        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                        myLayoutManager = new LinearLayoutManager(voyage.this);
                        myRecyclerView.setLayoutManager(myLayoutManager);
                        voyageAdapter = new VoyageAdapter(voyage.this, listeTravels);
                        myRecyclerView.setAdapter(voyageAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        });


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_voyage,menu);
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
