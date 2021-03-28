package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private TextField txtOutputs;;
    @FXML
    private StackPane machinesPane;
    @FXML
    private Button btnReduce;
    @FXML
    private Button btnSave;
    
    private Automaton a;
    GridPane gridToFill; 
    TextField txt;
    String[] states;
    String[] inputs;
    
    public void initialize() {
    	a = new Automaton();
    }

    @FXML
    void createStates(ActionEvent event) {
    	if (txtStates.getText() != null && txtInputs.getText() != null && txtOutputs.getText()!= null) {	
			String l = txtStates.getText();
			states = l.split(",");
			String l1 = txtInputs.getText();
			inputs = l1.split(",");
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
    	machinesPane.getChildren().clear();
		gridToFill = new GridPane();
		gridToFill.setAlignment(Pos.CENTER);
		btnReduce.setAlignment(Pos.BOTTOM_RIGHT);
		btnReduce.setVisible(true);
		btnReduce.setDisable(true);
		machinesPane.getChildren().add(btnReduce);
		txt = new TextField("/");
		txt.setDisable(true);
		txt.setPrefWidth(30);
		gridToFill.add(txt, 0, 0);

		for (int i = 0; i < states.length; i++) {
			TextField txtN = new TextField(states[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridToFill.add(txtN, 0, i+1);
		}

		for (int i = 0; i < inputs.length; i++) {
			TextField txtN = new TextField(inputs[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridToFill.add(txtN, i+1, 0);
		}

		for (int i = 0; i < states.length; i++) {
			for (int j = 0; j < inputs.length; j++) {
				TextField txtN = new TextField();
				txtN.setPrefWidth(30);
				txtN.setEditable(true);
				gridToFill.add(txtN, j+1, i+1);
			}
		}
		machinesPane.getChildren().add(gridToFill);

	}
    

    @FXML
    void melay(ActionEvent event) {
    	scheme(states, inputs);
    }

    @FXML
	void moore(ActionEvent event) {
    	String[][] m = new String[a.getMatrix().length][a.getMatrix()[0].length+1];
    	a.setMatrix(m);
    	scheme(states, inputs);
    	int j = inputs.length + 1;
		for (int i = 0; i < states.length; i++) {
			TextField tf = new TextField();
			tf.setPrefWidth(30);
			tf.setEditable(true);
			gridToFill.add(tf, j, i + 1);
		}
	}

    @FXML
    void reduce(ActionEvent event) {

    }
    
    @FXML
    void save(ActionEvent event) {

    }

}

