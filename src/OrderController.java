package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


public class OrderController extends PizzaController{
	
	@FXML
    private ChoiceBox<String> SizeBox;

    @FXML
    private ChoiceBox<Integer> CheeseBox;

    @FXML
    private ChoiceBox<Integer> HamBox;

    @FXML
    private ChoiceBox<Integer> PepperoniBox;

    @FXML
    private TextField NumberBox;

    @FXML
    private Button ConfirmButton;
    
    

    private ObservableList<String> size = FXCollections.observableArrayList(
			"Small", "Medium", "Large");
    
    private ObservableList<Integer> cheese = FXCollections.observableArrayList(
			1, 2, 3);
    
    private ObservableList<Integer> meat = FXCollections.observableArrayList(
			0, 1, 2, 3);
    
    
    @FXML
    void initialize(){
    	SizeBox.setItems(size);
    	CheeseBox.setItems(cheese);
    	HamBox.setItems(meat);
    	PepperoniBox.setItems(meat);
    	
    	ConfirmButton.setOnAction(event->{
    		try {
				Pizza pizza = new Pizza(SizeBox.getValue(), CheeseBox.getValue(), HamBox.getValue(), PepperoniBox.getValue());
				LineItem item = new LineItem(Integer.parseInt(NumberBox.getText()),pizza);
			
				order.add(item);
				JOptionPane.showMessageDialog(null,
					    "Your order is saved!",
					    "Order saved",
					    JOptionPane.INFORMATION_MESSAGE);
				Stage stage = (Stage) ConfirmButton.getScene().getWindow();
			    stage.close();
			   
			} catch (IllegalPizza e) {
					JOptionPane.showMessageDialog(null,
					    "Illegal Input! Please read the note and try placing the order again.",
					    "Error!",
					    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
    	});
    }
}
