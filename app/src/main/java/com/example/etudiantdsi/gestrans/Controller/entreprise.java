package com.example.etudiantdsi.gestrans.Controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.etudiantdsi.gestrans.R;

public class entreprise extends AppCompatActivity {

    private Intent redirection;
    private SharedPreferences pref;
    private TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("A propos de nous");
        setContentView(R.layout.activity_entreprise);

        desc = (TextView) findViewById(R.id.desc);
        desc.setText("        Pionnier des centres d'appel en Tunisie, notre société a été crée en 1998, spécialisé dans le contact multi canal pour le service client, la vente et le marketing.\n" +
                "\n" +
                "        1300 m2 aménagés pour les besoins de l’activité, 250 positions actives dédiées à la production téléphonique et BPO, 24 bureaux d’encadrement de proximité (supervisions, coach, contrôle qualité) à 90% en open space auxquels s’ajoutent les postes d’encadrement de niveaux supérieurs. Le centre situé à Tunis compte également 3 salles de formation et 6 salles de pause.\n");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_entreprise,menu);
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
