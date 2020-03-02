package matapp.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    
//    ListProperty<Category> listaCategorias=new SimpleListProperty<>(FXCollections.observableArrayList());
    ListProperty<Category> categorias=new SimpleListProperty<>();
    
    ObjectProperty<Category> categoriaSeleccionada=new SimpleObjectProperty<>();
    
    ListProperty<Formula> formulas=new SimpleListProperty<>();
    
    ObjectProperty<Formula> formulaSeleccionada=new SimpleObjectProperty<>();
    
    
    MenuController menuController;
    
    
    ArrayList <FormulaVista> componentesFormulaVista=new ArrayList<>();
    
    FisicaFormulaController fisicaFormulaController;
    
    Stage stage;
    
    public FisicaMainController() {
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FisicaMainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }    	
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
		
		fisicaFormulaController=new FisicaFormulaController(aux);
		
//		stage=new Stage();
//		Scene scene=new Scene(fisicaFormulaController.getRoot());
//		stage.setTitle("Matapp");
//		stage.setScene(scene);
//		stage.show();
		menuController.setContent(fisicaFormulaController.getRoot());
		
	}
	private void onCargarFicheroAction() {
		String fichero = "";
		 //revisar 
//		try (BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/ficheros/Formulas.json").toExternalForm()))) {
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/ficheros/Formulas.json"))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        fichero += linea;
		    }
		 
		} catch (FileNotFoundException ex) {//colocar un alert
		    System.out.println(ex.getMessage());
		} catch (IOException ex) {
		    System.out.println(ex.getMessage());
		}
		
		Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();
		formulaListObject.set(gson.fromJson(fichero, FormulaList.class));
	}
	
	private void onGuardarAction() {
		File file=new File("Formulas.txt"); 
		
		FileWriter fW=null;
		try {
			fW = new FileWriter(file);
			BufferedWriter bW=new BufferedWriter(fW);
			Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();
			bW.write(gson.toJson(formulaListObject.get()));
			bW.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public VBox getRoot() {
		return root;
	}
}
