package com.gaslibre.gaslibre;
import android.os.Bundle;

import android.app.Activity;

import Control.UserController;
import DAO.DBManager;
import DAO.UserDAO;
import Model.User;
import android.util.Log;

import android.content.Context;

/**
 * Created by renato on 6/15/15.
 */
public class LoginScreen extends Activity {
    private UserDAO userDAO= new UserDAO(this);

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        DBManager dbManager= new DBManager(this);//inicializa as tabelas se naum existem.

        inicializaComponentes();
        test();

        //String email= textfild.getString
        //String senha=
        //userController.autenticaUsuario(email, senha);

       //if s=falso chama tela de registro

        //se naum, direciona p tela de mapa

    }

    private void inicializaComponentes(){
        //Texfild email= findById(R.id.email...)
        // ||               senha...

    }

    public Context getContext(){
        return this.getApplicationContext();
    }
    private void test(){
        User userTemp= new User("Bruno Ernandes", "higor.rsufu@gmail.com", "1234");
        Log.v("USER>>>", userTemp.getName()+"- "+userTemp.getEmail()+"- "+userTemp.getSenha());

        //userDAO.registraUsuario(userTemp);
        //User newUser= userDAO.AutenticaUsuario(userTemp.getEmail(), userTemp.getSenha());
        //Log.v("DoBANCOUSER>>>", newUser.getId()+"- "+newUser.getEmail()+"- "+newUser.getSenha());

        UserController u= new UserController(getContext());
        //como grava um usuario no banco
        u.registraUsuario(userTemp);

        //como autentica o usuario pra logar
        u.autenticaUsuario(userTemp.getEmail(), userTemp.getSenha());

    }

}
