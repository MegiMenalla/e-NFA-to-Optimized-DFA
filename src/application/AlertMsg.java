package application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
public class AlertMsg {

	public static void display(String title, String msg)
	{
		Stage window= new Stage();
		//to block input event
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		window.setMinHeight(100);
		
		Label label= new Label();
		label.setText(msg);
		
		Button close= new Button("Okay");
		close.setOnAction(e->window.close());
		
	    VBox vb= new VBox();
	    vb.getChildren().addAll(label,close);
	    vb.setAlignment(Pos.BOTTOM_CENTER);
	      vb.setStyle("-fx-padding: 10;" +
	              "-fx-border-style: solid inside;" +
	              "-fx-border-width: 2;" +
	              "-fx-border-insets: 5;" +
	              "-fx-border-radius: 5;" +
	              "-fx-border-color: red;"+
	              "-fx-font-size: 20px;");
	    Scene scene= new Scene(vb);
	    window.setScene(scene);
	    window.showAndWait();
	}
	
	
}
