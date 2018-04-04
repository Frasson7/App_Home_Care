package com.example.frasson.app_homecare.DataBaseObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.frasson.app_homecare.usuarios.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends SQLiteOpenHelper {

    public ClienteDAO(Context context)
    {

        super(context, "Cliente", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Cliente (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT NOT NULL, telefone TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Cliente";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insereCliente(Cliente cliente) { //inserir um novo cliente no banco de dados
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosCliente(cliente);

        db.insert("Cliente", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosCliente(Cliente cliente) {
        ContentValues dados = new ContentValues();
        dados.put("nome", cliente.getNome());
        dados.put("endereco", cliente.getEndereco());
        dados.put("telefone", cliente.getTelefone());
        return dados;
    }

    public List<Cliente> buscaCliente() { //busca os clientes no banco de dados
        String sql = "SELECT * FROM Cliente;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Cliente> clientes = new ArrayList<Cliente>();
        while (c.moveToNext()){

            Cliente cliente = new Cliente();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));

            clientes.add(cliente);
        }
        c.close();
        return clientes;
    }

    public void deletarCliente(Cliente cliente) { //deleta o cliente no banco de dados pela sua ID
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {cliente.getId().toString()};
        db.delete("Cliente", "id = ?", params);

    }

    public void editaCliente(Cliente cliente) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosCliente(cliente);
        String[] params = {cliente.getId().toString()};
        db.update("Cliente", dados, "id = ?", params);

    }
}
