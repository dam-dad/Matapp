package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class SlidePaneMenuController implements Initializable{
	/**
	 *
	 * @author Kilian González
	 * 
	 * Componente desplegable SlidePane el cual contendrá la lista de calculadoras que posee Mataap
	 * 
	 */
    //View

	@FXML
    private VBox root;

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

    
    public SlidePaneMenuController() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SlidePaneMenuView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }    	
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public VBox getRoot() {
		return root;
	}

	public JFXButton getEstandarButton() {
		return estandarButton;
	}

	public void setEstandarButton(JFXButton estandarButton) {
		this.estandarButton = estandarButton;
	}

	public JFXButton getBinariaButton() {
		return binariaButton;
	}

	public void setBinariaButton(JFXButton binariaButton) {
		this.binariaButton = binariaButton;
	}

	public JFXButton getFisicaButton() {
		return fisicaButton;
	}

	public void setFisicaButton(JFXButton fisicaButton) {
		this.fisicaButton = fisicaButton;
	}

	public JFXButton getMatricesButton() {
		return matricesButton;
	}

	public void setMatricesButton(JFXButton matricesButton) {
		this.matricesButton = matricesButton;
	}

	public JFXButton getNotaButton() {
		return notaButton;
	}

	public void setNotaButton(JFXButton notaButton) {
		this.notaButton = notaButton;
	}

	public void setRoot(VBox root) {
		this.root = root;
	}

}
