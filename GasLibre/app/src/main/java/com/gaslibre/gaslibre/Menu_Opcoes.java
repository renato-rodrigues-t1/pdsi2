package com.gaslibre.gaslibre;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.UserDAO;

/**
 * Created by osvaldo on 09/07/2015.
 */

public class Menu_Opcoes extends Activity implements View.OnClickListener {
    private UserDAO userDAO= new UserDAO(this);
    private UserController u;

    private Button buttonListaPostos;
    private Button buttonPostosMapa;
    private Button buttonBusca;


    private void inicializaComponentes() {


        buttonListaPostos= (Button) findViewById(R.id.botaoListarPostos);
        buttonPostosMapa= (Button) findViewById(R.id.botaoVerMapa);
        buttonBusca= (Button) findViewById(R.id.botaoBusca);

        buttonListaPostos.setOnClickListener((View.OnClickListener)this);
        buttonPostosMapa.setOnClickListener((View.OnClickListener) this);
        buttonBusca.setOnClickListener((View.OnClickListener) this);

    }


    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.botaoListarPostos:

                chamaTelaListar_Postos();

                break;

            case R.id.botaoVerMapa:
                chamaTelaMapsActivity();

                break;

            case R.id.botaoBusca:
                chamaTelaBuscar_Posto();

                break;
        }
    }

    private void chamaTelaListar_Postos(){
        Intent intent = new Intent(this, Listar_Postos.class);
        startActivity(intent);
        finish();
    }

    private void chamaTelaMapsActivity(){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    private void chamaTelaBuscar_Posto(){
        Intent intent = new Intent(this, Busca_Posto.class);
        startActivity(intent);
        finish();
    }

}
