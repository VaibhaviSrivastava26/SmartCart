import java.util.Scanner;
public class percentage {
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        System.out.println("ENTER MATHS MARKS:");
        Float a=s.nextFloat();
        System.out.println("ENTER Physics MARKS:");
        Float b=s.nextFloat();
        System.out.println("ENTER Chemistry MARKS:");
        Float c=s.nextFloat();
        Float d=(a+b+c)/300;
        Float e=d*100;
        System.out.println(e+"%");


    }
    
}
