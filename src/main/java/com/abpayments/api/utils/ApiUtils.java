package com.abpayments.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ApiUtils {
	
	
	public static int randomNumberGen(int min, int max) {
		BinaryOperator<Integer> bi = (a, b) -> new Random().nextInt(b - a) + a;
		return bi.apply(min, max);
	}
	
	
	
	public static int getPaymentPath(int userid) {
		int result = userid % 3;
		return result;
	}
	
	public static JSONArray getPaths(int userid) throws JSONException {
		switch (userid) {
		case 0:
			return new JSONArray("[\r\n"
					+ "		\"PayTM\",\r\n"
					+ "		\"UPI\",\r\n"
					+ "		\"NetBanking\"\r\n"
					+ "	]");
		case 1:
			return new JSONArray("[\r\n"
					+ "		\"UPI\",\r\n"
					+ "		\"PayTM\",\r\n"
					+ "		\"NetBanking\"\r\n"
					+ "	]");
		case 2:
			return new JSONArray("[\r\n"
					+ "		\"NetBanking\",\r\n"
					+ "		\"PayTM\",\r\n"
					+ "		\"UPI\"\r\n"
					+ "	]");
		
		}
		return new JSONArray();
	}
	
	public static int majority(int[] arr) {
		 HashMap<Integer, Integer> map = new HashMap<>();
	        
	        for(Integer i : arr){
	            if(map.containsKey(i)){
	                map.put(i, map.get(i) + 1);
	            }else{
	                map.put(i,1);
	            }
	        }
	        Set<Entry<Integer, Integer>> entryKey = map.entrySet();
	        int max = 0, res = -1;
	        for(Entry<Integer, Integer> entry : entryKey){
	            if(max < entry.getValue()){
	            max = entry.getValue();
	            res = entry.getKey();
	            }
	        }
	        return res;
	}

}
