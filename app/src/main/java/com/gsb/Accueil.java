package com.gsb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gsb.adapter.EchantillonAdapter;
import com.gsb.database.BdAdapter;
import com.gsb.modele.Composant;
import com.gsb.modele.Echantillon;
import com.gsb.modele.dao.ComposantDao;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    private Button bAjout;
    private ListView lvEchantillons;
    private ActivityResultLauncher<Intent> launcher;
    private ArrayList<Echantillon> echantillons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        refreshList();
                    }
                }
        );

        bAjout = findViewById(R.id.b_accueil_ajout);
        lvEchantillons = findViewById(R.id.lv_accueil_echantillon);

        refreshList();

        bAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Accueil.this, AjoutEchantillon.class);
                launcher.launch(intent);
            }
        });

        lvEchantillons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Echantillon e = echantillons.get(i);
                Intent intent = new Intent(Accueil.this, MajEchantillon.class);
                intent.putExtra("code",e.getCode());
                launcher.launch(intent);
            }
        });
    }

    public void refreshList() {
        BdAdapter bdAdapter = new BdAdapter(this);
        bdAdapter.open();
        echantillons = new ArrayList<>();
        Cursor cursor = bdAdapter.getData();
        while (cursor.moveToNext()) {
            echantillons.add(new Echantillon(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            ));
        }

        EchantillonAdapter adapter = new EchantillonAdapter(echantillons,getApplicationContext());
        lvEchantillons.setAdapter(adapter);

        bdAdapter.close();
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
        }
        return super.onOptionsItemSelected(item);
    }
}