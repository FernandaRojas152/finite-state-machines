package model;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Stack;
=======
>>>>>>> 7fc6a9a5237003f52890974fee584057b78c95f4
import java.util.HashMap;
/**
 * 
 * @author Fernanda, Balanta, Yimar
 *
 */
public class Automaton {
	
	private String type;
	private ArrayList<State> states;
	private char[] stimuli;
<<<<<<< HEAD
	private char[] outputs;
	
	private HashMap<State, Integer> index;
	
	public Automaton(String type, ArrayList<State> states, char[] stimuli, char[] outputs) {
		
		
		this.type = type;
		this.states = states;
		this.stimuli = stimuli;
		this.outputs = outputs;
		index = new HashMap<>();
		initializeIndex();
		
	}

	private void initializeIndex(){
=======
	private HashMap<State, Integer> index;
	
	private void generateIndex(){
>>>>>>> 7fc6a9a5237003f52890974fee584057b78c95f4
		for (int i = 0; i < states.size(); i++) {
			index.put(states.get(i), i);
		}
	}
<<<<<<< HEAD

	public String getType(){
		return type;
	}

	public ArrayList<State> getStates(){
		return states;
	}

	public char[] getStimuli(){
		return stimuli;
	}

	public char[] getOutputs(){
		return outputs;
	}
=======
	
>>>>>>> 7fc6a9a5237003f52890974fee584057b78c95f4
	
	public void moore() {
		
	}
	
	public void mealy() {
		
	}
}
