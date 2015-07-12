package com.gaslibre.gaslibre.Control.Posto;

import android.content.Context;
import android.util.Log;

import com.gaslibre.gaslibre.Control.Service.GetRESTFile;
import com.gaslibre.gaslibre.Control.Service.URLCommander;
import com.gaslibre.gaslibre.Control.Service.WebConnector;
import com.gaslibre.gaslibre.DAO.UserDAO;
import com.gaslibre.gaslibre.Model.User;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.gaslibre.gaslibre.Model.Posto;
import java.util.ArrayList;

/**
 * Created by renato on 5/23/15.
 */
public class WebConnectorPosto {

    private WebConnector webConnector;
    private UserDAO userDao;
    private Context context;

    private static WebConnectorPosto instance;

    private WebConnectorPosto() {
    }

    public static WebConnectorPosto getInstance(Context context) {
        if (instance == null) {
            instance = new WebConnectorPosto();
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

    public static void setInstance(WebConnectorPosto instance) {
        WebConnectorPosto.instance = instance;
    }

    /*
    this method receives an user locally persisted and retrieves its most recent version on the server which is the central point of information
    It is only called if its not the first login. Users just signed up does not have idServer until the first persistence.
     */

    public ArrayList<Posto> buscaPostos(int combustivel, float coordenateX, float coordenateY) {
        String result = "";
        //notificacaoDao = new NotificacaoDAO(contexto);
        //int idMensagem = notificacaoDao.getUltimoIdNotificacao();
        String combustivelString = "";
        if (combustivel == 1) {
            combustivelString = "gas";
        } else if (combustivel == 2) {
            combustivelString = "alc";
        } else if (combustivel == 3) {
            combustivelString = "die";
        }

        String url = URLCommander.getInstance().getURLBuscaPosto()+combustivel;
        Log.v("URL-------->",url);
        ArrayList<Posto> postos = new ArrayList<Posto>();

        try {
            Object[] array = {url, "GET"};
            result = (new GetRESTFile()).connect(array);

            if (!result.equals("-1")) {
                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() == 0) {
                    return postos;
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    Posto posto = new Posto();
                    JSONObject objJson = new JSONObject(jsonArray.getString(i));

                    // JsonObjects
                    JSONObject objectPosto = objJson.getJSONObject("Posto");

                    // Recebendo Dados
                    posto.setClassificacao(objectPosto.getString("classificacao").toString());
                    posto.setCoordenateX(Integer.parseInt(objectPosto.getString("coordenadaX")));
                    posto.setCoordenateY(Integer.parseInt(objJson.getString("coordenadaY")));
                    posto.setDiesel(Integer.parseInt(objectPosto.getString("diesel")));
                    posto.setGasolina((Integer.parseInt(objectPosto.getString("gasolina"))));
                    posto.setEtanol(Integer.parseInt(objectPosto.getString("etanol")));
                    posto.setEndereco(objectPosto.getString("endereco").toString());
                    posto.setId(Integer.parseInt(objectPosto.getString("id")));
                    posto.setServico(objectPosto.getString("servico").toString());

                    postos.add(posto);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return postos;
    }
}

    /*public ArrayList<Posto> buscaPostosByServico(String servico){
        String result = "";
        //notificacaoDao = new NotificacaoDAO(contexto);
        //int idMensagem = notificacaoDao.getUltimoIdNotificacao();

        String url = URLCommander.getInstance().getURLBuscaServico()+"/"+servico;
        ArrayList<Posto> postos = new ArrayList<Posto>();

        try {
            Object[] array = { url, "GET" };
            result = (new GetRESTFile()).connect(array);

            if(!result.equals("-1")){
                JSONArray jsonArray = new JSONArray(result);

                if (jsonArray.length() == 0) {
                    return postos;
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    Posto posto = new Posto();
                    JSONObject objJson = new JSONObject(jsonArray.getString(i));

                    // JsonObjects
                    JSONObject objectPosto = objJson.getJSONObject("Posto");

                    // Recebendo Dados
                    posto.setClassificacao(objectPosto.getString("classificacao").toString());
                    posto.setCoordenateX(Integer.parseInt(objectPosto.getString("coordenadaX")));
                    posto.setCoordenateY(Integer.parseInt(objJson.getString("coordenadaY")));
                    posto.setDiesel(Integer.parseInt(objectPosto.getString("diesel")));
                    posto.setGasolina((Integer.parseInt(objectPosto.getString("gasolina"))));
                    posto.setEtanol(Integer.parseInt(objectPosto.getString("etanol")));
                    posto.setEndereco(objectPosto.getString("endereco").toString());
                    posto.setId(Integer.parseInt(objectPosto.getString("id")));
                    posto.setServico(objectPosto.getString("servico").toString());

                    postos.add(posto);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return postos;
    }}
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

