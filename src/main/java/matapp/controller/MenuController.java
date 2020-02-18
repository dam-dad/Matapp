package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable{
	//MenuView
	@FXML
    private BorderPane root;

    @FXML
    private JFXHamburger menuHamburger;

    @FXML
    private JFXDrawer menuDrawer;
    
    
    //SlidePaneMenuView //no funciona, no puede acceder a dichos id 

    @FXML
    private JFXButton estandarButton;

    @FXML
    private JFXButton binariaButton;

    @FXML
    private JFXButton fisicaButton;

    @FXML
    private JFXButton matricesButton;

    @FXML
    private JFXButton notaButton;
    
    //controllers
    private SlidePaneMenuController slidePaneMenuController; 
    
    private FisicaMainController fisicaMainController;
    
    private MatrixController matrizController;
    
    private BinarioController binarioController;
    
    private CalculadoraBasicaController basicaController;
    
    public MenuController() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }    	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//controllers
		slidePaneMenuController=new SlidePaneMenuController();
		fisicaMainController=new FisicaMainController();
		matrizController = new MatrixController();
		binarioController= new BinarioController();
		basicaController = new CalculadoraBasicaController();
		
		
		root.setCenter(basicaController.getRoot());
		
		
		VBox vBox=slidePaneMenuController.getRoot();
		menuDrawer.setSidePane(vBox);
		/*try {
//			basicamente cargamos otro fxml en el vBox el cual usaremos luego en nuestro drawer
			VBox vBox = FXMLLoader.load(getClass().getResource("/fxml/SlidePaneMenuView.fxml"));
			menuDrawer.setSidePane(vBox);//aquí le asignamos el contenido a drawer
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		//funciones del drawer
		HamburgerBackArrowBasicTransition transiction=new HamburgerBackArrowBasicTransition(menuHamburger);
		transiction.setRate(-1);
		menuHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e->{
				transiction.setRate(transiction.getRate()*-1);				
				transiction.play();
				 
				if(menuDrawer.isOpened()) {
					menuDrawer.close();//se cierra
					menuDrawer.setPrefWidth(10);//revisar intento que la parte izquierda del border pane se encoja cuando cierro el drawer
//					tamanio(true);
				}else {
					menuDrawer.open();//se abrirá
					menuDrawer.setPrefWidth(150);
				}
		});
		//actions
		slidePaneMenuController.getEstandarButton().setOnAction(e->onEstandarButton());
		slidePaneMenuController.getFisicaButton().setOnAction(e->onFisicaButton());
		slidePaneMenuController.getMatricesButton().setOnAction(e->onMatrizButton());
		slidePaneMenuController.getBinariaButton().setOnAction(e->onBinarioButton());
	}
	/*private void tamanio(boolean encoger) {
		double aux;
		if (encoger) {
			while((aux=menuDrawer.getPrefWidth())>10)
				menuDrawer.setPrefWidth(--aux);
		}
	}*/
	
	private void onMatrizButton() {
		root.setCenter(matrizController.getRoot());
	}
	private void onBinarioButton() {
		root.setCenter(binarioController.getRoot());
	}

	public BorderPane getRoot() {
		return root;
	}
	
	private void onEstandarButton() {//aquí nos encargaríamos de que se mostrase la calculadora especificada
		
		root.setCenter(basicaController.getRoot());// aqui colocamos la calculadora esperada
		
	}
	private void onFisicaButton() {
		root.setCenter(fisicaMainController.getRoot());
		
	}

}
