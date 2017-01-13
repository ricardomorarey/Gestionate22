package com.gestionate.ricar.gestionate2;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gestionate.ricar.gestionate2.mysql.Conexion;

public class AgregarActivity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText9);
        et5 = (EditText)findViewById(R.id.editText10);
        Button boton_guardar = (Button) findViewById(R.id.button2);

        boton_guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String telefono = et1.getText().toString();
                String nombre = et2.getText().toString();
                String email = et3.getText().toString();
                String direccion = et4.getText().toString();
                String dni = et5.getText().toString();

                Conexion cn = new Conexion(getApplicationContext(),"BDClientes.db",null);
                SQLiteDatabase db = cn.getWritableDatabase();
                cn.InsertarUsuario(db, telefono, nombre, email, direccion,dni );

                Intent intent = new Intent(AgregarActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

}
