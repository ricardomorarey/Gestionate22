package com.example.ricar.gestionate2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ricar.gestionate2.Beans.BeansClientes;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    //private List<String> names;
    ArrayList<BeansClientes> lista = new ArrayList<BeansClientes>();


    //public MyAdapter (Context context, int layout, List<String> names){
    public MyAdapter (Context context, int layout, ArrayList<BeansClientes> lista ){
        this.context = context;
        this.layout = layout;
        //this.names = names;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item, null);

        String currentName = names.get(position);

        TextView textView = (TextView)v.findViewById(R.id.textView29);
        textView.setText(currentName);

        return  v;
    }
}
