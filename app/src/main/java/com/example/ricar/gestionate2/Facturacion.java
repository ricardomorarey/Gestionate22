package com.example.ricar.gestionate2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.database.sqlite.SQLiteDatabase.*;
import android.widget.TextView;

import com.example.ricar.gestionate2.Beans.BeansFacturasIP;
import com.example.ricar.gestionate2.Beans.BeansFacturasP;
import com.example.ricar.gestionate2.mysql.Conexion;

import java.util.ArrayList;

public class Facturacion extends AppCompatActivity {

    private ListView Lifp,Lifip;
    TextView pagadas, impagadas;
    ArrayList<BeansFacturasP> listafp = new ArrayList<BeansFacturasP>();
    ArrayList<BeansFacturasIP> listafip = new ArrayList<BeansFacturasIP>();
    BeansFacturasP facturasp;
    BeansFacturasIP facturasip;
    String idc, tlf, nom, mail, dir, dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion);

        Lifp = (ListView) findViewById(R.id.listpagadas);
        Lifip = (ListView) findViewById(R.id.listimpagadas);
        Bundle bundle = getIntent().getExtras();
        idc = bundle.getString("_id_c");
        tlf = bundle.getString("telefono");
        nom = bundle.getString("nombre");
        mail = bundle.getString("email");
        dir = bundle.getString("direccion");
        dn = bundle.getString("dni");



        //mis datos de mi consulta tabla de facturas
        Conexion cn = new Conexion(getApplicationContext(), "BDClientes.db", null, 1);
        final SQLiteDatabase db = cn.getWritableDatabase();

        Cursor f = db.rawQuery("SELECT * FROM facturasp WHERE _id_c=" + idc, null);
        if (f.moveToFirst()) {
            do {
                facturasp = new BeansFacturasP(f.getString(0), f.getString(1), f.getString(2), f.getString(3));
                listafp.add(facturasp);
            } while (f.moveToNext());
        }
        ArrayAdapter<BeansFacturasP> adapfp = new ArrayAdapter<BeansFacturasP>(getApplicationContext(), android.R.layout.simple_list_item_1, listafp);
        Lifp.setAdapter(adapfp);

        Cursor fi = db.rawQuery("SELECT * FROM facturasip WHERE _id_c=" + idc, null);
        if (fi.moveToFirst()) {
            do {
                facturasip = new BeansFacturasIP(fi.getString(0), fi.getString(1), fi.getString(2), fi.getString(3));
                listafip.add(facturasip);
            } while (fi.moveToNext());
        }
        final ArrayAdapter<BeansFacturasIP> adapfip = new ArrayAdapter<BeansFacturasIP>(getApplicationContext(), android.R.layout.simple_list_item_1, listafip);
        Lifip.setAdapter(adapfip);

        pagadas = (TextView) findViewById(R.id.textViewsumafp);
        impagadas = (TextView) findViewById(R.id.textViewtotalfincobradas);

        //Sumatorios facturas
        Cursor s = db.rawQuery("SELECT Sum(importep)AS suma FROM facturasp WHERE _id_c =" + idc, null);
        if (s.moveToFirst()) {
            do {
                String sum = s.getString(0);
                pagadas.setText(sum + " Euros");

            } while (s.moveToNext());
        }

        Cursor si = db.rawQuery("SELECT Sum(importeip)AS sumai FROM facturasip WHERE _id_c =" + idc, null);
        if (si.moveToFirst()) {
            do {
                String sum = si.getString(0);
                impagadas.setText(sum + " Euros");

            } while (si.moveToNext());
        }
    }
}
