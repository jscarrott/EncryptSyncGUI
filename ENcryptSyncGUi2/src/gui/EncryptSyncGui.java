package gui;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.NoSuchPaddingException;

import core.CoordinatingClass;
import core.Encryptor;
import core.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EncryptSyncGui extends Application {
	
	private core.CoordinatingClass coordClass;
	private Stage stage;
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

	@Override
	 public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
        stage.setTitle("FXML Login Sample");
        stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
        stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
        gotoLogin();
        primaryStage.show();
	    }
	
	public EncryptSyncGui() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {
		coordClass = new core.CoordinatingClass();
	}

	public static void main(String[] args) {
		Application.launch(EncryptSyncGui.class);
		
		
	}
	
	private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("LoginPage.fxml");
            //login.initialize(null, null);
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EncryptSyncGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	 void gotoMainPage() {
        try {
            MainPageController mainPage = (MainPageController) replaceSceneContent("MainPage.fxml");
            //mainPage.initialize(null, null);
            mainPage.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EncryptSyncGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	 void gotoNewUserPage() {
	        try {
	           NewUserController newUser = (NewUserController) replaceSceneContent("NewUserPage.fxml");
	          // newUser.initialize(null, null);
	            newUser.setApp(this);
	        } catch (Exception ex) {
	            Logger.getLogger(EncryptSyncGui.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	
	private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = EncryptSyncGui.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(EncryptSyncGui.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 800, 600);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }
	
	void createNewUser(String name, String password, String unencryptedDirectory, String encryptedDirectory){
		try {
			coordClass.addNewUser(name, unencryptedDirectory, encryptedDirectory, password);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	void writeProfiles(){
		try {
			coordClass.saveUserListToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	boolean loginUser(String user, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException{
		if(coordClass.loginUser(user, password)) return true;
		return false;
	}
	
	/*ArrayList<String> getUsers(){
		ArrayList<String> data = new ArrayList<String>();
		List<User> userList = coordClass.getUsers();
		for(int counter = 0; counter < userList.size(); counter++){
			data.add(userList.get(counter).toString());
		}
		return data;
	}*/
	
	List<User> getUsers(){
		return coordClass.getUsers();
	}
	
}
