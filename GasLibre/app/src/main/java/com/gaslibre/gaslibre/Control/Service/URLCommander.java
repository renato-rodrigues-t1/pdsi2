package com.gaslibre.gaslibre.Control.Service;

/**
 * Created by renato on 5/23/15.
 */

import com.gaslibre.gaslibre.Model.User;
public class URLCommander {
    //returns right URL to conect the server depending on the the needed task

    private static URLCommander urlCommanderSingleton;

    private String serverURL= "http://52.24.179.139/";

    private String urlUsers= "users/";
    private String urlReports= "reports/";
    private String urlConversations= "conversations/";
    private String urlMessages= "messages/";
    private String urlInterests= "interests/";
    private String urlLanguages= "languages/";
    private String urlMatches= "matches/";

    private URLCommander() { }

    public static URLCommander getInstance(){
        if (urlCommanderSingleton == null){
            urlCommanderSingleton= new URLCommander();
        }
        return urlCommanderSingleton;
    }

    public String getServerURL() {
        return serverURL;
    }

    public String getURLLogin(String email, String senha){
        return serverURL+"gaslibre/gaslibreweb/users/doLogin/"+email+"/"+senha;
    }

    public String getURLBuscaPosto(int combustivel, String servico){
        return serverURL+ "gaslibre/gaslibreweb/postos/getAllPostswithAFuel/"+combustivel+"/"+servico;
    }

    public String getUrlBuscaServico(String servico){
        return serverURL+"gaslibre/gaslibreweb/postos/getAllPostsByServico/"+servico;
    }

    public String getURLRegistraUser(){
        return serverURL+"gaslibre/gaslibreweb/users/addUser/";
    }
}
