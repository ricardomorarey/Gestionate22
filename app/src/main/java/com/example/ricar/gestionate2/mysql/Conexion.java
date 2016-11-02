package com.example.ricar.gestionate2.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

/**
 * Created by ricar on 05/09/2016.
 */
public class Conexion extends SQLiteOpenHelper {

    String sql = "CREATE TABLE clientes (_id_c INTEGER PRIMARY KEY AUTOINCREMENT, telefono INTEGER, nombre TEXT , email TEXT, direccion TEXT, dni INTEGER)";
    String sqlp = "CREATE TABLE pacientes (_id_p INTEGER PRIMARY KEY AUTOINCREMENT, nombrepaciente TEXT, animal TEXT , raza TEXT, sexo TEXT, historial TEXT,_id_c INTEGER, FOREIGN KEY(_id_c) REFERENCES clientes(_id_c) ON DELETE CASCADE )";
    String sqlfp = "CREATE TABLE facturasp (_id_fp INTEGER PRIMARY KEY AUTOINCREMENT, fechap TEXT, importep REAL,_id_c INTEGER, FOREIGN KEY(_id_c) REFERENCES clientes(_id_c)ON DELETE CASCADE )";
    String sqlfip = "CREATE TABLE facturasip (_id_fip INTEGER PRIMARY KEY AUTOINCREMENT, fechaip TEXT, importeip REAL,_id_c INTEGER, FOREIGN KEY(_id_c) REFERENCES clientes(_id_c)ON DELETE CASCADE)";


    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);
        db.execSQL(sqlp);
        db.execSQL(sqlfp);
        db.execSQL(sqlfip);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE clientes IF EXISTS");
        db.execSQL("DROP TABLE pacientes IF EXISTS");
        db.execSQL("DROP TABLE facturasp IF EXISTS");
        db.execSQL("DROP TABLE facturasip IF EXISTS");
        onCreate(db);

    }

    public void InsertarUsuario(SQLiteDatabase db, String telefono, String nombre, String email, String direccion, String dni){

        SQLiteStatement pst = db.compileStatement("INSERT INTO clientes(telefono, nombre, email, direccion, dni)VALUES(?,?,?,?,?)");
        pst.bindString(1, telefono);
        pst.bindString(2, nombre);
        pst.bindString(3, email);
        pst.bindString(4, direccion);
        pst.bindString(5, dni);
        pst.execute();
    }

    public void InsertarPaciente(SQLiteDatabase db, String nombrepaciente, String animal, String raza, String sexo, String historial, String _id_c){

        SQLiteStatement pst = db.compileStatement("INSERT INTO pacientes(nombrepaciente, animal, raza, sexo, historial, _id_c)VALUES(?,?,?,?,?,?)");
        pst.bindString(1, nombrepaciente);
        pst.bindString(2, animal);
        pst.bindString(3, raza);
        pst.bindString(4, sexo);
        pst.bindString(5, historial);
        pst.bindString(6, _id_c);
        pst.execute();
    }

    public void InsertarFacturasp(SQLiteDatabase db, String fechap, String importep, String _id_c){

        SQLiteStatement pst = db.compileStatement("INSERT INTO facturasp(fechap, importep, _id_c)VALUES(?,?,?)");
        pst.bindString(1, fechap);
        pst.bindString(2, importep);
        pst.bindString(3, _id_c);
        pst.execute();
    }

    public void InsertarFacturasip(SQLiteDatabase db, String fechaip, String importeip, String _id_c){

        SQLiteStatement pst = db.compileStatement("INSERT INTO facturasip(fechaip, importeip, _id_c)VALUES(?,?,?)");
        pst.bindString(1, fechaip);
        pst.bindString(2, importeip);
        pst.bindString(3, _id_c);
        pst.execute();
    }

}
