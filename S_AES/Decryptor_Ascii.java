public class Decryptor_Ascii {
	private KeyGenerator generator;

	public KeyGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(KeyGenerator generator) {
		this.generator = generator;
	}
	
	public byte[][] decrypt(byte[][] message)
	{
		byte[][] nibble = message;
		byte[][] key3 = generator.keyNibbles(4,5);
		byte[][] step1 = AddRoundKey.add(nibble, key3);
		byte[][] step2= ShiftRows.shift(step1);
		byte[][] step3 = SubNibbles.invertSubstitute(step2);
		byte[][] key2 = generator.keyNibbles(2,3);
		byte[][] step4 = AddRoundKey.add(step3, key2);
		byte[][] step5 = MixColumns.invertMix(step4);
		byte[][] step6 = ShiftRows.shift(step5);
		byte[][] step7 = SubNibbles.invertSubstitute(step6);
		byte[][] key1 = generator.keyNibbles(0,1);
		byte[][] step8 = AddRoundKey.add(step7, key1);
		byte[][] cipherNibble = step8;
		return cipherNibble;
	}
	
	public static String Decrypt_Ascii(String Plain_string, String key_string) {
		String Cipher_string =new String();
		byte[] key = Util.StringToByte(key_string);
		Decryptor_Ascii saes = new Decryptor_Ascii();
		KeyGenerator withKey = new KeyGenerator(key);
		saes.setGenerator(withKey);
		withKey.generate();
		for(int i=0;i<Plain_string.length();i+=2) {
			//取出ASCII串中的第i个字符，转化为8bit二进制字符串
			char ch1 = Plain_string.charAt(i);
			char ch2 = Plain_string.charAt(i+1);
			int ch_num1 = (int)ch1;
			int ch_num2 = (int)ch2;
			String Sub_Plain_Bits1 = Util.Add_Zeros(Integer.toBinaryString(ch_num1));
			String Sub_Plain_Bits2 = Util.Add_Zeros(Integer.toBinaryString(ch_num2));
			byte[][] messageASNibbles = {{ Util.bitsToByte(Sub_Plain_Bits1.substring(0,4)), Util.bitsToByte(Sub_Plain_Bits2.substring(0,4))},
					 { Util.bitsToByte(Sub_Plain_Bits1.substring(4,8)), Util.bitsToByte(Sub_Plain_Bits2.substring(4,8)) }};
			//对二进制子明文进行加密，得到子密文
			byte[][] encrypted = saes.decrypt(messageASNibbles);
			byte[] encrypted_array = Util.nibblesToArray(encrypted);
			int[] Sub_Cipher_Array = Util.ByteToInt2(encrypted_array);
			for(int k=0;k<Sub_Cipher_Array.length;k++) {
				System.out.print(Sub_Cipher_Array[k]);
			}
			int[] Sub_Cipher_Array1 = new int[8];
			int[] Sub_Cipher_Array2 = new int[8];
			for(int k=0;k<8;k++) {
				Sub_Cipher_Array1[k]=Sub_Cipher_Array[k];
				Sub_Cipher_Array2[k]=Sub_Cipher_Array[k+8];
			}
			System.out.println();
			Cipher_string+=(char)Util.Cauculate(Sub_Cipher_Array1);
			Cipher_string+=(char)Util.Cauculate(Sub_Cipher_Array2);
		}
		return Cipher_string;
	}
}
