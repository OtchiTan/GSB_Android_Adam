package com.gsb;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gsb.adapter.EchantillonAdapter;
import com.gsb.database.BdAdapter;
import com.gsb.modele.Echantillon;

import java.util.ArrayList;

public class ListeEchantillon extends AppCompatActivity {

    private ListView lvEchantillons;
    private Button bQuitter;

    private ActivityResultLauncher<Intent> launcher;

    private ArrayList<Echantillon> echantillons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_echantillon);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        refreshList();
                    }
                }
        );

        lvEchantillons = findViewById(R.id.lv_liste_listeEchantillon);
        bQuitter = findViewById(R.id.b_liste_quitter);

        refreshList();

        lvEchantillons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Echantillon echantillon = echantillons.get(i);
                Intent intent = new Intent(ListeEchantillon.this, MajEchantillon.class);
                intent.putExtra("code",echantillon.getCode());
                launcher.launch(intent);
            }
        });

        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void refreshList() {BdAdapter bdAdapter = new BdAdapter(this);
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
}