package com.example.etudiantdsi.gestrans.Model;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface apiGesTrans {

    //Getting user
    @GET("gestrans/list.json")
    Call<User>getUser(@Query("id") String id );

    //Getting user travel
    @GET("gestrans/travels.json")
    Call<List<Travel>>getUserTravels(
            @Query("id") String id ,
            @Query("employee_id") String employee_id ,
            @Query("role") String role );

    //Getting travel incidents
    @GET("gestrans/incidents.json")
    Call<List<Incident>>getTravelIncidents(@Query("id_travel") String id );

    //update Travel
    @POST("gestrans/updateTravel.php")
    @FormUrlEncoded
    Call<String> updateTravel(@Field("id") String id,
                              @Field("etat") String etat);

    //update Incident
    @POST("gestrans/updateIncident.php")
    @FormUrlEncoded
    Call<String> updateIncident(@Field("id") String id,
                                @Field("etat") String etat);

    //add incident
    @POST("gestrans/addIncident.php")
    @FormUrlEncoded
    Call<String> addIncident(@Field("motif") String motif ,
                             @Field("description") String description,
                             @Field("travel_id") String id
    );





    /*@GET("api/user/{id}")
    Call<User>getUser(@Path("id") String id);

    @GET("gestrans/voyage.json")
    Call<List<Travel>>getAllTravel();



    @GET("employees/{id}")
    Call<Driver>getUser2(@Path("id") String id);

    @GET("gestrans/updateTravel.php")
    Call<String>update(
            @Query("id") String id ,
            @Query("etat") String etat
    );

    @GET("gestrans/addIncident.php")
    Call<String>ajouterIncident(
            @Query("motif") String motif ,
            @Query("description") String description,
            @Query("travel_id") String id
    );

    @GET("gestrans/incidents.php")
    Call<List<Incident>>getIncidents(@Query("id") String id);

    /*@GET("gestrans/updateIncident.php")
    Call<String>updateIncident(
            @Query("id") String id,
            @Query("etat") String etat
    );*/






}
