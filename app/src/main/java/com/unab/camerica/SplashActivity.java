package com.unab.camerica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {

    ArrayList<HashMap<String, Object>> listaDePaises = new ArrayList<>();
    ArrayList<HashMap<String, Object>> listaDePartidos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        cargarPaises();
    }

    private void cargarPaises() {
        DatabaseReference paisesDB = FirebaseDatabase.getInstance().getReference().child("paises");
        paisesDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot pais : dataSnapshot.getChildren()) {
                    listaDePaises.add((HashMap)pais.getValue());
                }
                cargarPartidos();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void cargarPartidos() {
        DatabaseReference partidosDB = FirebaseDatabase.getInstance().getReference().child("partidos");
        partidosDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot partido : dataSnapshot.getChildren()) {
                    listaDePartidos.add((HashMap)partido.getValue());
                }
                setData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setData() {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();

        String stringPaises = gson.toJson(listaDePaises);
        String stringPartidos = gson.toJson(listaDePartidos);

        editor.putString("paises", stringPaises);
        editor.putString("partidos", stringPartidos);

        editor.commit();

        goToMain();
    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
