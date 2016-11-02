package com.example.ricar.gestionate2.Beans;

/**
 * Created by Ricardo Gonzalez Mora-Rey on 09/09/2016.
 */
public class BeansFacturasIP {


    private String _id_fip;
    private String fechaip;
    private String importeip;
    private String _id_c;

    public String get_id_fip() {
        return _id_fip;
    }

    public void set_id_fip(String _id_fip) {
        this._id_fip = _id_fip;
    }

    public String get_id_c() {
        return _id_c;
    }

    public void set_id_c(String _id_c) {
        this._id_c = _id_c;
    }

    public String getImporteip() {
        return importeip;
    }

    public void setImporteip(String importeip) {
        this.importeip = importeip;
    }

    public String getFechaip() {
        return fechaip;
    }

    public void setFechaip(String fechaip) {
        this.fechaip = fechaip;
    }

    public BeansFacturasIP(String _id_fip, String fechaip, String importeip, String _id_c) {
        this._id_fip = _id_fip;
        this.fechaip = fechaip;
        this.importeip = importeip;
        this._id_c = _id_c;
    }

    @Override
    public String toString (){
        return this.fechaip.concat(" importe:"+importeip);
    }

}


