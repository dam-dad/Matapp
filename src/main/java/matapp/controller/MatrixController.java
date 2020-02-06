package matapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.ejml.simple.SimpleMatrix;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import matapp.matrices.componente.Matriz;

public class MatrixController implements Initializable {

	
	int i;
	Matriz m = new Matriz();
	
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
    private ListView<Object> resultBox;
    
    @FXML
    private Button resultButton;
    
    @FXML
    private Button moveMatrixButton;

    public MatrixController() {
		 try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/CalculadoraMatricesView.fxml"));
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
    	rowSpinner.valueProperty().addListener((o, nv, ov)->NewMatrixDimension());
    	columnSpinner.valueProperty().addListener((o, nv, ov)->NewMatrixDimension());
    	resultButton.setDisable(true);
    	resultButton.setOnAction(e->onResultAction());
    	moveMatrixButton.setDisable(true);
    	resultBox.selectionModelProperty().addListener((o, nv, ov)->{
    		moveMatrixButton.setDisable(false);
    	});
    
	}
    
    @FXML
    void onMoveMatrixAction(ActionEvent event) {
    	
    }
    
    private void onResultAction() {
    	resultButton.setDisable(true);
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	resultBox.getItems().add(0,mat);
    	Matriz mr = new Matriz();
		if(i ==1) {
			try {
				SimpleMatrix resul = m.getMa().plus(mat.getMa());
		    	mr.setMa(resul);
			}catch(java.lang.IllegalArgumentException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al sumar");
				alert.setContentText("No se puden sumar dos matrices que tengan distintas dimenciones");
				alert.showAndWait();
			}
		}else if(i == 2) {
			try {
				//No funciona
				SimpleMatrix resul = m.getMa().plus(-1, mat.getMa());
				System.out.println(resul);
		    	mr.setMa(resul);
			}catch(java.lang.IllegalArgumentException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al Restar");
				alert.setContentText("No se puden restar dos matrices que tengan distintas dimenciones");
				alert.showAndWait();
			}
		}else if(i ==4) {
			//no funciona
			SimpleMatrix resul = m.getMa().mult(mat.getMa());
	    	mr.setMa(resul);
		}else {
			//No se como hacer que no crezca
	    	columnSpinner.setDisable(true);
			rowSpinner.setDisable(true);
			matrixOperador.setMa(new SimpleMatrix(1, 1));
			
		}
		resultBox.getItems().add(0,"=");
    	resultBox.getItems().add(0,mr);
    	matrixOperador.setMa(mr.getMa());
	}

	private void NewMatrixDimension() {
		matrixOperador.setMa(new SimpleMatrix(rowSpinner.getValue(), columnSpinner.getValue()));
	}

	@FXML
    void onAddtionAction(ActionEvent event) {
		m.setMa(matrixOperador.getMa());
    	resultBox.getItems().add(0,m);
    	resultBox.getItems().add(0," + ");
    	resultButton.setDisable(false);
    	i =1;
    	
    }
	@FXML
    void onSubtrectionAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getItems().add(0,m);
    	resultBox.getItems().add(0," - ");
    	resultButton.setDisable(false);
    	i =2;
    	
    }
	

    @FXML
    void onDivideAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getItems().add(0,m);
    	resultBox.getItems().add(0," / ");
    	resultButton.setDisable(false);
    	i =3;
    }
    

    @FXML
    void onMultiplyAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getItems().add(0,m);
    	resultBox.getItems().add(0," * ");
    	resultButton.setDisable(false);
    	i =3;
    }

    @FXML
    void onDeterminantAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	double resulDeterminat = matrixOperador.getMa().determinant();
    	resultBox.getItems().add(0,new Label("|A| : "+resulDeterminat));
    }

    @FXML
    void onDiagonalAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().extractDiag();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getItems().add(0,new HBox(new Label("Diagonal : "),mr));
    	
    }


    @FXML
    void onInverseAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().invert();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getItems().add(0,new HBox(new Label("A^-1 : "), mr));
    }

    @FXML
    void onOrthogonalAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
       	SimpleMatrix maResult = matrixOperador.getMa().transpose();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getItems().add(0,new HBox(new Label("A^t : "),mr));
    }

    @FXML
    void onRangeAction(ActionEvent event) {
    }


    @FXML
    void onVectorAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	boolean vector = matrixOperador.getMa().isVector();
    	if(vector) {
    		resultBox.getItems().add(0,new HBox(mat,new Label("  Es Vector")));
    	}else {
    		resultBox.getItems().add(0,new HBox(mat,new Label("  No es Vector")));
    	}
    	
    }

	public HBox getRoot() {
		return root;
	}

	public void setRoot(HBox root) {
		this.root = root;
	}

	

}
