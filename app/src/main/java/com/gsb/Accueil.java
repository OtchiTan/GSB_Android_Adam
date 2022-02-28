package com.gsb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gsb.database.BdAdapter;
import com.gsb.modele.Echantillon;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    private Button bAjout;
    private Button bListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bAjout = findViewById(R.id.b_accueil_ajout);
        bListe = findViewById(R.id.b_accueil_liste);

        bAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Accueil.this, AjoutEchantillon.class);
                startActivity(intent);
            }
        });

        bListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Accueil.this, ListeEchantillon.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accueil, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_quitter:
                finish();
                break;
            case R.id.menu_ajout:
                startActivity(new Intent(Accueil.this, AjoutEchantillon.class));
                break;
            case R.id.menu_liste:
                startActivity(new Intent(Accueil.this, ListeEchantillon.class));
                break;
            case R.id.menu_maj:
                startActivity(new Intent(Accueil.this, MajEchantillon.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}