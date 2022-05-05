package com.gsb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsb.R;
import com.gsb.modele.Composant;
import com.gsb.modele.Echantillon;

import java.util.ArrayList;

public class ComposantAdapter extends BaseAdapter {

    protected ArrayList<Composant> composants;
    protected Context context;
    protected LayoutInflater inflater;

    public ComposantAdapter(ArrayList<Composant> composants, Context context) {
        this.composants = composants;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return composants.size();
    }

    @Override
    public Composant getItem(int i) {
        return composants.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.layout_composant, viewGroup, false);
        Composant c = getItem(i);

        TextView tvCode = view.findViewById(R.id.tv_composant_code);
        TextView tvLibelle = view.findViewById(R.id.tv_composant_libelle);

        tvCode.setText(c.getCode());
        tvLibelle.setText(c.getLibelle());

        return view;
    }
}
