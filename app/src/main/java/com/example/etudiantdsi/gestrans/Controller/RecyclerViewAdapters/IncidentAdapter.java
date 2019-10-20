package com.example.etudiantdsi.gestrans.Controller.RecyclerViewAdapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.etudiantdsi.gestrans.Model.Incident;
import com.example.etudiantdsi.gestrans.Model.RetrofitInstance;
import com.example.etudiantdsi.gestrans.Model.apiGesTrans;
import com.example.etudiantdsi.gestrans.R;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {

    private List<Incident> listeIncident;
    private Context c;
    private AlertDialog.Builder alert;
    private Retrofit rf = RetrofitInstance.getRetroInstance();
    private apiGesTrans api = rf.create(apiGesTrans.class);
    private String rep;

    public IncidentAdapter(Context c,List<Incident> listeIncident) {
        this.listeIncident = listeIncident;
        this.c=c;
    }

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.incident_item_display,parent,false);
        IncidentViewHolder incidentViewHolder = new IncidentViewHolder(view);
        return incidentViewHolder;
    }

    public static class IncidentViewHolder extends RecyclerView.ViewHolder{
        TextView debIncident,finIncident,motif,desc,etat;
        ScrollView scroll;
        ImageButton endIncident,endTravel;

        public IncidentViewHolder(View itemView){
            super(itemView);
            debIncident= itemView.findViewById(R.id.debIncident);
            finIncident= itemView.findViewById(R.id.finIncident);
            motif= itemView.findViewById(R.id.motif);
            desc= itemView.findViewById(R.id.desc);
            etat=itemView.findViewById(R.id.etat);
            endIncident= itemView.findViewById(R.id.endIncident);
            endTravel= itemView.findViewById(R.id.endTravel);
        }
    }

    @Override
    public void onBindViewHolder(final IncidentViewHolder holder, final int position) {
        final Incident i = listeIncident.get(position);
        String[] chaine = i.getDeb_incident().trim().split(" ");
        holder.debIncident.setText(chaine[0] + " à " + chaine[1]);
        holder.finIncident.setText(i.getFin_incident());
        if (i.getFin_incident()==null) {
            holder.etat.setText("En_cours");
            holder.etat.setTextColor(Color.RED);
            holder.finIncident.setText("Incident en cours");
        }else {
            holder.etat.setText("Résolu");
            holder.etat.setTextColor(Color.GREEN);
            holder.endIncident.setVisibility(View.GONE);
            holder.endTravel.setVisibility(View.GONE);
        }
        holder.motif.setText(i.getMotif());
        holder.desc.setText(i.getDescription());
        //Click sur fin incident
        holder.endIncident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(c);
                alert.setTitle("ATTENTION !!");
                alert.setMessage("Voulez vous vraiment enregistrer la fin du cet Incident ?");
                alert.setPositiveButton("fin Incident", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        /*Intent intent =  new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mejrihamzaemail@gmail.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Avis pour résolution d'incident");
                            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour \n Je vous signale la résolution de l'incident "+i.getId()+" du voyage "+i.getId_travel()+".\n Motif          :"+i.getMotif()+" \n Description   :"+i.getDescription()+"\n \n Bien Cordialement");
                            try {
                                c.startActivity(Intent.createChooser(intent, "Send mail..."));
                                Log.i("Terminer l'envoi ...", "");
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(c, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }*/

                        retrofit.Call<String> updateCall = api.updateIncident(i.getId(),"resolu");
                        updateCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                if(response.isSuccess()){
                                    rep = response.body();
                                    if (rep.trim().equals("true")) {
                                        holder.finIncident.setText(i.getFin_incident());
                                        holder.etat.setText("Résolu");
                                        holder.etat.setTextColor(Color.GREEN);
                                        holder.endIncident.setVisibility(View.GONE);
                                        holder.endTravel.setVisibility(View.GONE);
                                        Toast.makeText(c, "Etat incident mis à jour ", Toast.LENGTH_LONG).show();
                                    }else
                                        Toast.makeText(c, "Mis à jour échouée", Toast.LENGTH_LONG).show();
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
        });
        //Click sur Fin voyage
        holder.endTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = new AlertDialog.Builder(c);
                alert.setTitle("ATTENTION !!");
                alert.setMessage("Voulez vous vraiment signaler la fin du voyage (Incident persiste!) ?");
                alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        /*Intent intent =  new Intent(Intent.ACTION_SEND);
                            intent.setType("message/rfc822");
                            intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"mejrihamzaemail@gmail.com"});
                            intent.putExtra(Intent.EXTRA_SUBJECT, "Avis pour inachevèvement du voyage");
                            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour \n Je vous signale l'inachèvement du voyage "+i.getId_travel()+" , à cause de l'incident "+i.getId()+"\n Motif          :"+i.getMotif()+" \n Description   :"+i.getDescription()+"\n \n \n Bien Cordialement");
                            try {
                                c.startActivity(Intent.createChooser(intent, "Send mail..."));
                                Log.i("Terminer l'envoi ...", "");
                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(c, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                            }*/

                        retrofit.Call<String> updateTCall = api.updateTravel(i.getId_travel(), "inacheve");
                        updateTCall.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                if (response.isSuccess()) {
                                    rep = response.body();
                                    if (rep.trim().equals("true")) {
                                        retrofit.Call<String> updateCall = api.updateIncident(i.getId(),"persiste");
                                        updateCall.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                               rep = response.body();
                                               if(response.isSuccess()){
                                                   holder.finIncident.setText(i.getFin_incident());
                                                   holder.etat.setText("Persiste");
                                                   holder.etat.setTextColor(Color.RED);
                                                   holder.endIncident.setVisibility(View.GONE);
                                                   holder.endTravel.setVisibility(View.GONE);
                                                   Toast.makeText(c, "Etat Travel et Incident mis à jour ", Toast.LENGTH_LONG).show();
                                               }else
                                                   Toast.makeText(c, "Mis à jour échouée ", Toast.LENGTH_LONG).show();

                                            }

                                            @Override
                                            public void onFailure(Throwable t) {
                                                t.getStackTrace();
                                            }
                                        });


                                    } else
                                        Toast.makeText(c, "Mis à jour échouée", Toast.LENGTH_LONG).show();
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
        });
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#f6eaff"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#D8CEF6"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
    }

    @Override
    public int getItemCount() {
        return listeIncident.size();
    }

}
