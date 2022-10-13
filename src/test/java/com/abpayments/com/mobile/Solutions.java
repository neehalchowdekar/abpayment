package com.abpayments.com.mobile;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solutions {
	
	
	public static void main(String[] args) {
		String message = "Hi , your login otp is 567890. Please dont share it with anyone.";
		message = "message";
		
		char[] arr = message.toCharArray();
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < arr.length; i++) {
			if(Character.isDigit(arr[i])) {
				sb.append(arr[i]);
			}
		}
		
		System.out.println(sb.toString());
		
		String [] expected = {"India" , "US" , "UK"};
		String [] actual   = {"Japan" , "UK" , "France"};
		
		//o/p : missing expected value : India, US and actual Japan, France which are not expected
		
		Map<String, Integer> map = new HashMap<>();
		map.put("Amit", 75);
		map.put("Vijay", 90);
		map.put("Aman", 79);
		map.put("Amy", 88);
		map.put("Bob", 55);
	
		SortedMap<String, Integer> tmap = new TreeMap<>(map);
		
		Set<Entry<String, Integer>> keys =  tmap.entrySet();
		
		for(Entry<String, Integer> key : keys) {
			System.out.println(key.getKey() + ": " + key.getValue());
		}
	}
	
	public static void solution(String[] expected, String[] actual) {
		
		
	}

}
