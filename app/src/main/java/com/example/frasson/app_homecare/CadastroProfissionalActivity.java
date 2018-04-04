package com.example.frasson.app_homecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.frasson.app_homecare.DataBaseObject.ProfissionalDAO;
import com.example.frasson.app_homecare.usuarios.Profissional;


public class CadastroProfissionalActivity extends AppCompatActivity {

    private ProfissionalHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_profissional);

        helper = new ProfissionalHelper(this);

        Intent intent = getIntent();
        Profissional profissional = (Profissional) intent.getSerializableExtra("profissional");
        if (profissional != null){
            helper.editaProfissional(profissional);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case R.id.menu_cadastro_ok:
                    Profissional profissional = helper.pegaProfissional();
                    ProfissionalDAO dao = new ProfissionalDAO(this);
                    if (profissional.getId() != null){
                        dao.editarProfissional(profissional);
                    } else {
                        dao.inserirProfissional(profissional);
                    }
                    dao.close();

                    Toast.makeText(CadastroProfissionalActivity.this, "Profissional " + profissional.getNome() +  " salvo!", Toast.LENGTH_SHORT).show();

                    finish();
                    break;
            }

            return super.onOptionsItemSelected(item);
    }

    }


