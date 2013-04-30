package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

public class NewUserController extends AnchorPane implements Initializable {
	
	private EncryptSyncGui application;
	private String UnencryptedDirectory;
	private String EncryptedDirectory;
	
	@FXML private TextField nameField;
	@FXML private PasswordField passwordField;
	
	void setApp(EncryptSyncGui application){
		this.application = application;
	}
	
	@FXML protected void SelectUnencryptedDirectory(ActionEvent event){
		DirectoryChooser fc = new DirectoryChooser();
		fc.setTitle("Open Dialog");
		String currentDir = System.getProperty("user.dir") + File.separator;
		File file = new File(currentDir);
		fc.setInitialDirectory(file);
		UnencryptedDirectory = fc.showDialog(null).toString();
		//TODO Catch exception from canceling the dialog, as well as handling the null string created.
		
	}
	
	@FXML protected void SelectEncryptedDirectory(ActionEvent event){
		DirectoryChooser fc = new DirectoryChooser();
		fc.setTitle("Open Dialog");
		String currentDir = System.getProperty("user.dir") + File.separator;
		File file = new File(currentDir);
		fc.setInitialDirectory(file);
		EncryptedDirectory = fc.showDialog(null).toString();
		//TODO Catch exception from canceling the dialog, as well as handling the null string created.
	}

	@FXML protected void HandleCreateProfileButton(ActionEvent event){
		application.createNewUser(nameField.getText(), passwordField.getText(), UnencryptedDirectory, EncryptedDirectory);
		application.writeProfiles();
		application.setCurrentUserFromString(nameField.getText());
		application.gotoMainPage();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
