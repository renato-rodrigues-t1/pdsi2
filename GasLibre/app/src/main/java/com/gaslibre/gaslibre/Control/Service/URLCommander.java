package com.gaslibre.gaslibre.Control.Service;

/**
 * Created by renato on 5/23/15.
 */
public class URLCommander {
    //returns right URL to conect the server depending on the the needed task

    private static URLCommander urlCommanderSingleton;

    private String serverURL= "http://52.24.179.139/macaw/";

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

    public String getURLUsers(){
        return serverURL+urlUsers;
    }

    public String getUrlReports() {
        return serverURL+urlReports;
    }

    public String getUrlConversations() {
        return serverURL+urlConversations;
    }

    public String getUrlMessages() {
        return serverURL+urlMessages;
    }

    public String getUrlInterests() {
        return serverURL+urlInterests;
    }

    public String getUrlLanguages() {
        return serverURL+urlLanguages;
    }

    public String getUrlMatches() {
        return serverURL+urlMatches;
    }

    public String getURLUsersGetUser(){
        return getURLUsers()+"getUser/";
    }

}
