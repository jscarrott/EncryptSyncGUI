package gui;

import java.io.IOException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

import javax.crypto.NoSuchPaddingException;

import core.CoordinatingClass;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class LoginController extends AnchorPane implements Initializable {
	
	
	 
	    @FXML private Text actiontarget1;
	    @FXML private PasswordField PasswordField;
	    @FXML private TextField UserTextField;
	    @FXML private TableView<User> UserList;
	    @FXML private TableColumn<User, String> ProfileNameColumn;
	    
	    private EncryptSyncGui application;
	    
	    public void setApp(EncryptSyncGui application){
	        this.application = application;
	        ObservableList<User> data = FXCollections.observableList(application.getUsers());
	       ProfileNameColumn = new TableColumn<User, String>("Profile");
	        ProfileNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
	        ProfileNameColumn.setMinWidth(200);
	        UserList.getColumns().add((TableColumn<User, String>) ProfileNameColumn);
	        
	        UserList.setItems(data);
	    }
	    
	    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
	        actiontarget1. setText("Sign in button pressed");
	        application.loginUser(UserList.getSelectionModel().getSelectedItem().getName(), PasswordField.getText());
	        application.gotoMainPage();

	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
	    
	    

}
