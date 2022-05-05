package com.gsb.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gsb.modele.Echantillon;

import java.util.ArrayList;

public class BdAdapter {
    static final int VERSION_BDD = 4;
    private static final String NOM_BDD = "gsb.db";
    private static final String TABLE_ECHANT = "echantillons";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    static final String COL_CODE = "CODE";
    static final int NUM_COL_CODE = 1;
    static final String COL_LIB = "LIB";
    static final int NUM_COL_LIB = 2;
    static final String COL_STOCK = "STOCK";
    static final int NUM_COL_STOCK = 3;

    private CreateBdEchantillon bdArticles;
    private Context context;
    private SQLiteDatabase db;

    public BdAdapter(Context context) {
        this.context = context;
        bdArticles = new CreateBdEchantillon(context, NOM_BDD, null, VERSION_BDD);
    }

    public BdAdapter open() {
        db = bdArticles.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public long insererEchantillon(Echantillon echantillon) {
        ContentValues values = new ContentValues();

        values.put(COL_CODE,echantillon.getCode());
        values.put(COL_LIB,echantillon.getLibelle());
        values.put(COL_STOCK,echantillon.getQuantite());

        return db.insert(TABLE_ECHANT, null, values);
    }

    private Echantillon cursorToEchant(Cursor cursor) {
        Echantillon echantillon = null;

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            echantillon = new Echantillon(
                    cursor.getString(NUM_COL_CODE),
                    cursor.getString(NUM_COL_LIB),
                    cursor.getString(NUM_COL_STOCK)
            );
        }
        cursor.close();
        return echantillon;
    }

    public Echantillon getEchantillonWithLib(String code) {
        Cursor cursor = db.query(TABLE_ECHANT, new String[] {COL_ID,COL_CODE, COL_LIB, COL_STOCK},
                COL_CODE + " LIKE \"" + code + "\"", null, null, null, null);
        return cursorToEchant(cursor);
    }

    public int updateEchantillon(String code, Echantillon echantillon){
        ContentValues values = new ContentValues();

        values.put(COL_CODE,echantillon.getCode());
        values.put(COL_LIB,echantillon.getLibelle());
        values.put(COL_STOCK,echantillon.getQuantite());
        return db.update(TABLE_ECHANT, values,COL_CODE + " = \"" + code + "\"", null);
    }

    public int removeEchantillonWithCode(String code) {
        return db.delete(TABLE_ECHANT, COL_CODE + " = \"" + code + "\"", null);
    }

    public Cursor getData() {
        return db.rawQuery("SELECT * FROM echantillons", null);
    }

    public Cursor execSQL(String sql){
        return db.rawQuery(sql, null);
    }

    public Cursor getComposantWithCode(String code) {
        Cursor cursor = db.query("composant", new String[] {"code","libelle"},
                "code" + " LIKE \"" + code + "\"", null, null, null, null);
        return cursor;
    }

    public Cursor getAssociations(String codeEch){
        Cursor cursor = db.query("possede", new String[] {"id","codeEch","codeComp"},
                "codeEch" + " LIKE \"" + codeEch + "\"", null, null, null, null);
        return cursor;
    }

    public ArrayList<Echantillon> getEchantillons() {
        ArrayList<Echantillon> echantillons = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM echantillons", null);

        while (cursor.moveToNext()) {
            echantillons.add(cursorToEchant(cursor));
        }

        return echantillons;
    }

    public ArrayList<String> getCompsFromEch(String ech) {
        ArrayList<String> echantillons = new ArrayList<>();

        Cursor cursor = db.query("possede", new String[] {"id","codeEch","codeComp"},
                "codeEch" + " LIKE \"" + ech + "\"", null, null, null, null);

        while (cursor.moveToNext()) {
            echantillons.add(cursor.getString(2));
        }

        return echantillons;
    }

    public long insererPossede(String codeEch, String codeComp) {
        ContentValues values = new ContentValues();

        values.put("codeEch",codeEch);
        values.put("codeComp",codeComp);

        return db.insert("possede", null, values);
    }
}
