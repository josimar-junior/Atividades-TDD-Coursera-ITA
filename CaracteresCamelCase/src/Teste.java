import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Teste {

	public static void main(String[] args) {
		String cpt = "numeroCPF";
		String new_string = "";
	    for (int i=0; i < cpt.length(); i++){
	        char c = cpt.charAt(i);
	        if(Character.isUpperCase(c)){               
	            new_string = new_string + " " + Character.toLowerCase(c);
	        }
	        else {
	            new_string = new_string + c;
	        }
	    }
	    System.out.println(new_string);
	}

}
