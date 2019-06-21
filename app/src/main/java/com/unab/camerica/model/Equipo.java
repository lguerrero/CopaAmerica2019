package com.unab.camerica.model;

import java.io.Serializable;

public class Equipo implements Serializable {
    String apiId;
    String bandera;
    String codigo;
    String nombre;

    public Equipo(String apiId, String bandera, String codigo, String nombre) {
        this.apiId = apiId;
        this.bandera = bandera;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Equipo(String api_id, String bandera, String codigo, String nombre, String locaGol) {
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
