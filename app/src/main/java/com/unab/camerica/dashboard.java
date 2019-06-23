package com.unab.camerica;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.unab.camerica.adapters.PartidoAdapter;
import com.unab.camerica.model.Equipo;
import com.unab.camerica.model.Partido;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.dashboard);

    }


}




