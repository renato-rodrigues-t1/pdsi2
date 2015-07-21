package com.gaslibre.gaslibre;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
    public static boolean erro= false;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        inicializaComponentes();

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoRegistrar:
                if(editNome.getText().toString().length()>0 && editUser.getText().toString().length()>0 && editSenha.getText().toString().length()>0){
                    if(editUser.getText().toString().indexOf("@") > 0) {

                        User temp = new User(editNome.getText().toString(), editUser.getText().toString(), editSenha.getText().toString());

                        AddUserAsyncTask getAsync = new AddUserAsyncTask(this, temp);
                        getAsync.execute();

                    }else{
                        exibeErroArrouba();
                    }
                }else{
                    exibeErroCamposObrigatorios();
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
        builder1.setTitle("Email invalido");
        builder1.setMessage("Ops, seu email não contem o caracter '@' rsrs");
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

    private void exibeErroCamposObrigatorios(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Campos Vazios");
        builder1.setMessage("Por favor, preencha todos os campos");
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

    private class AddUserAsyncTask extends AsyncTask<Void, Void, User> {

        ProgressDialog progressBar;
        int idUsuario;
        Context context2;
        String email, senha;
        AlertDialog alert11;
        User userTepmp;

        private void dialogCarregando(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(RegistroScreen.this);
            builder1.setTitle("Registrando seus dados");
            builder1.setMessage("por favor, aguarde...");
            builder1.setCancelable(true);
            alert11 = builder1.create();
            alert11.show();
        }

        private AddUserAsyncTask(Context context, User user) {
            userTepmp= user;
            context2= context;
        }

        public AddUserAsyncTask(Context context) {
            this.context2 = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogCarregando();
            //progressBar = ProgressDialog.show(LoginScreen.this, getApplicationContext().getString(R.string.title_activity_logando),
            //      getApplicationContext().getString(R.string.corpo_texto__Login));
            //progressBar.show();

        }

        @Override
        protected User doInBackground(Void... params) {
            //User retorno= u.autenticaUsuarioServer(this.context2, this.email, this.senha);

            //User temp = new User(editNome.getText().toString(), editUser.getText().toString(), editSenha.getText().toString());
            boolean retorno= u.registraUsuarioServer(userTepmp, context2);
            if (retorno) {
                chamaTelaDeLogin();
            } else {
                //falha de registro

                exibeErroRegistro();
            }


            if(retorno){
                u.colocaUsuarioNaSessao(new User());
                Log.v("login", "deu certo");
                erro= false;
                chamaTelaDeLogin();
            }else{
                Log.v("login", "nulo");
                erro= true;
                //erroDeLogin();
            }

            return userTepmp;
        }

        @Override
        protected void onPostExecute(User user) {
            try {
                synchronized (this) {
                    wait(1700);
                    alert11.cancel();
                    if(erro){
                        exibeErroRegistro();
                    }
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                //Log.d(TAG, "Waiting didnt work!!");
                e.printStackTrace();
            }
        }
    }

}
