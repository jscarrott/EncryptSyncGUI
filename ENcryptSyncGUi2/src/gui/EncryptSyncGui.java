package gui;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.NoSuchPaddingException;

import core.CoordinatingClass;
import core.Encryptor;
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
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(EncryptSyncGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	 void gotoMainPage() {
        try {
            LoginController login = (LoginController) replaceSceneContent("MainPage.fxml");
           // login.setApp(this);
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
	
	
	
	void loginUser(String user, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException{
		coordClass.addNewUser("Bob Harold", "C:\\Users\\Home\\git\\EncryptSync\\EncryptSync\\testIn", "C:\\Users\\Home\\git\\EncryptSync\\EncryptSync\\testOut") ;
		coordClass.saveUserListToFile();
	}
}
