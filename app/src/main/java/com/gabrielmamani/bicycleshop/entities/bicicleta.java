package com.gabrielmamani.bicycleshop.entities;

/**
 * Created by gabrielmamani on 1/24/18.
 */

public class bicicleta {

    final public static String BICYCLE_REFERENCE = "bicicleta";

    public String key;
    public int cantidad;
    public String marca;
    public String nombre_bici;
    public double precio;
    public String tipo;
    public String detalle;
    public String image;

    public bicicleta() {
    }

    public bicicleta(String key, int cantidad, String marca, String nombre_bici, double precio, String tipo, String detalle, String image) {
        this.key = key;
        this.cantidad = cantidad;
        this.marca = marca;
        this.nombre_bici = nombre_bici;
        this.precio = precio;
        this.tipo = tipo;
        this.detalle = detalle;
        this.image = image;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNombre_bici() {
        return nombre_bici;
    }

    public void setNombre_bici(String nombre_bici) {
        this.nombre_bici = nombre_bici;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
