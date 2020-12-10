package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class skedar {
	  /* public static void main(String[]args) {
	     
	   String file="af2";
	   Automat auto= new Automat();
	   auto=lexo(file);
	  ArrayList<kalim> afjd= new ArrayList<kalim>();
	  ArrayList<kalim> afd= new ArrayList<kalim>();
	  ArrayList<kalim> optim= new ArrayList<kalim>();
	   afjd=auto.konverto();
	 System.out.println("AFjD ");
	   System.out.println("kalimet:  "+afjd);
	   
	   afd=auto.konvertoAfd(afjd);
	   System.out.println("AFD ");
	   System.out.println("kalimet:  "+afd);
	  
	   optim=auto.konverto3(afd);
	   System.out.println("Optimal");
	   System.out.println("kalimet:  "+optim);
	   
	   }*/
	  /* Automat a= new Automat();
	   public skedar(String str) {
		   a=lexo(str);
	   }*/
	
	public static  Automat lexo(String file){
	   Automat A= new Automat();
	
	   Scanner s = null;
	   String x,y,w;
	   String []str= new String[3];
	   ArrayList<String> z= new ArrayList<String>();
	   String elem;
	 
	try { 
		  s = new Scanner(new File(file));
		  elem =s.nextLine();
		  ArrayList<String> arr = new ArrayList<String>(Arrays.asList(elem.split(" ")));
		  A.set_alfabet(arr);
		  //System.out.println("alfabeti "+ A.get_alfabet());
		  
		  elem =s.nextLine();
		  ArrayList<String> arr1 = new ArrayList<String>(Arrays.asList(elem.split(" ")));
		  A.set_gjendje(arr1);
		 // System.out.println("gjendje "+ A.get_gjendje());
		  
		  elem=s.nextLine();
		  ArrayList<String> arr2 = new ArrayList<String>(Arrays.asList(elem.split(" ")));
		  A.set_fundore(arr2); 
		 //System.out.println("gjendjet fudore "+ A.get_fundore());
		 
		  elem=s.nextLine();
		  A.set_fillestare(elem);
		  //System.out.println("gjendje fillestare  "+A.get_fillestare());
		  
		  while (s.hasNextLine()) {
			  
			  elem=s.nextLine();
			  str = elem.split(" ");
			  x=str[0];
			  y=str[1];
			  w=str[2];z.clear();
			  z.add(w);
			  kalim k= new kalim();
			  k.addKalim(x,y,z); 
			  A.add_kalim(k);
			  System.out.println("kalimi "+k);
			  z.clear();
		}
	
	} catch (FileNotFoundException e) {
		System.out.println("Skedari nuk ekziston!");
	}
	 
	   
	   s.close();
		
		
		return A;
	}
 
	
	
	
	
}

