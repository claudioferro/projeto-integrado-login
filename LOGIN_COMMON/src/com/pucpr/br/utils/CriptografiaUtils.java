package com.pucpr.br.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class CriptografiaUtils {

	public static boolean isEqual(byte[] criptBytes1, byte[] criptBytes2) {

		if (criptBytes1.length != criptBytes2.length)
			return false;

		int i;
		for (i = 0; i < criptBytes1.length; i++) {

			if (criptBytes1[i] != criptBytes2[i])
				break;

		}

		return i == criptBytes1.length;

	}

	public static String criptografar2(String mensagem)
			throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		md5.reset();
		md5.update(mensagem.getBytes());

		byte[] cripto = md5.digest();

		return cripto.toString();
	}

	public static String criptografar(String senha) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return senha;
		}
	}

}
