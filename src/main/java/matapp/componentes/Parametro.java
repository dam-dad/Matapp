package matapp.componentes;

import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import matapp.expreval.Param;
import matapp.formulas.Formula;
import matapp.formulas.Variable;
import static matapp.expreval.ExprEval.*;

public class Parametro extends GridPane{
	
	ListProperty<JFXTextField> valorText=new SimpleListProperty<>(FXCollections.observableArrayList());
	Formula formula;
	public Parametro(Formula formula) {
		super();
		setHgap(10);
		this.formula=formula;

		setAlignment(Pos.CENTER_LEFT);
		JFXTextField auxTextField=null;
		for(int i=0;i<formula.getVariables().size();i++) {
			auxTextField=new  JFXTextField();
			valorText.get().add(auxTextField);
			addRow(i, new Label(this.formula.getVariables().get(i).getName()),
					valorText.get().get(i),
					new Label(this.formula.getVariables().get(i).getMagnitud()));
		}
		
	}
	public double calcular() {
		
		ArrayList<Param<?>> params=new ArrayList<>();
		for (int i=0;i<formula.getVariables().size();i++) {
			params.add(param(formula.getVariables().get(i).getName(),Double.valueOf(valorText.get().get(i).getText())));
		}
		Param<?>[] auxParams= new Param<?>[params.size()];
		
		Double result = (Double) eval(formula.getExpression(), params.toArray(auxParams)); //LA E resultante es significa 10 elevado a...
		
		
		return result;
	}
	
	
	
	public final ListProperty<JFXTextField> valorTextProperty() {
		return this.valorText;
	}
	
	public final ObservableList<JFXTextField> getValorText() {
		return this.valorTextProperty().get();
	}
	
	public final void setValorText(final ObservableList<JFXTextField> valorText) {
		this.valorTextProperty().set(valorText);
	}
	
		

	

}
