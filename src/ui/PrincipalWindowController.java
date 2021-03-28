package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Automaton;

public class PrincipalWindowController {
	
	@FXML
    private Pane pane;
    @FXML
    private Label title;
    @FXML
    private Button btnMealy;
    @FXML
    private Button btnMoore;
    @FXML
    private TextField txtStates;
    @FXML
    private TextField txtInputs;
    @FXML
    private Button btnFinish;
    
    @FXML
    private TextField initialState;

    @FXML
    private TextField inputState;

    @FXML
    private Button finish;

    @FXML
    private TextField stimuli;
    private Automaton a;

    @FXML
    private TextField txtOutputs;;
    
    public void initialize() {
    	a = new Automaton();
    }

    @FXML
    void createStates(ActionEvent event) {
    	if (txtStates.getText() != null && txtInputs.getText() != null && txtOutputs.getText()!= null) {	
			String l = txtStates.getText();
			String[] states = l.split(",");
			String l1 = txtInputs.getText();
			String[] inputs = l1.split(",");
			txtStates.setEditable(false);
			txtInputs.setEditable(false);
			txtOutputs.setEditable(false);
			btnMoore.setDisable(false);
			btnMealy.setDisable(false);
			String[][] m = new String[states.length+1][inputs.length+1];
			a.setMatrix(m);
		}
    }
    
    public void scheme(String[] states, String[] inputs) {
    	
    }

    

    @FXML
    void finish(ActionEvent event) {

    }

    @FXML
    void melay(ActionEvent event) {

    }

    @FXML
    void moore(ActionEvent event) {

    }

    @FXML
    void reduce(ActionEvent event) {

    }

}

