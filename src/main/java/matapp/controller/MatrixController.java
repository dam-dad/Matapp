package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.ejml.simple.SimpleMatrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import matapp.matrices.componente.Matriz;

public class MatrixController implements Initializable {

	//Model
	
	
	//View
    @FXML
    private HBox root;

    @FXML
    private Matriz matrixOperador;

    @FXML
    private Spinner<Integer> rowSpinner;

    @FXML
    private Spinner<Integer> columnSpinner;

    @FXML
    private VBox resultBox;

    public MatrixController() {
		 try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalculadoraMatricesView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	matrixOperador.setEdit(true);
    	rowSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1 , 100,1));
    	columnSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1 , 100,1));
    	//Bindear o hacer listener para que los numeros de filas y columnas de la matriz sean como los que marcan los Spinner
    	rowSpinner.valueProperty().addListener((o, nv, ov)->NewMatrixDimension());
    	columnSpinner.valueProperty().addListener((o, nv, ov)->NewMatrixDimension());
	}
    
    
    private void NewMatrixDimension() {
		matrixOperador.setMa(new SimpleMatrix(rowSpinner.getValue(), columnSpinner.getValue()));
	}

	@FXML
    void onAddtionAction(ActionEvent event) {

    }

    @FXML
    void onDeterminantAction(ActionEvent event) {
    	Matriz m = new Matriz();
    	m.setMa(matrixOperador.getMa());
    	m.setEdit(false);
    	resultBox.getChildren().add(m);
    	double resulDeterminat = matrixOperador.getMa().determinant();
    	resultBox.getChildren().add(new Label("|A| : "+resulDeterminat));
    }

    @FXML
    void onDiagonalAction(ActionEvent event) {
    	Matriz m = new Matriz();
    	m.setMa(matrixOperador.getMa());
    	m.setEdit(false);
    	resultBox.getChildren().add(m);
    	SimpleMatrix maResult = matrixOperador.getMa().extractDiag();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(new HBox(new Label("Diagonal : "),mr));
    	
    }

    @FXML
    void onDivideAction(ActionEvent event) {

    }

    @FXML
    void onInverseAction(ActionEvent event) {
    	Matriz m = new Matriz();
    	m.setMa(matrixOperador.getMa());
    	m.setEdit(false);
    	resultBox.getChildren().add(m);
    	SimpleMatrix maResult = matrixOperador.getMa().invert();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(new HBox(new Label("A^-1 : "), mr));
    }

    @FXML
    void onMoveMatrixAction(ActionEvent event) {

    }

    @FXML
    void onMultiplyAction(ActionEvent event) {
    	
    }

    @FXML
    void onOrthogonalAction(ActionEvent event) {
    	Matriz m = new Matriz();
    	m.setMa(matrixOperador.getMa());
    	m.setEdit(false);
    	resultBox.getChildren().add(m);
    	SimpleMatrix maResult = matrixOperador.getMa().transpose();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(new HBox(new Label("A^t : "),mr));
    }

    @FXML
    void onRangeAction(ActionEvent event) {
    	
    }

    @FXML
    void onSubtrectionAction(ActionEvent event) {

    }

    @FXML
    void onVectorAction(ActionEvent event) {
    	Matriz m = new Matriz();
    	m.setMa(matrixOperador.getMa());
    	m.setEdit(false);
    	boolean vector = matrixOperador.getMa().isVector();
    	if(vector) {
    		resultBox.getChildren().add(new HBox(m,new Label("  Es Vector")));
    	}else {
    		resultBox.getChildren().add(new HBox(m,new Label("  No es Vector")));
    	}
    	
    }

	public HBox getRoot() {
		return root;
	}

	public void setRoot(HBox root) {
		this.root = root;
	}

	

}
