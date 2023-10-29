public class Encryptor {

	// 密钥生成器
	private KeyGenerator generator;
	
	public Encryptor() {
	}

	public KeyGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(KeyGenerator generator) {
		this.generator = generator;
	}

	// 加密（半字节组形式）
	public byte[][] encrypt(byte[][] message)
	{
		byte[][] nibble = message;
		byte[][] key1 = generator.keyNibbles(0,1);
		byte[][] step1 = AddRoundKey.add(nibble, key1);
		byte[][] step2 = SubNibbles.substitute(step1);
		byte[][] step3 = ShiftRows.shift(step2);
		byte[][] step4 = MixColumns.mix(step3);
		byte[][] key2 = generator.keyNibbles(2,3);
		byte[][] step5 = AddRoundKey.add(step4, key2);
		byte[][] step6 = SubNibbles.substitute(step5);
		byte[][] step7 = ShiftRows.shift(step6);
		byte[][] key3 = generator.keyNibbles(4,5);
		byte[][] step8 = AddRoundKey.add(step7, key3);
		byte[][] cipherNibble = step8;
		return cipherNibble;
	}

	// 加密，返回加密字符串
	public static String Encry(String Plain_string, String key_string) {
		String output = new String();
		Encryptor saes = new Encryptor();
		byte[] key = Util.StringToByte(key_string);
		KeyGenerator withKey = new KeyGenerator(key);
		saes.setGenerator(withKey);
		withKey.generate();
		byte[][] messageASNibbles = {{ Util.bitsToByte(Plain_string.substring(0,4)), Util.bitsToByte(Plain_string.substring(8,12))},
				{ Util.bitsToByte(Plain_string.substring(4,8)), Util.bitsToByte(Plain_string.substring(12,16)) }};
		byte[][] encrypted = saes.encrypt(messageASNibbles);
		byte[] encrypted_array = Util.nibblesToArray(encrypted);
		int[] Sub_Cipher_Array = Util.ByteToInt2(encrypted_array);
		for(int i=0;i<Sub_Cipher_Array.length;i++) {
			output+=String.valueOf(Sub_Cipher_Array[i]);
		}
		return output;


	}
}