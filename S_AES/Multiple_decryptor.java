public class Multiple_decryptor {
    public static String decrypt(String ciphertext, String key, String format){

        System.out.println("Message to decrypt: \n" + ciphertext);

        String plaintext = new String();

        int count = key.length() / 16; //多重加密次数

        if("binary".equals(format)){
            String[] key_sub = new String[count];
            for (int i = 0; i < count; i++) {
                key_sub[i] = key.substring(16 * i, 16 * (i + 1));
            }

            String str_to_decrypt = ciphertext;
            for (int i = count-1; i >= 0; i--) {
                //第count-i重解密
                System.out.println("第" + (count-i) + "重解密");
                str_to_decrypt = Decryptor.Decry(str_to_decrypt,key_sub[i]);

                plaintext = str_to_decrypt;

                System.out.println("Message decrypted: ");
                System.out.println(plaintext);
            }
        }
        else if("ascii".equals(format)){
            String[] key_sub = new String[count];
            for (int i = 0; i < count; i++) {
                key_sub[i] = key.substring(16 * i, 16 * (i + 1));
            }

            String str_to_decrypt = ciphertext;
            for (int i = count-1; i >= 0; i--) {
                //第count-i重解密
                System.out.println("第" + (count-i) + "重解密");
                str_to_decrypt = Decryptor_Ascii.Decrypt_Ascii(str_to_decrypt,key_sub[i]);

                plaintext = str_to_decrypt;

                System.out.println("Message decrypted: ");
                System.out.println(plaintext);
            }
        }

        return plaintext;


    }

}
