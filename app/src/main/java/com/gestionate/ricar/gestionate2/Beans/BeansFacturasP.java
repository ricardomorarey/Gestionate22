package com.gestionate.ricar.gestionate2.Beans;

/**
 * Created by ricar on 07/09/2016.
 */
public class BeansFacturasP {

    private String _id_fp;
    private String fechap;
    private String importep;
    private String _id_c;

    public String get_id_fp() {
        return _id_fp;
    }

    public void set_id_fp(String _id_fp) {
        this._id_fp = _id_fp;
    }

    public String get_id_c() {
        return _id_c;
    }

    public void set_id_c(String _id_c) {
        this._id_c = _id_c;
    }

    public String getFechap() {
        return fechap;
    }

    public void setFechap(String fechap) {
        this.fechap = fechap;
    }

    public String getImportep() {
        return importep;
    }

    public void setImportep(String importep) {
        this.importep = importep;
    }

    public BeansFacturasP(String _id_fp, String fechap, String importep, String _id_c) {
        this._id_fp = _id_fp;
        this.fechap = fechap;
        this.importep = importep;
        this._id_c = _id_c;
    }

    @Override
    public String toString (){
        return this.fechap.concat(" importe:"+importep);
    }

}
