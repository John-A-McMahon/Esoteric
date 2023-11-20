package esoteric;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

public class Esoteric {

	public static String esotericCompile(String code) {
		code=code.replaceAll("RETURNTYPE:STRING", "S");
		code=code.replaceAll("RETURNTYPE:RAW", "R");
		code=code.replaceAll("RETURNTYPE:INTEGER", "I");
		code=code.replaceAll("Conditional", "C");
		code=code.replaceAll("IF", "C");
		code=code.replaceAll("NOT", "N");
		code=code.replaceAll("JUMP", "J");
		code=code.replaceAll("flag", "f");
		code=code.replaceAll("FLAG", "F");
		code=code.replaceAll("JUMP", "J");
		code=code.replaceAll("RIGHT", "+");
		code=code.replaceAll("LEFT", "-");
		code=code.replaceAll("LEFT", "-");
		code=code.replaceAll("WRITE", "W");
		
		
		code=code.replaceAll(" ", "");
		code=code.replaceAll("\n", "");
		code=code.replaceAll("\t", "");
		code=code.replaceAll("\r", "");
		String newCode="";
		for(int i=0; i<code.length(); i++) {
			
			if(code.charAt(i)!='('&&code.charAt(i)!=')') {
				newCode+=""+code.charAt(i);
			}
		}
		code=newCode;
		
		return code;
		
	}
	
	public static int esotericRead(ArrayList<Integer> tape,int tapeIndex) {
		
		return tape.get(tapeIndex);
	}
	public static void esotericWrite(ArrayList<Integer> tape,int tapeIndex,int value) {
		tape.set(tapeIndex, value);
	}
	
	
	public static int nextZerothE(String str) {
		int depth = 0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='C'||str.charAt(i)=='J'||str.charAt(i)=='F') {
				depth++;
			}
			
			if(str.charAt(i)=='E') {
				depth--;
			}
			
			if(depth ==0) {
				return i;
			}
			
		}
		return -1;
	}
	public static String esotericString(String code) {
		ArrayList<Integer> raw = esotericRaw(code);
		String returnStr = "";
		for(int i=0; i<raw.size();) {
			String binary = "";
			if(raw.size()<8) {
				break;
			}
			for(int e=0; e<8; e++) {
				//binary = "";
				
				binary+=""+raw.remove(0);
			}
			
			returnStr+=" "+Integer.parseInt(binary, 2);
		}
		return returnStr;
	}
	
	
	public static void printEsoteric(String code) {
		
		if(code.charAt(0)=='S') {
			//System.out.println(esotericString(code));
			ArrayList<Integer> raw = esotericRaw(code);
			String str = "";
			String strT = "";
			for(int i=0;i<raw.size(); i++) {
				
				
				strT+=""+raw.get(i);
				if(strT.length()==8) {
					str+=(char)Integer.valueOf(strT,2).intValue();
					strT="";
				}
			}
			System.out.println(str);
		}
		if(code.charAt(0)=='R') {
			System.out.println(esotericRaw(code));
		}
		if(code.charAt(0)=='N') {
			ArrayList<Integer> raw = esotericRaw(code);
			String number = "";
			for(int i=0; i<raw.size(); i++) {
				number+=raw.remove(i);
			}
			System.out.println(new BigInteger(number,2));
		}
	}
	
	public static ArrayList<Integer> esotericRaw(String code){
		
		/*
		 *+=Move right
		 *-=Move left
		 *W
		 *
		 *C...E = Condition (runs if standing on 1)
		 *E = END 
		 *J#E=Jump to code index # if the current tape value is 
		 *H=halt
		 *F=flag
		 * */
		
		
		
		
		
		
		ArrayList<Integer> tape = new ArrayList<>();
		tape.add(0);
		
		int tapeIndex = 0;
		
		
		int codeIndex = 0;
		
		
		
		for(codeIndex = 0; codeIndex<code.length(); codeIndex++) {
			if(code.charAt(codeIndex) == '+'){
				tapeIndex++;
				if(tape.size()==tapeIndex) {
					tape.add(0);
				}
			}
			if(code.charAt(codeIndex) == '-'){
				tapeIndex--;
				if(tapeIndex<0) {
					tape.add(0,0);
					tapeIndex++;
				}
			}
			if(code.charAt(codeIndex) == 'C'){
				
				int skipIndex = codeIndex+nextZerothE(code.substring(codeIndex));
				
				
				//if false skip code
				if(esotericRead(tape,tapeIndex)==0) {
					codeIndex=skipIndex;
				}
			}
			
			
				//NOT or XOR
				if(code.charAt(codeIndex) == 'N'){
					tape.set(tapeIndex, 1-tape.get(tapeIndex));
				}
				//jump to nth flag
				int skipIndex;
				if(code.charAt(codeIndex) == 'F') {
					skipIndex = codeIndex+code.substring(codeIndex).indexOf('E');
					
					int flagNumber=Integer.valueOf(code.substring(codeIndex+1,skipIndex));
					
					
					int flag = 0;
					int index = 0;
					while(flag!=flagNumber) {
						index++;
						if(code.charAt(index)=='f') {
							flag++;
						}
//						if(index>code.length()) {
//							throw new Exception("INVALID FLAG");
//						}
					}
					codeIndex=index;
					
					
					
					
					
					
					
				}
			if(code.charAt(codeIndex) == 'J'){
				
				
				
				
				//jump to nth flag
				if(code.charAt(codeIndex) == 'F') {
					skipIndex = codeIndex+code.substring(codeIndex).indexOf('E');
					
					int flagNumber=Integer.valueOf(code.substring(codeIndex+1,skipIndex));
					
					
					int flag = 0;
					int index = 0;
					while(flag!=flagNumber) {
						index++;
						if(code.charAt(codeIndex)=='f') {
							flag++;
						}
//						if(index>code.length()) {
//							throw new Exception("INVALID FLAG");
//						}
					}
					codeIndex=index;
					
					
					
					
					
					
					
				}
				else {
					skipIndex = codeIndex+code.substring(codeIndex).indexOf('E');
					
					codeIndex=Integer.valueOf(code.substring(codeIndex+1,skipIndex));
				}
			}
			if(code.charAt(codeIndex) == 'H'){
				break;
			}
			
			
			if(code.charAt(codeIndex) == 'W'){
				
				
				if(code.charAt(codeIndex+1)=='1') {
					esotericWrite(tape,tapeIndex,1);
				}
				if(code.charAt(codeIndex+1)=='0') {
					esotericWrite(tape,tapeIndex,0);
				}
				
				
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		return tape;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//compiler in progress?

		//in terminal run:
		//java Esoteric.java <codeFileName> 
		//(Without the <> and replace codeFileName with the file of the esoteric language)
		if(args.length==1) {
			String code = "";
			try {
				System.out.println("RUNNING "+args[0]+": ");
				String line = "";
				BufferedReader reader = new BufferedReader(new FileReader(args[0]));
				while((line=reader.readLine())!=null) {
					code+=line;
				}
				
				
				code=esotericCompile(code);
				printEsoteric(code);
				System.out.println(esotericRaw(code));
				System.out.println("Done" );
				reader.close();
				
				
				//wont run Demos
				return;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("RUNNING Demo programs: ");
		String demo = "NCHE";
		System.out.println(esotericRaw(demo));
		//Hello world Program
		String code="RETURNTYPE:STRING"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "WRITE(1)\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "RIGHT\r\n"
				+ "HALT";
		code=esotericCompile(code);
		printEsoteric(code);
		
		
		
		
		
		
		
		
		
		
		
		//Complex control Program
				code="RETURNTYPE:RAW "
						
						+ "FLAG 2 E"
						+ "flag"
						+ "RIGHT"
						+ "RIGHT"
						+ "RIGHT"
						+ "RIGHT"
						+ "WRITE(1)"
						+ "FLAG 3 E"
						+ "flag"
						+ "WRITE(1)"
						+ "FLAG 1 E"
						+ "flag"
						+ ""
						+ "";
				code=esotericCompile(code);
				printEsoteric(code);
				
				
				System.out.println("hi");
				//Functions and conditionals AND RECURSION?????
				code="RETURNTYPE:RAW "
						+ "WRITE(1)"
						+ "RIGHT"
						+ "WRITE(1)"
						+ "RIGHT"
						+ "WRITE(1)"
						+ "RIGHT"
						+ "WRITE(1)"
						+ ""
						+ ""
						+ "LEFT"
						+ "LEFT"
						+ "LEFT"
						+ ""
						+ "flag"
						+ "IF"
						+ "	N "
						+ "	RIGHT "
						+ "	FLAG 1 E "
						+ "E ";
				//FLAG 1 E is basically a function that gets rid of all zeros
				code=esotericCompile(code);
				printEsoteric(code);
				
				
				
				//Alternate compiled syntax
				code="RETURNTYPE:RAW "
						+ "fW1+W1+W1++++";
				printEsoteric(code);
				
				
				//Infinite loop
				code="RETURNTYPE:RAW "
						+ "flag"
						+ "FLAG 1 E";
				code=esotericCompile(code);
				printEsoteric(code);
		
	}

}
