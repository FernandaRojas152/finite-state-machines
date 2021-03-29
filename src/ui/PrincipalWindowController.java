package ui;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Automata;
import model.State;

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
    @FXML
    private Button btnNew;
    
    private Automata a;
    private GridPane gridToFill; 
    private GridPane gridLastPartition;
    private TextField txt;
    private String[] states;
    private String[] inputs;
    private int wichIsLastCliked;
    
    public void initialize() {
    	a = new Automata();
    	wichIsLastCliked = -1;
    	Tooltip tt = new Tooltip();
    	tt.setText("Botón que guarda el autómata");
    	btnSave.setTooltip(tt);
    	Tooltip tt1 = new Tooltip();
    	tt1.setText("Botón que genera el autómata mínimo equivalente");
    	btnReduce.setTooltip(tt1);
    }

    @FXML
    void createAutomaton(ActionEvent event) {
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
		/*btnReduce.setAlignment(Pos.BOTTOM_RIGHT);
		btnReduce.setVisible(true);
		btnSave.setAlignment(Pos.BOTTOM_CENTER);
		btnSave.setVisible(true);
		machinesPane.getChildren().add(btnReduce);
		btnReduce.setDisable(true);
		machinesPane.getChildren().add(btnSave);*/
		btnSave.setDisable(false);
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
    	wichIsLastCliked = 1;
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
		wichIsLastCliked = 2;
	}

    @FXML
    void reduce(ActionEvent event) {
    	ArrayList<State> list = a.getMinimizedAutomaton();

		machinesPane.getChildren().clear();
		gridLastPartition = new GridPane();
		gridLastPartition.setAlignment(Pos.CENTER);
		TextField txt = new TextField("/"); 
		txt.setDisable(true);
		btnNew.setDisable(false);
		txt.setPrefWidth(30);
		gridLastPartition.add(txt, 0, 0);

		for (int i = 0; i < a.getStimuli().length; i++) {
			TextField txtN = new TextField(""+a.getStimuli()[i]);
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridLastPartition.add(txtN, i+1, 0);
		}

		for (int i = 0; i < list.size(); i++) {
			TextField txtN = new TextField(list.get(i).getName());
			txtN.setDisable(true);
			txtN.setPrefWidth(30);
			gridLastPartition.add(txtN, 0, i+1);
		}

		if (wichIsLastCliked == 2) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < a.getStimuli().length; j++) {
					State successor = list.get(i).getSuState().get(j);
					String name = successor.getName();
					TextField tf = new TextField(name);
					gridLastPartition.add(tf, j+1, i+1);
				}
				TextField tfm = new TextField(list.get(i).getResult()[0]+"");
				tfm.setDisable(true);
				tfm.setPrefWidth(30);
				gridLastPartition.add(tfm, a.getStimuli().length+2, i+1);
			}
		}else {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < a.getStimuli().length; j++) {

					State successor = list.get(i).getSuState().get(j);
					char c = list.get(i).getResult()[j];
					String name = successor.getName();
					TextField tf = new TextField(name+","+c);
					gridLastPartition.add(tf, j+1, i+1);
				}
			}
		}
		machinesPane.getChildren().add(gridLastPartition);
    }
    
    @FXML
    void reset(ActionEvent event) {
    	machinesPane.getChildren().clear();
       	txtStates.setText("");
    	txtStates.setEditable(true);
    	txtInputs.setText("");
    	txtInputs.setEditable(true);;
    	txtOutputs.setText("");
    	txtOutputs.setEditable(true);
		
    	btnMealy.setDisable(true);
    	btnMoore.setDisable(true);
 
    	btnSave.setDisable(true);
    	btnReduce.setDisable(true);
		
		a.setMatrix(null) ;
		
		btnNew.setDisable(true);
    }
    
    @FXML
    void save(ActionEvent event) {
    	createStates();
    	
    	char[] i = a.castArray(inputs);
    	char[] o = a.castArray(txtOutputs.getText().split(","));
    	String t = null;
    	ArrayList<State> s = null;
    	
    	if (wichIsLastCliked == 1) {
			t = "Maely";
			s = a.mealy(i.length);
		}else {
			t = "Moore";
			s = a.moore();
		}
    	/*a.setOutputs(o);
    	a.setStates(s);
    	a.setStimuli(i);
    	a.setType(t);*/
    	
    	String[][] m = a.getMatrix();
    	a = new Automata(t, s, i, o);
    	a.setMatrix(m);
    	
    	btnReduce.setDisable(false);

    }
    
    private void createStates() {
    	String[][] m = a.getMatrix();
		for (Node node : gridToFill.getChildren()) {

			int c = GridPane.getColumnIndex(node);
			int r = GridPane.getRowIndex(node);

			TextField txt = (TextField)node;
			String s = txt.getText();
			
			m[r][c] = s;

		}
		a.setMatrix(m);
	}

}

