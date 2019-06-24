package com.unab.camerica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

public class Compartir extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);


        setContentView(R.layout.compartir);

        //BOTON PARA COMPARTIR
        Button batonCompacter = findViewById(R.id.btncompartirPublicar);
        //COMPARTE TEXTO DESDE LA APP
        batonCompacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Mis padres me enseñaron tres cosas fundamentales: que para poder estar orgulloso de ti mismo y ser alguien hace falta trabajar; que es preciso actuar con seriedad; y que debes respetar a los demás para recibir respeto a cambio. Trabajo, seriedad y respeto. Si haces estas tres cosas, podrás ser alguien en la vida, me dijeron.\n" +
                        "\n" +
                        "    “No quiero ser una estrella, prefiero ser un buen ejemplo para los niños”.\n" +
                        "\n" +
                        "    “Mis padres me educaron dándome cariño y protección.... Y eso que mi padre nunca me expresó su amor con palabras, nunca me dijo: Te quiero. Y, sin embargo, yo sabía que me quería más que a nada.\n" +
                        "Zinedine Zidane");
                startActivity(Intent.createChooser(intent, "Compartiendo"));
            }
        });

    }



}