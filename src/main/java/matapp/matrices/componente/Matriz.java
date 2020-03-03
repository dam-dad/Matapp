package matapp.matrices.componente;

import org.ejml.simple.SimpleMatrix;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Matriz extends GridPane{
	/**
	 * @author Andrea Morales
	 * 
	 *Componente Matriz donde meteremos nuestra matriz pa despues operarla
	 * 
	 */
	
	//Modelo
	private ObjectProperty<SimpleMatrix> ma = new SimpleObjectProperty<SimpleMatrix>(new SimpleMatrix(1, 1));//matriz que devuelve o recibe
	private BooleanProperty edit = new SimpleBooleanProperty();//Operacion con la mtriz
	private BooleanProperty vector = new SimpleBooleanProperty();

	//Constructor donde realizamos los listener y las separaciones
	public Matriz() {
		super();	
		setHgap(5);
		setVgap(5);
		setGridLinesVisible(true);
		generarMatriz();
		ma.addListener((o, ov, nv)->generarTamanoGridPane());
		edit.addListener((o, ov, nv)->generarMatriz());
		ma.addListener((o, ov, nv)->vectorValue());
		
	}

	//Funcion que cons calcula si la matriz en un vector o no y se lo aniade a la property booleana de vector
	private void vectorValue(){vector.setValue(ma.getValue().isVector());}

	//Funcion que genera el GridPane que es el componente dependiendo del tamaño que se le asigne a la matriz
	private void generarTamanoGridPane() {
		getColumnConstraints().clear();
		for (int cols = 0; cols < ma.get().numCols(); cols++) {
			getColumnConstraints().add(new ColumnConstraints());
		}
		
		getRowConstraints().clear();
		for (int rows = 0; rows < ma.get().numRows(); rows++) {
			getRowConstraints().add(new RowConstraints());
		}
		generarMatriz();
		
	}

	//Rellena la matriz de label o de TextField dependiendo del tipo de matriz que sea (editable o no )
	private void generarMatriz() {
		getChildren().clear();
		for(int i = 0; i< ma.get().numCols(); i++) {
			for(int j =0; j<ma.get().numRows() ; j++) {
				if (edit.get()) {
					final int col = i;
					final int row = j;
					TextField tf = new TextField(""+ma.get().get(j,i));
					tf.setMinWidth(80);
					tf.setMaxWidth(80);
					tf.textProperty().addListener((o, ov, nv) -> onTextChanged(col, row, o, ov, nv));
					add(tf, i, j);
				}else {
					Label labe = new Label(""+ma.get().get(j,i));
					labe.setMaxWidth(80);
					labe.setMinWidth(80);
					add(labe, i, j);
					setGridLinesVisible(true);
				}
			}
		}

		
	}

	
	//Captura los errores si el numero introducido en el textField del componente no es double desde el principio 
	private void onTextChanged(int col, int row, ObservableValue<? extends String> o, String ov, String nv) {
		
		try {
			
			ma.get().set(row, col, Double.parseDouble(nv));
			
		}catch(NumberFormatException e) {}
	}


	public final ObjectProperty<SimpleMatrix> maProperty() {
		return this.ma;
	}
	


	public final SimpleMatrix getMa() {
		return this.maProperty().get();
	}
	


	public final void setMa(final SimpleMatrix ma) {
		this.maProperty().set(ma);
	}
	


	public final BooleanProperty editProperty() {
		return this.edit;
	}
	


	public final boolean isEdit() {
		return this.editProperty().get();
	}
	


	public final void setEdit(final boolean edit) {
		this.editProperty().set(edit);
	}

	public final BooleanProperty vectorProperty() {
		return this.vector;
	}
	

	public final boolean isVector() {
		return this.vectorProperty().get();
	}
	

	public final void setVector(final boolean vector) {
		this.vectorProperty().set(vector);
	}
	
	
}
