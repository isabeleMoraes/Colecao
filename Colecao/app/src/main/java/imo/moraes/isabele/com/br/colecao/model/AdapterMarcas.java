package imo.moraes.isabele.com.br.colecao.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import imo.moraes.isabele.com.br.colecao.R;

public class AdapterMarcas extends BaseAdapter {

    ListaMarcas marcas;
    Activity activity;

    public AdapterMarcas(ListaMarcas marcas, Activity activity) {
        this.marcas = marcas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return marcas.getMarcas().size();
    }

    @Override
    public Object getItem(int i) {
        return marcas.getMarcas().get(i);
    }

    @Override
    public long getItemId(int i) {
        return marcas.getMarcas().get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = activity.getLayoutInflater().inflate(android.R.layout.simple_spinner_dropdown_item, viewGroup, false);

        TextView nomeMarca = view.findViewById(android.R.id.text1);
        nomeMarca.setText(marcas.getMarcas().get(i).getNome());

        return view;
    }
}
