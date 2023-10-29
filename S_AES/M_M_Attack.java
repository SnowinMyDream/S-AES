// 中间相遇攻击
import java.lang.Math;
public class M_M_Attack {
    public static String attack(String plaintext, String ciphertext){
        int counter = 0;
        for(int i = 0; i<Math.pow(2,16);i++){
            String key_1 = Integer.toBinaryString(i);
            while(true) {
                if(key_1.length()<16) {
                    key_1='0'+key_1;
                }
                else {
                    break;
                }
            }
//            System.out.println(key_1);
            String mid_text_en = Encryptor.Encry(plaintext,key_1);

            for(int j = 0; j<Math.pow(2,16);j++){
                String key_2 = Integer.toBinaryString(j);
                while(true) {
                    if(key_2.length()<16) {
                        key_2='0'+key_1;
                    }
                    else {
                        break;
                    }
                }

                String mid_text_de = Decryptor.Decry(ciphertext,key_2);

                if(mid_text_en.equals(mid_text_de)){
                    counter++;
                    System.out.println("找到第"+counter+"个密钥："+key_1+key_2);
                }
            }

        }

        return "0";
    }

    public static void main(String[] args) {
        attack("0111011100011100","1010101110101100");
    }

}
