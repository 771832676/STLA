package com.stla.figure.utility;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
public class AESUtils {

    //实际的加密解密操作
    private static String Operation(String src, String key, int mode) throws Exception {
        if (key==null) {
            return "Key不能为空";
        }
        if (key.length()!=16) {
            return "Key需要16位长度";
        }
        String result = "";
        byte[] raw = key.getBytes("utf-8");
        SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(mode, keySpec);
            byte[] encrypted = cipher.doFinal(src.getBytes("utf-8"));
            //将+替换为%2B
            result = new BASE64Encoder().encode(encrypted).replace("+", "%2B");
        } else {
            cipher.init(mode, keySpec);
            //将%2B替换为+
            src = src.replace("%2B", "+");
            byte[] encrypted = cipher.doFinal(new BASE64Decoder().decodeBuffer(src));
            result = new String(encrypted, "utf-8");
        }

        return result;
    }

    /**
     * 加密 * * @param src
     * @return
     * @throws Exception
     */  public static String Encrypt(String src, String key) throws Exception {
        return Operation(src, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密 * * @param src
     * @return
     * @throws Exception
     */  public static String Decrypt(String src, String key) throws Exception {
        return Operation(src, key, Cipher.DECRYPT_MODE);
    }

    public static void main(String[] args) throws Exception {
      /*  String a = Encrypt("0001,北京顺天立安科技有限公司,2018-07-27,2022-02-02,毛海涛,16619817752,10000", "stladjmjszyzancc");
        System.out.println("加密数据:" + a);*/
        String b = Decrypt("vX4WwcMGyE%2BinG4xS%2B4LRoQdz1N6DMjlDR0sfL24n3gvazhi/ceMCnj2XJIprs0YhcrI7t1zQkrCxY3QTqba7gVd9ndkGi5qlGwm80obwPf2EopzZMWl/HAwj8PDoQQXSaNWnDpIhQb03XjO2Q5G%2BVgofrrk1MEKB1btmLDTY9RJ13SeZI%2Bujhxpqo5xzxPb/MhfED8djRwUbZPDIWLH8Q==", "stladjmjszyzancc");
        System.out.println("解密数据:" + b);
    }

}
