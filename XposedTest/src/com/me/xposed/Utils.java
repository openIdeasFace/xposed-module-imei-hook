package com.me.xposed;

import java.util.Random;

public class Utils {

	public static String randomnetworktype() {	
		String[] networktypes = new String[]{"LTE","UMTS","IDEN","HSUPA","HSPA","HSDPA","GPRS","EVDO","EDGE","CDMA"};
		Random rnd = new Random();
		String result = networktypes[rnd.nextInt(networktypes.length)];
		return result;
	}
	
	public static String randomNum(int n)
	{
		String res = "";
		Random rnd = new Random();
		for (int i = 0; i < n; i++)
		{
			res = res + rnd.nextInt(10);
		}
		return res;
	}
	
	public static String randomPhone()
	{
		/** 前锟斤拷为 */
		String head[] = { "+8613", "+8615", "+8617", "+8618", "+8616" };
		Random rnd = new Random();
		String res = head[rnd.nextInt(head.length)];
		for (int i = 0; i < 9; i++)
		{
			res = res + rnd.nextInt(10);
		}
		return res;
	}
	
	public static String randomMac()
	{
		String chars = "abcde0123456789";
		String res = "";
		Random rnd = new Random();
		int leng = chars.length();
		for (int i = 0; i < 17; i++)
		{
			if (i % 3 == 2)
			{
				res = res + ":";
			} else
			{
				res = res + chars.charAt(rnd.nextInt(leng));
			}

		}
		return res;
	}
	
	public static String randomMac1()
	{
		String chars = "ABCDE0123456789";
		String res = "";
		Random rnd = new Random();
		int leng = chars.length();
		for (int i = 0; i < 17; i++)
		{
			if (i % 3 == 2)
			{
				res = res + ":";
			} else
			{
				res = res + chars.charAt(rnd.nextInt(leng));
			}

		}
		return res;
	}
	
	public static String randomIP() {
		String chars = "0123456789";
		String res = "";
		Random rnd = new Random();
		int leng = chars.length();
		for (int i = 0; i < 4; i++)
		{
			int length = rnd.nextInt(3);
			if(length == 0) {
				length = 1;
			}
			String temp = "";
			for(int j=0;j<length;j++) {
				temp += chars.charAt(rnd.nextInt(leng));
			}
			int temNum = Integer.parseInt(temp);
			res += temNum+".";
		}
		return res;
	}
	
	public static String randomABC(int n)
	{
		String chars = "abcdef0123456789";
		String res = "";
		Random rnd = new Random();
		int leng = chars.length();
		for (int i = 0; i < n; i++)
		{
			res = res + chars.charAt(rnd.nextInt(leng));

		}
		return res;
	}
	
	public static String randomChar() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		int n = rand.nextInt(26);
		if(n < 6) {
			n = 6;
		}
		int leng = chars.length();
		String res = "";
		for (int i = 0; i < n; i++)
		{
			res = res + chars.charAt(rand.nextInt(leng));
		}
		return res;
		
	}
	
	public static String randomISP() {
		String[] isps = new String[]{"中国联通","中国移动","中国电信"};
		Random rnd = new Random();
		return isps[rnd.nextInt(isps.length)];
	}
}
