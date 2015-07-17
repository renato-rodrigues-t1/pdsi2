package com.gaslibre.gaslibre.Control.Service;

/**
 * Created by renato on 5/23/15.
 */
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.gaslibre.gaslibre.Model.User;
public class ErrorsCommander {
    //returns right URL to conect the server depending on the the needed task

    private static ErrorsCommander errorsCommander;

    private String serverURL= "http://52.24.179.139/";

    private String urlUsers= "users/";
    private String urlReports= "reports/";
    private String urlConversations= "conversations/";
    private String urlMessages= "messages/";
    private String urlInterests= "interests/";
    private String urlLanguages= "languages/";
    private String urlMatches= "matches/";

    public static void erroSemConexao(Context context){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Erro de conexao");
        builder1.setMessage("verifique sua conexao, por favor e tente novamente");
        builder1.setCancelable(true);
        builder1.setPositiveButton("ENTENDI",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}