package com.unab.camerica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class input_datos extends AppCompatActivity {

    TextView tvFecha;
    TextView nomPaisL;
    TextView nomPaisV;
    TextView banPaisL;
    TextView banPaisV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.input_datos);

        recibiDatos();
    }

    private void recibiDatos (){
        Bundle extra = getIntent().getExtras();

        String id = extra.getString("id");
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
