package com.gaslibre.gaslibre.Control.Service;

import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;


public class WebConnector {



    String url = URLCommander.getInstance().getServerURL();
    HttpParams params;
    HttpClient httpClient;
    HttpPost httpPost;

    public WebConnector() {
        params = new BasicHttpParams();
        httpPost = new HttpPost();
    }

    private String toString(InputStream is) throws IOException {

        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    // edit
    public String getRESTFileContent(String urlString, String metodo)  {

        String response = "";

        try {
			/*
			 * Uri Adicionado por Amanda - 31/10/13
			 * Uri eh um objeto que monta uma URL com as propriedades de escape, espaÁo, acentos, etc formatados.
			 */
            URL url = new URL(urlString);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            url = uri.toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(metodo);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(5 * 10000);


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;

            StringBuilder builder = new StringBuilder();
            while ((output = br.readLine()) != null) {
                builder.append(output + "\n");
                response = output;
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {
            Log.e("WsRest", "Erro de Conex„o: " + e.toString() );
        }
        return response;

    }

    private String convertStreamToString(InputStream instream) throws IOException {
        return toString(instream);
    }

    public String setRESTFileContentJSON(JSONObject objeto, String stringURL, String metodo) throws Exception  {


        URL url = new URL(stringURL);
        String retorno = "";

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod(metodo);
        conexao.setDoOutput(true);
        conexao.setRequestProperty("Content-Type", "application/json");
        //conexao.setRequestProperty("Accept-Charset", "UTF-8");
        conexao.setConnectTimeout(5 * 1000);

        String input = objeto.toString();
        Log.e("Input Servidor ", input);


        OutputStream os = conexao.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conexao.getResponseCode() == 500) {
            throw new RuntimeException("Erro HTTP: "
                    + conexao.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conexao.getInputStream()),"UTF-8"));
        String output;

        while((output = br.readLine()) != null) {
            retorno = output;
            Log.v("TAG",retorno);
        }
        conexao.disconnect();
        return retorno;

    }

    public String setRESTFileContentJSONArray(JSONArray objeto, String stringURL, String metodo) throws Exception  {


        URL url = new URL(stringURL);
        String retorno = "";

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod(metodo);
        conexao.setDoOutput(true);
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setRequestProperty("Accept-Charset", "UTF-8");
        conexao.setConnectTimeout(5 * 1000);

        String input = objeto.toString();
        Log.e("Input Servidor ", input);


        OutputStream os = conexao.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conexao.getResponseCode() == 500) {
            throw new RuntimeException("Erro HTTP: "
                    + conexao.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conexao.getInputStream()),"UTF-8"));
        String output;

        while((output = br.readLine()) != null) {
            retorno = output;
            Log.v("TAG",retorno);
        }
        conexao.disconnect();
        return retorno;

    }

    public void downloadFile(String urlString, String pathDestinoArquivo) throws Exception  {

        //String urlString2 = URLEncoder.encode(urlString).toString();
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        url = uri.toURL();

        URLConnection conexao = url.openConnection();
        conexao.setConnectTimeout(5 * 1000);
        conexao.connect();

        // dados prontos para startar uma stream
        int tamanhoArquivo = conexao.getContentLength();
        InputStream is = url.openStream();

        FileOutputStream fos = new FileOutputStream(pathDestinoArquivo);
        byte dados[] = new byte[1024];
        int dados_lidos = 0;
        while ((dados_lidos = is.read(dados)) != -1) {
            fos.write(dados, 0, dados_lidos);
            Log.v("TAG", "Lendo dados");
        }
        is.close();
        fos.close();


    }

    public int uploadPostagemGrupoUsuario(String idUsuario, String idGrupo, String urlServer, File file) {

        int retorno = -1;

        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;

        // tamanho m·ximo de buffer È 1MB
        int maxBufferSize = 1 * 1024 * 1024;
        int serverResponseCode = 200;

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            URL url = new URL(urlServer);
            connection = (HttpURLConnection) url.openConnection();

            // Allow Inputs & Outputs
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // Enable POST method
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
				/*
				 * ------------------------------------ POST
				 * ------------------------------------
				 */
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);
				/* 1- CabeÁalho */
            outputStream
                    .writeBytes("Content-Disposition: form-data; name=\"arquivo\";filename=\""
                            + file.getPath() + "\"" + lineEnd);
            outputStream.writeBytes(lineEnd);

				/* 2- Conteudo */

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Read file
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnd);
				/* 3- Fim do post */
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                    + lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

				/*
				 * ------------------------------------ POST FIM
				 * ------------------------------------
				 */
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

            fileInputStream.close();
            outputStream.flush();
            outputStream.close();


		/*		BufferedReader br = new BufferedReader(new InputStreamReader(
						(connection.getInputStream())));
				String output;

				while ((output = br.readLine()) != null) {
						Log.v("TAG RESPONSE",output);
				}*/

            serverResponseCode = connection.getResponseCode();
            connection.disconnect();
            if(serverResponseCode == 200)
                return 1;
            return -1;

        } catch (Exception e) {
            e.printStackTrace();
            return retorno;
        }

    }

}

