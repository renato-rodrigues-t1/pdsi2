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
import java.util.ArrayList;


import com.gaslibre.gaslibre.Control.Posto.PostoController;
import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.DBManager;
import com.gaslibre.gaslibre.DAO.UserDAO;
import com.gaslibre.gaslibre.Model.Posto;

/**
 * Created by osvaldo on 02/07/2015.
 */

public class Busca_Posto extends Activity implements View.OnClickListener {
    private UserDAO userDAO= new UserDAO(this);
    private PostoController p= new PostoController(this);

    private Button buttonAlcool;
    private Button buttonDiesel;
    private Button buttonGasolina;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_posto);
        DBManager dbManager= new DBManager(this);//inicializa as tabelas se naum existem.

        inicializaComponentes();
        //test();
        //String email= textfild.getString
        //String senha=
        //userController.autenticaUsuario(email, senha);

        //if s=falso chama tela de registro

        //se naum, direciona p tela de mapa

    }
private void inicializaComponentes() {


        buttonAlcool= (Button) findViewById(R.id.botaoAlcool);
        buttonDiesel= (Button) findViewById(R.id.botaoDiesel);
        buttonGasolina= (Button) findViewById(R.id.botaoGasolina);

        buttonAlcool.setOnClickListener((View.OnClickListener)this);
        buttonDiesel.setOnClickListener((View.OnClickListener) this);
        buttonGasolina.setOnClickListener((View.OnClickListener) this);

        }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoGasolina:

                chamaTelaPreco_Alcool();

        break;

        case R.id.botaoDiesel:
        chamaTelaPreco_Diesel();

        break;

            case R.id.botaoAlcool:
                chamaTelaPreco_Alcool();

                break;
    }
}

    private void chamaTelaPreco_Alcool(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 2;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 2);
        a.execute();
        chamaTelaLista();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaPreco_Diesel(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 3;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 3);
        a.execute();
        chamaTelaLista();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaPreco_Gasolina(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 1;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 1);
        a.execute();
        chamaTelaLista();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaLista(){
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
        finish();
    }
    private class GetUserAsyncTaskPosto extends AsyncTask<Void, Void, ArrayList<Posto>> {

        ProgressDialog progressBar2;
        Context context;
        int combustivel;

        private GetUserAsyncTaskPosto(Context context, int comb) {
            this.context = context;
            this.combustivel= comb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar2 = ProgressDialog.show(Busca_Posto.this, getApplicationContext().getString(R.string.title_activity_listar__postos), getApplicationContext().getString(R.string.corpo_texto__postos));
            //progressBar2.show();
        }

        @Override
        protected ArrayList<Posto> doInBackground(Void... params) {
            ArrayList<Posto> retorno= p.buscaPostos(combustivel, 1, 2); //u.autenticaUsuarioServer(this.context, this.email, this.senha);

            //Log.v("sasas", retorno.get(0).getEndereco());

            if(retorno!=null){
                Log.v("login", "deu certo");

            }else{
                Log.v("login", "nulo");
                //erroDeLogin();
            }

            return retorno;

        }

        protected void onPostExecute(Posto posto) {
            //progressBar2.dismiss();
        }
    }

}
