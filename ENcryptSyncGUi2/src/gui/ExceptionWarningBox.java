package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExceptionWarningBox  extends Stage{
	
	 Stage dialogStage;
	
	 public  void Dialog(String WarningText, String ButtonText ) {
		 dialogStage = new Stage();
		 dialogStage.initModality(Modality.WINDOW_MODAL);
		 Button okayButton = new Button(ButtonText);
		 dialogStage.setTitle("Warning!");
		 dialogStage.setMinHeight(150);
		 dialogStage.setMinWidth(300);
		 okayButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dialogStage.close();
				
			}
		 });
		dialogStage.setScene(new Scene(VBoxBuilder.create().
		     children(new Text(WarningText),okayButton).
		    alignment(Pos.CENTER).padding(new Insets(5)).build()));
		
		 dialogStage.showAndWait();
}
	
}
