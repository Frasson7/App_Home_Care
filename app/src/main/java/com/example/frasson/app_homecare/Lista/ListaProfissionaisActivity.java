package com.example.frasson.app_homecare.Lista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.frasson.app_homecare.CadastroProfissionalActivity;
import com.example.frasson.app_homecare.DataBaseObject.ProfissionalDAO;
import com.example.frasson.app_homecare.R;
import com.example.frasson.app_homecare.usuarios.Profissional;

import java.time.Instant;
import java.util.List;

public class ListaProfissionaisActivity extends AppCompatActivity {

    private ListView listaProfissional;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profissionais);

        listaProfissional = (ListView) findViewById(R.id.lista_profissionais);


        listaProfissional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listaprofissional, View item, int position, long id) {
                Profissional profissional = (Profissional) listaProfissional.getItemAtPosition(position);

                Intent intentVaiProCadastro = new Intent(ListaProfissionaisActivity.this, CadastroProfissionalActivity.class);
                intentVaiProCadastro.putExtra("profissional", profissional);
                startActivity(intentVaiProCadastro);
            }
        });

        Button cadastrarProfissionais = (Button) findViewById(R.id.button_cadastrar_profissionais);
        cadastrarProfissionais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiCadastro = new Intent(ListaProfissionaisActivity.this, CadastroProfissionalActivity.class);
                startActivity(intentVaiCadastro);
            }
        });

        registerForContextMenu(listaProfissional);

    }

    private void ListarProfissional() {
        ProfissionalDAO dao = new ProfissionalDAO(this);
        List<Profissional> profissionais = dao.buscaProfissionais();
        dao.close();

        ListView listaProfissionais =(ListView) findViewById(R.id.lista_profissionais);
        ArrayAdapter<Profissional> adapter = new ArrayAdapter<Profissional>(this, android.R.layout.simple_list_item_1, profissionais);
        listaProfissionais.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ListarProfissional();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Profissional profissional = (Profissional) listaProfissional.getItemAtPosition(info.position);

                ProfissionalDAO dao = new ProfissionalDAO(ListaProfissionaisActivity.this);
                dao.deletarProfissional(profissional);
                dao.close();

                ListarProfissional();
                return false;
            }
        });

    }
}
