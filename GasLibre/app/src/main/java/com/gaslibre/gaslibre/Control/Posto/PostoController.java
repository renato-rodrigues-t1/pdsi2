package com.gaslibre.gaslibre.Control.Posto;

import android.content.Context;

import com.gaslibre.gaslibre.Model.Posto;

import java.util.ArrayList;

/**
 * Created by renato on 6/25/15.
 */
public class PostoController {

    //gasolina 1
    //etanol 2
    //diesel= 3

    private Context context;
    public static int combustivel;

    public PostoController(Context context) {
        this.context = context;
    }

    public ArrayList<Posto> buscaPostos(int combustivel, float coordenateX, float coordenateY){

        return WebConnectorPosto.getInstance(this.context).buscaPostos(combustivel, coordenateX, coordenateY);

    }

}
