package com.unab.camerica.adapters;

import android.content.Context;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.unab.camerica.R;
import com.unab.camerica.model.Partido;

public class PartidoAdapter extends ArrayAdapter<Partido> {

    public PartidoAdapter(Context context, Partido[] data) {
        super(context, R.layout.partido, data);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        Partido actual = this.getItem(position);
        View item = convertView;

        PartidoHolder holder;

        if(item == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            item = inflater.inflate(R.layout.partido, null);
            holder = new PartidoHolder();
            holder.codigoLocal = item.findViewById(R.id.cod_local);
            holder.codigoVisita = item.findViewById(R.id.cod_visita);
            holder.banderaLocal = item.findViewById(R.id.bandera_local);
            holder.banderaVisita = item.findViewById(R.id.bandera_visita);
            holder.hora = item.findViewById(R.id.hora);
            holder.fecha = item.findViewById(R.id.fecha);
            holder.localGol = item.findViewById(R.id.localGol);
            holder.visitGol = item.findViewById(R.id.visitGol);


            item.setTag(holder);
        } else {
            holder = (PartidoHolder)item.getTag();
        }

        holder.codigoLocal.setText(actual.getLocal().getCodigo());
        holder.codigoVisita.setText(actual.getVisita().getCodigo());
        holder.banderaLocal.setText(actual.getLocal().getBandera());
        holder.banderaVisita.setText(actual.getVisita().getBandera());
        holder.fecha.setText(actual.getFecha());
        holder.hora.setText(actual.getHora());
        holder.localGol.setText(actual.getLocalGol());
        holder.visitGol.setText(actual.getVisitGol());

        return item;
    }



    private class PartidoHolder {
        TextView codigoLocal;
        TextView codigoVisita;
        TextView banderaLocal;
        TextView banderaVisita;
        TextView hora;
        TextView fecha;
        TextView localGol;
        TextView visitGol;
    }
}
