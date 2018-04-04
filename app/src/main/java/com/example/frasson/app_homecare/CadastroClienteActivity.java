package com.example.frasson.app_homecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frasson.app_homecare.DataBaseObject.ClienteDAO;
import com.example.frasson.app_homecare.usuarios.Cliente;

public class CadastroClienteActivity extends AppCompatActivity {

    private ClienteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        helper = new ClienteHelper(this);

        Intent intent = getIntent(); //pegar o valor do intent na ListaClienteActivty.java para que possamos puxar as informações do cliente e editar.
        Cliente cliente = (Cliente) intent.getSerializableExtra("cliente"); //recuperar os dados da etiqueta que seria "cliente" e coloca dentro do objeto cliente
        if(cliente != null){// validando o intent que puxa
            helper.editaCliente(cliente);
        }
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu){ //Criando a opção de salvar no menu de cadastro
           MenuInflater inflater = getMenuInflater();
           inflater.inflate(R.menu.menu_cadastro, menu);

            return super.onCreateOptionsMenu(menu);
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //dizendo para a opção salvar o que tem que ser feita.
        switch (item.getItemId()){
            case R.id.menu_cadastro_ok:
                Cliente cliente = helper.pegaCliente(); //pega os dados do ClienteHelper que possue os dados do cliente
                ClienteDAO dao = new ClienteDAO(this);
                if(cliente.getId() != null){ //se o ID do cliente for diferente de null ele vai pegar esse ID e chamar o metodo editar Cliente
                    dao.editaCliente(cliente);

                } else { //se o metodo for null, quer dizer que esse cliente ainda não existe, então ele vai inserir no banco de dados

                    dao.insereCliente(cliente); //insere no banco de dado o cliente
                }
                dao.close();

                Toast.makeText(CadastroClienteActivity.this, "Cliente " + cliente.getNome() +  " salvo!", Toast.LENGTH_SHORT).show(); //mostra o Toast que o cliente foi salvo


                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

