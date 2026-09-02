import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sr= new Scanner(System.in);

        System.out.println("Enter number 1:");
        int a = sr.nextInt();

        System.out.println("Enter number 2:");
        int b = sr.nextInt();

        int sum = a + b;

        System.out.println("Sum of two numbers:");
        System.out.println(sum);

        sr.close();
    }
}