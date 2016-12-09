package com.example.ricar.gestionate2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ricar.gestionate2.Beans.BeansClientes;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    protected Activity activity;
    //protected ArrayList<ItemCompra> items;
    protected ArrayList<BeansClientes> lista ;

    public MyAdapter(Activity activity, ArrayList<BeansClientes> lista) {
        this.activity = activity;
        this.lista = lista;
    }



    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(lista.get(position).get_id_c());
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.list_item, null);
        }

        BeansClientes item = lista.get(position);


        TextView nombre = (TextView) vi.findViewById(R.id.tvItem1);
        nombre.setText(item.getNombre());

        TextView telf = (TextView) vi.findViewById(R.id.tvItem2);
        telf.setText(item.getTelefono());

        return vi;
    }
}
