public class cbc {
	public static void S_AES_CBC(String string1,String string2, String key_string,String IV,int state) {
		if(state==0) {
			System.out.println("Plain_text_1:"+string1);
			System.out.println("Plain_text_2:"+string2);
			System.out.println("IV:"+IV);
			
			String Cipher1 = Encryptor.Encry(XOR(string1,IV), key_string);//明文分组1先与常序列IV异或并加密得到密文分组1
			String Cipher2 = Encryptor.Encry(XOR(string2,Cipher1), key_string);//明文分组2与密文分组1异或并加密得到密文分组2
			System.out.println("Cipher_text_1:"+Cipher1);
			System.out.println("Cipher_text_2:"+Cipher2);
		}
		else {
			System.out.println("Cipher_text_1:"+string1);
			System.out.println("Cipher_text_2:"+string2);
			System.out.println("IV:"+IV);
			
			String Plain1 = XOR(Decryptor.Decry(string1, key_string),IV);
			String Plain2 = XOR(Decryptor.Decry(string2, key_string),string1);
			
			System.out.println("Plain_text_1:"+Plain1);
			System.out.println("Plain_text_2:"+Plain2);
		}
	}
	//对两个bit序列进行异或操作
	public static String XOR(String s1,String s2) {
		String output = new String();
		for(int i=0;i<s1.length();i++) {
			if(s1.substring(i,i+1).equals(s2.substring(i,i+1))) {
				output+="0";
			}
			else {
				output+="1";
			}
		}
		return output;
	}
	
	public static void main(String args[]) {
		//加密
		System.out.println("CBC加密过程");
		S_AES_CBC("0111010101110000","0111010101110000","0001110110100101","1010101010101010",0);
		//解密
		System.out.println("CBC解密过程");
		S_AES_CBC("1110101101101000","0110111101101001","0001110110100101","1010101010101010",1);
		System.out.println("篡改密文后的解密过程");
		S_AES_CBC("0000101101101000","0000111101101001","0001110110100101","1010101010101010",1);
	}
}
