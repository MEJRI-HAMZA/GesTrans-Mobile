package com.example.etudiantdsi.gestrans.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.etudiantdsi.gestrans.R;

public class flotte extends AppCompatActivity {

    private Intent redirection;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Notre flotte");
        setContentView(R.layout.activity_flotte);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_flotte,menu);
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
