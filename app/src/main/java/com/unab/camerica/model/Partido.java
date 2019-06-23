package com.unab.camerica.model;

import java.io.Serializable;

public class Partido implements Serializable {
    Equipo local;
    Equipo visita;
    String hora;
    String fecha;
    String localGol;
    String visitGol;
    int pos;


    public Partido(Equipo local, Equipo visita, String hora, String fecha, String localGol, String visitGol, int pos ) {
        this.local = local;
        this.visita = visita;
        this.hora = hora;
        this.fecha = fecha;
        this.localGol = localGol;
        this.visitGol = visitGol;
        this.pos = pos;

    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisita() {
        return visita;
    }

    public void setVisita(Equipo visita) {
        this.visita = visita;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLocalGol() {
        return localGol;
    }

    public void setLocalGol(String localGol) {
        this.localGol = localGol;
    }

    public String getVisitGol() {
        return visitGol;
    }

    public void setVisitGol(String visitGol) {
        this.visitGol = visitGol;
    }
}
