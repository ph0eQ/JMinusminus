/**
 * Sean Hoyt
 * Sample input to test changes to scanner
 */
/**
 * a bogus / comment
 * with some slightly //nested comments *
 */
package pass;
import java.lang.System;

/**
 * A simple file to verify scanner tokenizes all java reserved
 * words as well as the operators, and literals
 * This file will not pass the compilation tests yet as the parser has not been modified to deal
 * with the new keywords yet. This file just exists to prove Scanner will locate all keywords and operators.
 * @author Sean Hoyt
 *
 */
private class project_1_input extends Object implements String {
	String field;
	public project_1_input(){
		super();
	}
	interface Bicycle {
		abstract void ride(String terrain){
			this.field = terrain;
		}
	}
	public static String hello(){
		String s = null;
		
		return "hello";
	}
	public strictfp doodle(){
		return;
	}
	private native synchronized con(){
		int c= 0;
		c++;
	}
	public static void reservedWordsAndOperators(){
		const int solid = 10;
		int array[] = new int[10];
		int a = 5;
		byte b = 6;
		short c = 101;
		long d = 1001L;
	    float e = 101f;
	    double doub = 1000.01;
	    int f = a + b;
	    boolean result = true;
	    result = false;
	    char S = 'S';
	    int r = 1 + 2;
	    short s = 10000;
	    int i = 0;
	    for (int j = 100, i = 0; i <= 100 && j < 50;j--, i++){
	    	do {
	    		a += 5;
	    	} while (i < 50 && j >= i);
	    }
	    b = a ^ c;
	    a ^= c;
	    b = c % a;
	    b %= c;
	    c -= f;
	    a = f + a - c;
	    int g = 1000;
	    a = g / b;
	    a /= b;
	    c = b & 5;
	    c &= b;
	    int q = 1 * 19;
	    q *= 444;
	    a |= f;
	    
	    
	    goto 70;
	    throw aaoeu;
	    throws baoeu;
	    transient int noCereal;
	   
	    if(1){
	    	//another
	    }else{
	    	//thing
	    }
	    
	    
	    //some vars takene off of docs.oracle.com/javase/tutorial/java/nutandsbolts/datatypes.html
	    // 26 in decimal
	    final int decVal = 26;
	    // 26 in hex
	    int hexVal = 0x1a;
	    // 26 in binary
	    int binVal = 0b11010;
	    // 26 in octal
	    int octVal = 032;
	    
	    long lDecVal = 26L;
	    // 26 in hex again
	    long lhexVal = 0x1aL;
	    //26 in binary again
	    long lbinVal = 0b11010L;
	    //25 in octal again
	    long loctVal = 032L;
	    try{
	    protected int ab = 3;
	    private double bc = 16.01D;
	    }catch {
	    	volatile v;
	    }finally{
	    	continue;
	    }
		switch(a) {
			case 1 :
				String s1 = "whoops";
				if(s1 instanceof String){
					
				}
				break;
			case 5 :
				String s2 = "see daisies";
				break;
			default :
		    	String s3 = "yahoo";
		    	break;
		}
	}
	
}
