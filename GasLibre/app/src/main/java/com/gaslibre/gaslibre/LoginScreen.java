package com.gaslibre.gaslibre;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

    private EditText editUser;
    private EditText editSenha;
    private Button buttonLogin;
    private Button buttonRegistrar;
    protected static boolean erro= false;
    protected UserController usuarioController= new UserController();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //verifica se ususario logado (se tem usuario na sessao)
        if(usuarioController.estaLogadoNaSessao()){
            //caso em que nao estah logado direciona pra tela de login
            chamaTelaBusca();
        }

        u= new UserController(getContext());
        DBManager dbManager= new DBManager(this);//inicializa as tabelas se naum existem.

        inicializaComponentes();
        //test();
        //String email= textfild.getString
        //String senha=
        //userController.autenticaUsuario(email, senha);

        //if s=falso chama tela de registro

        //se naum, direciona p tela de mapa

    }

    private void exibeErroLogin(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Falha de Login");
        builder1.setMessage("verifique se seu email e senhas estao corretos");
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
        //teste commit Osvaldo

        //textoUsuauio= (TextView)findViewById(R.id.textoUsuario);
        //textoSenha= (TextView)findViewById(R.id.textSenha);
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

                    if (usuarioEntrado.length() > 0 && senhaEntrada.length() > 0) {
                       // if(usuarioEntrado.indexOf("@")>0) {
                            GetUserAsyncTask getAsync = new GetUserAsyncTask(this, usuarioEntrado, senhaEntrada);
                            getAsync.execute();
                        //}else{
                           //exibeErroArrouba();
                        //}
                    }
                break;

            case R.id.botaoRegistrar:
                chamaTelaRegistro();

                break;
        }
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

    private void chamaTelaMapa(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    private void chamaTelaBusca(){
        Intent intent = new Intent(this, Busca_Posto.class);
        startActivity(intent);
        finish();
    }

    private void chamaTelaRegistro(){
        Intent intent = new Intent(this, RegistroScreen.class);
        startActivity(intent);
        finish();
    }

    private void test(){
        User userTemp= new User("Bruno Ernandes", "renato.rsufu@gmail.com", "1234");
        Log.v("USER>>>", userTemp.getName() + "- " + userTemp.getEmail() + "- " + userTemp.getSenha());

        //userDAO.registraUsuario(userTemp);
        //User newUser= userDAO.AutenticaUsuario(userTemp.getEmail(), userTemp.getSenha());
        //Log.v("DoBANCOUSER>>>", newUser.getId()+"- "+newUser.getEmail()+"- "+newUser.getSenha());


        //como grava um usuario no banco
        u.registraUsuario(userTemp);

        //como autentica o usuario pra logar
        //u.autenticaUsuario(userTemp.getEmail(), userTemp.getSenha());

    }

    private class GetUserAsyncTask extends AsyncTask<Void, Void, User> {

        ProgressDialog progressBar;
        int idUsuario;
        Context context;
        String email, senha;
        AlertDialog alert11;

        private void dialogCarregando(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(LoginScreen.this);
            builder1.setTitle("Autenticando seus  dados");
            builder1.setMessage("por favor, aguarde...");
            builder1.setCancelable(true);
            alert11 = builder1.create();
            alert11.show();
        }

        private GetUserAsyncTask(Context context, String email, String senha) {
            this.context = context;
            this.email = email;
            this.senha = senha;
        }

        public GetUserAsyncTask(Context context) {
            this.context = context;
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
            User retorno= u.autenticaUsuarioServer(this.context, this.email, this.senha);

            if(retorno!=null){
                u.colocaUsuarioNaSessao(new User());
                Log.v("login", "deu certo");
                erro= false;
                chamaTelaBusca();
            }else{
                Log.v("login", "nulo");
                erro= true;
                //erroDeLogin();
            }

            /*User u;//= new User();
            u= UsersController.getInstance().getUserServer(userLocal);
            Log.v("Vindo do Server======", u.getName());
            return u;*/
            /*try {
                // thread to sleep for 1000 milliseconds
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }*/
            //progressBar.dismiss();
            return retorno;

        }

        @Override
        protected void onPostExecute(User user) {
            try {
                synchronized (this) {
                    wait(1700);
                    alert11.cancel();
                    if(erro){
                        exibeErroLogin();
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
