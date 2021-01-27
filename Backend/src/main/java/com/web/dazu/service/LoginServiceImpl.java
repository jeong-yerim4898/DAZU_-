package com.web.dazu.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public String getAccessToken(String code) {
		String accessToken = "";
        String refreshToken = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=2ce9bedc0889520f06b58f54d0724e65");
            sb.append("&redirect_uri=http://localhost:8000/dazu/login");
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();
            
            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
 
            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            accessToken = element.getAsJsonObject().get("accessToken").getAsString();
            refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();
            
            System.out.println("accessToken : " + accessToken);
            System.out.println("refresh_token : " + refreshToken);
            
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return accessToken;
	}

	@Override
	public HashMap<String, Object> getMemberInfoKAKAO(String accessToken) throws Exception{
		//	    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
		     URL url = new URL(reqURL);
		     HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		     conn.setRequestMethod("POST");
		        
		     //    요청에 필요한 Header에 포함될 내용
		     conn.setRequestProperty("Authorization", "Bearer " + accessToken);
		        
		     int responseCode = conn.getResponseCode();
		     System.out.println("responseCode : " + responseCode);
		        
		     BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		        
		     String line = "";
		     String result = "";
		        
		     while ((line = br.readLine()) != null) {
		         result += line;
		     }
		     System.out.println("response body : " + result);
		        
		     JsonParser parser = new JsonParser();
		     JsonElement element = parser.parse(result);
		        
		     JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		     String userId = element.getAsJsonObject().get("id").getAsString();
		        
		     String nickname = properties.getAsJsonObject().get("nickname").getAsString();
		     String profileImage = properties.getAsJsonObject().get("profile_image").getAsString();
		     
		     userInfo.put("profileImage", profileImage);
		     userInfo.put("nickname", nickname);
		     userInfo.put("id", userId);
		        
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
		    
		 return userInfo;
	}

	@Override
	public void logout(String accessToken) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + accessToken);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String result = "";
	        String line = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println(result);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
