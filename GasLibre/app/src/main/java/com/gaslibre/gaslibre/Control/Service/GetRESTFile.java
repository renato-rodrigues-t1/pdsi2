package com.gaslibre.gaslibre.Control.Service;

//executa processo em background
public class GetRESTFile{
	
	public String connect(Object...params) {

		String result = "";
		try {
			
			if(params.length > 0) {
					WebConnector web = new WebConnector();
					String urlUsuario = (String) params[0];
					String metodo = (String) params[1];
					result = web.getRESTFileContent(urlUsuario,metodo).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			//resultado = -1;
		}
		return result;
	}
	
}
