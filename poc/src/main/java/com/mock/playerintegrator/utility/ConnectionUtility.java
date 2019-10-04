package com.mock.playerintegrator.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.playerintegrator.controller.IntegratorController;
import com.mock.playerintegrator.dto.RequestDto;

public class ConnectionUtility {
	
	
	private static final Logger logger=Logger.getLogger(ConnectionUtility.class);
	
	public static String post(String url,RequestDto dto) {
		try {
			logger.error(url);
			URL urlObj = new URL (url);
			HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			ObjectMapper obj = new ObjectMapper(); 
			String jsonString=obj.writeValueAsString(dto);
			if (jsonString != null) {
				con.setRequestProperty("Content-Length", Integer.toString(jsonString.length()));

				// For POST only - START
				con.setDoOutput(true);
				OutputStream os = con.getOutputStream();
				os.write(jsonString.getBytes("UTF8"));
				InputStream  inputStream=con.getInputStream();
				StringBuilder builder=new StringBuilder();
				int i=0;
				while((i=inputStream.read())!=-1) {
					
					builder.append((char)i);
				}
				System.out.println("response"+builder);
				return builder.toString();
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}
}
