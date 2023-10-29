public class KeyGenerator {

	private byte keyBits[];
	 
	protected short [] keyWords;
	
	
	/*
	 * Rcon is a round constant, defined as follows: RC[i] = xi+2, so that 
	 * RC[1] = x3 =1000 and RC[2]=x4mod(x4 +x+1)=x+1=0011. 
	 * RC[i] forms the leftmost nibble of a short, with the rightmost nibble being all zeros. 
	 * Thus, Rcon(1) = 10000000 and Rcon(2) = 00110000.
	 */
	static short roundCon1 = Util.bitsToShort("10000000");
	static short roundCon2 = Util.bitsToShort("00110000");
	
	public KeyGenerator(byte[] keyBits) {
		this.keyBits = keyBits;
		keyWords = new short[6];
	}

	// 生成w0-w5
	public short genW0()
	{
		short w0 = 0;
		String wString = "";
		for (int i = 0; i < 8; i++)
		{
			wString = wString + keyBits[i];
		}
		w0 = Util.bitsToShort(wString);
		keyWords[0] = w0;
		return w0;
	}
	
	public short genW1()
	{
		short w1 = 0;
		String wString = "";
		for (int i = 8; i < 16; i++)
		{
			wString = wString + keyBits[i];
		}
		w1 = Util.bitsToShort(wString);
		keyWords[1] = w1;
		return w1;
	}
	
	public short genW2()
	{
		int round1 = 1;
		short w2 = (short) (keyWords[0] ^ g(keyWords[1], round1));
		keyWords[2] = w2;
		return w2;
	}
	
	public short genW3()
	{
		short w3 = (short) (keyWords[2] ^ keyWords[1]);
		keyWords[3] = w3;
		return w3;
	}
	
	public short genW4()
	{
		int round2 = 2;
		short w4 = (short) (keyWords[2] ^ g(keyWords[3], round2));
		keyWords[4] = w4;
		return w4;
	}
	
	public short genW5()
	{
		short w5 = (short) (keyWords[4] ^ keyWords[3]);
		keyWords[5] = w5;
		return w5;
	}
	
	public static String fill8Bits(String wordString)
	{
		while (wordString.length() < 8)
		{
			wordString = "0" + wordString;
		}
		return wordString;
	}
	
	public static String fill4Bits(String nibbleString)
	{
		while (nibbleString.length() < 4)
		{
			nibbleString = "0" + nibbleString;
		}
		return nibbleString;
	}
	
	public short[] splitWord(short word)
	{
		short[] twoNibbles = new short[2];
		String word2String = fill8Bits(Integer.toBinaryString(word) );
		twoNibbles[0] = Util.bitsToShort(word2String.substring(0, 4));
		twoNibbles[1] = Util.bitsToShort(word2String.substring(4));
		return twoNibbles;
	}
	
	public byte[][] keyNibbles(int round0, int round1)
	{
		short[] keyNibble1 = splitWord(keyWords[round0]);
		short[] keyNibble2 = splitWord(keyWords[round1]);
		byte[][] key = Util.createEmptyNibbles();
		key[0][0] = (byte)keyNibble1[0];
		key[1][0] = (byte)keyNibble1[1];
		key[0][1] = (byte)keyNibble2[0];
		key[1][1] = (byte)keyNibble2[1];
		return key;
	}
	
	public short[] rotateWordNibbles(short[] wordNibbles)
	{
		short[] newNibbles = new short[2];
		newNibbles[0] = wordNibbles[1];
		newNibbles[1] = wordNibbles[0];
		return newNibbles;
	}
	
	public short subWord(short [] wordNibbles)
	{
		short newWord;
		short[] subWord = new short[2];
		subWord[0] = SubNibbles.subNib((byte)wordNibbles[0]);
		subWord[1] = SubNibbles.subNib((byte)wordNibbles[1]);
		String nibble0 = fill4Bits(Integer.toBinaryString(subWord[0]));
		String nibble1 = fill4Bits(Integer.toBinaryString(subWord[1]));
//		newWord = (short)( Byte.parseByte(nibble0 + nibble1, 2));
		newWord = (short) (Short.parseShort(nibble0 + nibble1, 2));
		return newWord;
	}

	// g函数
	public short g(short word, int round)
	{
		int rconst = 0;
		if (round == 1)
		{
			rconst = roundCon1;
		}
		if (round == 2)
		{
			rconst = roundCon2;
		}
		return (short)(rconst ^ subWord(rotateWordNibbles( splitWord(word) ) ) );
	}
	
	public void generate()
	{
		genW0();
		genW1();
		genW2();
		genW3();
		genW4();
		genW5();
	}

	
}
