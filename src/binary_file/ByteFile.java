package binary_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ByteFile {

	public static void main(String[] args) throws IOException {

		try {

			String filePath = "C:\\Users\\alsmi\\Desktop\\lyrics.txt";

			// 희한하게 파일 첫줄에 엔터 없으면 그 줄이 한글 깨진 채로 나옴; 뭐지

			FileInputStream fis = new FileInputStream(filePath);

			// 파일 인풋 스트림만 사용하는 경우 한글이 깨짐
			InputStreamReader isr = new InputStreamReader(fis);

			int i;
			String str = "";

			while ((i = isr.read()) != -1) {
				// System.out.print((char)i); //바이트 -> 문자화
				str += (char) i;
			}

			// while ((i = isr.read()) != -1) {
			// System.out.println(i); //바이트를 읽어들임
			// }

			byte[] byteArray = str.getBytes("UTF-8");



			System.out.println("읽어들임:"+str.getBytes("UTF-8").length);
			
			
			
			
			// byteArray의 length와 바이트어레이 계산해서 나온 값이 같아야 함

			
			System.out.println("바이트 수 비교;"+byteCheck(str));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 바이트 수 체크

	public static int byteCheck(String str) {

		int ko=0;
		int etc=0;
		int total=0;
		for (int j = 0; j < str.length(); j++) {
			//한글인 경우 +3
			 if(Character.getType(str.charAt(j)) == 5) {
				 ko++;
				 ko++;
				 ko++;
			 }else{
				 etc++;
			 }
			 total=ko+etc;
		}
		 return total;
	
	}
}