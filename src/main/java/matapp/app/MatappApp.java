package matapp.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import matapp.controller.MenuController;

public class MatappApp extends Application {
	MenuController menuController;
	@Override
	public void start(Stage primaryStage) throws Exception {
		menuController=new MenuController();
		
		Scene scene=new Scene(menuController.getRoot());
		primaryStage.setTitle("Matapphuvuhviu");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
