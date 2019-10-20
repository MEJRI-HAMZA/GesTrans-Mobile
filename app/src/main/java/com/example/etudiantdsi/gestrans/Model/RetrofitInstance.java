package com.example.etudiantdsi.gestrans.Model;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitInstance {


   public  static retrofit.Retrofit getRetroInstance(){
            //Création d’un objet retrofit qui nous permet de nous connecter au fournisseur du web service
            retrofit.Retrofit RF =new retrofit.Retrofit.Builder()
                    .baseUrl("http://192.168.1.19:80")
                    //.baseUrl("http://127.0.0.1:8000")
                    //.baseUrl("http://localhost:8000")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return RF;
        }


}
