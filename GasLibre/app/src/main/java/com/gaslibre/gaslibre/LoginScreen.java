package com.gaslibre.gaslibre;
import android.os.Bundle;

import android.app.Activity;

/**
 * Created by renato on 6/15/15.
 */
public class LoginScreen extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        inicializaComponentes();


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

}
