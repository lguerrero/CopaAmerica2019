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


      /*  adResul = (TextView) findViewById(R.id.adResul);

        adResul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, IngresoResultados.class);
                startActivity(i);
                //finish();
            }
        });*/

        final PartidoAdapter adapter = new PartidoAdapter(this, cargarPartidos());
        final ListView lista = findViewById(R.id.partidos);

        View header = getLayoutInflater().inflate(R.layout.partidos_header, null);

        lista.setAdapter(adapter);
        //lista.addHeaderView(header);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Object ObjetoQl = adapter.getItem(position);

                String fecha    = ((Partido) ObjetoQl).getFecha().toString();
                String EquipoL  = ((Partido) ObjetoQl).getLocal().getNombre().toString();
                String EquipoV  = ((Partido) ObjetoQl).getVisita().getNombre().toString();
                String codBanL  = ((Partido) ObjetoQl).getLocal().getBandera().toString();
                String conBanV  =   ((Partido) ObjetoQl).getVisita().getBandera().toString();

                Intent i = new Intent(IngresoResultados.this , input_datos.class);
                i.putExtra("id", position);
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
            Intent i = new Intent(this, MainActivity.class);
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


            int estado = ((Double)partidoActual.get("estado")).intValue();
            //int visitaG = 0;//((Double)partidoActual.get("visitGol")).intValue();

            LinkedTreeMap<String, Object> dataLocal = listaPaises.get(localID);
            LinkedTreeMap<String, Object> dataVisita = listaPaises.get(visitaID);
            //LinkedTreeMap<String, Object> dataLocalg = listaPartidos.get(localG);
            //LinkedTreeMap<String, Object> dataVisitag = listaPartidos.get(visitaG);

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
                        partidoActual.get("visitGol").toString(),
                        partidoActual.get("localGol").toString()
                );



                partidos.add(partido);
            }
        }
        return partidos.toArray(new Partido[partidos.size()]);
    }


}

