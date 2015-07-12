package com.gaslibre.gaslibre;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.util.Log;

import com.gaslibre.gaslibre.Control.Posto.PostoController;

public class Lista extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lista);
        initialiseComponents();

        if(PostoController.prioridade.equals("preco"))
            montaListaPorPreco();
        else
            montaListaPorDistancia();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void initialiseComponents(){



    }

    private void montaListaPorPreco(){
        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        ScrollView scrollView= (ScrollView) findViewById(R.id.scrollView);
        //LinearLayout lista=  (LinearLayout)findViewById(R.id.linearElementsContainer);
        LinearLayout parentPanel = (LinearLayout) findViewById(R.id.linearElementsContainer);

        LayoutInflater inflator = (LayoutInflater)this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        for(int i= 0; i< PostoController.listaOrdenadaPorPreco.size(); i++) {

            View item= (View) inflator.inflate(R.layout.item_lista_posto, null);

            LinearLayout cadaElementoLista = (LinearLayout) findViewById(R.id.linearEachElement);
            LinearLayout colunaEsquerda = (LinearLayout) findViewById(R.id.colunaEsquerda);
            TextView nomePosto = (TextView) item.findViewById(R.id.nomePosto);
            nomePosto.setText(PostoController.listaOrdenadaPorPreco.get(i).getName());
            TextView endereco = (TextView) item.findViewById(R.id.endereco);
            endereco.setText(PostoController.listaOrdenadaPorPreco.get(i).getEndereco());
            TextView servico = (TextView) item.findViewById(R.id.servicoPosto);
            servico.setText(PostoController.listaOrdenadaPorPreco.get(i).getServico());
            LinearLayout colunaDireita = (LinearLayout) findViewById(R.id.colunaDireita);
            TextView preco = (TextView) item.findViewById(R.id.preco);
            switch(PostoController.combustivelPesquisado) {
                case 1:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getGasolina());
                    break;

                case 2:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getEtanol());
                    break;

                case 3:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getDiesel());
                    break;
            }

            parentPanel.addView(item);

        }
    }

    private void montaListaPorDistancia(){
        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        ScrollView scrollView= (ScrollView) findViewById(R.id.scrollView);
        //LinearLayout lista=  (LinearLayout)findViewById(R.id.linearElementsContainer);
        LinearLayout parentPanel = (LinearLayout) findViewById(R.id.linearElementsContainer);

        LayoutInflater inflator = (LayoutInflater)this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        for(int i= 0; i< PostoController.listaOrdenadaPorPreco.size(); i++) {

            View item= (View) inflator.inflate(R.layout.item_lista_posto, null);

            LinearLayout cadaElementoLista = (LinearLayout) findViewById(R.id.linearEachElement);
            LinearLayout colunaEsquerda = (LinearLayout) findViewById(R.id.colunaEsquerda);
            TextView nomePosto = (TextView) item.findViewById(R.id.nomePosto);
            nomePosto.setText(PostoController.listaOrdenadaPorPreco.get(i).getName());
            TextView endereco = (TextView) item.findViewById(R.id.endereco);
            endereco.setText(PostoController.listaOrdenadaPorPreco.get(i).getEndereco());
            TextView servico = (TextView) item.findViewById(R.id.servicoPosto);
            servico.setText(PostoController.listaOrdenadaPorPreco.get(i).getServico());
            LinearLayout colunaDireita = (LinearLayout) findViewById(R.id.colunaDireita);
            TextView preco = (TextView) item.findViewById(R.id.preco);
            switch(PostoController.combustivelPesquisado) {
                case 1:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getGasolina());
                    break;

                case 2:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getEtanol());
                    break;

                case 3:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getDiesel());
                    break;
            }

            parentPanel.addView(item);

        }
    }
}
