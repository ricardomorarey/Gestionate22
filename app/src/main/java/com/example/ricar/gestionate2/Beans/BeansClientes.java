package com.example.ricar.gestionate2.Beans;

/**
 * Created by ricar on 05/09/2016.
 */
public class BeansClientes {

    private String _id_c, telefono, nombre, email, direccion, dni;

    public String get_id_c() {
        return _id_c;
    }

    public void set_id_c(String _id_c) {
        this._id_c = _id_c;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BeansClientes(String _id_c, String telefono, String nombre, String email, String direccion, String dni) {
        super();
        this._id_c = _id_c;
        this.telefono = telefono;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.dni = dni;
    }

    @Override
    public String toString (){
        return this.nombre + this.telefono;
    }


}
