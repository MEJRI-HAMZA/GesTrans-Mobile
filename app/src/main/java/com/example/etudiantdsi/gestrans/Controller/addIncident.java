package com.example.etudiantdsi.gestrans.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters.IncidentAdapter;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class addIncident extends AppCompatActivity {

    private Intent intent,redirection;
    private SharedPreferences pref;
    private Retrofit rf;
    private apiGesTrans api;
    private EditText desc;
    private Spinner motif;
    private ImageButton add;
    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;
    private IncidentAdapter myIncidentAdapter;
    private ArrayAdapter<String> mySpinnerAdapter ;
    private String id,rep;
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Ajout d'incident");
        setContentView(R.layout.activity_add_incident);

        motif = (Spinner) findViewById(R.id.motif);
        String[] motifs={"panne","accident","autre"};
        mySpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,motifs);
        mySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motif.setAdapter(mySpinnerAdapter);

        desc = (EditText) findViewById(R.id.desc);
        add = (ImageButton) findViewById(R.id.BtnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desc.getText().toString().trim().equals(""))
                    desc.setError("Veuillez saisir une description pour cet incident");
                else{
                    alert = new AlertDialog.Builder(addIncident.this);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Voulez vous vraiment ajouter cet Incident ?");
                    alert.setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            try{ intent = getIntent();
                            }catch (Exception e){ e.getStackTrace(); }
                            id = intent.getStringExtra("id");
                            rf = RetrofitInstance.getRetroInstance();
                            api = rf.create(apiGesTrans.class);

                            /*Intent intent =  new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mejrihamzaemail@gmail.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Avis d'incident");
                            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour \n Je vous signale que nous avons eu un incident pendant le voyage "+id+"\n Motif       :"+motif.getSelectedItem().toString()+"\n Description   : "+desc.getText().toString()+"\n \n \n Bien Cordialement");
                            try {
                                startActivity(Intent.createChooser(intent, "Send mail..."));
                                Log.i("Terminer l'envoi ...", "");
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(addIncident.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }*/

                            //Toast.makeText(addIncident.this,id,Toast.LENGTH_LONG).show();
                            retrofit.Call<String> addCall = api.addIncident(motif.getSelectedItem().toString(),desc.getText().toString(),id);
                            addCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    rep = response.body();
                                    if (response.isSuccess()){
                                        Toast.makeText(addIncident.this,"Incident ajouté",Toast.LENGTH_LONG).show();
                                        redirection = new Intent(addIncident.this,voyage.class);
                                        startActivity(redirection);
                                        finishAffinity();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    t.getStackTrace();
                                }
                            });
                        }
                    });
                    alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_addincident,menu);
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
                redirection = new Intent(this, HomePage.class);
                startActivity(redirection);
                return true;
            case R.id.entreprise:
                redirection = new Intent(this, entreprise.class);
                startActivity(redirection);
                return true;
            case R.id.flotte:
                redirection = new Intent(this, flotte.class);
                startActivity(redirection);
                return true;
            case R.id.travels:
                redirection = new Intent(this, voyage.class);
                startActivity(redirection);
                return true;
            case R.id.travelsIcone:
                redirection = new Intent(this,voyage.class);
                startActivity(redirection);
                return true;
            case R.id.logout:
                redirection = new Intent(this, MainActivity.class);
                pref = getSharedPreferences(MainActivity.MY_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                redirection = new Intent(this, MainActivity.class);
                startActivity(redirection);
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
