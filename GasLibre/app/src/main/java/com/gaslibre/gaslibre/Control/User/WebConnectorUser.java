package com.gaslibre.gaslibre.Control.User;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.gaslibre.gaslibre.Control.Service.*;
import com.gaslibre.gaslibre.DAO.UserDAO;
import com.gaslibre.gaslibre.Model.User;

/**
 * Created by renato on 5/23/15.
 */
public class WebConnectorUser {

    private WebConnector webConnector;
    private UserDAO userDao;
    public static Context context;

    private static WebConnectorUser instance;

    private WebConnectorUser(){}

    public static WebConnectorUser getInstance(Context context){
        if(instance==null){
            instance= new WebConnectorUser();
            instance.setWebConnector(new WebConnector());
            instance.setContext(context);
        }
        return instance;
    }

    public WebConnector getWebConnector() {
        return webConnector;
    }

    public void setWebConnector(WebConnector webConnector) {
        this.webConnector = webConnector;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static void setInstance(WebConnectorUser instance) {
        WebConnectorUser.instance = instance;
    }

    public User getUserServer(String email, String senha){

        String urlLogin= URLCommander.getInstance().getURLLogin(email, senha);
            String result = "";
            User userReturn= null;

            Log.v("URLcUSER>>>>>>>",urlLogin);

        try {
                Object[] array = { urlLogin, "GET" };
                result = (new GetRESTFile()).connect(array);
            Log.v("result",result);
                if(!result.equals("-1")){
                    userReturn= new User();

                    /*JSONArray jsonArray = new JSONArray(result);

                    if (jsonArray.length() == 0) {
                        return userReturn= null;
                    }

                    userReturn= new User();
                        //userReturn = new User();
                        JSONObject objJson = new JSONObject(jsonArray.getString(0));

                        // JsonObjects
                        JSONObject objectUser = objJson.getJSONObject("User");

                        // Retrieving data from server
                        userReturn.setId("1");
                        userReturn.setName(objectUser.getString("Nome").toString());
                        userReturn.setEmail(objectUser.getString("Email").toString());
                        userReturn.setSenha(objectUser.getString("Senha").toString());
*/
                }
            } catch (Exception e) {
                e.printStackTrace();
                return userReturn;
            }
            return userReturn;
        }
    }

/*
public class WebConnectorNotificacao {

    public int insereNotificacao(Notificacao notificacao, int idUsuarioDestinatario) {
        int retorno = -1;
        JSONObject jsonObject = new JSONObject();
        String result = "";

        try {
            jsonObject.put("usuario_id", notificacao.getUsuario_remetente().getIdServer());
            jsonObject.put("titulo", notificacao.getTitulo());
            jsonObject.put("descricao", notificacao.getDescricao());

            String url = Global.getURLAdicionaMensagem() + idUsuarioDestinatario;
            String metodo = Global.ADICAO;

            Object[] array = { jsonObject, url, metodo };

            result = (new SetRESTFile()).connect(array);
            retorno = Integer.parseInt(result);

        } catch (Exception e) {
            e.printStackTrace();
            retorno = -1;
        }
        return retorno;
    }

    public List<Notificacao> getNotificacoesServidor(int idUsuario, Context contexto) {
        String result = "";
        notificacaoDao = new NotificacaoDAO(contexto);
        int idMensagem = notificacaoDao.getUltimoIdNotificacao();

        String url = Global.getURLGetMensagensUsuario() + idUsuario + "/" + idMensagem;
        List<Notificacao> notificacoes = new ArrayList<Notificacao>();

        try {
            Object[] array = { url, Global.FIND };
            result = (new GetRESTFile()).connect(array);

            if(!result.equals("-1")){
                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() == 0) {
                    return notificacoes;
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    Notificacao notificacao = new Notificacao();
                    Usuario usuario = new Usuario();
                    JSONObject objJson = new JSONObject(jsonArray.getString(i));

                    // JsonObjects
                    JSONObject objectNotificacao = objJson.getJSONObject("Mensagem");
                    JSONObject objectUsuario = objJson.getJSONObject("Usuario");
                    JSONObject objectUsuarioMensagem = objJson.getJSONObject("Usuario_Mensagem");
                    JSONObject objectUsuarioDestinatario = objJson.getJSONObject("Usuario_Destinatario");

                    // Recebendo Dados
                    notificacao.setId_server(objectNotificacao.getInt(("id_mensagem_server").toString()));
                    usuario.setIdServer(objectNotificacao.getString(("id_remetente")));
                    usuario.setNome(objectUsuario.getString("nome_remetente"));
                    notificacao.setTitulo(objectNotificacao.getString("titulo_mensagem"));
                    notificacao.setDescricao(objectNotificacao.getString("descricao_mensagem"));
                    notificacao.setId_destinatario(objectUsuarioMensagem.getInt(("id_destinatario").toString()));
                    notificacao.setNome_destinatario(objectUsuarioDestinatario.getString(("nome_destinatario").toString()));
                    //notificacao.setData_criacao(objectNotificacao.getString("data_criacao").toString()));

                    notificacao.setUsuario_remetente(usuario);
                    notificacoes.add(notificacao);
                    notificacaoDao.salvarNotificacoesUsuario(notificacao);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return notificacoes;
        }
    }*/

