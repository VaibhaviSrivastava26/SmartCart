public class StringMethodsByVaibhavi {

    public static void main(String[] args) {

        // String declaration
        String s = "study with vaibhavi";

        // replace() -> spaces ko "_" se replace karta hai
        s = s.replace(" ", "_");
        System.out.println("After Replace : " + s);

        // length() -> total characters count karta hai
        int l = s.length();
        System.out.println("Length : " + l);

        // toUpperCase() -> string ko uppercase me convert karta hai
        String u = s.toUpperCase();
        System.out.println("Uppercase : " + u);

        // concat() -> do strings ko join karta hai
        String first = "Hello ";
        String second = "Vaibhavi";
        System.out.println("Concatenate : " + first.concat(second));

        // Original String
        System.out.println("Original : " + s);

        // trim() -> starting aur ending ke extra spaces remove karta hai
        String t = "   Study With Vaibhavi   ";
        System.out.println("Trim : " + t.trim());

        // contains() -> check karta hai word present hai ya nahi
        System.out.println("Contains 'java' : " + s.contains("java"));

        // replace word
        System.out.println("Replace Word : " + s.replace("vaibhavi", "Java"));

        // charAt() -> specific index ka character deta hai
        System.out.println("Character at index 3 : " + s.charAt(3));

        // toLowerCase()
        System.out.println("Lowercase : " + u.toLowerCase());

        // startsWith()
        System.out.println("Starts With 'study' : " + s.startsWith("study"));

        // endsWith()
        System.out.println("Ends With 'vaibhavi' : " + s.endsWith("vaibhavi"));

        // indexOf()
        System.out.println("Index of 'with' : " + s.indexOf("with"));

        // equals()
        String a = "Java";
        String b = "Java";
        System.out.println("Equals : " + a.equals(b));

        // equalsIgnoreCase()
        String x = "JAVA";
        String y = "java";
        System.out.println("Equals Ignore Case : " + x.equalsIgnoreCase(y));

        // substring()
        System.out.println("Substring : " + s.substring(0, 5));

        System.out.println("\n---- Study With Vaibhavi Java Practice ----");
    }
}