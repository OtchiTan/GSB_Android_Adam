package com.gsb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsb.R;
import com.gsb.modele.Echantillon;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class EchantillonAdapter extends BaseAdapter {

    protected ArrayList<Echantillon> echantillons;
    protected Context context;
    protected LayoutInflater inflater;

    public EchantillonAdapter(ArrayList<Echantillon> echantillons, Context context) {
        this.echantillons = echantillons;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return echantillons.size();
    }

    @Override
    public Echantillon getItem(int i) {
        return echantillons.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.layout_echantillon, viewGroup, false);
        Echantillon e = getItem(i);

        TextView tvCode = view.findViewById(R.id.tv_echantillon_code);
        TextView tvLibelle = view.findViewById(R.id.tv_echantillon_libelle);
        TextView tvStock = view.findViewById(R.id.tv_echantillon_stock);

        tvCode.setText(e.getCode());
        tvLibelle.setText(e.getLibelle());
        tvStock.setText(e.getQuantite());

        return view;
    }
}
