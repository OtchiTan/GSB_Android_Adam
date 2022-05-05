package com.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gsb.modele.dao.ComposantDao;

public class AjouterComp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_comp);

        EditText etCode = findViewById(R.id.et_ajoutComp_code);
        Button bAjouter = findViewById(R.id.b_ajoutComp_button);

        String code = getIntent().getExtras().getString("code");

        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codeComp = etCode.getText().toString();
                ComposantDao.associate(AjouterComp.this, code, codeComp);
                finish();
            }
        });
    }
}