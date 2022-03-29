package com.gsb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gsb.database.BdAdapter;
import com.gsb.modele.Echantillon;

public class MajEchantillon extends AppCompatActivity {

    private EditText etQuantite;
    private Button bSupprimer;
    private Button bAjouter;

    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maj_echantillon);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etQuantite = findViewById(R.id.et_maj_quantite);
        bSupprimer = findViewById(R.id.b_maj_supprimer);
        bAjouter = findViewById(R.id.b_maj_ajout);

        code = getIntent().getExtras().getString("code");

        bSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrementEchantillon();
            }
        });

        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementEchantillon();
            }
        });
    }

    public void incrementEchantillon() {
        BdAdapter adapter = new BdAdapter(this);
        adapter.open();

        Echantillon echantillon = adapter.getEchantillonWithLib(code);

        if (etQuantite.getText().toString().matches("")) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
            return;
        }

        int stock = Integer.parseInt(echantillon.getQuantite());
        int stockToAdd = Integer.parseInt(etQuantite.getText().toString());
        int newQuantite = stock + stockToAdd;

        if (stockToAdd <= 0) {
            Toast.makeText(this, "La quantité à ajouter doit être positive", Toast.LENGTH_LONG).show();
            return;
        }

        echantillon.setQuantite(String.valueOf(newQuantite));
        adapter.updateEchantillon(echantillon.getCode(),echantillon);
        finish();
    }

    public void decrementEchantillon() {
        BdAdapter adapter = new BdAdapter(this);
        adapter.open();

        Echantillon echantillon = adapter.getEchantillonWithLib(code);

        if (etQuantite.getText().toString().matches("")) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
            return;
        }

        int stock = Integer.parseInt(echantillon.getQuantite());
        int stockToAdd = Integer.parseInt(etQuantite.getText().toString());
        int newQuantite = stock - stockToAdd;

        if (newQuantite <= 0) {
            Toast.makeText(this, "Il n'y a pas assez de stock", Toast.LENGTH_LONG).show();
            return;
        }
        echantillon.setQuantite(String.valueOf(newQuantite));
        adapter.updateEchantillon(echantillon.getCode(),echantillon);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_maj, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_maj_supprimer){
            BdAdapter adapter = new BdAdapter(MajEchantillon.this);
            adapter.open();
            adapter.removeEchantillonWithCode(code);
            adapter.close();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}