import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;

public class Compiler{
	public String vDir = "d";
	public String hDir = "n";
	public int vPos = 0;
	public int hPos = 0;
	public String[][] map;
	public ArrayList<Integer> stack = new ArrayList<Integer>();
	public boolean debugging = false;
	
	public void changeDir(String dir){
		switch(dir){
			case "u":
				vDir = "u";
				hDir = "n";
				break;
			case "ur":
				vDir = "u";
				hDir = "r";
				break;
			case "r":
				vDir = "n";
				hDir = "r";
				break;
			case "dr":
				vDir = "d";
				hDir = "r";
				break;
			case "d":
				vDir = "d";
				hDir = "n";
				break;
			case "dl":
				vDir = "d";
				hDir = "l";
				break;
			case "l":
				vDir = "n";
				hDir = "l";
				break;
			case "ul":
				vDir = "u";
				hDir = "l";
				break;
			default:
				break;
		}
	}
	
	public void cont(){
		if(debugging){
			System.out.println("Current Coords: " + (hPos+1) + "," + (vPos+1));
			System.out.println(stack);
			System.out.println("\n");
		}
		if(hDir.equals("n") && vDir.equals("u")){
			vPos--;
		} else if(hDir.equals("r") && vDir.equals("u")){
			vPos--;
			hPos++;
		} else if(hDir.equals("r") && vDir.equals("n")){
			hPos++;
		} else if(hDir.equals("r") && vDir.equals("d")){
			vPos++;
			hPos++;
		} else if(hDir.equals("n") && vDir.equals("d")){
			vPos++;
		} else if(hDir.equals("l") && vDir.equals("d")){
			vPos++;
			hPos--;
		} else if(hDir.equals("l") && vDir.equals("n")){
			hPos--;
		} else if(hDir.equals("l") && vDir.equals("u")){
			vPos--;
			hPos--;
		}
		interpret();
	}
	
	public void createMap(String str) {
		String[] rows = str.split("#");
		String[][] mkMap = new String[rows.length][];
		
		int i = 0;
		for(String row : rows) {
			mkMap[i++] = row.split("");
		}
		map = mkMap.clone();
	}
	
	public void error(String err){
		System.out.println("Error at (" + (hPos+1) + "," + (vPos+1) + "): " + err);
	}
	
	public boolean hasChar(int v, int h){
		try{
		return !map[v][h].equals(" ");
		} catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
	}
	
	public void interpret(){
		String dirEr = "Wrong direction";
		try {
			switch(map[vPos][hPos]){
				case "|":
					if(hDir.equals("n")){
						if(debugging){
							System.out.println("|: Continuing vertically");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "-":
					if(vDir.equals("n")){
						if(debugging){
							System.out.println("-: Continuing horizontally");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "/":
					if((vDir.equals("u") && hDir.equals("r")) || (vDir.equals("d") && hDir.equals("l"))){
						if(debugging){
							System.out.println("/: Continuing diagonally");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "\\":
					if((vDir.equals("d") && hDir.equals("r")) || (vDir.equals("u") && hDir.equals("l"))){
						if(debugging){
							System.out.println("\\: Continuing diagonally");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "+":
					if(debugging){
						System.out.println("+: Incrementing the top value on the stack");
					}
					try{
						int temp = stack.get(stack.size()-1);
						temp++;
						stack.remove(stack.size()-1);
						stack.add(temp);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to increment a nonexistant value on the stack");
					}
					break;
				case "_":
					if(debugging){
						System.out.println("_: Decrementing the top value on the stack");
					}
					try{
						int temp = stack.get(stack.size()-1);
						temp--;
						stack.remove(stack.size()-1);
						stack.add(temp);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to decrement a nonexistant value on the stack");
					}
					break;
				case "*":
					if(debugging){
						System.out.println("*: Multiplying the top two numbers on the stack");
					}
					try{
						int temp1 = stack.get(stack.size()-1);
						int temp2 = stack.get(stack.size()-2);
						int temp3 = temp1*temp2;
						stack.remove(stack.size()-1);
						stack.remove(stack.size()-1);
						stack.add(temp3);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to multiply a nonexistant value on the stack");
					}
					break;
				case "<":
					if(debugging){
						System.out.println("<: Checking if the top value is geater than 0");
					}
					if(hDir.equals("r")){
						if(stack.get(stack.size()-1) > 0){
							changeDir("ur");
						} else {
							changeDir("dr");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case ">":
					if(debugging){
						System.out.println(">: Checking if the top value is geater than 0");
					}
					if(hDir.equals("l")){
						if(stack.get(stack.size()-1) > 0){
							changeDir("ul");
						} else {
							changeDir("dl");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "^":
					if(debugging){
						System.out.println("^: Checking if the top value is geater than 0");
					}
					if(vDir.equals("d")){
						if(stack.get(stack.size()-1) > 0){
							changeDir("dr");
						} else {
							changeDir("dl");
						}
						cont();
					} else {
						error(dirEr);
					}
					break;
				case "a":
					if(debugging){
						System.out.println("a: Adding the top two numbers on the stack");
					}
					try{
						int temp1 = stack.get(stack.size()-1);
						int temp2 = stack.get(stack.size()-2);
						int temp3 = temp1+temp2;
						stack.remove(stack.size()-1);
						stack.remove(stack.size()-1);
						stack.add(temp3);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to add a nonexistant value on the stack");
					}
					break;
				case "b":
					if(debugging){
						System.out.println("b: Printing the integer value on the top of the stack");
					}
					try {
						System.out.print(stack.get(stack.size()-1));
						if(debugging){
							System.out.println("");
						}
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to print a nonexistant value on the stack");
					}
					break;
				case "c":
					if(debugging){
						System.out.println("c: Changing direction clockwise");
					}
					scanC();
					break;
				case "d":
					if(debugging){
						System.out.println("d: Dividing the top two numbers on the stack");
					}
					try{
						int temp1 = stack.get(stack.size()-1);
						int temp2 = stack.get(stack.size()-2);
						int temp3 = (int)Math.floor(temp1/temp2);
						stack.remove(stack.size()-1);
						stack.remove(stack.size()-1);
						stack.add(temp3);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to divide a nonexistant value on the stack");
					}
					break;
				case "e":
					if(debugging){
						System.out.println("e: ending the program");
					}
					break;
				case "f":
					if(debugging){
						System.out.println("f: Removing the top value from the stack");
					}
					try {
						stack.remove(stack.size()-1);
					} catch(ArrayIndexOutOfBoundsException e){
						error("tried to remove item " + (stack.size()-1) + " from stack of size " + stack.size());
					}
					cont();
					break;
				case "j":
					if(debugging){
						System.out.println("j: Jumping to coordinates given by the top two values on the stack");
						System.out.println("Current Coords: " + (hPos+1) + "," + (vPos+1));
						System.out.println(stack);
						System.out.println("\n");
					}
					try{
						int jumpH = stack.get(stack.size()-1);
						int jumpV = stack.get(stack.size()-2);
						hPos = jumpH-1;
						vPos = jumpV-1;
						interpret();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to jump using a nonexistant value on the stack");
					}
					break;
				case "n":
					if(debugging){
						System.out.println("n: adding 0 to the stack");
					}
					stack.add(0);
					cont();
					break;
				case "o":
					if(debugging){
						System.out.println("o: Changing direction counter-clockwise");
					}
					scanCC();
					break;
				case "p":
					if(debugging){
						System.out.println("p: Printing the ASCII character indicated by the top value on the stack");
					}
					try{
						System.out.print((char)(int)stack.get(stack.size()-1));
						if(debugging){
							System.out.println("");
						}
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to increment a nonexistant value on the stack");
					}
					break;
				case "r":
					if(debugging){
						System.out.println("r: reversing direction");
					}
					if(hDir.equals("l")){
						hDir = "r";
					} else if(hDir.equals("r")){
						hDir = "l";
					}
					if(vDir.equals("u")){
						vDir = "d";
					} else if(vDir.equals("d")){
						vDir = "u";
					}
					cont();
					break;
				case "s":
					if(debugging){
						System.out.println("s: Subtracting the top two numbers on the stack");
					}
					try{
						int temp1 = stack.get(stack.size()-1);
						int temp2 = stack.get(stack.size()-2);
						int temp3 = temp1-temp2;
						stack.remove(stack.size()-1);
						stack.remove(stack.size()-1);
						stack.add(temp3);
						cont();
					} catch(ArrayIndexOutOfBoundsException e) {
						error("Tried to subtract a nonexistant value on the stack");
					}
					break;
				case "u":
					if(debugging){
						System.out.println("u: getting value from user input");
					}
					try{
						Scanner user = new Scanner(System.in);
						
						int input = user.nextInt();
						stack.add(input);
						cont();
					}catch(InputMismatchException e){
						error("Input must be an integer");
					}
						break;
				default:
					error("Unknown character: " + map[vPos][hPos]);
					break;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			error("Tried to interpret at nonexistant coords");
		}
	}
	
	public void scanC(){
		String dirEr = "Wrong direction";
		if(hDir.equals("n") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos)){
				cont();
			} else {
				changeDir("ur");
				scanC();
			}
		} else if(hDir.equals("r") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos+1)){
				cont();
			} else {
				changeDir("r");
				scanC();
			}
		} else if(hDir.equals("r") && vDir.equals("n")){
			if(hasChar(vPos, hPos+1)){
				cont();
			} else {
				changeDir("dr");
				scanC();
			}
		} else if(hDir.equals("r") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos+1)){
				cont();
			} else {
				changeDir("d");
				scanC();
			}
		} else if(hDir.equals("n") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos)){
				cont();
			} else {
				changeDir("dl");
				scanC();
			}
		} else if(hDir.equals("l") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos-1)){
				cont();
			} else {
				changeDir("l");
				scanC();
			}
		} else if(hDir.equals("l") && vDir.equals("n")){
			if(hasChar(vPos, hPos-1)){
				cont();
			} else {
				changeDir("ul");
				scanC();
			}
		} else if(hDir.equals("l") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos-1)){
				cont();
			} else {
				changeDir("u");
				scanC();
			}
		}
	}
	
	public void scanCC(){
		String dirEr = "Wrong direction";
		if(hDir.equals("n") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos)){
				cont();
			} else {
				changeDir("ul");
				scanCC();
			}
		} else if(hDir.equals("r") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos+1)){
				cont();
			} else {
				changeDir("u");
				scanCC();
			}
		} else if(hDir.equals("r") && vDir.equals("n")){
			if(hasChar(vPos, hPos+1)){
				cont();
			} else {
				changeDir("ur");
				scanCC();
			}
		} else if(hDir.equals("r") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos+1)){
				cont();
			} else {
				changeDir("r");
				scanCC();
			}
		} else if(hDir.equals("n") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos)){
				cont();
			} else {
				changeDir("dr");
				scanCC();
			}
		} else if(hDir.equals("l") && vDir.equals("d")){
			if(hasChar(vPos+1, hPos-1)){
				cont();
			} else {
				changeDir("d");
				scanCC();
			}
		} else if(hDir.equals("l") && vDir.equals("n")){
			if(hasChar(vPos, hPos-1)){
				cont();
			} else {
				changeDir("dl");
				scanCC();
			}
		} else if(hDir.equals("l") && vDir.equals("u")){
			if(hasChar(vPos-1, hPos-1)){
				cont();
			} else {
				changeDir("l");
				scanCC();
			}
		}
	}
	
	public String readFile(String filename) throws IOException {
		StringBuilder builder = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line = reader.readLine();

			while (line != null) {
				builder.append(line);
				builder.append("#");
				line = reader.readLine();
			}
			return builder.toString();
		} catch(IOException e){
			return e.toString();
		}
	}
	
	public static void main(String[] args){
		Compiler x = new Compiler();
		try{
			x.createMap(x.readFile(args[0]));
			if(x.debugging){
				System.out.println(Arrays.deepToString(x.map) + "\n");
			}
			x.interpret();
		} catch(IOException|ArrayIndexOutOfBoundsException e) {
			System.out.println("Please pass a valid filename as an argument, in this form: java Compiler <filename>");
		}
	}
}
