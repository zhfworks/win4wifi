package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetWiFi {
	public static void main(String[] args) {
		
		GetWiFi win10 = new GetWiFi();
		BufferedReader br = win10.doCMD("netsh wlan show profiles");
		List<String> list = win10.doFilter(br,"�����û������ļ�");
//		for(String each:list){
//			System.out.println("wifi����:"+each);
//		}
		
		for(String each:list) {
			String cmdStr2="netsh wlan show profiles name=\""+each.trim()+"\" key=clear";
//			System.out.println(cmdStr2);
			br = win10.doCMD(cmdStr2);
			List<String> list2 = win10.doFilter(br, "�ؼ�����");
			if(!list2.isEmpty()) {
				System.out.println(each+"==>>"+list2.get(0).trim());
			}
					
		}	
		
		
	}
	
	public List<String> doFilter(BufferedReader br,String key) {
		if(br==null) {
//			throw new RuntimeException("br����Ϊ��");
			throw new NullPointerException("br����Ϊ��");
		}
		if(key==null || "".equals(key)) {
			System.out.println("key����Ϊ��");
		}
		String line = null;
		List<String>list = new ArrayList<String>();
		try {
			while ((line=br.readLine())!=null) {
				String lin = line.trim();
				if(lin.startsWith(key)) {
					list.add(lin.split(":")[1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return list;
		
	}
	
	
	
	public BufferedReader doCMD(String cmd) {
		if(cmd!=null) {
			BufferedReader br=null;
			try {
				Runtime runtime = Runtime.getRuntime();
				br = new BufferedReader(new InputStreamReader(runtime.exec(cmd).getInputStream()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return br;
		}
		return null;
	}
	
	
	
}
