package com.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gsb.database.BdAdapter;
import com.gsb.modele.Echantillon;

public class AjoutEchantillon extends AppCompatActivity {

    private EditText etCode;
    private EditText etLibelle;
    private EditText etStock;
    private Button bValider;
    private Button bQuitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCode = findViewById(R.id.et_ajout_code);
        etLibelle = findViewById(R.id.et_ajout_libelle);
        etStock = findViewById(R.id.et_ajout_stock);
        bQuitter = findViewById(R.id.b_ajout_quitter);
        bValider = findViewById(R.id.b_ajout_valider);

        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutEchantillon();
            }
        });

        bQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void ajoutEchantillon() {
        BdAdapter adapter = new BdAdapter(this);

        adapter.open();
        Toast.makeText(this,"Ajout",Toast.LENGTH_LONG).show();
        adapter.insererEchantillon(new Echantillon(
                etCode.getText().toString(),
                etLibelle.getText().toString(),
                etStock.getText().toString()
        ));
        Toast.makeText(this,String.valueOf(adapter.getData().getCount()),Toast.LENGTH_LONG).show();
        adapter.close();
        finish();
    }
}