package com.example.ricar.gestionate2.Beans;

/**
 * Created by ${"Ricardo"} on 22/09/2016.
 */

class BeansEmailing {

    private String _id_email;
    private String _id_c;
    private String nombre;
    private String email;

    public String get_id_email() {
        return _id_email;
    }

    public void set_id_email(String _id_email) {
        this._id_email = _id_email;
    }

    public String get_id_c() {
        return _id_c;
    }

    public void set_id_c(String _id_c) {
        this._id_c = _id_c;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BeansEmailing(String _id_email, String _id_c, String nombre, String email) {
        this._id_email = _id_email;
        this._id_c = _id_c;
        this.nombre = nombre;
        this.email = email;
    }

    @Override
    public String toString() {
        return "BeansEmailing{" +
                "_id_email='" + _id_email + '\'' +
                ", _id_c='" + _id_c + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
