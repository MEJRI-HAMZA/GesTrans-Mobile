package com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etudiantdsi.gestrans.Controller.HomePage;
import com.example.etudiantdsi.gestrans.Controller.MainActivity;
import com.example.etudiantdsi.gestrans.Controller.displayMap;
import com.example.etudiantdsi.gestrans.Controller.gestionIncident;
import com.example.etudiantdsi.gestrans.Model.Employee;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.Travel;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VoyageAdapter extends RecyclerView.Adapter<VoyageAdapter.VoyageViewHolder> {

    private SharedPreferences pref;
    private Intent redirection ;
    private List<Travel> listeTravel;
    private Context c;
    private Retrofit rf = RetrofitInstance.getRetroInstance();;
    private apiGesTrans api = rf.create(apiGesTrans.class);
    private AlertDialog.Builder alert;

    public VoyageAdapter(Context c , List<Travel> listeTravel) {
        this.c=c;
        this.listeTravel = listeTravel;
    }


    @Override
    public VoyageViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voyage_item_display,parent,false);
        VoyageViewHolder voyageViewHolder = new VoyageViewHolder(view);
        return voyageViewHolder;
    }

    public static class VoyageViewHolder extends RecyclerView.ViewHolder{
        ImageButton maps;
        TextView dateEtHeure,pointDepart,chauffeur,bus,etat;
        ImageButton start,end,incident;

        public VoyageViewHolder (View itemView){
            super(itemView);
            maps = itemView.findViewById(R.id.maps);
            dateEtHeure = itemView.findViewById(R.id.debIncident);
            pointDepart = itemView.findViewById(R.id.pointDepart);
            chauffeur = itemView.findViewById(R.id.driver);
            bus = itemView.findViewById(R.id.bus);
            etat = itemView.findViewById(R.id.etat);
            start = itemView.findViewById(R.id.startTravel);
            end = itemView.findViewById(R.id.endTravel);
            incident = itemView.findViewById(R.id.incident);

        }

    }

    @Override
    public void onBindViewHolder(final VoyageViewHolder holder, final int position) {
        final Travel v = listeTravel.get(position);
        //Affichage des boutton de gestion de voyage
        pref =this.c.getSharedPreferences(MainActivity.MY_PREFERENCES,Context.MODE_PRIVATE);
        if(pref.getString("role","role").trim().equals("employe")){
            holder.start.setVisibility(View.GONE);
            holder.end.setVisibility(View.GONE);
            holder.incident.setVisibility(View.GONE);
        }
        //Date et heure de voyage
        String[] chaine = v.getDeb_voyage().trim().split(" ");
        holder.dateEtHeure.setText(chaine[0]+" à "+chaine[1]);
        //Point de départ
        if(v.getType().trim().equals("sortie_site"))
            holder.pointDepart.setText("Devant le site");
        else
            holder.pointDepart.setText("Chez vous chère employé !");
        //Nom de chauffeur de voyage
        holder.chauffeur.setText("ERROR");
        //Bus de voyage
        holder.bus.setText(v.getBus().getPseudo());
        //Etat voyage
        holder.etat.setText(v.getEtat());
        if(!v.getEtat().trim().equals("planifie")){
            holder.etat.setTextColor(Color.GREEN);
        }
        //Click sur le boutton Start pour commencer le voyage
        holder.start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(v.getEtat().trim().equals("planifié")){

                alert = new AlertDialog.Builder(c);
                alert.setTitle("ATTENTION !!");
                alert.setMessage("Voulez vous vraiment commencer le voyage ?");
                alert.setPositiveButton("Commencer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*Intent intent =  new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mejrihamzaemail@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Avis pour début du voyage");
                        intent.putExtra(Intent.EXTRA_TEXT, "Bonjour \n Je vous signale le début du voyage "+v.getId()+"\n \n \n Bien Cordialement");
                        try {
                            c.startActivity(Intent.createChooser(intent, "Send mail..."));
                            Log.i("Terminer l'envoi ...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(c, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }*/
                        retrofit.Call<String> updateCall = api.updateTravel(String.valueOf(v.getId()), "en_cours");
                        updateCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                if (response.isSuccess()) {
                                    String rep = response.body();
                                    if (rep.trim().equals("true")) {
                                        Toast.makeText(c, "Etat voyage mis à jour ", Toast.LENGTH_LONG).show();
                                        v.setEtat("en_cours");
                                        holder.etat.setText("en_cours");
                                        holder.etat.setTextColor(Color.GREEN);
                                    } else
                                        Toast.makeText(c, "Mis à jour échouée", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                System.out.println(t.getMessage());
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
                else {
                    System.out.println("voyage en cours ou non inachevé");
                    alert = new AlertDialog.Builder(c);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Ce voyage a déja démmaré !!");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }

            }
        });
        //Click sur le boutton Terminer pour signaler la fin du voyage
        holder.end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.getEtat().trim().equals("en_cours")){

                    alert = new AlertDialog.Builder(c);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Voulez vous vraiment enregistrer la fin du voyage ?");
                    alert.setPositiveButton("Terminer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*Intent intent =  new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mejrihamzaemail@gmail.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Avis pour fin du voyage");
                            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour \n Je vous signale la fin du voyage "+v.getId()+"\n \n \n Bien Cordialement");
                            try {
                                c.startActivity(Intent.createChooser(intent, "Send mail..."));
                                Log.i("Terminer l'envoi ...", "");
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(c, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }*/
                            retrofit.Call<String> updateCall = api.updateTravel(String.valueOf(v.getId()), "termine");
                            updateCall.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Response<String> response, Retrofit retrofit) {
                                    if (response.isSuccess()) {
                                        String rep = response.body();
                                        if (rep.trim().equals("true")) {
                                            Toast.makeText(c, "Etat voyage mis à jour ", Toast.LENGTH_LONG).show();
                                            v.setEtat("termine");
                                            holder.etat.setText("Terminé");
                                            holder.etat.setTextColor(Color.RED);
                                        } else
                                            Toast.makeText(c, "Mis à jour échouée", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Throwable t) {
                                    System.out.println(t.getMessage());
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
                else {
                    alert = new AlertDialog.Builder(c);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Ce voyage n'a pas encore démmaré ou déja terminé!!");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
            }
        });
        //Click sur le boutton de gestion des incidents
        holder.incident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(v.getEtat().trim().equals("en_cours")) {
                    redirection = new Intent(c,gestionIncident.class);
                    redirection.putExtra("id",String.valueOf(v.getId()));
                    c.startActivity(redirection);

                }else{
                    System.out.println("Travel en Attente");
                    alert = new AlertDialog.Builder(c);
                    alert.setTitle("ATTENTION !!");
                    alert.setMessage("Ce voyage n'a pas encore démmaré ou terminé  !!");
                    alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }
            }
        });
        //ImageBoutton
        holder.maps.setImageResource(R.drawable.maps);
        holder.maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirection = new Intent(c,displayMap.class);
                redirection.putExtra("type",v.getType());
                String details ;
                ArrayList<String> listeDetails = new ArrayList<>();
                for(Employee e : v.getEmployees()){
                    details = e.getNom()+","+e.getPrenom()+","+e.getCoordonnees_gps()+","+e.getAdresse();
                    listeDetails.add(details);
                }
                redirection.putStringArrayListExtra("listeDetails",listeDetails);
                c.startActivity(redirection);
            }
        });

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#e7e9ff"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#CECEF6"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }


    }

    @Override
    public int getItemCount() {
        return listeTravel.size();
    }
}
