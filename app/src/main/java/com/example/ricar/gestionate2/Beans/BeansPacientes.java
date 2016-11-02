package com.example.ricar.gestionate2.Beans;

/**
 * Created by ricar on 07/09/2016.
 */
public class BeansPacientes {

    private  String _id_p;
    private String nombrepaciente;
    private String animal;
    private String raza;
    private String sexo;
    private String historial;
    private String _id_c;

    public String get_id_p() {
        return _id_p;
    }

    public void set_id_p(String _id_p) {
        this._id_p = _id_p;
    }

    public String get_id_c() {
        return _id_c;
    }

    public void set_id_c(String _id_c) {
        this._id_c = _id_c;
    }

    public String getNombrepaciente() {
        return nombrepaciente;
    }

    public void setNombrepaciente(String nombrepaciente) {
        this.nombrepaciente = nombrepaciente;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public BeansPacientes(String _id_p, String nombrepaciente, String animal, String raza, String sexo, String historial, String _id_c) {
        this._id_p = _id_p;
        this.nombrepaciente = nombrepaciente;
        this.animal = animal;
        this.raza = raza;
        this.sexo = sexo;
        this.historial = historial;
        this._id_c = _id_c;
    }

    @Override
    public String toString (){
        return this.nombrepaciente.concat(" - "+animal).concat(" - "+raza);
    }

}
