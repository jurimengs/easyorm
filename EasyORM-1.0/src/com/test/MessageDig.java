package com.test;


import java.security.MessageDigest;
import java.util.Arrays;

public class MessageDig {
	//alg= MD5 SHA SHA-1
	public byte[] mDigest(String s,String alg) {
		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance(alg);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			return md;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] digest(byte[] data,String algorithm){
		try {
			MessageDigest mdTemp = MessageDigest.getInstance(algorithm);
			mdTemp.update(data);
			byte[] md = mdTemp.digest();
			return md;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String sign(byte[] data,String alg){
		String sign = null;
		try {
			MessageDigest mdTemp = MessageDigest.getInstance(alg);
			mdTemp.update(data);
			byte[] md = mdTemp.digest();
			sign = ByteUtil.bytes2Hex(md);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sign;
	}
	
	public boolean verify(byte[] data,String sign,String alg){
		boolean r = false;
		try {
			byte[] signBytes = ByteUtil.hex2Bytes(sign);
			MessageDigest mdTemp = MessageDigest.getInstance(alg);
			mdTemp.update(data);
			byte[] md = mdTemp.digest();
			r = Arrays.equals(md, signBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public static void main(String[] args){
		MessageDig md = new MessageDig();
		String ac = "Q0001";
		String mid = "778002199990015";
		String cardNo = "7280005200000011";
		
		byte[] res;
		String signCardNo = "";
		try {
			res = SandAPICipher.en3des("237E5B1590882CFE".getBytes(), cardNo.getBytes());
			signCardNo = ByteUtil.bytes2Hex(res);
			System.out.println("3des 加密后得到 signCardNo : "+signCardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] old;
		try {
			old = SandAPICipher.de3des("237E5B1590882CFE".getBytes(), ByteUtil.hex2Bytes(signCardNo));
			System.out.println("3des 解密signCardNo, 得到 : "+ new String(old).trim());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("ac+mid+signCardNo 签名串 sign :" + md.sign((ac+mid+signCardNo).getBytes(), "SHA-1"));
	}
}
