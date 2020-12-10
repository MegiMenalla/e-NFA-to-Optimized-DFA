package application;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Automat {
   private ArrayList<String> alfabet;
   private ArrayList<String> gjendje;
   private ArrayList<String> fundore;
   private String fillestare;
   private ArrayList<kalim> kalime;
   //constructor...
   public Automat() {
	   alfabet=new ArrayList<String>();
	   gjendje=new ArrayList<String>();
	   fundore=new ArrayList<String>();
	   fillestare="";
	   kalime=new ArrayList<kalim>();
   }
// kalimet spontane do te shenohen me simbolin '$'
   public Automat(ArrayList<String> alf,ArrayList<String>gj, ArrayList<String>f, String q0,ArrayList<kalim> k) {
	   alfabet=alf;
	   gjendje= gj;
	   fundore=f;
	   fillestare=q0;
	   kalime=k;
   }
//set................................................................................................
   public void set_alfabet(ArrayList<String> alf) {
	 alfabet=alf;
   }
   public void set_gjendje(ArrayList<String> gj) {
	   gjendje=gj;
   }
   public void set_fundore(ArrayList<String> f) {
	   fundore=f;
   }
   public void set_fillestare(String s) {
	   fillestare=s;
   }
   public void add_kalim( kalim k) {
	   kalime.add(k);
   }

//get.......................................................................................................
   
   public  ArrayList<String> get_alfabet(){
	   return alfabet;
   }
   public  ArrayList<String> get_fundore(){
	   fundore=  (ArrayList<String>) fundore.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	   return fundore;
   }
   public String  get_fillestare() {
	   return fillestare;
   }
   public ArrayList<kalim> get_kalime() {
	return kalime;
   }
   public  ArrayList<String> get_gjendje(){
	  return gjendje;
   }
   

//kotrollo qe q0 ben pjese ne bashkesine e gjendjeve te perdorura.........................
   public int kontroll1() {  
	   if(gjendje.contains(fillestare))
	   {
		   return 0;
	   }
	return -1;   
   }
   
   
 //kotrollo qe F jo boshe---pastaj qe F nenbashkesi e gjendjeve te perdorura ne kalime   
   public int kontroll2() {
	  
	   if(fundore.isEmpty()) 
		   return -1;
	   else {//nqs te pakten 1 gjendje fundore eshte eshte perdorur te kalimet
		   for(String o:fundore)
			   if(gjendje.contains(o))
				   return 0;
	   }
	   return -1;
   }
 

//...............................MBYLLJA E PARE ME EPSILON.................................................
  public ArrayList<String> mbylljeE1(String x){// merr si parameter nje gjendje
	  ArrayList<String> mbE1= new ArrayList<String>();
	  
	  for(kalim y: kalime)
	  {
		  if(y.getFillim().equals(x)) {
			  if(y.getSimbol().contentEquals("$")) {
				  mbE1.addAll(y.getDestinacion());
			  }
		  }
	  }
	  return mbE1;
  }
  public ArrayList<String> mbylljeE11(String q){
	  ArrayList<String> mbE11=new ArrayList<String>();
	  ArrayList<String> tmp=new ArrayList<String>();
	  ArrayList<String> tmp1=new ArrayList<String>();
	 
		  tmp=mbylljeE1(q);
		  while(!tmp.isEmpty()) 
		  {
			  mbE11.addAll(tmp);
			  for(String y:tmp)
			  {
				  tmp1=mbylljeE1(y);
				  mbE11.addAll(tmp1);
				  tmp=tmp1;
			  } 
	  }
		  mbE11.add(q);
		  for(String m: mbE11) {
			  if(fundore.contains(m))
			  {
				  fundore.add(q);
			  } 
		  }
	return mbE11;
  }
//.................................KALIMI ME SIMBOL........................................................

  public ArrayList<String> mbyllje_me_simbol(ArrayList<String> mbE1, String a){
	  ArrayList<String> simbol= new ArrayList<String>();
	  for(String x:mbE1)
		  for(kalim k: kalime)
		  {
			  if(k.getFillim().contentEquals(x))
			  {
				  if(k.getSimbol().contentEquals(a))
					  simbol.addAll(k.getDestinacion());
			  }
		  }
	  simbol.trimToSize();
	return simbol;
  }
  
  
//...............................MBYLLJA E DYTE ME EPSILON....................................................
  public ArrayList<String> mbylljeE2(ArrayList<String> simbol){
	  
	  ArrayList<String> perfundim= new ArrayList<String>();	  
	  ArrayList<String> tmp= new ArrayList<String>();
	  for(String t:simbol)
	  {
		  tmp=mbylljeE11(t);
		  perfundim.addAll(tmp);
		  tmp.clear();
	  }
	  tmp=perfundim;
	perfundim =  (ArrayList<String>) tmp.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	return perfundim;  
  }
  
//...................................KONVERTO ne AFJD.................................................................
  public ArrayList<kalim> konverto() {
	  ArrayList<kalim> afjd= new ArrayList<kalim>();
	  
	  ArrayList<String> nje= new ArrayList<String>();
	  ArrayList<String> dy= new ArrayList<String>();
	  ArrayList<String> tre= new ArrayList<String>();
	  for(String q:gjendje)
	  {
		  nje=mbylljeE11(q);
		  for(String s: alfabet) {
			  dy=mbyllje_me_simbol(nje,s);
			  tre=mbylljeE2(dy);
			  kalim k= new kalim();
			  k.set_fillim(q);
			  k.set_Simbol(s);
			 
			  k.set_destinacion(tre);
			  afjd.add(k);
			  
		  }
		
	  }
	  fundore =  (ArrayList<String>) fundore.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	  
	 // System.out.println("Gjendjet fundore te afjd"+fundore);
	  return afjd;
  }

  public String toString() {
	  
      return "kalimi: "+kalime;
   }
//
//
//  
//....................................AFD.......................................................................
//
//
//  
//.................................MBYLLJA ME SIMBOLAfd........................................................

  public ArrayList<String> mbyllje_me_simbol2(ArrayList<String> mbE1, String a,ArrayList<kalim> afjd){
	  ArrayList<String> simbol= new ArrayList<String>();
	  for(String x:mbE1)
		  for(kalim k: afjd)
		  {
			  if(k.getFillim().contentEquals(x))
			  {
				  if(k.getSimbol().contentEquals(a))
					  simbol.addAll(k.getDestinacion());
			  }
		  }
	  simbol =  (ArrayList<String>) simbol.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	return simbol;
  }
  
  
//shton ne seen
public ArrayList<ArrayList<String>> kontroll_GjendjeRe(ArrayList<ArrayList<String>> seen,ArrayList<String> tmp){
	
		  if(!seen.contains(tmp))
			  seen.add(tmp);
	
	  
	  return seen;
}
  
//krijon kalime te reja qe qofte se jane pare gjendje te reja
//kthen gjendjen e dy listave qe mbajne gjendjet e krijuara dhe ato qe duhen krijuar
public ArrayList<ArrayList<ArrayList<String>>> shtoGjendje(ArrayList<ArrayList<String>> seen,ArrayList<ArrayList<String>> done,ArrayList<kalim>afd,ArrayList<kalim>afjd)
{   
    String con;
    ArrayList<String> tmp=new ArrayList<String>();
    ArrayList<ArrayList<String>> seen1= new ArrayList<ArrayList<String>>();	
    
      
		for(ArrayList<String> st:seen)
		{
			if(!done.contains(st)) {
				for(String a:alfabet)
				{
					tmp=mbyllje_me_simbol2(st,a,afjd);
					done.add(st);
					con=String.join("", st);
					for(String s:st) {
						if(fundore.contains(s))
							fundore.add(con);
					}
					kalim r=new kalim();
					r.addKalim(con, a, tmp);
					afd.add(r);
					seen1.add(tmp);
				}
				
			}
		}
		for(ArrayList<String> tmp1:seen1)
	        seen=kontroll_GjendjeRe(seen,tmp1);
		ArrayList<ArrayList<ArrayList<String>>> stivat= new ArrayList<ArrayList<ArrayList<String>>>();
		stivat.add(seen);
		stivat.add(done);
		
		
      return 	stivat;
     
}

  
  public ArrayList<String> fundore_re(ArrayList<kalim> afd) {
		ArrayList<String> f= new ArrayList<String>();
		for(String a:fundore)
		{
			if(gjendje.contains(a))
				f.add(a);
		}
		f =  (ArrayList<String>) f.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
		return f;
  }
  
//konvertimi AFJD-AFD
public ArrayList<kalim> konvertoAfd(ArrayList<kalim> afjd)
{    
	  String con1; int xh=0;
	  ArrayList<String> tmp2= new ArrayList<String>();
	  ArrayList<String> tmp5= new ArrayList<String>();
	  tmp5.add("DEAD_State");
	  ArrayList<kalim> afd=new ArrayList<kalim>();
    ArrayList<ArrayList<String>> seen= new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> done= new ArrayList<ArrayList<String>>();
	    String con;
	    ArrayList<String> tmp=new ArrayList<String>();
	    ArrayList<ArrayList<String>> seen1= new ArrayList<ArrayList<String>>();	
		 ArrayList<String> f= new ArrayList<String>();
		 f.add(fillestare);
		 done.add(f);//permban vetem gjendjen fillestare
		 seen.add(f);//permban gjendjen fillestare dhe destinacionete q0 me elem. e alfabetit
		 
  //merr kalimet qe fillojne me gjendje fillestare
	  for(kalim k:afjd)
	  { if(k.getFillim().equals(fillestare))
	   {
		  for(String s: alfabet) {
			  if(k.getSimbol().equals(s))
			  {
				  kalim o=new kalim();
				  tmp2.clear();
				  if(k.getDestinacion().isEmpty()) 
				  { con1=String.join("",tmp5);
				    xh++;
				  }
				  else
				  con1=String.join("",k.getDestinacion());
				  tmp2.add(con1);
				  o.addKalim(k.getFillim(), k.getSimbol(),tmp2);
				  afd.add(o);
				  seen=kontroll_GjendjeRe(seen,k.getDestinacion());
			  }
		  }
	  }
	 }
	

   int c=1; 
  
		while(c>0) {
		        c=0;
		      
				for(ArrayList<String> st:seen)
				{
					if(!done.contains(st)&& !st.isEmpty()) {
						for(String a:alfabet)
						{
							tmp=mbyllje_me_simbol2(st,a,afjd);
							done.add(st);
							con=String.join("", st);
							kalim r=new kalim();
							con1=String.join("", tmp);
							tmp2.clear();
							tmp2.add(con1);
							if(tmp.isEmpty()) 
							{
								r.addKalim(con, a, tmp5);
							    xh++;
							}
							else
							r.addKalim(con, a, tmp2);
								
							afd.add(r);
							//System.out.println(" Nga "+con+" me "+a+" shkon ne "+tmp2);
							seen1.add(tmp);
							c++;
							for(String s:st) 
							{
								if(fundore.contains(s))
									{
									fundore.add(con);
									   break;
									}
							}
						}
						
					}
				}
				for(ArrayList<String> tmp1:seen1)
			        seen=kontroll_GjendjeRe(seen,tmp1);
	        }
		gjendje.clear();
		String str;
		for(ArrayList<String> w:done) {
			str=String.join("", w);
			gjendje.add(str);
		}
		 gjendje =  (ArrayList<String>) gjendje.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
		// System.out.println("gjendjet e afd "+gjendje);
   //shtoj gjendjet e reja fundore qe krijohen		
	     tmp.clear();
		 tmp=fundore_re(afd);
		 fundore=tmp;
		// System.out.println("gjendjet fundore te afd"+fundore);
      //eshte pare gj_errori-shto kalimet e gj_error-it te vetja me cdo simbol
		 if(xh>0) {
				for(String a:alfabet) {
					kalim m=new kalim();
					m.addKalim("DEAD_State", a, tmp5);
					afd.add(m);
				}
				
		 }
	  
		
      		 
	  return afd;
}
//
//
//
//.............................................OPTIMIZIMI...............................................................
//
//
//
// kontrolli dhe fshirja e gjendjeve te pakapshme bashme me kalimet qe permbajne keto gjendje
 public void fshi(ArrayList<kalim> afd,String gj) {
    	  ArrayList<kalim> afd1= new ArrayList<kalim>();
    	 for(kalim k: afd)
    		 afd1.add(k);
    	  System.out.println(gj+"  eshte gjendje e pakapshme!");
    	  for(kalim l:afd1)//fshin te gjitha kalimet qe fillonin ose mbaronin me gjendjen e pakapshme
    		  if(l.getFillim().equals(gj)||l.getDestinacion().contains(gj))
    			 afd.remove(l);
    	  gjendje.remove(gj);
     
	  
  }
  public String fshi1(ArrayList<kalim> afd)
  {   
	  int c=0;
	  ArrayList<String> arrin= new ArrayList<String>();
	  arrin.add(fillestare);
	  do {c++;
		 for(String a:alfabet)
		  {
			 arrin.addAll(mbyllje_me_simbol2(arrin,a,afd));
		     arrin= (ArrayList<String>) arrin.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
		     System.out.println("++++++++++++++++++ "+arrin);
		  }
	  }while(c<gjendje.size());
	  
	  arrin.trimToSize();
	  
	  if(arrin.size()<gjendje.size())
	  {
		  for(String a:arrin)
			  if(!gjendje.contains(a))
				  return a;
	  }
	  
	  System.out.println("Nuk ka gjendje te pa arritshme!");
	  return "bosh";
  }
  public void gj_pakapshme(ArrayList<kalim> afd) {
	  
	  String p=fshi1(afd);
	  while(!p.contentEquals("bosh")) {
		  fshi(afd,p);
		  System.out.println("---eshte fshire gjendja "+p+"---ne te cilen nuk hyhet");
		  p=fshi1(afd);
	  }
	  
  }

  
 

 
  //......................kombinimet e shenjuara(dyshet qe fillojne me gjendje fundore)......................
  
  public ArrayList<ArrayList<String>> Verdh(ArrayList<kalim> afd){
	  ArrayList<ArrayList<String>> verdh = new ArrayList<ArrayList<String>>();
	  ArrayList<String> tmp= new ArrayList<String>();
	  //kombinimi (q1,q2)/ q1 nga F dhe q2 nga Q-F
	  for(String gj1: gjendje)
	  {
		  
		  if(fundore.contains(gj1))
		  {  
		   for(String gj2: gjendje)
		  
	        if(!gj1.equals(gj2)&&  !fundore.contains(gj2))
	        {  
	        	tmp=cifti(gj1,gj2);
	        	verdh.add(tmp);
	        	
	        }
		       
		    
		  }
		  //kombinimi jof-f
		  else if(!fundore.contains(gj1))
		  {  
		   for(String gj2: gjendje)
		  
	        if(!gj1.equals(gj2) &&  fundore.contains(gj2))
	        {  
	        	tmp=cifti(gj1,gj2);
	        	verdh.add(tmp);
	        	
	        }
		       
		    
		  }
	  }  
	return verdh;
  }  
  
  
  //
  //
  public ArrayList<String> cifti(String g1, String g2){
	  ArrayList<String> dy= new ArrayList<String>();
	  dy.add(g1);
	  dy.add(g2);
	  
	  return dy;
  }
  
  
 //......................kombinimet e pashenjuara.......................................................
  
  
  public ArrayList<ArrayList<String>> Bardh(ArrayList<kalim> afd){
	  ArrayList<ArrayList<String>> bardh = new ArrayList<ArrayList<String>>();
	  ArrayList<String> tmp= new ArrayList<String>();
	  for(String gj1: gjendje)
	  {  //kombinimi fundre-fundore
		  if(fundore.contains(gj1))
		  {  
		   for(String gj2: gjendje)
		   {  
	        if(!gj1.equals(gj2)&&  fundore.contains(gj2))
	        {   tmp=cifti(gj1,gj2);
	        	bardh.add(tmp);
	        
	          }
	        }
		  }
		  else //kombinimi jofundore-jofundore
			  if(!fundore.contains(gj1))
			  {  
			   for(String gj2: gjendje)
			   {  
		        if(!gj1.equals(gj2)&&  !fundore.contains(gj2))
		        {   tmp=cifti(gj1,gj2);
		        	bardh.add(tmp);
		        
		          }
		        }
			  }
	  }
	 
	return bardh;
  }
  
  
  //...................kalim me simbol per cdo cift nga lista e cifteve te pashenjuara.........................
  public int shenjo(ArrayList<ArrayList<String>> verdh, ArrayList<ArrayList<String>> bardh,ArrayList<kalim> afd) {
	 ArrayList<String> tmp= new ArrayList<String>();
	 ArrayList<ArrayList<String>> tmp1= new ArrayList<ArrayList<String>>();
	 int c=0;
	  for(ArrayList<String> cift: bardh)
	  {   System.out.println("cifti "+cift);
		  for(String a:alfabet) 
		  {  
			  tmp= mbyllje_me_simbol2(cift,a,afd);
			 System.out.println(" me simbolin "+a+"shkon ne "+tmp);
			  if(verdh.contains(tmp)&& tmp.size()>1) {
				
				  verdh.add(cift);
				  tmp1.add(cift);
				  c++;
				  System.out.println("++++++++++++++++++++++++++++++"+cift+" u shenjua.");
			  }
		  }
	  }
	  for(ArrayList<String> x:tmp1) {
		   bardh.remove(x);
	  }
	 return c;
  }
  
  //bashkim i gjendjeve te pashenjuara ne nje arraylist pa perseritje
  public ArrayList<String> nje_gjendje(ArrayList<ArrayList<String>> bardh)
  {
	ArrayList<String> gj= new ArrayList<String>();
	ArrayList<ArrayList<String>> tmp= new ArrayList<ArrayList<String>>();
	gj.add(bardh.get(0).get(0));
	gj.add(bardh.get(0).get(1));
	for(ArrayList<String> c:bardh)
	{
		for(String m: c)
		{
			if(gj.contains(m)) {
				gj.addAll(c);
				tmp.add(c);
			}
		}
	}
	for(ArrayList<String> h: tmp)
		if(bardh.contains(h))
			bardh.remove(h);
	gj= (ArrayList<String>) gj.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	System.out.println("gjendja "+gj);
	return gj;
  }
  //Kalimet e reja
  public ArrayList<kalim> kalimeOptimali(ArrayList<String> nje_gj, ArrayList<kalim> afd) {
	  String gjRe;
	  ArrayList<kalim> optimal= new ArrayList<kalim>();
	  ArrayList<String> gj= new ArrayList<String>();
	  for(String g:gjendje)
		  gj.add(g);
	  gjRe=String.join("", nje_gj);//gjendjet e pashenjuara i ruaj si nje string te vetem me emer bashkimin e tyre
	  for(String q: gjendje)
	  {
		  if(nje_gj.contains(q))
			  gj.remove(q);
	  }
	  gj.add(gjRe);
	  
	   for(String t: nje_gj)
	   {
		   if(fundore.contains(t))
		   {     
			   fundore.remove(t);
			   if(!fundore.contains(gjRe))
				   fundore.add(gjRe);
		   }
		   
		   if(t.equals(fillestare))
			   fillestare=gjRe;
	   }
	  
	  ArrayList<String> tmp= new ArrayList<String>();
	  tmp.add(gjRe); 
	  gjendje.add(gjRe);
	  //formimi i kalimeve
	  for(kalim k:afd)
		  for(String a: alfabet)
			  if(k.getSimbol().equals(a)) {
				  String g=String.join("", k.getDestinacion());
				  if(nje_gj.contains(g))
				  {   
					  if(nje_gj.contains(k.getFillim()))
					  {
						  
						  kalim p= new kalim();
						  p.addKalim(gjRe, a,tmp );
						  optimal.add(p);
						  
					  }
					  else if(!nje_gj.contains(k.getFillim())) 
					  {
					  kalim n= new kalim();
					  n.addKalim(k.getFillim(), a,tmp);
					  optimal.add(n);
					  
					  }
				  }
				  else if(nje_gj.contains(k.getFillim()))
				  {
					  kalim m= new kalim();
					  m.addKalim(gjRe, a, k.getDestinacion());
					  optimal.add(m);
				  }
				 else
					 optimal.add(k); 
			  }
	  
	  
	  
	  
	  return optimal;
  }
  
  
  
  
  
  
  //konvertimi nga AFD ne AFD te minimizuar
public ArrayList<kalim> konverto3(ArrayList<kalim> afd){ 
	  // System.out.println(fundore);
	  //System.out.println(fillestare);
	  ArrayList<kalim> optimal= new ArrayList<kalim>();
	  
	  gj_pakapshme(afd);
	  ArrayList<ArrayList<String>> verdh= new ArrayList<ArrayList<String>>();
	  ArrayList<ArrayList<String>> bardh= new ArrayList<ArrayList<String>>();
	  
	  verdh=Verdh(afd);

	  System.out.println("te shenjuar "+verdh);	  
	  bardh=Bardh(afd);
	  
      System.out.println("te pashenjuar "+bardh);
	  int c=1;
	  while(c>0)
	  {  
		     c=shenjo(verdh,bardh,afd);
			 System.out.println("exe 1 here");
		}
	

	  ArrayList<String> gj=new ArrayList<String>();
	  for(String g:gjendje)
		  gj.add(g);
	  ArrayList<String> nje_gj= new ArrayList<String>();
	  ArrayList<ArrayList<String>> gj_bashkuar= new ArrayList<ArrayList<String>>();
	  
	  do {
		  if(!bardh.isEmpty())
		  {  nje_gj=nje_gjendje(bardh);
		     if(!nje_gj.isEmpty())
		     gj_bashkuar.add(nje_gj);
		   
		  }
	 }while(!bardh.isEmpty());
	  
	  System.out.println("gj_bashkuar "+gj_bashkuar);
	
	  
	  if(gj_bashkuar.isEmpty())
		  optimal=afd;
	  else {
	  ArrayList<kalim> moment= new ArrayList<kalim>();
	 for(ArrayList<String> t:gj_bashkuar) {
		 moment=kalimeOptimali(t,afd);
		 afd=moment;
	 }
	 optimal.addAll(moment);
	  }
	gj.clear();
	 for(kalim k: optimal)
	 {
		 gj.add(k.getFillim());
		gj.addAll(k.getDestinacion());
	 }
	 gjendje=(ArrayList<String>) gj.stream().collect(Collectors.toSet()).stream().collect(Collectors.toList());
	 
	 optimal=eleminoPerseritjet(optimal);
	
	 
	  System.out.println("gjendjet e af optimal "+gjendje);
	
	  System.out.println("gjendjet fundore te af optimal  "+fundore);
	   System.out.println("te shenjuar pas "+verdh);
	   System.out.println("te pashenjuar pas "+bardh);
	   
	  return optimal;
}



public ArrayList<kalim> eleminoPerseritjet(ArrayList<kalim> optimal) {
	ArrayList<kalim> opt= new ArrayList<kalim>();
	
   int c=0;
    	
   for(String a: gjendje)
	   for(String b:alfabet)
	   {   c=0;
		   for(kalim k:optimal)
		   {
			   if(c>0)
				   break;
			   if(k.getFillim().contentEquals(a)&&k.getSimbol().contentEquals(b))
				   { opt.add(k);
				     c++;
				   }	   
		   }
	
	   }
	 return opt;
   }
  
}

