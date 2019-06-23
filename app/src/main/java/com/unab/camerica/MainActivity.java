package com.unab.camerica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.model.Equipo;
import com.unab.camerica.model.Partido;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // TextView adResul;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);


      /*  adResul = (TextView) findViewById(R.id.adResul);

        adResul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, IngresoResultados.class);
                startActivity(i);
                //finish();
            }
        });*/

        PartidoAdapter adapter = new PartidoAdapter(this, cargarPartidos());
        ListView lista = findViewById(R.id.partidos);

        View header = getLayoutInflater().inflate(R.layout.partidos_header, null);

        lista.setAdapter(adapter);
        lista.addHeaderView(header);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.addresultados){
            Intent i = new Intent(MainActivity.this, IngresoResultados.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.verdashboard){
            Intent i = new Intent(MainActivity.this, dashboard.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }


    private Partido[] cargarPartidos() {
        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String stringPartidos = preferences.getString("partidos", "");
        String stringPaises = preferences.getString("paises", "");

        ArrayList<LinkedTreeMap<String, Object>> listaPartidos = gson.fromJson(stringPartidos, ArrayList.class);
        ArrayList<LinkedTreeMap<String, Object>> listaPaises = gson.fromJson(stringPaises, ArrayList.class);

        ArrayList<Partido> partidos = new ArrayList<>();
        for(LinkedTreeMap<String, Object> partidoActual: listaPartidos) {
            int localID = ((Double)partidoActual.get("local")).intValue();
            int visitaID = ((Double)partidoActual.get("visita")).intValue();
            int localGol = ((Double)partidoActual.get("localGol")).intValue();
            String lGol = Integer.toString(localGol);
            int visitaG = ((Double)partidoActual.get("visitGol")).intValue();
            String VGol = Integer.toString(visitaG);

            int estado = ((Double)partidoActual.get("estado")).intValue();


            LinkedTreeMap<String, Object> dataLocal = listaPaises.get(localID);
            LinkedTreeMap<String, Object> dataVisita = listaPaises.get(visitaID);
            //LinkedTreeMap<String, Object> dataLocalg = listaPartidos.get(localG);
            //LinkedTreeMap<String, Object> dataVisitag = listaPartidos.get(visitaG);

            if(estado == 1) { // controlar solo mostrar partidos con estado 1 que significa resultado ingresado
                Equipo local = new Equipo(
                        dataLocal.get("api_id").toString(),
                        (String) dataLocal.get("bandera"),
                        (String) dataLocal.get("codigo"),
                        (String) dataLocal.get("nombre")
                        //(String) dataLocalg.get("locaGol")
                        //dataLocalg.get("localGol").toString()
                );
                Equipo visita = new Equipo(
                        dataVisita.get("api_id").toString(),
                        (String) dataVisita.get("bandera"),
                        (String) dataVisita.get("codigo"),
                        (String) dataVisita.get("nombre")
                );


                Partido partido = new Partido(
                        local,
                        visita,
                        (String) partidoActual.get("hora"),
                        (String) partidoActual.get("fecha"),
                        lGol,
                        VGol,
                        0

                );



            partidos.add(partido);
            }
        }
        return partidos.toArray(new Partido[partidos.size()]);
    }

}
