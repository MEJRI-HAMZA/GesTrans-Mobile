package com.example.etudiantdsi.gestrans.Controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.etudiantdsi.gestrans.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class displayMap extends FragmentActivity implements OnMapReadyCallback {

    public static final String GOOGLE_MAP_API_KEY ="AIzaSyAI1Y1X3QJvA8PVONe2tZJwDCxBorv3Ne0";
    private GoogleMap mMap;
    private Intent i;
    private MarkerOptions
            siteMarker = new MarkerOptions()
            .position(new LatLng(36.833917, 10.206131))
            .title("ARRIVEE")
            .snippet("Notre Site"),
            ramassageMarker = new MarkerOptions()
                    .position(new LatLng(36.800770, 10.191701))
                    .title("DEPART")
                    .snippet("Point de ramassage Tunis Marine");
    private List<MarkerOptions> listeMarkers = new ArrayList<>();
    private LatLng pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);
        try {
            i = getIntent();
            createMarkers(i);
        }catch(Exception e){
            e.getStackTrace();
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        for (MarkerOptions m : listeMarkers) googleMap.addMarker(m);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.784782,10.174219),15));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
    }

    public void createMarkers (Intent i ){
        if(i.getStringExtra("type").trim().equalsIgnoreCase("sortie_site")){
            listeMarkers.add(siteMarker
                    .title("DEPART")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            ArrayList<String> details = i.getStringArrayListExtra("listeDetails");
            for ( String s : details){
                String[] det = s.split(",");
                pos = new LatLng(Double.parseDouble(det[2].trim()), Double.parseDouble(det[3].trim()));
                listeMarkers.add(new MarkerOptions().position(pos).title(det[0]+" "+det[1]).snippet("Adress :"+det[4]));
            }
        }else {
            listeMarkers.add(ramassageMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            listeMarkers.add(siteMarker);
        }
    }

}
