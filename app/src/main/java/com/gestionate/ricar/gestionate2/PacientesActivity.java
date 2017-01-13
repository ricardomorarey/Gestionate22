package com.gestionate.ricar.gestionate2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gestionate.ricar.gestionate2.mysql.Conexion;

public class PacientesActivity extends AppCompatActivity {

    private EditText edit1, edit2, edit3, edit4, edit5;
    private String idc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        edit1 = (EditText) findViewById(R.id.editText);
        edit2 = (EditText) findViewById(R.id.editText4);
        edit3 = (EditText) findViewById(R.id.editText5);
        edit4 = (EditText) findViewById(R.id.editText6);
        edit5 = (EditText) findViewById(R.id.editText11);
        Button btnadd = (Button) findViewById(R.id.button4);

        Bundle bundle = getIntent().getExtras();
        idc = bundle.getString("_id_c");

        //botones
        btnadd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String _id_c= idc;
                String nombrepaciente = edit1.getText().toString();
                String animal = edit2.getText().toString();
                String raza = edit3.getText().toString();
                String sexo = edit4.getText().toString();
                String historial = edit5.getText().toString();

                Conexion cn = new Conexion(getApplicationContext(),"BDClientes.db",null);
                SQLiteDatabase db = cn.getWritableDatabase();
                cn.InsertarPaciente(db, nombrepaciente, animal, raza, sexo, historial, _id_c);

                Intent i = new Intent(PacientesActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
}


