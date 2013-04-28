package gui;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

import javax.swing.text.html.ListView;

import core.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class MainPageController  extends AnchorPane implements Initializable {
	
	@FXML javafx.scene.control.ListView<File> UnencryptedListFiles;
	@FXML ListView EncryptedListFiles;
	
	private EncryptSyncGui application;
	private Path UnencryptedDirectory;
	
void setApp(EncryptSyncGui application){
	this.application = application;
	
	ObservableList<File> data = FXCollections.observableList(application.getCurrentProfile().getUnencryptedDirectory().getContainedFiles());
	UnencryptedListFiles = new javafx.scene.control.ListView<File>(data);
	}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
}

}
