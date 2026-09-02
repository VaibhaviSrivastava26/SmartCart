import java.util.Scanner; //case sensitive a A 
public class user_input{
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        System.out.println("wwhat is yourr name?");//as it is print on screen
        //String name= s.next();single word string store krwani hoyegi 
        String name= s.nextLine();// full sentence stored
        System.out.println("I am "+ name +".");
        //System.out.println(surname);

        
    }
}