package algorithm;


import java.util.Scanner;

public class Align {
	
	public static void main(String[] args) {
		


		Scanner sc = new Scanner(System.in);
        int[] arr = new int[sc.nextInt()]; 
        int tmp = 0;
 
        for (int i = 0; i < arr.length; i++) {
        	arr[i] = sc.nextInt(); 
        }
 
        for (int k = 0; k < arr.length; k++) {
            for (int j = 0; j < arr.length - 1; j++) { 
                if (arr[j] > arr[j + 1]) { 
                	tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
 
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
		
	
		
	}
}
