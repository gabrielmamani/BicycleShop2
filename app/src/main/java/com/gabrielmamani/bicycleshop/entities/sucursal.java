package com.gabrielmamani.bicycleshop.entities;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by gabrielmamani on 1/6/18.
 */

//@SuppressWarnings("serial")
public class sucursal implements Serializable,Comparable<sucursal> {

    final public static String SUCURSAL_REFERENCE = "sucursal";

    public String nit;
    public String empresa;
    public String direccion;
    public String key;

    public sucursal() {
    }

    public sucursal(String nit, String empresa, String direccion) {
        this.nit = nit;
        this.empresa = empresa;
        this.direccion = direccion;
    }

    public sucursal(String nit, String empresa, String direccion, String key) {
        this.nit = nit;
        this.empresa = empresa;
        this.direccion = direccion;
        this.key = key;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(@NonNull sucursal o) {
        String a=new String(String.valueOf(this.getNit()));
        String b=new String(String.valueOf(o.getNit()));
        return a.compareTo(b);
    }

}
