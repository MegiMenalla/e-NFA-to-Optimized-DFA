package application;

import java.util.ArrayList;

public class kalim {

	private String fillim;
	private String simbol;
	private ArrayList<String> destinacion;
	public kalim() {
		fillim="";
		simbol="";
		destinacion=new ArrayList<String>();
	}
public kalim(String q0, String a, ArrayList<String> dest1) {
		// TODO Auto-generated constructor stub
	fillim=q0;
	simbol=a;
	destinacion=dest1;
	}
	//set
	public String set_fillim(String q) {
		fillim=q;
		return fillim;
	}
	public String set_Simbol(String s) {
		simbol=s;
		return simbol=s;
	}
	public ArrayList<String> set_destinacion(ArrayList<String> arrayList) {
		//if(!d.isEmpty())
		destinacion.addAll(arrayList);	
		/*else
			destinacion.add("ß");*/
		return destinacion;
	}
 //get....................................................................................
	public String getFillim() {
		return fillim;
	}
	public String getSimbol() {
		return simbol;
	}
	public ArrayList<String> getDestinacion(){
		return destinacion;
	}
//................................................................................................
 public void addKalim(String k, String l, ArrayList<String> arrayList) {
		fillim=set_fillim(k);
		simbol=set_Simbol(l);
		destinacion=set_destinacion(arrayList);
	}

	
 public String toString()
	{   String out = "";
        out="\n"+fillim+" "+simbol+" "+destinacion;
   	   return out;
	}
 


}

