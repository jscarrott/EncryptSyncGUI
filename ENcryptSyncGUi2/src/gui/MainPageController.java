package gui;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainPageController  extends AnchorPane implements Initializable {
	
	@FXML javafx.scene.control.ListView<File> UnencryptedListFiles;
	@FXML javafx.scene.control.ListView <File> EncryptedListFiles;
	
	private EncryptSyncGui application;
	private Path UnencryptedDirectory;
	private ObservableList<File> UnEnData;
	private ObservableList<File> EnData;
	
void setApp(EncryptSyncGui application){
	this.application = application;
	UnEnData = FXCollections.observableArrayList(application.getCurrentProfile().getUnencryptedDirectory().getContainedFiles());
	UnencryptedListFiles .setItems(UnEnData);
	EnData = FXCollections.observableArrayList(application.getCurrentProfile().getEncryptedDirectory().getContainedFiles());
	EncryptedListFiles .setItems(EnData);
	}

@FXML protected void handleEncryptFilesButton(ActionEvent event){
	application.encryptFilesUsingCurrentProfile();
	application.updateFilesInCurrentProfile();
	EnData.clear();
	EnData.addAll(application.getCurrentProfile().getUnencryptedDirectory().getContainedFiles());
	UnEnData.clear();
	UnEnData.addAll(application.getCurrentProfile().getUnencryptedDirectory().getContainedFiles());
	EncryptedListFiles .setItems(EnData);
	UnencryptedListFiles .setItems(UnEnData);
	
}

@FXML protected void handleDecryptFilesButton(ActionEvent event){
	application.decryptFilesUsingCurrentProfile();
	application.updateFilesInCurrentProfile();
	UnEnData = FXCollections.observableArrayList(application.getCurrentProfile().getUnencryptedDirectory().getContainedFiles());
	EnData = FXCollections.observableArrayList(application.getCurrentProfile().getEncryptedDirectory().getContainedFiles());
}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}

}
