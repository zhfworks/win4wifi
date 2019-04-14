package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NetInfo {
	public static void main(String[] args) {
		
		Runtime runtime = Runtime.getRuntime();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(runtime.exec("ipconfig").getInputStream()));
			String line =null;
			StringBuilder sb = new StringBuilder();
			while((line=br.readLine())!=null) {
				sb.append(line+"\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
