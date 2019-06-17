package com.example.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class restApiController {
	
	
	//목록 페이지 URL
    @RequestMapping("/list")
    public String restApiController(HttpServletRequest request, Model model) throws Exception {
    	

    	  // POSTMAN 토큰값 불러오기!!
		  URL tokenUrl = new URL("http://192.168.50.12:5000/v3/auth/tokens");
		  
		  HttpURLConnection tokenConn = (HttpURLConnection) tokenUrl.openConnection();
		  tokenConn.setDoOutput(true);
		  tokenConn.setRequestMethod("POST");
		  tokenConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); 
		  
		  
		  //POSTMAN token값 인증하기 위한 json정보(body정보)
		  String json = "{\r\n    \"auth\": {\r\n        \"identity\": {\r\n            \"methods\": [\"password\"],\r\n            \"password\": {\r\n                \"user\": {\r\n                \t\"domain\" : {\r\n                \t\t\"name\" : \"default\"\r\n                \t},\r\n                    \"name\": \"admin\",\r\n                    \"password\": \"cone@234\"\r\n                }\r\n            }\r\n        },\r\n        \"scope\": {\r\n        \t\"project\": {\r\n            \t\"domain\" : {\r\n                \"name\": \"default\"\r\n            },\r\n            \"id\": \"bce40eb4b1364cfca9708536d7c28940\"\r\n        }\r\n    }\r\n}\r\n}";
		  
     	  OutputStream osw = tokenConn.getOutputStream();	  
		  osw.write(json.getBytes("UTF-8") );
		  osw.flush();
		  
		  //getResponseCode ="웹의 접근 에러 코드 :: 201이면 성공"
		  if (tokenConn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { 
		      throw new RuntimeException("Failed : HTTP error code : " 
		       + tokenConn.getResponseCode()); 
		     } 		  
		  System.out.println("연결상태 확인:::"+tokenConn.getResponseCode());
		  
		      
	      //보낸 값의 정보 가져오기
	      Map<String, List<String>> map = tokenConn.getHeaderFields();
	      		System.out.println("Printing Response Header...\n");
	      		
	      		//보낸값에 대한 응답 (key,Value) 가져오기
	      		System.out.println("----------------------------- KEY/VALUE 정보 -----------------------------");
			  	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			  		System.out.println("Key : " + entry.getKey() 
			                             + " ,/ Value : " + entry.getValue());
			  	}
			  	System.out.println("-----------------------------------------------------------------------------");
	
			  	//보낸값 하나의 정보 가져오기 테스트(ex 토큰값 가져오기!)
			  	List<String> token = map.get("X-Subject-Token");
			  	System.out.println("토큰값:::"+token);

	      tokenConn.disconnect(); 
	      
	      
	      //* ******************************** 목록 페이지 URL ********************************
		  // 목록페이지 오픈스택 URL
		  URL url = new URL("http://192.168.50.12:35357/v3/projects/");

		  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		  conn.setRequestMethod("GET");
		  conn.setRequestProperty("Accept","application/json");
		  
		  //POSTMAN 토큰값 (KEY,VALUE)
		  conn.setRequestProperty("X-Auth-Token",token.toString());
		  conn.getResponseCode();
	  
		  InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"UTF-8");
		  
		  //파싱시작 
          JSONObject jsonObject = null;
          JSONParser parser = new JSONParser();
          jsonObject = (JSONObject) parser.parse(isr); // 파싱 수행
          
          //POSTMAN에서 JSON값 불러오기(EX) projects부터 짜르겠다.) - 목록
          JSONArray arrRestList = (JSONArray) jsonObject.get("projects");          
          System.out.println("POSTMAN JSON 값 (목록)::::"+arrRestList);
          
          
          //리스트
          List<restApiVo> list = new ArrayList<>();
          
          for (int i=0; i<arrRestList.size(); i++) {
			
			  restApiVo restApiVo = new restApiVo();
			  
			  JSONObject objRestApi= (JSONObject) arrRestList.get(i); 
			  
			  String name = (String) objRestApi.get("name"); 							// 이름
			  String description = (String) objRestApi.get("description");			    // 설명
			  String id = (String) objRestApi.get("id");										// 프로젝트ID
			  String domain_id = (String) objRestApi.get("domain_id");				// 도메인이름
			  boolean enabled = (boolean) objRestApi.get("enabled");				// 활성화됨

			  list.add(restApiVo);
			  model.addAttribute("arrRestList", arrRestList);
			  
          }        

	    return "list";
    }
    
    
    
    //* ******************************** 상세페이지 URL ********************************
    @RequestMapping(value="/list/{id}/detail")
    public String Details(@PathVariable(value="id") String id, HttpServletRequest request, Model model) throws Exception {
    	
    	
    	 // POSTMAN 토큰값 불러오기!!
		  URL tokenUrl = new URL("http://192.168.50.12:5000/v3/auth/tokens");
		  
		  HttpURLConnection tokenConn = (HttpURLConnection) tokenUrl.openConnection();
		  tokenConn.setDoOutput(true);
		  tokenConn.setRequestMethod("POST");
		  tokenConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); 
		  
		  //POSTMAN code값 인증하기 위한 json정보
		  String json = "{\r\n    \"auth\": {\r\n        \"identity\": {\r\n            \"methods\": [\"password\"],\r\n            \"password\": {\r\n                \"user\": {\r\n                \t\"domain\" : {\r\n                \t\t\"name\" : \"default\"\r\n                \t},\r\n                    \"name\": \"admin\",\r\n                    \"password\": \"cone@234\"\r\n                }\r\n            }\r\n        },\r\n        \"scope\": {\r\n        \t\"project\": {\r\n            \t\"domain\" : {\r\n                \"name\": \"default\"\r\n            },\r\n            \"id\": \"bce40eb4b1364cfca9708536d7c28940\"\r\n        }\r\n    }\r\n}\r\n}";
		  
		  OutputStream osw = tokenConn.getOutputStream();	  
		  osw.write( json.getBytes("UTF-8") );
		  osw.flush();
		  
		  //getResponseCode ="웹의 접근 에러 코드 :: 201이면 성공"
		  if (tokenConn.getResponseCode() != HttpURLConnection.HTTP_CREATED) { 
		      throw new RuntimeException("Failed : HTTP error code : " 
		       + tokenConn.getResponseCode()); 
		     } 
		  System.out.println("연결상태확인:::"+tokenConn.getResponseCode());
		  
      
	      //보낸 값의 정보 가져오기
	      Map<String, List<String>> map = tokenConn.getHeaderFields();
	      		System.out.println("Printing Response Header...\n");
	      		
	      		//보낸값에 대한 key,Value 가져오기
	      		System.out.println("----------------------------- KEY/VALUE 정보 -----------------------------");
			  	for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				  		System.out.println("Key : " + entry.getKey() 
				                             	+ " ,/ Value : " + entry.getValue());
			  	}
			  	System.out.println("-----------------------------------------------------------------------------");
	
			  	//보낸값 하나의 정보 가져오기 테스트(ex 토큰값 가져오기!)
			  	List<String> token = map.get("X-Subject-Token");
			  	System.out.println("토큰값은?::::"+token);

	      tokenConn.disconnect(); 
	      
	      
    	//상세정보 오픈스택 URL
		URL url = new URL("http://192.168.50.12:35357/v3/projects/"+id);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept","application/json");
		  
		//POSTMAN 토큰값 (KEY,VALUE)
		conn.setRequestProperty("X-Auth-Token", token.toString());
		conn.getResponseCode();
		BufferedReader br = new BufferedReader(new  InputStreamReader((conn.getInputStream())));
		InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"UTF-8");
		 
		//파싱시작
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        jsonObject = (JSONObject) parser.parse(isr); // 파싱 수행
        
        
        //POSTMAN에서 JSON값 불러오기(EX) projects부터 짜르겠다.) - 상세
        JSONObject jsonData = (JSONObject) jsonObject.get("project");
        System.out.println("POSTMAN JSON값 (상세)::::"+jsonData);

        
        //객체담기
 	    restApiDetailVo restApiDetailVo = new restApiDetailVo();
    	
    	String name = (String)jsonData.get("name");                       // 이름
    	String project_id = (String)jsonData.get("id");						 // 프로젝트id
    	String description = (String)jsonData.get("description");		 // 설명
    	boolean enabled = (boolean)jsonData.get("enabled");		 // 활성화됨
    	
    	restApiDetailVo.setName(name);
    	restApiDetailVo.setId(project_id);
    	restApiDetailVo.setDescription(description);
    	restApiDetailVo.setEnabled(enabled);
    	
    	request.setAttribute("name", name);
    	request.setAttribute("project_id", project_id);
    	request.setAttribute("description", description);
    	request.setAttribute("enabled", enabled);
    	
    	return "detail";
    }
     
}

