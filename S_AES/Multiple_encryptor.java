//多重加密
public class Multiple_encryptor {
    public static String encrypt(String plaintext, String key, String format) {

        System.out.println("Message to encrypt: \n" + plaintext);

        String ciphertext = new String();

        int count = key.length() / 16; //多重加密次数

        if("binary".equals(format)){
            String[] key_sub = new String[count];
            for (int i = 0; i < count; i++) {
                key_sub[i] = key.substring(16 * i, 16 * (i + 1));
            }

            String str_to_encrypt = plaintext;
            for (int i = 0; i < count; i++) {
                //第i+1重加密
                System.out.println("第" + (i + 1) + "重加密");
                str_to_encrypt = Encryptor.Encry(str_to_encrypt,key_sub[i]);

                ciphertext = str_to_encrypt;
                System.out.println("Message encrypted: ");
                System.out.println(ciphertext);
            }
        }
        else if("ascii".equals(format)){
            String[] key_sub = new String[count];
            for (int i = 0; i < count; i++) {
                key_sub[i] = key.substring(16 * i, 16 * (i + 1));
            }

            String str_to_encrypt = plaintext;
            for (int i = 0; i < count; i++) {
                //第i+1重加密
                System.out.println("第" + (i + 1) + "重加密");
                str_to_encrypt = Encryptor_Ascii.Encrypt_Ascii(str_to_encrypt,key_sub[i]);

                ciphertext = str_to_encrypt;
                System.out.println("Message encrypted: ");
                System.out.println(ciphertext);
            }
        }
        else {
            System.out.println("未选择格式");
        }


        return ciphertext;
    }
}
