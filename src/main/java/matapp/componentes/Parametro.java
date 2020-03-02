package matapp.componentes;

import java.util.ArrayList;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Translate;
import matapp.expreval.Param;
import matapp.formulas.Formula;
import matapp.formulas.Variable;
import static matapp.expreval.ExprEval.*;

public class Parametro extends GridPane{
	/**
	 * @author Kilian González
	 * 
	 * Componente personalizado, nos permite mostrar todos los parametros de la formula
	 * para que así el usuario los rellene y lugo pueda calcular el resultado
	 * 
	 */
	
	//properties
	ListProperty<Label> nombreLabel=new SimpleListProperty<>(FXCollections.observableArrayList());//nombre fórmula
	ListProperty<JFXTextField> valorText=new SimpleListProperty<>(FXCollections.observableArrayList());//lista de jfxTexfields donde se introduciran los valores
	
	Formula formula;
	public Parametro(Formula formula) {
		super();
		setHgap(10);
		this.formula=formula;

		setAlignment(Pos.CENTER_LEFT);
		final NumberValidator numberValidator = new NumberValidator();
		
		JFXTextField auxTextField=null;
		Label auxLabel=null;
		
		//Recorremos todas las variables que posea la formula
		for(int i=0;i<formula.getVariables().size();i++) {
			auxTextField=new  JFXTextField();
			valorText.get().add(auxTextField);
			
			valorText.get().get(i).getValidators().add(numberValidator);//le añadios a cada una un validador
			int j=i;
			//añadimos un listener para establecer ciertos criterios del validador 
			valorText.get().get(i).setOnKeyReleased(e -> {
		        if (!valorText.get().get(j).getText().equals(""))
		        	valorText.get().get(j).validate();
		    });
			
			auxLabel=new Label(this.formula.getVariables().get(i).getName());
			auxLabel.setTooltip(new Tooltip(formula.getVariables().get(i).getDescripcion()));
			nombreLabel.add(auxLabel);
			
			//añadimos una fila por cada variable 
			addRow(i,nombreLabel.get().get(i),
					valorText.get().get(i),
					new Label(this.formula.getVariables().get(i).getMagnitud()));	 
		}
	}
	public String calcular() {
		
		try {
			ArrayList<Param<?>> params=new ArrayList<>();
			for (int i=0;i<formula.getVariables().size();i++) {
				params.add(param(formula.getVariables().get(i).getName(),Double.valueOf(valorText.get().get(i).getText())));
			}
			Param<?>[] auxParams= new Param<?>[params.size()];
			
			Double result = (Double) eval(formula.getExpression(), params.toArray(auxParams)); //LA E resultante es significa 10 elevado a...
			
			
			return result+"  "+formula.getResult().getMagnitud();
		} catch (NumberFormatException e) {
			return null;
		}
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
