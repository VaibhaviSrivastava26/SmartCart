import java.util.Scanner;
public class user_input_program {
    public static void main (String[] args){
    Scanner s =new Scanner(System.in);
    System.out.println("WHAT IS YOUR NAME?");
    String name=s.next( );
    System.out.println("I am"+" " + name + ".");
    }
    
}
