package com.example.ricar.gestionate2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ricar.gestionate2.mysql.Conexion;

public class FacturasActivity extends AppCompatActivity {

    private EditText et1, et2, et3, et4;
    private Button btnadp, btnadip;
    String idc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturas);

        et1 = (EditText)findViewById(R.id.editText7);
        et2 = (EditText)findViewById(R.id.editText8);
        et3 = (EditText)findViewById(R.id.editText12);
        et4 = (EditText)findViewById(R.id.editText13);
        btnadp = (Button)findViewById(R.id.buttonañadir);
        btnadip = (Button)findViewById(R.id.buttonañaimp);

        Bundle bundle = getIntent().getExtras();
        idc = bundle.getString("_id_c");


        //botones
        btnadp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String _id_c = idc.toString();
                String fep = et1.getText().toString();
                String imp = et2.getText().toString();

                Conexion cn = new Conexion(getApplicationContext(),"BDClientes.db",null,1);
                SQLiteDatabase db = cn.getWritableDatabase();
                cn.InsertarFacturasp(db, fep, imp, _id_c);

                Intent i = new Intent(FacturasActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        btnadip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String _id_c = idc.toString();
                String feip = et3.getText().toString();
                String imip = et4.getText().toString();

                Conexion cn = new Conexion(getApplicationContext(),"BDClientes.db",null,1);
                SQLiteDatabase db = cn.getWritableDatabase();
                cn.InsertarFacturasip(db, feip, imip, _id_c);

                Intent in = new Intent(FacturasActivity.this, MainActivity.class);
                startActivity(in);

            }
        });

    }
}
