package com.amaru.ventas_amaru.dev;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class pruebas {
    public static void main(String[] args) {

        String recortr ="angular-secret";
        /*/System.out.println(recortr.substring(2,12));*/
        //String recorte = codigo.substring(2,10);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode(recortr));
    }
}
