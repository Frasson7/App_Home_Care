package com.example.frasson.app_homecare.DataBaseObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.frasson.app_homecare.usuarios.Cliente;
import com.example.frasson.app_homecare.usuarios.Profissional;

import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO extends SQLiteOpenHelper{


    public ProfissionalDAO(Context context) {
        super(context, "Profissional", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Profissional (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, telefone TEXT NOT NULL, especialidade TEXT NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Profissional";
        db.execSQL(sql);
        onCreate(db);

    }

    public void inserirProfissional(Profissional profissional) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosProfissional(profissional);
        db.insert("Profissional", null, dados);
    }

    @NonNull
    private ContentValues pegaDadosProfissional(Profissional profissional) {
        ContentValues dados = new ContentValues();
        dados.put("nome", profissional.getNome());
        dados.put("telefone", profissional.getTelefone());
        dados.put("especialidade", profissional.getEspecialidade());
        return dados;
    }

    public List<Profissional> buscaProfissionais() {
        String sql = "SELECT * FROM Profissional;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor p = db.rawQuery(sql, null);

        List<Profissional> profissionais = new ArrayList<Profissional>();
        while (p.moveToNext()){

            Profissional profissional = new Profissional();
            profissional.setId(p.getLong(p.getColumnIndex("id")));
            profissional.setNome(p.getString(p.getColumnIndex("nome")));
            profissional.setTelefone(p.getString(p.getColumnIndex("telefone")));
            profissional.setEspecialidade(p.getString(p.getColumnIndex("especialidade")));

            profissionais.add(profissional);

        }
        p.close();
        return profissionais;
    }

    public void deletarProfissional(Profissional profissional) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {profissional.getId().toString()};
        db.delete("Profissional", "id = ?", params);

    }

    public void editarProfissional(Profissional profissional) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosProfissional(profissional);
        String[] params = {profissional.getId().toString()};
        db.update("Profissional", dados, "id = ?", params);

    }
}

