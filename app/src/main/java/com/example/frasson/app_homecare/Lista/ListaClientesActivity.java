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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.frasson.app_homecare.CadastroClienteActivity;
import com.example.frasson.app_homecare.DataBaseObject.ClienteDAO;
import com.example.frasson.app_homecare.R;
import com.example.frasson.app_homecare.usuarios.Cliente;

import java.util.List;

public class ListaClientesActivity extends AppCompatActivity {

    private ListView listaCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        listaCliente = (ListView) findViewById(R.id.lista_cliente) ; // dizendo que o listaCliente vai ser a lista do activity)lista_cliente.xml

        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() { // pegar e identificar o click nos Clientes
            @Override
            public void onItemClick(AdapterView<?> listacliente, View item, int position, long id) {
                Cliente cliente = (Cliente) listaCliente.getItemAtPosition(position);

                Intent intentVaiProCadastro = new Intent(ListaClientesActivity.this, CadastroClienteActivity.class); //intent para levar os dados para o Cadastro
                intentVaiProCadastro.putExtra("cliente", cliente); //diz que quando vai para o Cadastro, ele pega o valor do cliente o "cliente" é uma etiqueta de identificação. o cliente tem que ser serializavel
                //portanto damos um implements Serializable na classe Cliente para poder converter automaticamente o objeto da classe para binário.
                startActivity(intentVaiProCadastro);

//                Toast.makeText(ListaClientesActivity.this, "Cliente " + cliente.getNome() + " cliacado!", Toast.LENGTH_SHORT).show(); //Testar se o click está funcionando.
            }
        });



        Button cadastrarCliente = (Button) findViewById(R.id.button_cadastrar_cliente); //Botão de adicionar novo cliente
        cadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // intent de levar a ListaCliente para o Formulário de Cadastro
                Intent intentVaiCadastro = new Intent(ListaClientesActivity.this, CadastroClienteActivity.class);
                startActivity(intentVaiCadastro);
            }
        });

        registerForContextMenu(listaCliente);

    }

    private void ListarCliente() { //Serve para listar o cliente e podendo chamar em outras partes do codigo sem ter que repetir o codigo
        ClienteDAO dao = new ClienteDAO(this);
        List<Cliente> clientes = dao.buscaCliente();
        dao.close();
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        listaCliente.setAdapter(adapter);
    }



    @Override
    protected void onResume() {//Função usada para quando o aplicativo mudar de tela ele da um stop na tela anterior, então esse comando da um "voltar a funcionar" quando volta para a tela principal
        super.onResume();
        ListarCliente(); //chamando o metodo ListarCliente
    }



    @Override //deletar cliente
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Cliente cliente = (Cliente) listaCliente.getItemAtPosition(info.position);

                ClienteDAO dao = new ClienteDAO(ListaClientesActivity.this);
                dao.deletarCliente(cliente);
                dao.close();

                ListarCliente();
                return false;
            }
        });

    }
}
