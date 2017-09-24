import java.util.Scanner;

public class solve {

	public static void main(String[] args) {
		
         	Scanner sc = new Scanner(System.in); 
		System.out.println("请输入第一个整数："); 
         	int a = sc.nextInt(); 
         
		System.out.println("请输入第二个整数："); 
         	int  b =  sc.nextInt();

		int sum = a+b;
         
		System.out.println("你的信息如下："); 
         	System.out.println("他们的和为"+sum); 
	}

}
