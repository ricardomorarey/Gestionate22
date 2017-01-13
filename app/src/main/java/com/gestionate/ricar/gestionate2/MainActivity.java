package com.gestionate.ricar.gestionate2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.gestionate.ricar.gestionate2.Beans.BeansClientes;
import com.gestionate.ricar.gestionate2.mysql.Conexion;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ListView Li;
    private final ArrayList<BeansClientes> lista = new ArrayList<>();
    private BeansClientes clientes;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //forzar el icono en la action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        //searchview
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_main);


        mSearchView = (SearchView) findViewById(R.id.search_view);
        Li = (ListView) findViewById(R.id.listView1);
        Li.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista));
        Li.setTextFilterEnabled(true);
        setupSearchView();

    //fin del seachview

        Li = (ListView) findViewById(R.id.listView1);

        //fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });
        //termino el fab

        Conexion cn = new Conexion(getApplicationContext(), "BDClientes.db", null);
        SQLiteDatabase db = cn.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM clientes", null);
        if (c.moveToFirst()) {
            do {
                clientes = new BeansClientes(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5));
                lista.add(clientes);
            } while (c.moveToNext());
        }

        final ArrayAdapter<BeansClientes> adap = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        Li.setAdapter(adap);

        //pulsacion en el item del Listview y voy a la ficha.
        Li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                //recojo los parametros
                String _id_c = adap.getItem(position).get_id_c();
                String telefono = adap.getItem(position).getTelefono();
                String nombre = adap.getItem(position).getNombre();
                String email = adap.getItem(position).getEmail();
                String direccion = adap.getItem(position).getDireccion();
                String dni = adap.getItem(position).getDni();
                //CReo el intent y seteo esos datos
                Intent in = new Intent(getApplicationContext(), FichaActivity.class);
                in.putExtra("_id_c", _id_c);
                in.putExtra("telefono", telefono);
                in.putExtra("nombre", nombre);
                in.putExtra("email", email);
                in.putExtra("direccion", direccion);
                in.putExtra("dni", dni);
                startActivity(in);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            Li.clearTextFilter();
        } else {
            Li.setFilterText(newText);
        }
        return true;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Buscar nombre o telefono");
    }
}



