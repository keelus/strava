package com.google.server;

import org.springframework.util.DigestUtils;

public class Crypto {
    public static String EncriptarConMd5(String contrasenya) {
        return DigestUtils.md5DigestAsHex(contrasenya.getBytes()).toUpperCase();
    }

    public static boolean EsHashIdentico(String hashMd5, String valorSinHash) {
        return EncriptarConMd5(valorSinHash).equals(hashMd5);
    }
}
