package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainPageController  extends AnchorPane implements Initializable {
	
	private EncryptSyncGui application;
	
void setApp(EncryptSyncGui application){
	this.application = application;
	}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}

}
