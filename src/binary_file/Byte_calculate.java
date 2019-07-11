package binary_file;

public class Byte_calculate {
	public static void main(String[] args) throws Exception {
	      
	      String data = "서울시 노원구 중계1989동 20001동 1999호";
	      int hangulByte = 2;  //인코딩을 고려한 한글 1자의 바이트수
	      int maxLength = 30; //최대 추출할 바이트 길이
	      
	      System.out.println(cutHangul(data, hangulByte, maxLength));
	   }   
	   
	   
	   public static String cutHangul(String inputString, int hangulByte,
	         int maxByte) {

	      byte[] inputByte = inputString.getBytes();
	      int cutByte = 0;
	      for (int i = 0; i < inputString.length() - 1; i++) {
	         if (isIncludeHangul(inputString.substring(i, i + 1))) {
	            if (cutByte + hangulByte > maxByte) {
	               break;
	            }
	            cutByte += hangulByte;
	         } else {
	            if (cutByte + 1 > maxByte) {
	               break;
	            }
	            cutByte += 1;
	         }
	      }
	      System.out.println(cutByte+" 바이트까지 추출");
	      return new String(inputByte, 0, cutByte);

	   }
	         public static boolean isIncludeHangul(String input){
	      for (int k = 0; k < input.length(); k++) {
	         if(Character.getType(input.charAt(k))==Character.OTHER_LETTER){
	            return true;
	         }
	      }
	      return false;
	      
	   }
}
