package com.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gsb.database.BdAdapter;
import com.gsb.modele.Echantillon;

public class MajEchantillon extends AppCompatActivity {

    private EditText etQuantite;
    private Button bSupprimer;
    private Button bAjouter;
    private Button bQuitter;

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
        bQuitter = findViewById(R.id.b_maj_quitter);

        code = getIntent().getExtras().getString("code");

        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        int newQuantite = Integer.parseInt(echantillon.getQuantite()) + Integer.parseInt(etQuantite.getText().toString());

        echantillon.setQuantite(String.valueOf(newQuantite));
        adapter.updateEchantillon(echantillon.getCode(),echantillon);
        finish();
    }

    public void decrementEchantillon() {
        BdAdapter adapter = new BdAdapter(this);
        adapter.open();

        Echantillon echantillon = adapter.getEchantillonWithLib(code);

        int newQuantite = Integer.parseInt(echantillon.getQuantite()) - Integer.parseInt(etQuantite.getText().toString());

        echantillon.setQuantite(String.valueOf(newQuantite));
        adapter.updateEchantillon(echantillon.getCode(),echantillon);
        finish();
    }
}