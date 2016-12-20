package com.example.ricar.gestionate2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FichaPacientes extends AppCompatActivity {

    private String noma;
    private String ani;
    private String raz;
    private String sex;
    private String histo;
    private String idp;
    private TextView tvnoma;
    private TextView tvani;
    private TextView tvraz;
    private TextView tvsex;
    private TextView tvhisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_pacientes);

        tvnoma=(TextView)findViewById(R.id.tvnoma);
        tvani=(TextView)findViewById(R.id.ani);
        tvraz=(TextView)findViewById(R.id.raz);
        tvsex=(TextView)findViewById(R.id.sex);
        tvhisto=(TextView)findViewById(R.id.tvhisto);

        //muestro datos extraidos del listview que vienen de la busqueda
        Bundle bundle = getIntent().getExtras();
        idp= bundle.getString("_id_p");
        noma=bundle.getString("nombrepaciente");
        ani = bundle.getString("animal");
        raz = bundle.getString("raza");
        sex = bundle.getString("sexo");
        histo = bundle.getString("historial");

        tvnoma.setText(noma);
        tvani.setText(ani);
        tvraz.setText(raz);
        tvsex.setText(sex);
        tvhisto.setText(histo);

    }
}
