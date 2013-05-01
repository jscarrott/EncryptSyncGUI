package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

public class NewUserController extends AnchorPane implements Initializable {
	
	/**handles the FXML for the new user page, generating a new user from the fields supplied.
	 * 
	 */

	private EncryptSyncGui application;
	private String UnencryptedDirectory;
	private String EncryptedDirectory;

	@FXML
	private TextField nameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button createProfileButton;

	void setApp(EncryptSyncGui application) {
		this.application = application;
	}

	/** Calls a Directory chooser to select a directory checks if all fields are filled and removes transparency from create button if they are.
	 * 
	 * @param event Button press
	 */
	@FXML
	protected void SelectUnencryptedDirectory(ActionEvent event) {
		try {
			DirectoryChooser fc = new DirectoryChooser();
			fc.setTitle("Open Dialog");
			String currentDir = System.getProperty("user.dir") + File.separator;
			File file = new File(currentDir);
			fc.setInitialDirectory(file);
			UnencryptedDirectory = fc.showDialog(null).toString();
			// TODO Catch exception from canceling the dialog, as well as
			// handling the null string created.
		} catch (Exception e) {

		}
		if (checkAllFieldsFilled())
			createProfileButton.setOpacity(1);

	}
	/** Calls a Directory chooser to select a directory checks if all fields are filled and removes transparency from create button if they are.
	 * 
	 * @param event Button press
	 */
	@FXML
	protected void SelectEncryptedDirectory(ActionEvent event) {
		try {
			DirectoryChooser fc = new DirectoryChooser();
			fc.setTitle("Open Dialog");
			String currentDir = System.getProperty("user.dir") + File.separator;
			File file = new File(currentDir);
			fc.setInitialDirectory(file);
			EncryptedDirectory = fc.showDialog(null).toString();
			// TODO Catch exception from canceling the dialog, as well as
			// handling the null string created.
		} catch (Exception e) {

		}
		if (checkAllFieldsFilled())
			createProfileButton.setOpacity(1);

	}
/** Creates a new user using the required method then saves it to the config file and set the current user profile. Then changes the page to the main page.
 * 
 * @param event button press #HandleCreateProfileButton 
 */
	@FXML
	protected void HandleCreateProfileButton(ActionEvent event) {
		if (checkAllFieldsFilled()) {
			application.createNewUser(nameField.getText(), passwordField.getText(), UnencryptedDirectory, EncryptedDirectory);
			application.writeProfiles();
			application.setCurrentUserFromString(nameField.getText());
			application.gotoMainPage();
		}
	}
/** checks whether all fields are filled returns true if they are.
 * 
 * @return returns true if all fields are filled
 */
	boolean checkAllFieldsFilled() {
		if (nameField.getText().equals(null))
			return false;
		if (passwordField.getText().equals(null))
			return false;
		try {
			if (UnencryptedDirectory.equals(null))
				return false;
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (EncryptedDirectory.equals(null))
				return false;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
