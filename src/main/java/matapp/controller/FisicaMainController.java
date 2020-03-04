package matapp.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import matapp.componentes.FormulaVista;
import matapp.formulas.Category;
import matapp.formulas.Formula;
import matapp.formulas.FormulaList;

public class FisicaMainController implements Initializable{
	/**
	 * @author Kilian González
	 * 
	 * Menú inicial de la calculadora física
	 * 
	 */
    //View
    @FXML
    private VBox root;
    
    @FXML
    private JFXMasonryPane contenedorFormulasMasonry;

    @FXML
    private JFXListView<Category> categoriasList;
    
    @FXML
    private ScrollPane scroll;
    
//	model
    ObjectProperty<FormulaList> formulaListObject=new SimpleObjectProperty<>();
    
    ListProperty<Category> categorias=new SimpleListProperty<>();
    
    ObjectProperty<Category> categoriaSeleccionada=new SimpleObjectProperty<>();
    
    ListProperty<Formula> formulas=new SimpleListProperty<>();
    
    ObjectProperty<Formula> formulaSeleccionada=new SimpleObjectProperty<>();
    
    //
    MenuController menuController;
    
    
    ArrayList <FormulaVista> componentesFormulaVista=new ArrayList<>();
    
    FisicaFormulaController fisicaFormulaController;
    
    Stage stage;
      	
    public FisicaMainController(MenuController menuController) {
    	
    	 try {
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FisicaMainView.fxml"));
			 loader.setController(this);
			 loader.load();
		 } catch (IOException e) {
			
			 e.printStackTrace();
		 }
    	 this.menuController=menuController;
     } 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//bindeos
		
		categoriasList.itemsProperty().bind(categorias);
		categoriaSeleccionada.bind(categoriasList.getSelectionModel().selectedItemProperty());
		categoriaSeleccionada.addListener((o,ov,nv)-> onCategoriaChanged(o,ov,nv));
		
		
		//cargamos el fichero de formulas
		onCargarFicheroAction();
		
		categorias.bind(formulaListObject.get().categoriesProperty());
		
		
		contenedorFormulasMasonry.prefWidthProperty().bind(scroll.widthProperty().subtract(50));
		
	}
	
	
	
	
	private void onCategoriaChanged(ObservableValue<? extends Category> o, Category ov, Category nv) {
		FormulaVista aux;
		componentesFormulaVista.clear();
		contenedorFormulasMasonry.getChildren().clear();
		for(int i=0;i<categoriaSeleccionada.get().getFormulas().size();i++) {
			aux=new FormulaVista(categoriaSeleccionada.get().getFormulas().get(i).getName(), categoriaSeleccionada.get().getFormulas().get(i).getImgExpresion());
			componentesFormulaVista.add(aux);				
			//revisar
			int l=i;
				
			componentesFormulaVista.get(i).setOnMouseClicked(e->onClickFormula(e,l));//creo un evento por cada componente vista que haya
		}
		contenedorFormulasMasonry.getChildren().addAll(componentesFormulaVista);
		Platform.runLater(scroll::requestLayout);

	}
	
	private void onClickFormula(MouseEvent e,int l) {//al clicar en una formula
		formulaSeleccionada.set(categoriaSeleccionada.get().getFormulas().get(l));
		Formula aux=formulaSeleccionada.get();
		
		fisicaFormulaController=new FisicaFormulaController(aux,this);
		menuController.setContent(fisicaFormulaController.getRoot());
		
	}
	private void onCargarFicheroAction() {
		try {
			String json = IOUtils.resourceToString("/ficheros/Formulas.json", Charset.forName("UTF-8"));
			Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();
			formulaListObject.set(gson.fromJson(json, FormulaList.class));
			
			System.out.println(formulaListObject.get());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public VBox getRoot() {
		return root;
	}
}
