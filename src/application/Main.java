package application;
	

import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class Main extends Application {
	
	Stage window;
	Scene scene;
	BorderPane bp;
	Button afjd;
	Button afd;
	Button optimal;
	Automat auto;
	String tmp;
	TextArea text;
	
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception {
	window=primaryStage;
	window.setTitle("The one with the Project!");
    bp= new BorderPane();
    auto= new Automat();
    
    //Buttons
    afjd= new Button("Konverto_AFjD");
    afd= new Button("Konverto_AFD");
    optimal= new Button("Minimizo_AFD");    
    HBox bottom= new HBox(68);
    bottom.getChildren().addAll(afjd,afd,optimal);
    bp.setBottom(bottom);
    //Menu
    Menu fm= new Menu("Automat");
    
    
   //menu items
    MenuItem af10=  new MenuItem("a-tek,b-tek,c-tek ose cift");
    fm.getItems().add(af10);
    af10.setOnAction(e-> tmp=FileName("af10") );    
    
    
    MenuItem af1=  new MenuItem("(a+b)*(a+b)(a+b)*");
    fm.getItems().add(af1);
    af1.setOnAction(e-> tmp=FileName("af1") );
    
    MenuItem af2=  new MenuItem("AFD nga libri");
    fm.getItems().add(af2);
    af2.setOnAction(e-> tmp=FileName("af2") );
    
    MenuItem af3=  new MenuItem("AF pa q0");
    fm.getItems().add(af3);
    af3.setOnAction(e-> tmp=FileName("af3") );

    MenuItem af4=  new MenuItem("AF me D.S.");
    fm.getItems().add(af4);
    af4.setOnAction(e-> tmp=FileName("af4") );  
    
    MenuItem af5=  new MenuItem("AFD-ja nga seminari i optimizimit");
    fm.getItems().add(af5);
    af5.setOnAction(e-> tmp=FileName("af5") ); 
    
    MenuItem af6=  new MenuItem("Shifra e fundite str nuk eshte pare me pare");
    fm.getItems().add(af6);
    af6.setOnAction(e-> tmp=FileName("af6") );
    
    MenuItem af7=  new MenuItem("Shumefish i nr 3");
    fm.getItems().add(af7);
    af7.setOnAction(e-> tmp=FileName("af7") );
    
    MenuItem af8=  new MenuItem("A");
    fm.getItems().add(af8);
    af8.setOnAction(e-> tmp=FileName("af8") );
    
    MenuItem af9=  new MenuItem("B");
    fm.getItems().add(af9);
    af9.setOnAction(e-> tmp=FileName("af9") );
   
    //MenuBar
    MenuBar mb= new MenuBar();
    mb.getMenus().addAll(fm);
    bp.setTop(mb);     
    text = new TextArea();
    //text.appendText("Your Input: " + auto.get_alfabet() + "\n");
    bp.setCenter(text);
    
    
    //button on action
    afjd.setOnAction(e->{
    	
    	 ArrayList<kalim> tmp1= new ArrayList<kalim>();
		  
    	 if(kontrollet()==1) {
    		     tmp1=konv_afjd();
          text.appendText("Automati i fundem jodeterminist:"+"\n" +
         		   "Alfabeti: { "+ auto.get_alfabet() + "}"+"\n"+
         		   "Gjendjet: { "+ auto.get_gjendje() + "}"+"\n"+
         		   "Gjendje fillestare: { "+ auto.get_fillestare() + "}"+"\n"+
         		   "Gjendje fundore: { "+ auto.get_fundore() + "}"+"\n"+
         		   "Kalimet:  "+ tmp1+"\n"
         					);}
    	    text.appendText("\n\n");
    });
    
    afd.setOnAction(e->{
    	ArrayList<kalim> tmp1= new ArrayList<kalim>();
  	  
   	 if(kontrollet()==1) {
   		        tmp1=konv_afd();
       text.appendText("Automati i fundem determinist:"+"\n" +
        		   "Alfabeti: { "+ auto.get_alfabet() + "}"+"\n"+
        		   "Gjendjet: { "+ auto.get_gjendje() + "}"+"\n"+
        		   "Gjendje fillestare: { "+ auto.get_fillestare() + "}"+"\n"+
        		   "Gjendje fundore: { "+ auto.get_fundore() + "}"+"\n"+
        		   "Kalimet:  "+ tmp1+"\n"
        					);}   
   	                text.appendText("\n\n");
    });
    
    
    optimal.setOnAction(e->{
    	ArrayList<kalim> tmp1= new ArrayList<kalim>();
    	 if(kontrollet()==1) {
   		     tmp1=konv_optimal();
         text.appendText("Automati i fundem i minimizuar:"+"\n" +
        		   "Alfabeti: { "+ auto.get_alfabet() + "}"+"\n"+
        		   "Gjendjet: { "+ auto.get_gjendje() + "}"+"\n"+
        		   "Gjendje fillestare: { "+ auto.get_fillestare() + "}"+"\n"+
        		   "Gjendje fundore: { "+ auto.get_fundore() + "}"+"\n"+
        		   "Kalimet:  "+ tmp1+"\n"
        					);}   
    	 text.appendText("\n\n");
    });
    
    
      bp.setStyle("-fx-padding: 10;" +
            "-fx-border-style: solid inside;" +
            "-fx-border-width: 2;" +
            "-fx-border-insets: 5;" +
            "-fx-border-radius: 5;" +
            "-fx-border-color: blue;"+
            "-fx-font-size: 18px;");
		scene= new Scene(bp,700,500);
		window.setScene(scene);
		window.show();
		
	}
	
     public String FileName(String a) {
    	// System.out.println(a);
    	 return a;
     }
	
	 public ArrayList<kalim> konv_afjd() {
		   auto=skedar.lexo(tmp);
		   ArrayList<kalim> afjd= new ArrayList<kalim>();
		   afjd=auto.konverto();			
		  return afjd;
	 }
		
	 public ArrayList<kalim> konv_afd() {
	  
		   auto=skedar.lexo(tmp);
		   kontrollet();
		   ArrayList<kalim> afjd= new ArrayList<kalim>();
		   ArrayList<kalim> afd= new ArrayList<kalim>();
		   afjd=auto.konverto();
		   afd=auto.konvertoAfd(afjd);			
			 return afd;
	 }
	 public ArrayList<kalim> konv_optimal() {
		  
		   auto=skedar.lexo(tmp);
		   ArrayList<kalim> afjd= new ArrayList<kalim>();
		   ArrayList<kalim> afd= new ArrayList<kalim>();
		   afjd=auto.konverto();
		   afd=auto.konvertoAfd(afjd);
		   afjd=auto.konverto3(afd);
		  return afjd;
	 }
	 
	 public int  kontrollet() {
		 auto=skedar.lexo(tmp);
		 if(auto.kontroll1()==-1) {
			 AlertMsg.display("Ne kete automat nuk mund te hyhet!", "Skedari qe zgjodhet nuk ka gjendje fillestare ose \n gjendja fillestare e tij nuk eshte pjese e bashkesise se gjendjeve \n te perdorura ne kalime!");
		     return -1;
		 }
		 else if(auto.kontroll2()==-1) {
			 AlertMsg.display("Automati nuk mund te perfundoje!", "Skedari qe zgjodhet nuk ka gjendje fundore ose \n gjendjet fundore te dhena nuk jane pjese e bashkesise se gjendjeve \n te perdorura!");
		     return -1;
		 }
		 else {
			  
			    text.appendText("Automati i dhene:"+"\n" +
			 		   "Alfabeti: { "+ auto.get_alfabet() + "}"+"\n"+
			 		   "Gjendjet: { "+ auto.get_gjendje() + "}"+"\n"+
			 		   "Gjendje fillestare: { "+ auto.get_fillestare() + "}"+"\n"+
			 		   "Gjendje fundore: { "+ auto.get_fundore() + "}"+"\n"+
			 		   "Kalimet:  "+auto.get_kalime()+"\n"
			 					);
			    text.appendText("\n\n");
			    return 1;
		 }
	 }
	 
	
}
