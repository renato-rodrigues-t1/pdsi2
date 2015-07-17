package com.gaslibre.gaslibre;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView.OnItemClickListener;
import com.gaslibre.gaslibre.Control.Posto.PostoController;
import com.gaslibre.gaslibre.Control.Service.GPSHelper;
import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.Model.Posto;
import com.google.android.gms.maps.model.LatLng;

import java.util.Collections;
import java.util.Comparator;

public class Lista extends ActionBarActivity implements View.OnClickListener{

    private LatLng LtLg;
    private ImageButton logoff;
    private TextView combustivelTexto;

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
        logoff= (ImageButton) findViewById(R.id.logoff);
        logoff.setOnClickListener((View.OnClickListener) this);
        combustivelTexto= (TextView) findViewById(R.id.combustivelLabel);

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

            LatLng tmp= new LatLng(PostoController.listaOrdenadaPorPreco.get(i).getCoordenateX(), PostoController.listaOrdenadaPorPreco.get(i).getCoordenateY());
            item.setTag(tmp);
            item.setOnClickListener((View.OnClickListener) this);

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
                    combustivelTexto.setText("gasolina");
                    break;

                case 2:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getEtanol());
                    combustivelTexto.setText("etanol");
                    break;

                case 3:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorPreco.get(i).getDiesel());
                    combustivelTexto.setText("diesel");
                    break;
            }

            parentPanel.addView(item);

        }
    }

    private void montaListaPorDistancia() {

        GPSHelper gps = new GPSHelper(this);

        // - Requisição da localização pelo gps
        gps.getMyLocation();

        double latitude  = gps.getLatitude();
        double longitude = gps.getLongitude();

        this.LtLg = new LatLng(latitude, longitude);


        for(int i = 0; i < PostoController.listaOrdenadaPorPreco.size(); i++) {

            LatLng temp     = new LatLng(PostoController.listaOrdenadaPorPreco.get(i).getCoordenateX(), PostoController.listaOrdenadaPorPreco.get(i).getCoordenateY());
            double distance = gps.CalculationByDistance(temp, this.LtLg);

            PostoController.listaOrdenadaPorPreco.get(i).setDistance(distance);
            //this.aviso(String.valueOf(lista.get(i).getDistance()));

        }

        PostoController.listaOrdenadaPorDistancia= PostoController.listaOrdenadaPorPreco;

        // 3 - Ordenação da lista
        Collections.sort(PostoController.listaOrdenadaPorDistancia, new Comparator<Posto>() {
            @Override
            public int compare(Posto p0, Posto p1) {
                return Double.compare(p0.getDistance(), p1.getDistance());
            }
        });


        RelativeLayout relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        ScrollView scrollView= (ScrollView) findViewById(R.id.scrollView);
        //LinearLayout lista=  (LinearLayout)findViewById(R.id.linearElementsContainer);
        LinearLayout parentPanel = (LinearLayout) findViewById(R.id.linearElementsContainer);

        LayoutInflater inflator = (LayoutInflater)this.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        for(int i= 0; i< PostoController.listaOrdenadaPorDistancia.size(); i++) {

            View item= (View) inflator.inflate(R.layout.item_lista_posto, null);

            LatLng tmp= new LatLng(PostoController.listaOrdenadaPorDistancia.get(i).getCoordenateX(), PostoController.listaOrdenadaPorDistancia.get(i).getCoordenateY());
            item.setTag(tmp);
            item.setOnClickListener((View.OnClickListener) this);
            //item.setTag();

            LinearLayout cadaElementoLista = (LinearLayout) findViewById(R.id.linearEachElement);
            LinearLayout colunaEsquerda = (LinearLayout) findViewById(R.id.colunaEsquerda);
            TextView nomePosto = (TextView) item.findViewById(R.id.nomePosto);
            nomePosto.setText(PostoController.listaOrdenadaPorDistancia.get(i).getName());
            TextView endereco = (TextView) item.findViewById(R.id.endereco);
            endereco.setText(PostoController.listaOrdenadaPorDistancia.get(i).getEndereco());
            TextView servico = (TextView) item.findViewById(R.id.servicoPosto);
            servico.setText(PostoController.listaOrdenadaPorDistancia.get(i).getServico());
            LinearLayout colunaDireita = (LinearLayout) findViewById(R.id.colunaDireita);
            TextView preco = (TextView) item.findViewById(R.id.preco);
            switch(PostoController.combustivelPesquisado) {
                case 1:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorDistancia.get(i).getGasolina());
                    combustivelTexto.setText("gasolina");
                    break;

                case 2:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorDistancia.get(i).getEtanol());
                    combustivelTexto.setText("etanol");
                    break;

                case 3:
                    preco.setText("R$ "+PostoController.listaOrdenadaPorDistancia.get(i).getDiesel());
                    combustivelTexto.setText("diesel");
                    break;
            }

            parentPanel.addView(item);

        }
    }

    public void onClick(View v) {
        if(v.getId()==R.id.logoff){
            UserController u= new UserController();
            u.retiraUsuarioDaSessao();
            chamaTelaLogin();
        }else {
            Log.v("shahsahshsPP//////", v.getTag() + "");
            Log.v("shahsahshsPP//////", v.getTag() + "");
            LatLng tmp = (LatLng) v.getTag();
            //Log.v("shahsahshsPP//////", tmp.latitude()+" ".toString());
            GPSHelper.TargetedPosto = tmp;
            chamaMapa();
        }
    }

    private void chamaTelaLogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
        finish();
    }
    private void chamaMapa(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
