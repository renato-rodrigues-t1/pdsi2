package com.gaslibre.gaslibre.Control.Service;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


//Executa processo em backrground

public class SetRESTFile{
	

	public String connect(Object... params) {

		String result = "";
		try {
			
			if(params.length > 0) {
					WebConnector web = new WebConnector();
					
					String urlUsuario = (String) params[1];
					String tipo = (String) params[2];
					
					if(params[0] instanceof JSONObject)  {					
						JSONObject jsonObject = (JSONObject) params[0];				
						result = web.setRESTFileContentJSON(jsonObject,urlUsuario,tipo).toString();
					} else if(params[0] instanceof JSONArray) {
						JSONArray jsonArray = (JSONArray) params[0];				
						result = web.setRESTFileContentJSONArray(jsonArray,urlUsuario,tipo).toString();
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//resultado = -1;
		}
		return result;
	}

	
}
