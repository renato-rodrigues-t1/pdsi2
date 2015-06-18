package com.gaslibre.gaslibre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Control.UserController;
import DAO.DBManager;
import Model.User;

/**
 * Created by renato on 6/17/15.
 */
public class RegistroScreen extends Activity implements View.OnClickListener{

    UserController u= new UserController(this);
    private TextView textoNome;
    private TextView textoUsuauio;
    private TextView textoSenha;
    private EditText editNome;
    private EditText editUser;
    private EditText editSenha;
    private Button buttonLogin;
    private Button buttonRegistrar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        inicializaComponentes();

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoRegistrar:
                User temp= new User(editNome.getText().toString(), editUser.getText().toString(), editSenha.getText().toString());
                u.registraUsuario(temp);

                chamaTelaDeLogin();

                break;
        }
    }
    private void inicializaComponentes() {

        textoUsuauio= (TextView)findViewById(R.id.textoUsuario);
        textoSenha= (TextView)findViewById(R.id.textSenha);
        textoNome= (TextView)findViewById(R.id.textNome);
        editUser= (EditText) findViewById(R.id.editUsuario);
        editSenha= (EditText) findViewById(R.id.editSenha);
        editNome= (EditText) findViewById(R.id.editNome);
        buttonLogin= (Button) findViewById(R.id.botaoLogin);
        buttonRegistrar= (Button) findViewById(R.id.botaoRegistrar);

        buttonRegistrar.setOnClickListener((View.OnClickListener)this);

    }

    private void chamaTelaDeLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
