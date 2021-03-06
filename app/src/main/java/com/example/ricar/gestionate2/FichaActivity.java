package com.example.ricar.gestionate2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ricar.gestionate2.Beans.BeansFacturasIP;
import com.example.ricar.gestionate2.Beans.BeansFacturasP;
import com.example.ricar.gestionate2.Beans.BeansPacientes;
import com.example.ricar.gestionate2.mysql.Conexion;

import java.util.ArrayList;

import static com.example.ricar.gestionate2.R.id.sendmail;

public class FichaActivity extends AppCompatActivity {


    TextView tv5, tv6, tv7, tv16, tv17;
    TextView tvsp, tvsip;

    ListView Lip;
    ListView Lifp;
    ListView Lifip;

    ArrayList<BeansPacientes> listap = new ArrayList<BeansPacientes>();
    ArrayList<BeansFacturasP> listafp = new ArrayList<BeansFacturasP>();
    ArrayList<BeansFacturasIP> listafip = new ArrayList<BeansFacturasIP>();

    String idc, tlf, nom, mail, dir, dn;

    Button _agrepacientes;
    Button _agrefacturas;
    //Button borrar_cliente;
    ImageButton _mandaremail;


    BeansPacientes pacientes;
    BeansFacturasP facturasp;
    BeansFacturasIP facturasip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);

        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        tv16 = (TextView) findViewById(R.id.textView16);
        tv17 = (TextView) findViewById(R.id.textView17);
        Lip = (ListView) findViewById(R.id.listViewp);
        Lifp = (ListView) findViewById(R.id.listViewfp);
        Lifip = (ListView) findViewById(R.id.listViewfip);
        tvsp = (TextView) findViewById(R.id.textViewsp);
        tvsip = (TextView) findViewById(R.id.textViewsip);
        _mandaremail = (ImageButton) findViewById(sendmail);


        /*borrar_cliente=(Button)findViewById(R.id.buttonborrarcliente);
        //borrar cliente
        borrar_cliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                idc = bundle.getString("_id_c");
                Conexion cn = new Conexion(getApplicationContext(),"BDClientes.db",null,1);
                SQLiteDatabase db = cn.getWritableDatabase();
                String sql=("DELETE FROM clientes WHERE _id_c =" +idc);
                db.execSQL(sql);
                db.close();
                Intent intent = new Intent(FichaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

        //muestro datos extraidos del listview que vienen de la busqueda
        Bundle bundle = getIntent().getExtras();
        idc = bundle.getString("_id_c");
        tlf = bundle.getString("telefono");
        nom = bundle.getString("nombre");
        mail = bundle.getString("email");
        dir = bundle.getString("direccion");
        dn = bundle.getString("dni");

        tv5.setText(tlf);
        tv6.setText(nom);
        tv7.setText(mail);
        tv16.setText(dir);
        tv17.setText(dn);
        //muestro datos de mi consulta en mi base de datos y mis tablas
        Conexion cn = new Conexion(getApplicationContext(), "BDClientes.db", null, 1);
        final SQLiteDatabase db = cn.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM pacientes WHERE _id_c=" + idc, null);
        if (c.moveToFirst()) {
            do {
                pacientes = new BeansPacientes(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));
                listap.add(pacientes);
            } while (c.moveToNext());
        }
        ArrayAdapter<BeansPacientes> adap = new ArrayAdapter<BeansPacientes>(getApplicationContext(), android.R.layout.simple_list_item_1, listap);
        Lip.setAdapter(adap);

        //pulsacion en el boton de mandar email
        _mandaremail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FichaActivity.this, Mailing.class);
                intent.putExtra("email", mail);
                startActivity(intent);
            }
        });

        //mis datos de mi consulta tabla de facturas
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

        //Sumatorios facturas
        Cursor s = db.rawQuery("SELECT Sum(importep)AS suma FROM facturasp WHERE _id_c =" + idc, null);
        if (s.moveToFirst()) {
            do {
                String sum = s.getString(0);
                tvsp.setText(sum);

            } while (s.moveToNext());
        }

        Cursor si = db.rawQuery("SELECT Sum(importeip)AS sumai FROM facturasip WHERE _id_c =" + idc, null);
        if (si.moveToFirst()) {
            do {
                String sum = si.getString(0);
                tvsip.setText(sum);

            } while (si.moveToNext());
        }

        //Botones pagina
        _agrepacientes = (Button) findViewById(R.id.button);
        _agrefacturas = (Button) findViewById(R.id.button3);

        _agrepacientes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(FichaActivity.this, PacientesActivity.class);
                intent.putExtra("_id_c", idc);
                startActivity(intent);

            }
        });
        _agrefacturas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(FichaActivity.this, FacturasActivity.class);
                intent.putExtra("_id_c", idc);
                startActivity(intent);

            }
        });

        //pulsacion en el item del Listview y voy a la ficha.
        Lip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //recojo los parametros
                String _id_p = listap.get(arg2).get_id_p();
                String noma = listap.get(arg2).getNombrepaciente();
                String ani = listap.get(arg2).getAnimal();
                String raz = listap.get(arg2).getRaza();
                String sex = listap.get(arg2).getSexo();
                String histo = listap.get(arg2).getHistorial();
                //CReo el intent y seteo esos datos
                Intent in = new Intent(getApplicationContext(), FichaPacientes.class);
                in.putExtra("_id_p", _id_p);
                in.putExtra("nombrepaciente", noma);
                in.putExtra("animal", ani);
                in.putExtra("raza", raz);
                in.putExtra("sexo", sex);
                in.putExtra("historial", histo);
                startActivity(in);
            }
        });
    }

}
