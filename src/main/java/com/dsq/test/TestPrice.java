package com.dsq.test;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 网上复制的 多线程读取文件的代码
 * @Author duanshaoqian
 * @Date 2023/2/13 18:10
 **/
public class TestPrice {


    public static String key = "19a7728454314241";


    public static void main(String[] args) {

        TestPrice getHuaWeiPrice = new TestPrice();
        long price = getHuaWeiPrice.getTruePrice("6b67577085a1d59dd2bff364280626c0");
        System.out.println("price = " + price);


    }


    public long getTruePrice(String price) {


        try {
            String s = decryptData(price, key);
            Double v = (Double)Double.parseDouble(s);
            return v.longValue();
        } catch (Exception e) {
            System.out.println("华为 price:"+price+", mgs:"+e.getMessage());
            return 0;
        }

    }



    public String decryptData(String encryptedPrice, String key)
    {
        byte[] tt = null;
        try
        {
            tt = Hex.decodeHex(encryptedPrice.toCharArray());
        } catch (Exception e)
        {
            //此处请自己增加异常处理逻辑
            e.printStackTrace();
            return "0";
        }
        if (null != tt)
        {
            byte[] iv = new byte[16];
            byte[] cipherText = new byte[tt.length - 16];
            System.arraycopy(tt, 0, iv, 0, 16);
            System.arraycopy(tt, 16, cipherText, 0, cipherText.length);

            try
            {
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                SecretKeySpec skeySpec = new
                        SecretKeySpec(Base64.decodeBase64(key), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
                byte[] result = cipher.doFinal(cipherText);

                if (null != result && result.length > 0)
                {
                    String clearPrice = new String(result, Charsets.UTF_8);
                    return clearPrice;
                }
            }
            catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                   InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex)
            {
                //此处请自己增加异常处理逻辑
                ex.printStackTrace();
                return "0";
            }
        }
        return "0";
    }


}
