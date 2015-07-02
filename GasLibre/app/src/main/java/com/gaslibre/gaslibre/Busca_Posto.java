package com.gaslibre.gaslibre;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.gaslibre.gaslibre.Control.Posto.PostoController;
import com.gaslibre.gaslibre.Control.User.UserController;
import com.gaslibre.gaslibre.DAO.UserDAO;

/**
 * Created by osvaldo on 02/07/2015.
 */

public class Busca_Posto extends Activity implements View.OnClickListener {
    private UserDAO userDAO= new UserDAO(this);
    private UserController u;

    private Button buttonAlcool;
    private Button buttonDiesel;
    private Button buttonGasolina;


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
        //startActivity(intent);
        finish();
    }

    private void chamaTelaPreco_Diesel(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 3;
        //startActivity(intent);
        finish();
    }

    private void chamaTelaPreco_Gasolina(){
        //Intent intent = new Intent(this, Ver_postos.class);
        PostoController.combustivel = 1;
        //startActivity(intent);
        finish();
    }

}
