package com.example.frasson.app_homecare;

import android.widget.EditText;

import com.example.frasson.app_homecare.usuarios.Cliente;

public class ClienteHelper {

    private final EditText nomeCliente;
    private final EditText enderecoCliente;
    private final EditText telefoneCliente;

    private Cliente cliente;



    public ClienteHelper(CadastroClienteActivity activity){ //coletar as informações dos campos de texto e colocando nas variaveis.
        nomeCliente = (EditText) activity.findViewById(R.id.cliente_nome);
        enderecoCliente = (EditText) activity.findViewById(R.id.cliente_endereco);
        telefoneCliente = (EditText) activity.findViewById(R.id.cliente_telefone);
        cliente = new Cliente();

           }

           public Cliente pegaCliente(){ //pega os clientes criados pelas variaveis.
        cliente.setNome(nomeCliente.getText().toString());
        cliente.setEndereco(enderecoCliente.getText().toString());
        cliente.setTelefone(telefoneCliente.getText().toString());
        return cliente;


           }

    public void editaCliente(Cliente cliente) { //editar o aluno
        nomeCliente.setText(cliente.getNome());
        enderecoCliente.setText(cliente.getEndereco());
        telefoneCliente.setText(cliente.getTelefone());
        this.cliente = cliente;


    }
}
