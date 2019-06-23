package com.unab.camerica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.unab.camerica.Objetos.FirerebaseReferenceInput;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;


public class input_datos extends AppCompatActivity {

    TextView tvFecha;
    TextView nomPaisL;
    TextView nomPaisV;
    TextView banPaisL;
    TextView banPaisV;
    EditText num1local;
    EditText num2Visita;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.input_datos);

        recibiDatos();

        Bundle extra = getIntent().getExtras();

        Object pos = extra.getInt("pos");

        final String nodo = pos.toString();

        final String subnodoL = "localGol";
        final String subnodoV = "visitGol";

        final String estado = "estado";

        //Toast.makeText(this, " "+nodo+" ", Toast.LENGTH_SHORT).show();

        FirebaseDatabase database = FirebaseDatabase.getInstance(); // se obtiene la instancia Firebase
        final DatabaseReference myRef = database.getReference(FirerebaseReferenceInput.INPUT_REFERENCE); //generamos una referencia con variable estatica contenida en pack objetos

        num1local = (EditText)findViewById(R.id.num1);
        num2Visita = (EditText)findViewById(R.id.num2);



        //myRef.child(nodo).child(subnodo).setValue(88);


        //myRef.addValueEventListener(ValueEventListener);


        Button button= findViewById(R.id.envia);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                 String golLocal = num1local.getText().toString();
                 String golVisita = num2Visita.getText().toString();

                int golLocalE = Integer.parseInt(golLocal);
                int golVisitaE = Integer.parseInt(golVisita);

                myRef.child(nodo).child(estado).setValue(1);
                myRef.child(nodo).child(subnodoL).setValue(golLocalE);
                myRef.child(nodo).child(subnodoV).setValue(golVisitaE);
                Toast.makeText(input_datos.this, "Datos guardados correctamente.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(input_datos.this, SplashActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    public void recibiDatos (){
        Bundle extra = getIntent().getExtras();

        String fecha  = extra.getString("fecha");
        String nombreLocal = extra.getString("nombreLocal");
        String nombreVisita = extra.getString("nombreVisita");
        String codBanL = extra.getString("codBanL");
        String codBanV = extra.getString("codBanV");

        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvFecha.setText(fecha);

        nomPaisL = (TextView) findViewById(R.id.nomPaisL);
        nomPaisL.setText(nombreLocal);

        nomPaisV = (TextView) findViewById(R.id.nomPaisV);
        nomPaisV.setText(nombreVisita);

        banPaisL = (TextView) findViewById(R.id.banPaisL);
        banPaisL.setText(codBanL);

        banPaisV = (TextView) findViewById(R.id.banPaisV);
        banPaisV.setText(codBanV);


    }



}
