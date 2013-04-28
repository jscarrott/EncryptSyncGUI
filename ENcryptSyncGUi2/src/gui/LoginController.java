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
	    @FXML private PasswordField passwordField;
	    @FXML private TextField userTextField;
	    @FXML private TableView<User> userList;
	    @FXML private TableColumn<User, String> profileNameColumn;
	    
	    private EncryptSyncGui application;
	    
	    @SuppressWarnings("unchecked")
		public void setApp(EncryptSyncGui application){
	        this.application = application;
	        ObservableList<User> data = FXCollections.observableList(application.getUsers());
	       profileNameColumn = new TableColumn<User, String>("Profile");
	       profileNameColumn = (TableColumn<User, String>) userList.getColumns().get(0);
	       profileNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
	        //ProfileNameColumn.setMinWidth(200);
	        //userList.getColumns().add((TableColumn<User, String>) profileNameColumn);
	        
	        userList.setItems(data);
	    }
	    
	    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
	        actiontarget1. setText("Sign in button pressed");
	        try {
		        if(application.loginUser(userList.getSelectionModel().getSelectedItem().getName(), passwordField.getText())){
		        	 application.gotoMainPage();
		        	
		        }
			} catch (Exception e) {
				actiontarget1.setText("Login method failed");
			}

	        actiontarget1. setText("Login Failed.");

	    }
	    
	    @FXML protected void handleNewUserButton(ActionEvent event){
	    	application.gotoNewUserPage();
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
	    
	    

}
