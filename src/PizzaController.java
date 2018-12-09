package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class PizzaController implements ActionListener{
	
	protected ArrayList<LineItem> order = new ArrayList<>();
    @FXML
    private Button PlaceOrderButton;
    @FXML
    protected Label ShowOrder;
    
    
    @FXML
    void initialize() {
    	
    	PlaceOrderButton.setOnAction(event->{
    		try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlaceOrderButton.fxml"));
                GridPane root1 = (GridPane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Place your order!");
                stage.setScene(new Scene(root1));  
                stage.show();
                
              }catch(Exception e) {
      			e.printStackTrace();
      		}
    });
	
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		 int i;
     	double cost = 0;
     	String list = "";
     	if (order.size() > 0){
     		for (i = 0; i < order.size(); i++){
     			list += "\n" + order.get(i).getPizza().toString();
     			cost += order.get(i).getCost();
     		}
     		list += "\nTotal Cost:$" + cost + ".";
     		ShowOrder.setText(list);
     	}
     	else
     		ShowOrder.setText("Order is empty");
		
	}
     

}
