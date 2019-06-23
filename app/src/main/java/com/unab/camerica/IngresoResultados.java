package com.unab.camerica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.model.Equipo;
import com.unab.camerica.model.Partido;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class IngresoResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_ingreso_resultados);


        final PartidoAdapter adapter = new PartidoAdapter(this, cargarPartidos());
        final ListView lista = findViewById(R.id.partidos);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Object ObjetoQl = adapter.getItem(position);

                int pos = ((Partido) ObjetoQl).getPos();
                String fecha    = ((Partido) ObjetoQl).getFecha();
                String EquipoL  = ((Partido) ObjetoQl).getLocal().getNombre();
                String EquipoV  = ((Partido) ObjetoQl).getVisita().getNombre();
                String codBanL  = ((Partido) ObjetoQl).getLocal().getBandera();
                String conBanV  =   ((Partido) ObjetoQl).getVisita().getBandera();

                Intent i = new Intent(IngresoResultados.this , input_datos.class);
                i.putExtra("pos", pos);
                i.putExtra("fecha",fecha);
                i.putExtra("nombreLocal", EquipoL);
                i.putExtra("nombreVisita", EquipoV);
                i.putExtra("codBanL" , codBanL);
                i.putExtra("codBanV", conBanV);

                //Toast.makeText(IngresoResultados.this, " "+EquipoL+" ", Toast.LENGTH_SHORT).show();
                //Log.i("MIRA", adapter1.getView(position, View, );
                startActivity(i);
        }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.addresultados){
            Intent i = new Intent(this, IngresoResultados.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.verdashboard){
            Intent i = new Intent(this, dashboard.class);
            startActivity(i);
        }
        if(item.getItemId()==R.id.verResult){
            Intent i = new Intent(this, SplashActivity.class);
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
        int pos = 0;
        for(LinkedTreeMap<String, Object> partidoActual: listaPartidos) {
            int localID = ((Double)partidoActual.get("local")).intValue();
            int visitaID = ((Double)partidoActual.get("visita")).intValue();

            String lGol = "-";
            String VGol = "-";

            int estado = ((Double)partidoActual.get("estado")).intValue();

            LinkedTreeMap<String, Object> dataLocal = listaPaises.get(localID);
            LinkedTreeMap<String, Object> dataVisita = listaPaises.get(visitaID);

            if(estado == 0) { // controlar solo mostrar partidos con estado 1 que significa resultado ingresado
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
                        pos
                );



                partidos.add(partido);
            }
            pos = pos+1;
        }
        return partidos.toArray(new Partido[partidos.size()]);
    }


}

