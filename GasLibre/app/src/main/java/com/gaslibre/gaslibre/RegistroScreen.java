package com.gaslibre.gaslibre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import
        android.content.DialogInterface;
import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.Model.User;

/**
 * Created by renato on 6/17/15.
 */
public class RegistroScreen extends Activity implements View.OnClickListener{

    UserController u= new UserController(this);
    private EditText editNome;
    private EditText editUser;
    private EditText editSenha;
    private Button buttonRegistrar;
    private Button buttonCancelar;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        inicializaComponentes();

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoRegistrar:
                if(editUser.getText().toString().indexOf("@") > 0) {

                    User temp = new User(editNome.getText().toString(), editUser.getText().toString(), editSenha.getText().toString());
                    if (u.registraUsuarioServer(temp, this)) {
                        chamaTelaDeLogin();
                    } else {
                        //falha de registro
                        exibeErroRegistro();
                    }
                }else{
                    exibeErroArrouba();
                }
                break;

            case R.id.cancel:
                chamaTelaDeLogin();
                break;
        }
    }

    private void exibeErroRegistro(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ops, nao consegui realizar seu registro :/");
        builder1.setCancelable(true);
        builder1.setPositiveButton("tentar novamente",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void exibeErroArrouba(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Ops, seu email nem contem o caracter '@' rsrs");
        builder1.setCancelable(true);
        builder1.setPositiveButton("tentar novamente",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void inicializaComponentes() {

        editUser= (EditText)findViewById(R.id.email);
        editSenha= (EditText)findViewById(R.id.editSenha);
        editNome= (EditText)findViewById(R.id.name);

        buttonRegistrar= (Button) findViewById(R.id.botaoRegistrar);
        buttonCancelar= (Button) findViewById(R.id.cancel);


        buttonRegistrar.setOnClickListener((View.OnClickListener)this);
        buttonCancelar.setOnClickListener((View.OnClickListener)this);

    }

    private void chamaTelaDeLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
}
