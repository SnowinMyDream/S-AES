public class Util {
	public static byte[] StringToByte(String s) {
		int[] Input = new int[s.length()];
		for(int i=0;i<s.length();i++) {
			Input[i]=Integer.parseInt(s.substring(i,i+1));
		}
		byte[] output = new byte[s.length()];
		for(int i=0;i<s.length();i++) {
			output[i]=(byte)Input[i];
		}
		return output;
	}
	
	public static int[] StringToArray(String s) {
		int[] Input = new int[s.length()];
		for(int i=0;i<s.length();i++) {
			Input[i]=Integer.parseInt(s.substring(i,i+1));
		}
		return Input;
	}
	
	public static byte[][] StringToByte2(String s) {
		int[] Input = new int[s.length()];
		for(int i=0;i<s.length();i++) {
			Input[i]=Integer.parseInt(s.substring(i,i+1));
		}
		byte[][] output = new byte[2][8];
		for(int i=0;i<2;i++) {
			for(int j=0;j<8;j++) {
				output[i][j]=(byte)Input[i*8+j];
			}
		}
		return output;
	}
	
	public static String Add_Zeros(String input) {
		while(true) {
			if(input.length()<8) {
				input='0'+input;
			}
			else {
				return input;
			}
		}
	}
	public static String Add_Zeros4(String input) {
		while(true) {
			if(input.length()<4) {
				input='0'+input;
			}
			else {
				return input;
			}
		}
	}
	//计算二进制串对应的十进制整数值
	public static int Cauculate(int[] input) {
		int output = 0;
		for(int i=0;i<input.length;i++) {
			output+=input[i]*Math.pow(2, input.length-1-i);
		}
		return output;
	}

	public static int[] ByteToInt2(byte[] input) {
		String temp_string1 = new String();
		String temp_string2 = new String();
		for(int i=0;i<input.length/2;i++) {
			temp_string1 += Add_Zeros4(Integer.toBinaryString((int)input[i]));
			temp_string2 += Add_Zeros4(Integer.toBinaryString((int)input[i+2]));
		}
		int[] output = StringToArray(temp_string1+temp_string2);
		return output;
	}
	public static int[] ByteToInt(byte[] input) {
		String temp_string1 = new String();
		String temp_string2 = new String();
		for(int i=0;i<input.length/2;i++) {
			temp_string1 += Integer.toBinaryString((int)input[i]);
			temp_string2 += Integer.toBinaryString((int)input[i+1]);
		}
		int[] output = StringToArray(temp_string1+temp_string2);
		return output;
	}
	
	public static byte[][] createEmptyNibbles()
	{
		byte[][] cols = new byte[2][];
		cols[0] = new byte[2];
		cols[1] = new byte[2];
		return cols;
	}
	
	public static void printNibbles(byte[][] nibble)
	{
		byte[] array = nibblesToArray(nibble);
		for (int i=0; i < 2; i++)
		{
			System.out.print(' ');
			String output0 = Integer.toBinaryString(array[i]);
			if (output0.length() < 4)
			{
				for (int p = 0; p < 4 - output0.length(); p++)
				{
					System.out.print('0');
				}
			}
			System.out.print(output0);
			System.out.print(' ');
			String output1 = Integer.toBinaryString(array[i+2]);
			if (output1.length() < 4)
			{
				for (int p = 0; p < 4 - output1.length(); p++)
				{
					System.out.print('0');
				}
			}
			System.out.print(output1);
			System.out.println();
		}
	}

	public static String nibblesToString(byte[][] nibble){
		byte[] array = nibblesToArray(nibble);
		String output = new String();
		for (int i=0; i < 2; i++)
		{
			String output0 = Integer.toBinaryString(array[i]);
			if (output0.length() < 4)
			{
				for (int p = 0; p < 4 - output0.length(); p++)
				{
//					System.out.print('0');
					output += '0';
				}
			}
//			System.out.print(output0);
			output += output0;
//			System.out.print(' ');
			String output1 = Integer.toBinaryString(array[i+2]);
			if (output1.length() < 4)
			{
				for (int p = 0; p < 4 - output1.length(); p++)
				{
//					System.out.print('0');
					output += '0';
				}
			}
//			System.out.print(output1);
//			System.out.println();
			output += output1;
		}

		return output;
	}


	
	public static byte[] nibblesToArray(byte[][] nibble)
	{
		byte[] array = new byte[4]; //Arrange by columns
		array[0] = nibble[0][0];
		array[1] = nibble[1][0];
		array[2] = nibble[0][1];
		array[3] = nibble[1][1];
		return array; 
	}
	
	public static byte[][] arrayToNibbles(byte[] array)
	{
		byte[][] nibble = createEmptyNibbles();
		nibble[0][0] = array[0];
		nibble[1][0] = array[1];
		nibble[0][1] = array[2];
		nibble[1][1] = array[3];
		return nibble;
	}
	
	public static byte bitsToByte(String bits)
	{
		try
		{
			return Byte.parseByte(bits, 2);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public static short bitsToShort(String bits)
	{
		try
		{
			return Short.parseShort(bits, 2);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
//		// test nibble creation
//		byte[][] nibble = createEmptyNibbles();
//		printNibbles(nibble);
//		nibble[0][0] = bitsToByte("0000");
//		nibble[1][0] = bitsToByte("1111");
//		nibble[0][1] = bitsToByte("1010");
//		nibble[1][1] = bitsToByte("0101");
//		printNibbles(nibble);
//		nibble[1][1] = bitsToByte("1111");
//		printNibbles(nibble);
//
//		String key_string = "0100101011110101";
//		byte[] result = StringToByte(key_string);
//		for(int i=0;i<16;i++) {
//			System.out.println(result[i]);
//		}
//		String Plain_string = "0110011011111011";
//		System.out.println(Plain_string.substring(4,8));
		byte[] test = {11,12,13,14};
		int[] output = ByteToInt(test);
		for(int i=0;i<16;i++) {
			System.out.println(output[i]);
		}
	}
}
