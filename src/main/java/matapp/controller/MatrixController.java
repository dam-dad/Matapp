package matapp.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.ejml.simple.SimpleMatrix;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import matapp.matrices.componente.Matriz;
import matapp.utils.FormulaUtils;

public class MatrixController implements Initializable {

	
	int i;
	Matriz m = new Matriz();
	
	//View
    @FXML
    private HBox root;

    @FXML
    private Matriz matrixOperador;


    @FXML
    private VBox resultBox;
    
    @FXML
    private JFXButton resultButton;
    

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
    	resultButton.setDisable(true);
    	
    //	matrixToImage(new SimpleMatrix(new double[][] {{2,3},{4,5}}) ) ;
    
	}
    public Image matrixToImage(SimpleMatrix matrixImage) {
    	Image resu= null;
    	String matrixIntroduce = "\\begin{Bmatrix}\n";
    	
    	for(int j =0;j<matrixImage.numRows();j++) {
    	    for(int i=0;i<matrixImage.numCols();i++) {
    			matrixIntroduce = matrixIntroduce + matrixImage.get(j, i);
    			if(i<matrixImage.numCols()-1) {
    				matrixIntroduce = matrixIntroduce+" & ";
    			}
    			
    		}	
    	    if(j<matrixImage.numRows()-1) {
    	    	matrixIntroduce = matrixIntroduce+"\\\\\n";
    	    }else {
    	    	matrixIntroduce = matrixIntroduce+"\n";
    	    }
 
    	}
    
    	try {
			resu =FormulaUtils.formulaToImage(matrixIntroduce + 
					"\\end{Bmatrix}",10, Color.black);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return resu;
    }
    
    
    @FXML
    void onResultAction(ActionEvent event) {
    	//resultButton.setDisable(true);
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	resultBox.getChildren().add(0, new ImageView(matrixToImage(mat.getMa())));
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
//	    	columnSpinner.setDisable(true);
//			rowSpinner.setDisable(true);
			matrixOperador.setMa(new SimpleMatrix(1, 1));
			
		}
		resultBox.getChildren().add(0,new Label("="));
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(mr.getMa())));
    	matrixOperador.setMa(mr.getMa());
	}

	private void NewMatrixDimension() {
//		matrixOperador.setMa(new SimpleMatrix(rowSpinner.getValue(), columnSpinner.getValue()));
	}

	@FXML
    void onAddtionAction(ActionEvent event) {
		m.setMa(matrixOperador.getMa());
    	resultBox.getChildren().add(0, new ImageView(matrixToImage(m.getMa())));
    	resultBox.getChildren().add(0,new Label(" + "));
    	resultButton.setDisable(false);
    	i =1;
    	
    }
	@FXML
    void onSubtrectionAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m.getMa())));
    	resultBox.getChildren().add(0,new Label(" - "));
    	resultButton.setDisable(false);
    	i =2;
    	
    }
	

    @FXML
    void onDivideAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m.getMa())));
    	resultBox.getChildren().add(0,new Label(" / "));
    	resultButton.setDisable(false);
    	i =3;
    }
    

    @FXML
    void onMultiplyAction(ActionEvent event) {
    	m.setMa(matrixOperador.getMa());
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m.getMa())));
    	resultBox.getChildren().add(0,new Label(" * "));
    	resultButton.setDisable(false);
    	i =3;
    }

    @FXML
    void onDeterminantAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	double resulDeterminat = matrixOperador.getMa().determinant();
    	resultBox.getChildren().add(0,new Label("|A| : "+resulDeterminat));
    }

    @FXML
    void onDiagonalAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().extractDiag();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(0,new HBox(new Label("Diagonal : "),mr));
    	
    }


    @FXML
    void onInverseAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().invert();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(0,new HBox(new Label("A^-1 : "), new ImageView(matrixToImage(maResult))));
    }

    @FXML
    void onOrthogonalAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
       	SimpleMatrix maResult = matrixOperador.getMa().transpose();
    	Matriz mr = new Matriz();
    	mr.setMa(maResult);
    	resultBox.getChildren().add(0,new HBox(new Label("A^t : "),new ImageView(matrixToImage(maResult))));
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
    		resultBox.getChildren().add(0,new HBox(new ImageView(matrixToImage(mat.getMa())),new Label("  Es Vector")));
    	}else {
    		resultBox.getChildren().add(0,new HBox(new ImageView(matrixToImage(mat.getMa())),new Label("  No es Vector")));
    	}
    	
    }
    @FXML
    void onLessColumnButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows(), matrixOperador.getMa().numCols()-1);
    	matrixOperador.setMa(maR);
    	
    }

    @FXML
    void onLessRowButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows()-1, matrixOperador.getMa().numCols());
    	matrixOperador.setMa(maR);
    }

    @FXML
    void onMoreColumnButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows(), matrixOperador.getMa().numCols()+1);
    	matrixOperador.setMa(maR);
    }

    @FXML
    void onMoreRowButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows()+1, matrixOperador.getMa().numCols());
    	matrixOperador.setMa(maR);
    }

	public HBox getRoot() {
		return root;
	}

	public void setRoot(HBox root) {
		this.root = root;
	}

	

}
