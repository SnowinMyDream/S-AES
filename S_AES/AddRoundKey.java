// 轮密钥加
public class AddRoundKey {

	public static byte[][] add(byte[][] nibble0, byte[][]nibble1) 
	{
		// 将4位组转换为字节数组
		byte[] array0 = Util.nibblesToArray(nibble0);
		byte[] array1 = Util.nibblesToArray(nibble1);
		byte[] arrayResult = new byte[4];
		// 异或
		for (int i = 0; i < 4; i++)
		{
			arrayResult[i] = (byte)(array0[i] ^ array1[i]);
		}
		return Util.arrayToNibbles(arrayResult);
	}
}
