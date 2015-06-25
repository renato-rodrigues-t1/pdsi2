package com.gaslibre.gaslibre;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.app.Activity;

import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.DBManager;
import com.gaslibre.gaslibre.DAO.UserDAO;
import com.gaslibre.gaslibre.Model.User;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by renato on 6/15/15.
 */
public class LoginScreen extends Activity implements View.OnClickListener {
    private UserDAO userDAO= new UserDAO(this);
    private UserController u;

    private TextView textoUsuauio;
    private TextView textoSenha;
    private EditText editUser;
    private EditText editSenha;
    private Button buttonLogin;
    private Button buttonRegistrar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        u= new UserController(getContext());
        DBManager dbManager= new DBManager(this);//inicializa as tabelas se naum existem.

        inicializaComponentes();
        test();
        //String email= textfild.getString
        //String senha=
        //userController.autenticaUsuario(email, senha);

       //if s=falso chama tela de registro

        //se naum, direciona p tela de mapa

    }

    private void inicializaComponentes() {
        //teste commit Osvaldo

        textoUsuauio= (TextView)findViewById(R.id.textoUsuario);
        textoSenha= (TextView)findViewById(R.id.textSenha);
        editUser= (EditText) findViewById(R.id.editUsuario);
        editSenha= (EditText) findViewById(R.id.editSenha);
        buttonLogin= (Button) findViewById(R.id.botaoLogin);
        buttonRegistrar= (Button) findViewById(R.id.botaoRegistrar);

        buttonLogin.setOnClickListener((View.OnClickListener)this);
        buttonRegistrar.setOnClickListener((View.OnClickListener)this);

    }

    public Context getContext(){
        return this.getApplicationContext();
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoLogin:

                String usuarioEntrado= editUser.getText().toString();
                String senhaEntrada= editSenha.getText().toString();
                if(u.autenticaUsuario(usuarioEntrado, senhaEntrada)){
                    u.colocaUsuarioNaSessao(new User());
                    chamaTelaMapa();
                }

                else
                    erroDeLogin();
                break;

            case R.id.botaoRegistrar:
                    chamaTelaRegistro();

                break;
        }
    }

    private void chamaTelaMapa(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    private void chamaTelaRegistro(){
        Intent intent = new Intent(this, RegistroScreen.class);
        startActivity(intent);
        finish();
    }

    private void erroDeLogin(){
        Context context = getApplicationContext();
        CharSequence text = "Usuario ou senha invalidos, por favor verifique dados ouregistre-se";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    private void test(){
        User userTemp= new User("Bruno Ernandes", "renato.rsufu@gmail.com", "1234");
        Log.v("USER>>>", userTemp.getName()+"- "+userTemp.getEmail()+"- "+userTemp.getSenha());

        //userDAO.registraUsuario(userTemp);
        //User newUser= userDAO.AutenticaUsuario(userTemp.getEmail(), userTemp.getSenha());
        //Log.v("DoBANCOUSER>>>", newUser.getId()+"- "+newUser.getEmail()+"- "+newUser.getSenha());


        //como grava um usuario no banco
        u.registraUsuario(userTemp);

        //como autentica o usuario pra logar
        //u.autenticaUsuario(userTemp.getEmail(), userTemp.getSenha());

    }

}
