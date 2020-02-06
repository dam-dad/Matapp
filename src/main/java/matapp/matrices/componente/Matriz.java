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

	
	private ObjectProperty<SimpleMatrix> ma = new SimpleObjectProperty<SimpleMatrix>(new SimpleMatrix(1, 1));//matriz que devuelve o recibe
	private BooleanProperty edit = new SimpleBooleanProperty();//Operacion con la mtriz
	
	public Matriz() {
		super();
		generarMatriz();
		ma.addListener((o, ov, nv)->generarTamanoGridPane());
		edit.addListener((o, ov, nv)->generarMatriz());
	}

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


	private void generarMatriz() {
		getChildren().clear();
		for(int i = 0; i< ma.get().numCols(); i++) {
			for(int j =0; j<ma.get().numRows() ; j++) {
				if (edit.get()) {
					final int col = i;
					final int row = j;
					TextField tf = new TextField(""+ma.get().get(j,i));
					tf.textProperty().addListener((o, ov, nv) -> onTextChanged(col, row, o, ov, nv));
					add(tf, i, j);
				}else {
					add(new Label(""+ma.get().get(j,i)), i, j);
					setGridLinesVisible(true);
				}
			}
		}
	}

	

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
	
}
