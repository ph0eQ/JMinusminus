// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package pass;
import java.lang.Integer;
import java.lang.Long;
import java.lang.System;
import java.io.FileNotFoundException;

/*
 * A multi-line comment
 * For realsies
 */
public class project_4_input {

    public static String message() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        System.out.println(project_4_input.message());
        int x = 6;
        int q = 11;
        int r = 10;
        //test of conditional expression
        int y = (x <= q && 5 > 9) ? q : r;
        //check of do-while loop
        do {
        	y++;
        	System.out.println("Y:"+ y);
        }while(y < 17);
        for(int i =0; i < y; i++){
        	System.out.println("meow + i:"+i);
        }
        boolean a = true;
        boolean b = false;
        
        //could not seem to get the longs to print out but they seem to store 
        //correctly, not sure what I'm missing...
        long s = 11111111111111l;
        long z = 3234233l;
      
        // System.out.println(s);
       // String f = Long.toString(z);
        if(b == false || 4 > 5){
        	System.out.println("Yay true");
        	if( b == true || y > 10){
            	throw new FileNotFoundException();
        	}
        }else {
        	System.out.println("Woo false");
        }
        
        if (a == false || b == true){
        	System.out.println("Not gonna happen");
        }else {
        	throw new FileNotFoundException();
        }
    }

}
