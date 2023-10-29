public class Decryptor {
	private KeyGenerator generator;

	public KeyGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(KeyGenerator generator) {
		this.generator = generator;
	}

	// 解密（半字节组形式）
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

	// 返回解密字符串
	public static String Decry(String Plain_string, String key_string) {
		String output = new String();
		Decryptor saes = new Decryptor();
		byte[] key = Util.StringToByte(key_string);
		KeyGenerator withKey = new KeyGenerator(key);
		saes.setGenerator(withKey);
		withKey.generate();
		byte[][] messageASNibbles = {{ Util.bitsToByte(Plain_string.substring(0,4)), Util.bitsToByte(Plain_string.substring(8,12))},
				{ Util.bitsToByte(Plain_string.substring(4,8)), Util.bitsToByte(Plain_string.substring(12,16)) }};
		byte[][] encrypted = saes.decrypt(messageASNibbles);
		byte[] encrypted_array = Util.nibblesToArray(encrypted);
		int[] Sub_Cipher_Array = Util.ByteToInt2(encrypted_array);
		for(int i=0;i<Sub_Cipher_Array.length;i++) {
			output+=String.valueOf(Sub_Cipher_Array[i]);
		}
		return output;
	}
}
