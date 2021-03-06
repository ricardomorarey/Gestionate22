package com.example.ricar.gestionate2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import com.example.ricar.gestionate2.Beans.BeansClientes;
import com.example.ricar.gestionate2.mysql.Conexion;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    ListView Li;
    ArrayList<BeansClientes> lista = new ArrayList<>();
    BeansClientes clientes;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //searchview
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_main);


        mSearchView = (SearchView) findViewById(R.id.serach_view);
        Li = (ListView) findViewById(R.id.listView1);
        Li.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista));
        Li.setTextFilterEnabled(true);


        setupSearchView();

        //fin del seachview

        //fab
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });
        //termino el fab


        //adaptador para list_item
        //MyAdapter myAdapter = new MyAdapter(this, lista);
        //Li.setAdapter(myAdapter);

        //adaptador original que funciona
        final ArrayAdapter<BeansClientes> MyAdapter = new ArrayAdapter<BeansClientes>(getApplicationContext(), R.layout.list_item, lista);
        Li.setAdapter(MyAdapter);

        //pulsacion en el item del Listview y voy a la ficha.
        Li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //recojo los parametros
                String _id_c = lista.get(arg2).get_id_c();
                String telefono = lista.get(arg2).getTelefono();
                String nombre = lista.get(arg2).getNombre();
                String email = lista.get(arg2).getEmail();
                String direccion = lista.get(arg2).getDireccion();
                String dni = lista.get(arg2).getDni();
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
            Li.setFilterText(newText.toString());
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


