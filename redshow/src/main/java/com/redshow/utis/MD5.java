package com.redshow.utis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class MD5 {

	private MD5() {}
	
	private static final char[] CHARACTERS = { 
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
			'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', 
			'7', '8', '9' 
	};
	
	
	//加密
	public static String encrypt(String str, String security) {
		String s = reCombine(str, security);
		try {
			//获取md5的加密对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			//获取字符串s的字节数组并将其作为参数进行加密计算，
			byte[] digest = md.digest(s.getBytes());
			//使用Base64的getEncoder方法获得Base64.Encoder，在将Encoder转换成String
			return Base64.getEncoder().encodeToString(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/*获取随机的字符串作为随机盐*/
	public static String getRandomStr(int length) {
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i=0; i<length; i++) {
			int index = r.nextInt(CHARACTERS.length);
			//将字符转换成字符串
			String s = Character.toString(CHARACTERS[index]);
			//自定义字母的大小写，
			if(index % 3 == 0) {
				sb.append(s.toUpperCase());
			} else {
				sb.append(s);
			}
		}
		return sb.toString();
	}
	/*组合字符串将字符与密码经行组合*/
	private static String reCombine(String str,String security) {
		StringBuilder sb = new StringBuilder();
		sb.append(security.substring(0, 5));
		sb.append(str);
		sb.append(security.substring(5));
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String salt = getRandomStr(20);
		System.out.println(salt);
		String encrpt = encrypt("aaaaaa", salt);
		System.out.println(encrpt);
	}
}
