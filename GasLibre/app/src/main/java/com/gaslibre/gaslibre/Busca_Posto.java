package com.gaslibre.gaslibre;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;


import com.gaslibre.gaslibre.Control.Posto.PostoController;
import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.DBManager;
import com.gaslibre.gaslibre.DAO.UserDAO;
import com.gaslibre.gaslibre.Model.Posto;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Created by osvaldo on 02/07/2015.
 */

public class Busca_Posto extends Activity implements View.OnClickListener {
    private UserDAO userDAO= new UserDAO(this);
    private PostoController p= new PostoController(this);

    private Button buttonAlcool;
    private Button buttonDiesel;
    private Button buttonGasolina;
    private Button buttonMaisProximo;
    private ImageButton logoff;
    private String[] arraySpinner;
    private Spinner s;


    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
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

    private void exibeErroPostos(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Falha de acesso");
        builder1.setMessage("Nao consegui acessar o servidor :/");
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

        buttonAlcool= (Button) findViewById(R.id.botaoAlcool);
        buttonDiesel= (Button) findViewById(R.id.botaoDiesel);
        buttonGasolina= (Button) findViewById(R.id.botaoGasolina);
        buttonMaisProximo= (Button) findViewById(R.id.botaoBuscaPorProximidade);
        logoff= (ImageButton) findViewById(R.id.logoff);

        this.arraySpinner = new String[] {
                "selecione", "Loja de Conveniência", "Coompreensor", "Troca de oleo", "borracharia"
        };
        s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        buttonAlcool.setOnClickListener((View.OnClickListener)this);
        buttonDiesel.setOnClickListener((View.OnClickListener) this);
        buttonGasolina.setOnClickListener((View.OnClickListener) this);
        buttonMaisProximo.setOnClickListener((View.OnClickListener) this);
        logoff.setOnClickListener((View.OnClickListener) this);


    s.setOnItemSelectedListener(new OnItemSelectedListener() {
        @Override
        public void onItemSelected(<?> parentView, View selectedItemView, int position, long id) {
            // your code here
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
        }

    });

}


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoGasolina:
                PostoController.prioridade= "preco";
                chamaTelaPreco_Gasolina();
        break;

        case R.id.botaoDiesel:
            PostoController.prioridade= "preco";
            chamaTelaPreco_Diesel();
        break;

        case R.id.botaoAlcool:
            PostoController.prioridade= "preco";
            chamaTelaPreco_Alcool();
        break;

        case R.id.botaoBuscaPorProximidade:
            PostoController.prioridade= "distancia";
            chamaTelaMaisProximo();
            break;

        case R.id.logoff:
            UserController u= new UserController();
            u.retiraUsuarioDaSessao();
            chamaTelaLogin();
            break;

        case R.id.spinner:
            PostoController.servico= s.getSelectedItem().toString();
        break;
    }
}

    private void defineFiltroServico(){
        String text = s.getSelectedItem().toString();
        Log.v("Spinner",s.getSelectedItem().toString());
        if(!text.equals("selecione")){
            PostoController.servico= text;
        }
    }

    private void chamaTelaPreco_Alcool(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 2;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 2);
        defineFiltroServico();
        a.execute();
        //chamaTelaLista();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaPreco_Diesel(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 3;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 3);
        defineFiltroServico();
        a.execute();
        //chamaTelaLista();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaPreco_Gasolina(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 1;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 1);
        defineFiltroServico();
        a.execute();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaMaisProximo(){
        //Intent intent = new Intent(this, Ver_postos.class);
        //PostoController.combustivel = 1;
        GetUserAsyncTaskPosto a= new GetUserAsyncTaskPosto(this, 1);
        defineFiltroServico();
        a.execute();
        //startActivity(intent);
        //finish();
    }

    private void chamaTelaLista(){
        Intent intent = new Intent(this, Lista.class);
        startActivity(intent);
        //finish();
    }

    private void chamaTelaLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
    private class GetUserAsyncTaskPosto extends AsyncTask<Void, Void, ArrayList<Posto>> {

        ProgressDialog progressBar2;
        Context context;
        int combustivel;
        boolean erro= false;

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

        AlertDialog alert11;
        private void dialogCarregando(){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Busca_Posto.this);
            builder1.setTitle("Carregando dados");
            builder1.setMessage("por favor, aguarde...");
            builder1.setCancelable(true);
            alert11 = builder1.create();
            alert11.show();
        }

        @Override
        protected ArrayList<Posto> doInBackground(Void... params) {
            p.combustivelPesquisado= combustivel;
            ArrayList<Posto> retorno= p.buscaPostos(combustivel, 1, 2); //u.autenticaUsuarioServer(this.context, this.email, this.senha);
            p.listaOrdenadaPorPreco= retorno;
            //Log.v("sasas", retorno.get(0).getEndereco());
            //Log.v("sasas", retorno.get(1).getEndereco());

            if(retorno!=null){
                Log.v("Postos", "lista carregada");
                erro= false;
                chamaTelaLista();

            }else{
                Log.v("Postos", "nulo");
                erro= true;
            }

            //progressBar2.dismiss();

            return retorno;

        }

        protected void onPostExecute(Posto posto) {

            Log.v("asaas",">>>>");
            try {
                synchronized (this) {
                    wait(1700);
                    alert11.cancel();
                    if(erro){
                        exibeErroPostos();
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
