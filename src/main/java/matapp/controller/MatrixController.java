package matapp.controller;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.ejml.simple.SimpleMatrix;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import matapp.matrices.componente.Matriz;
import matapp.utils.FormulaUtils;

public class MatrixController implements Initializable {
	/**
	 * @author Andrea Morales
	 * 
	 *Controllador de la calculadora de Matrices
	 * 
	 */
	//View
    @FXML
    private BorderPane root;

    @FXML
    private Matriz matrixOperador;


    @FXML
    private VBox resultBox;
    
    @FXML
    private Button resultButton;
    
    @FXML
    private Button moreRowButton;

    @FXML
    private Button lessRowButton;

    @FXML
    private Button lessColumnButton;

    @FXML
    private Button moreColumnButton;
    
    @FXML
    private Button inverseButton;

    @FXML
    private Button orthogonalButton;

    @FXML
    private Button negativeButton;

    @FXML
    private Button determinantButton;


    @FXML
    private Button additionButton;

    @FXML
    private Button subtractionButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private Button diagonalButton;
    
    @FXML
    private Label isVectorLabel;
    
    @FXML
    private ScrollPane scrollMatrix;
    
    @FXML
    private ScrollPane scrollResult;
    
    //Variables a usar para poder tener dos matrices y la i es lo que nos indicara 
    //la operacion a realizar cuando se pulse el boton igual (+,-,*/)
	private int i;
	private SimpleMatrix m = new SimpleMatrix();
    
	/*Lo enlazamos con el fxml de la de donde esta la view de las matrices*/
    public MatrixController() {
		 try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CalculadoraMatricesView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /*bindeamos y generamos las imagenes que van a ir en los botones
     * ademas de poner los listener y deshabilitar el boton de resultado  que solo funcionara 
     * cuando haya algo que operar*/
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	
    	matrixOperador.setEdit(true);
    	resultButton.setDisable(true);
    	try {
			inverseButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "A^{-1}",15, Color.white)));
			orthogonalButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "A^{t}",15, Color.white)));
			negativeButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "-A",15, Color.white)));
			determinantButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "|A|",15, Color.white)));
			additionButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "+",15, Color.white)));
			subtractionButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "-",15, Color.white)));
			divideButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "/",15, Color.white)) );
			multiplyButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "*",15, Color.white)));
			resultButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "=",15, Color.white)));
			diagonalButton.setGraphic(new ImageView(FormulaUtils.formulaToImage( "diagonal",15, Color.white)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	matrixOperador.maProperty().addListener((o, ov, nv)->vectorFuncion());
    	//scrollbar
    	matrixOperador.prefWidthProperty().bind(scrollMatrix.widthProperty().subtract(50));
    	resultBox.prefWidthProperty().bind(scrollResult.widthProperty().subtract(50));
	}
    /*Machaca el label que nos va diciendo si la matriz que estamos introduciendo es un vector o no */
    private void vectorFuncion() {
    	if(matrixOperador.isVector()) {
    		isVectorLabel.setText("Es Vector");
    	}else {
    		isVectorLabel.setText("No es Vector");
    	}
	}
    /*Con esta funciones pasamos las matrices a Imagenes*/
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
					"\\end{Bmatrix}",10, Color.BLACK);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return resu;
    }
    
    /*El boton de resultado donde dependiendo de la operacion que se halla selecionado 
     * lo opera */
    @FXML
    void onResultAction(ActionEvent event) {
    	resultBox.getChildren().add(0, new ImageView(matrixToImage(matrixOperador.getMa())));
    	Matriz mr = new Matriz();

		if(i ==1) {
			//Suma
			try {
				SimpleMatrix resul = m.plus(matrixOperador.getMa());
		    	mr.setMa(resul);
			}catch(java.lang.IllegalArgumentException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al sumar");
				alert.setContentText("No se puden sumar dos matrices que tengan distintas dimenciones");
				alert.showAndWait();
			}
		}else if(i == 2) {
			//Resta
			try {
				SimpleMatrix resul = m.plus(matrixOperador.getMa().negative());
				
		    	mr.setMa(resul);
			}catch(java.lang.IllegalArgumentException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error al Restar");
				alert.setContentText("No se puden restar dos matrices que tengan distintas dimenciones");
				alert.showAndWait();
			}
		}else if(i ==4) {
			//Multiplicacion
			SimpleMatrix resul = m.mult(matrixOperador.getMa());
	    	mr.setMa(resul);
		}else {
			//Division
			SimpleMatrix resul = m.divide(matrixOperador.getMa().get(0, 0));
			mr.setMa(resul);
			lessColumnButton.setDisable(false);
	    	lessRowButton.setDisable(false);
	    	moreColumnButton.setDisable(false);
	    	moreRowButton.setDisable(false);
		
		}
		resultButton.setDisable(true);
		resultBox.getChildren().add(0,new Label("="));
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(mr.getMa())));
    	resultBox.getChildren().add(0, new Separator());
    	matrixOperador.setMa(mr.getMa());
    	Platform.runLater(scrollResult::requestLayout);
	}

    /*Boton de sumar, donde  activa el boton de resultado y añade al VBox de los resultados 
     * la matriz a operar y el signo de la operacion */
	@FXML
    void onAddtionAction(ActionEvent event) {
		m = matrixOperador.getMa().copy();
		resultBox.getChildren().add(0, new ImageView(matrixToImage(m)));
    	resultBox.getChildren().add(0,new Label(" + "));
    	resultButton.setDisable(false);
    	i =1;
    	Platform.runLater(scrollResult::requestLayout);
    	
    }
	 /*Boton de restar, donde  activa el boton de resultado y añade al VBox de los resultados 
     * la matriz a operar y el signo de la operacion */
	@FXML
    void onSubtrectionAction(ActionEvent event) {
    	m=matrixOperador.getMa().copy();
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m)));
    	resultBox.getChildren().add(0,new Label(" - "));
    	resultButton.setDisable(false);
    	i =2;
    	Platform.runLater(scrollResult::requestLayout);
    	
    }
	
	 /*Boton de dividir, donde  activa el boton de resultado y añade al VBox de los resultados 
     * la matriz a operar y el signo de la operacion */
    @FXML
    void onDivideAction(ActionEvent event) {
    	m=matrixOperador.getMa().copy();
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m)));
    	resultBox.getChildren().add(0,new Label(" / "));
    	resultButton.setDisable(false);
    	i =3;
    	matrixOperador.setMa(new SimpleMatrix(1, 1));
    	lessColumnButton.setDisable(true);
    	lessRowButton.setDisable(true);
    	moreColumnButton.setDisable(true);
    	moreRowButton.setDisable(true);
    	Platform.runLater(scrollResult::requestLayout);
    }
    
    /*Boton de multiplicar, donde  activa el boton de resultado y añade al VBox de los resultados 
     * la matriz a operar y el signo de la operacion */
    @FXML
    void onMultiplyAction(ActionEvent event) {
    	m=matrixOperador.getMa().copy();
    	resultBox.getChildren().add(0,new ImageView(matrixToImage(m)));
    	resultBox.getChildren().add(0,new Label(" * "));
    	resultButton.setDisable(false);
    	i =3;

    	Platform.runLater(scrollResult::requestLayout);
    }
    /*Aquí cogemos la matriz del operdor y hallamos su determinante, aniadiendolo a 
     * el VBox de los resultados*/
    @FXML
    void onDeterminantAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	double resulDeterminat = matrixOperador.getMa().determinant();
    	resultBox.getChildren().add(0,new Label("|A| : "+resulDeterminat));
    	resultBox.getChildren().add(0, new Separator());

    	Platform.runLater(scrollResult::requestLayout);
    }
    /*Halla el derterminante de la matriz y lo aniade en el VBox del resultado*/
    @FXML
    void onDiagonalAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().extractDiag();  	
    	resultBox.getChildren().add(0,new HBox(new Label("Diagonal : "),new ImageView(matrixToImage(maResult))));
    	resultBox.getChildren().add(0, new Separator());

    	Platform.runLater(scrollResult::requestLayout);

    }

    /*Calcula la inversa de la matriz*/
    @FXML
    void onInverseAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
    	SimpleMatrix maResult = matrixOperador.getMa().invert();
    	resultBox.getChildren().add(0,new HBox(new Label("A^-1 : "), new ImageView(matrixToImage(maResult))));
    	resultBox.getChildren().add(0, new Separator());

    	Platform.runLater(scrollResult::requestLayout);

    }
    /*Calcula la matriz traspuesta*/
    @FXML
    void onOrthogonalAction(ActionEvent event) {
		 try {
    		Matriz mat = new Matriz();
			mat.setMa(matrixOperador.getMa());
			mat.setEdit(false);
			SimpleMatrix maResult = matrixOperador.getMa().transpose();
			resultBox.getChildren().add(0,new HBox(new Label("A^t : "),new ImageView(matrixToImage(maResult))));
			resultBox.getChildren().add(0, new Separator());
	
			Platform.runLater(scrollResult::requestLayout);
		 }catch(java.lang.RuntimeException e) {}
    }
    /*Halla matriz negativa de la matriz operados*/
    @FXML
    void onNegativeAction(ActionEvent event) {
    	Matriz mat = new Matriz();
    	mat.setMa(matrixOperador.getMa());
    	mat.setEdit(false);
       	SimpleMatrix maResult = matrixOperador.getMa().negative();
    	resultBox.getChildren().add(0 , new ImageView(matrixToImage(maResult)));
    	resultBox.getChildren().add(0, new Separator());

    	Platform.runLater(scrollResult::requestLayout);

    }

    /*Boton para reducir el numero de columnas de la matriz operador*/
    @FXML
    void onLessColumnButtonAction(ActionEvent event) {
    	if( matrixOperador.getMa().numCols()>1) {
    		SimpleMatrix maR = matrixOperador.getMa();
    		maR = new SimpleMatrix(matrixOperador.getMa().numRows(), matrixOperador.getMa().numCols()-1);
        	matrixOperador.setMa(maR);
    	}

    	Platform.runLater(scrollMatrix::requestLayout);
  
    }
    /*Boton para reducir el numero de filas de la matriz operador*/
    @FXML
    void onLessRowButtonAction(ActionEvent event) {
    	if(matrixOperador.getMa().numRows()>1) {
    		SimpleMatrix maR = matrixOperador.getMa();
        	maR = new SimpleMatrix(matrixOperador.getMa().numRows()-1, matrixOperador.getMa().numCols());
        	matrixOperador.setMa(maR);
    	}

    	Platform.runLater(scrollMatrix::requestLayout);
    
    }
    /*Boton para ampliar el numero de columnas de la matriz operador*/
    @FXML
    void onMoreColumnButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows(), matrixOperador.getMa().numCols()+1);
    	matrixOperador.setMa(maR);
    	Platform.runLater(scrollMatrix::requestLayout);
    }
    /*Boton para ampliar el numero de filas de la matriz operador*/
    @FXML
    void onMoreRowButtonAction(ActionEvent event) {
    	SimpleMatrix maR = matrixOperador.getMa();
    	maR = new SimpleMatrix(matrixOperador.getMa().numRows()+1, matrixOperador.getMa().numCols());
    	matrixOperador.setMa(maR);
    	Platform.runLater(scrollMatrix::requestLayout);
    }
    /*Boton que limpia la matriz*/
    @FXML
    void onClearButton(ActionEvent event) {
    	matrixOperador.setMa(new SimpleMatrix(1,1));
    }
    /*Boton de limpieza de la matriz y el cuadro de resultados*/
    @FXML
    void onClearAllButton(ActionEvent event) {
    	matrixOperador.setMa(new SimpleMatrix(1,1));
    	resultBox.getChildren().clear();
    }
	public BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	

}
