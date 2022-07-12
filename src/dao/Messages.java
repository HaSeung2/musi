package dao;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class Messages {

public String phone(String musicianphone) {
	String api_key = "NCSCTVONVQHFNXQD";
    String api_secret = "VU80WHPTTBSEPAAQN4CYO0VCLNJGABCP";
    Message coolsms = new Message(api_key, api_secret);
    Random random = new Random();
    String numStr = "";
    for(int i=0; i<6; i++) {
       String ran = Integer.toString(random.nextInt(10));
       numStr+=ran;
    }          
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", musicianphone);
    params.put("from", "01056419222");
    params.put("type", "SMS");
    params.put("text", "인증번호는 ["+numStr+"] 입니다.");
    params.put("app_version", "test app 1.2"); // application name and version
    try {
		coolsms.send(params); // 메시지 전송
	} catch (CoolsmsException e) {
	}
    return numStr;
}
public String phones(String normalphone) {
	String api_key = "NCSCTVONVQHFNXQD";
    String api_secret = "VU80WHPTTBSEPAAQN4CYO0VCLNJGABCP";
    Message coolsms = new Message(api_key, api_secret);
    Random random = new Random();
    String numStr = "";
    for(int i=0; i<6; i++) {
       String ran = Integer.toString(random.nextInt(10));
       numStr+=ran;
    }          
    HashMap<String, String> params = new HashMap<String, String>();
    params.put("to", normalphone);
    params.put("from", "01056419222");
    params.put("type", "SMS");
    params.put("text", "인증번호는 ["+numStr+"] 입니다.");
    params.put("app_version", "test app 1.2"); // application name and version
    try {
		coolsms.send(params); // 메시지 전송
	} catch (CoolsmsException e) {
	}
    return numStr;
}
}

