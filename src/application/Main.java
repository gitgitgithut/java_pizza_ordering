package application;
	
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	
	//<span style="font-size:14px;">@Override  
    public void start(Stage stage) throws Exception {  
    	stage.setTitle("JavaFX Pizza Order");
        //initial gridpane 
    	GridPane grid = new GridPane();
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(25, 25, 25, 25));    	
    	Scene scene = new Scene(grid, 500, 500);        
    	Text scenetitle = new Text("Welcome");
    	scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    	grid.add(scenetitle, 0, 0, 2, 1);
    	//create interface
    	Label Size = new Label("Size:");
    	grid.add(Size, 0, 1);    	
    	ChoiceBox<String> SizeList = new ChoiceBox<String>();
    	ObservableList<String> SizeItem =FXCollections.observableArrayList (
    	    "small", "medium", "large");
    	SizeList.setItems(SizeItem);
    	SizeList.setPrefWidth(150);
    	SizeList.setPrefHeight(50);
    	grid.add(SizeList, 1, 1);
    	

    	Label Cheese = new Label("Cheese:");
    	grid.add(Cheese, 0, 2);    	
    	ChoiceBox<String> CheeseList = new ChoiceBox<String>();
    	ObservableList<String> CheeseItem =FXCollections.observableArrayList (
    	    "cheese", "double cheese", "triple cheese");
    	CheeseList.setItems(CheeseItem);
    	CheeseList.setPrefWidth(150);
    	CheeseList.setPrefHeight(50); 	
    	grid.add(CheeseList, 1, 2);
    	

    	Label Ham = new Label("Ham:");
    	ChoiceBox<String> HamList = new ChoiceBox<String>();
    	ObservableList<String> HamItems =FXCollections.observableArrayList (
    	    "ham", "double ham", "triple ham");
    	HamList.setItems(HamItems);
    	HamList.setPrefWidth(150);
    	HamList.setPrefHeight(50);
    	grid.add(Ham, 0, 3);    	
    	grid.add(HamList, 1, 3);
    	
    	
    	Label Pepperoni = new Label("Pepperoni:");
    	grid.add(Pepperoni, 0, 4);
    	ChoiceBox<String> PepperoniList = new ChoiceBox<String>();
    	ObservableList<String> PepperoniItems =FXCollections.observableArrayList (
    	    "pepperoni", "double pepperoni", "triple pepperoni");
    	PepperoniList.setItems(PepperoniItems);
    	PepperoniList.setPrefWidth(150);
    	PepperoniList.setPrefHeight(50);
    	grid.add(PepperoniList, 1, 4);
    	
    	Label Quantity = new Label("Quantity:");
    	grid.add(Quantity, 0, 5);
    	TextField userText = new TextField();    	
    	grid.add(userText, 1, 5);
    	
    	Button btn = new Button("Create Your Own Pizza!");
    	HBox hbBtn = new HBox(10);
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    	hbBtn.getChildren().add(btn);
    	grid.add(hbBtn, 1, 6);
    	
    	final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 7);
        //events handle
    	btn.setOnAction(new EventHandler<ActionEvent>() {
    		 
    	    @Override
    	    public void handle(ActionEvent e) {    	       
    	    	try {
    	    		String[] Scheese = {"", "cheese", "double cheese", "triple cheese"};
    	    		String[] Sham = {"", "ham", "double ham", "triple ham"};
    	    		String[] Spepperoni = {"", "pepperoni", "double pepperoni", "triple pepperoni"};
    	    		//get the values from user input to create pizza object
    	    		String size = SizeList.selectionModelProperty().getValue().getSelectedItem();
    	    		String cheese = CheeseList.selectionModelProperty().getValue().getSelectedItem();
    	    		String ham = HamList.selectionModelProperty().getValue().getSelectedItem();
    	    		String pepperoni = PepperoniList.selectionModelProperty().getValue().getSelectedItem();
    	    		int Icheese = Arrays.asList(Scheese).indexOf(cheese);
    	    		int Iham = Arrays.asList(Sham).indexOf(ham);
    	    		int Ipepperoni = Arrays.asList(Spepperoni).indexOf(pepperoni);   		
    	    		
    	    		//Illegal pizza handle
    	    		if(userText.getText().isEmpty()|| size == null || cheese==null || ham==null ||pepperoni==null||(Iham+Ipepperoni) > 3){
    	    			actiontarget.setFill(Color.FIREBRICK);
    	    	        actiontarget.setText("Illegal pizza, dont leave blank or more than 3 meat!");
    	    		}
    	    		
    	    		int quantity = Integer.parseInt(userText.getText());
    	    		
    	    		if(quantity<1 || quantity>100 || userText.getText().trim().isEmpty()){
    	    			actiontarget.setFill(Color.FIREBRICK);
    	    	        actiontarget.setText("Please enter the number of pizza from 1 - 100");
    	    		}
    	    		//show pizza order
    	    		System.out.println(Icheese + " " + Iham+ " " + Ipepperoni + " " + quantity);
					Pizza p = new Pizza(size, Icheese, Iham, Ipepperoni);
					LineItem order = new LineItem(quantity, p);
					actiontarget.setFill(Color.FIREBRICK);
	    	        actiontarget.setText(order.toString());
	    	       
				} catch (IllegalPizza e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	
				
    	    }
    	});    	
    	
        stage.setScene(scene);  
        stage.show();  
    }//</span>  
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
