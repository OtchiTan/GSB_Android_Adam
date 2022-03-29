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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_echantillon);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCode = findViewById(R.id.et_ajout_code);
        etLibelle = findViewById(R.id.et_ajout_libelle);
        etStock = findViewById(R.id.et_ajout_stock);
        bValider = findViewById(R.id.b_ajout_valider);

        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajoutEchantillon();
            }
        });
    }

    public void ajoutEchantillon() {
        BdAdapter adapter = new BdAdapter(this);

        if (etStock.getText().toString().matches("") ||
                etLibelle.getText().toString().matches("") ||
                etCode.getText().toString().matches("")) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
            return;
        }

        int stock = Integer.parseInt(etStock.getText().toString());
        if (stock <= 0) {
            Toast.makeText(this, "La quantité doit être supérieur à 0", Toast.LENGTH_LONG).show();
            return;
        }
        adapter.open();
        adapter.insererEchantillon(new Echantillon(
                etCode.getText().toString(),
                etLibelle.getText().toString(),
                etStock.getText().toString()
        ));
        adapter.close();
        finish();
    }
}