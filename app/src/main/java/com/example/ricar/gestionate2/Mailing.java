package com.example.ricar.gestionate2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mailing extends AppCompatActivity {

    String mail;
    TextView etEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing);

        etEmail = (TextView) findViewById(R.id.etEmail);

        Bundle bundle = getIntent().getExtras();
        mail = bundle.getString("email");
        etEmail.setText(mail);

        Button btnSend = (Button) findViewById(R.id.buttonemail1);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //obtenemos los datos para el envío del correo
                TextView etEmail = (TextView) findViewById(R.id.etEmail);
                EditText etSubject = (EditText) findViewById(R.id.etasunto);
                EditText etBody = (EditText) findViewById(R.id.etcuerpoemail);

                //es necesario un intent que levante la actividad deseada
                Intent itSendprogramado = new Intent(android.content.Intent.ACTION_SEND);

                //vamos a enviar texto plano a menos que el checkbox esté marcado
                itSendprogramado.setType("plain/text");

                //colocamos los datos para el envío
                itSendprogramado.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{etEmail.getText().toString()});
                itSendprogramado.putExtra(android.content.Intent.EXTRA_SUBJECT, etSubject.getText().toString());
                itSendprogramado.putExtra(android.content.Intent.EXTRA_TEXT, etBody.getText());

                //iniciamos la actividad
                startActivity(itSendprogramado);
            }
        });
    }
}


