package com.test;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SandAPICipher {
	

	private static final byte[] key = {0x56,0x27,0x01,(byte)0xd7,0x34,(byte)0xbf,(byte)0x97,0x77,(byte)0x8e,(byte)0x93,0x76,(byte)0xf6,0x50,0x4c,0x16,(byte)0xf2};
	
	
	public static byte[] getKey(){
		return key;
	}
	
	
	public static byte[] endes(byte[] key, byte[] text,String alg) throws Exception{
		try {
			Cipher cipher = Cipher.getInstance(alg);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new Exception("��Des����ʱ����." + e);
		}
	} 
	
	public static byte[] dedes(byte[] key, byte[] text,String alg) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance(alg);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new Exception("��Des����ʱ����." + e);
		}
	}
	/**
	 * des����
	 * @param key
	 * @param text
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] endes(byte[] key, byte[] text) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new Exception("��Des����ʱ����." + e);
		}
	}

	/**
	 * des������
	 * @param key
	 * @param text
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] dedes(byte[] key, byte[] text) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "DES"));
			return cipher.doFinal(fillDesData(text));
		} catch (Exception e) {
			throw new Exception("��Des����ʱ����." + e);
		}
	}

	/**
	 * ������Ϊdes��Ҫ��8�ı������ں������0x00
	 * @param data
	 * @return byte[]
	 */
	private static byte[] fillDesData(byte[] data) {
		int nn = 8 - data.length % 8;
		if (nn != 8) {
			byte[] tbs = new byte[nn];
			for (int i = 0; i < nn; i++)
				tbs[i] = 0x00;
			data = ByteUtil.union(data, tbs);
		}
		return data;
	}

	/**
	 * 3des���� <br>
	 * �����㷨�� <br>
	 * endes(KEY_left,dedes(KEY_right,endes(KEY_left,DATA))) <br>
	 * @param key ����16�ֽ�
	 * @param data
	 * @return byte[]
	 * @throws Exception 
	 */
	public static byte[] en3des(byte[] key, byte[] data) throws Exception {
		// �����Կ�ֽڳ���
		if (key.length != 16 && key.length !=24)
			throw new Exception("��3Des���ܵ���Կ��Ҫ��16 ��24 �ֽ�.");
		try {
			data = endes(ByteUtil.sub(key, 0, 8), data);
			data = dedes(ByteUtil.sub(key, 8, 8), data);
			if(key.length == 24){
			    data = endes(ByteUtil.sub(key, 16, 8), data);
			}else{
				data = endes(ByteUtil.sub(key, 0, 8), data);
			}		
		} catch (Exception e) {
			throw new Exception("��3Des����ʱ����." + e);
		}
		return data;
	}
	
	/**
	 * 3des���� <br>
	 * �����㷨�� <br>
	 * dedes(KEY_left,endes(KEY_right,dedes(KEY_left,DATA))) <br>
	 * 
	 * @param key ����16�ֽ�
	 * @param data
	 * @return byte[]
	 * @throws Exception 
	 */
	public static byte[] de3des(byte[] key, byte[] data) throws Exception {
		// �����Կ�ֽڳ���
		if (key.length != 16 && key.length !=24)
			throw new Exception("��3Des���ܵ���Կ��Ҫ��16 ��24 �ֽ�.");
		try {
			data = dedes(ByteUtil.sub(key, 0, 8), data);
			data = endes(ByteUtil.sub(key, 8, 8), data);
			if(key.length == 24){
				data = dedes(ByteUtil.sub(key, 16, 8), data);
			}else{
				data = dedes(ByteUtil.sub(key, 0, 8), data);
			}	
			
		} catch (Exception e) {
			throw new Exception("��3Des����ʱ����." + e);
		}
		return data;
	}
	
	public static void main(String[] args) throws Exception{
		String s = "1234";
		
		System.out.println(ByteUtil.bytes2Hex(SandAPICipher.endes("12345678".getBytes(), s.getBytes())));
		
		
	}
	
}
