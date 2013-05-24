package gui;

import java.awt.image.ReplicateScaleFilter;
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
import javafx.stage.Stage;

public class LoginController extends AnchorPane implements Initializable {

	@FXML
	private Text actiontarget1;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField userTextField;
	@FXML
	private TableView<User> userList;
	@FXML
	private TableColumn<User, String> profileNameColumn;

	private EncryptSyncGui application;
/**sets up the listview with data
 * 
 * @param application
 */
	@SuppressWarnings("unchecked")
	public void setApp(EncryptSyncGui application) {
		this.application = application;
		ObservableList<User> data = FXCollections.observableList(application.getUsers());
		profileNameColumn = new TableColumn<User, String>("Profile");
		profileNameColumn = (TableColumn<User, String>) userList.getColumns().get(0);
		profileNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		TableColumn<User, String> UnencryptedDirColumn = new TableColumn<User, String>("UnencryptedDirectory");
		UnencryptedDirColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UnencryptedDirectoryString"));
		userList.getColumns().add((TableColumn<User, String>) UnencryptedDirColumn);
		TableColumn<User, String> EncryptedDirColumn = new TableColumn<User, String>("EncryptedDirectory");
		EncryptedDirColumn.setCellValueFactory(new PropertyValueFactory<User, String>("EncryptedDirectoryString"));
		userList.getColumns().add((TableColumn<User, String>) EncryptedDirColumn);

		userList.setItems(data);
	}
/** logs in the currently selected user.
 * 
 * @param event
 * @throws Exception 
 */
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) throws Exception {
		actiontarget1.setText("Sign in button pressed");
		try {
			if (application.loginUser(userList.getSelectionModel().getSelectedItem().getName(), passwordField.getText())) {
				application.setCurrentUserFromString(userList.getSelectionModel().getSelectedItem().getName());
				application.gotoMainPage();
			} else {
				actiontarget1.setText("Login Failed.");
				ExceptionWarningBox warningbox = new ExceptionWarningBox();
				warningbox.Dialog("Password incorrect!", "Ok");
			}
		} catch (Exception e) {
			actiontarget1.setText("Login method failed");
			ExceptionWarningBox warningbox = new ExceptionWarningBox();
			warningbox.Dialog("The Login failed possibly due to the configuration files being corrupted or no longer existing.", "Ok ");
		}

	}
//TODO: stop login button from activating if no user selected or no password input.
	@FXML
	protected void handleNewUserButton(ActionEvent event) {
		application.gotoNewUserPage();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
