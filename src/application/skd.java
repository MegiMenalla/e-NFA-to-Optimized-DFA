/*package application;

import java.util.ArrayList;

public class skd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String file="af8";
		   Automat auto= new Automat();
		   auto=skedar.lexo(file);
		  ArrayList<kalim> afjd= new ArrayList<kalim>();
		  ArrayList<kalim> afd= new ArrayList<kalim>();
		  ArrayList<kalim> optim= new ArrayList<kalim>();
		   afjd=auto.konverto();
		 //System.out.println("AFjD ");
		   //System.out.println("kalimet:  "+afjd);
		   afd=auto.konvertoAfd(afjd);
		   //System.out.println("AFD ");
		  // System.out.println("kalimet:  "+afd);
		  
		 optim=auto.konverto3(afd);
		 System.out.println("Optimal");
	     System.out.println("kalimet:  "+ optim);
	}

}*/
/*//
//shton kalimet ne gjendjen e errorit 
//
public void shtoQErrori(ArrayList<kalim> afd) {
	ArrayList<String> qe= new ArrayList<String>();
	qe.add("DEAD_State");
	for(kalim pa:afd)
	{
		if(pa.getDestinacion().isEmpty())
		{
			pa.set_destinacion(qe);
			
		}
	}
	
	
	for(String a:alfabet) {
		kalim m=new kalim();
		m.addKalim("DEAD_State", a, qe);
		afd.add(m);
	}
	
}*/
