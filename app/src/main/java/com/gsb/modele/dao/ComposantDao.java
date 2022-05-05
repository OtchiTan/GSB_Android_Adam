package com.gsb.modele.dao;

import android.content.Context;
import android.database.Cursor;

import com.gsb.adapter.EchantillonAdapter;
import com.gsb.database.BdAdapter;
import com.gsb.modele.Composant;
import com.gsb.modele.Echantillon;

import java.util.ArrayList;

public class ComposantDao {

    public static ArrayList<Composant> getComposants(Context ctx){
        ArrayList<Composant> composants = new ArrayList<>();

        BdAdapter bdAdapter = new BdAdapter(ctx);
        bdAdapter.open();
        Cursor cursor = bdAdapter.execSQL("SELECT * FROM composant");
        while (cursor.moveToNext()) {
            composants.add(new Composant(
                    cursor.getString(0),
                    cursor.getString(1)
            ));
        }
        bdAdapter.close();

        return composants;
    }

    public static ArrayList<Composant> getComposantsFromCode(Context ctx, String code){
        ArrayList<Composant> composants = new ArrayList<>();

        BdAdapter bdAdapter = new BdAdapter(ctx);
        bdAdapter.open();
        ArrayList<String> codes = bdAdapter.getCompsFromEch(code);
        for (String codeComp :
                codes) {
            composants.add(getComposantFromCode(ctx,codeComp));
        }
        bdAdapter.close();

        return composants;
    }

    public static Composant getComposantFromCode(Context ctx, String code){
        Composant composant = null;

        BdAdapter bdAdapter = new BdAdapter(ctx);
        bdAdapter.open();
        Cursor cursor = bdAdapter.getComposantWithCode(code);
        if (cursor.moveToNext()) {
            composant = new Composant(cursor.getString(0),cursor.getString(1));
        }
        bdAdapter.close();

        return composant;
    }

    public static void associate(Context ctx, String codeEch, String codeComp) {
        BdAdapter bdAdapter = new BdAdapter(ctx);
        bdAdapter.open();
        bdAdapter.insererPossede(codeEch,codeComp);
        bdAdapter.close();
    }
}
