package com.example.frasson.app_homecare;

import android.widget.EditText;

import com.example.frasson.app_homecare.usuarios.Profissional;

public class ProfissionalHelper {
    private final EditText profissionalEspecialidade;
    private final EditText profissionalTelefone;
    private final EditText profissionalNome;

    private Profissional profissional;


    public ProfissionalHelper(CadastroProfissionalActivity activity){

        profissionalNome = (EditText) activity.findViewById(R.id.profissionalNome);
        profissionalTelefone = (EditText) activity.findViewById(R.id.profissionalTelefone);
        profissionalEspecialidade = (EditText) activity.findViewById(R.id.profissionalEspecialidade);
        profissional = new Profissional();

    }
    public Profissional pegaProfissional() {
        profissional.setNome(profissionalNome.getText().toString());
        profissional.setTelefone(profissionalTelefone.getText().toString());
        profissional.setEspecialidade(profissionalEspecialidade.getText().toString());
        return profissional;
    }


    public void editaProfissional(Profissional profissional) {
        profissionalNome.setText(profissional.getNome());
        profissionalTelefone.setText(profissional.getTelefone());
        profissionalEspecialidade.setText(profissional.getEspecialidade());
        this.profissional = profissional;
    }
}
