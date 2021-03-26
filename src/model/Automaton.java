package model;

import java.util.ArrayList;
import java.util.Stack;
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
	private char[] outputs;
	
	private HashMap<State, Integer> index;
	
	public Automaton(String type, ArrayList<State> states, char[] stimuli, char[] outputs) {
		
		
		this.type = type;
		this.states = states;
		this.stimuli = stimuli;
		this.outputs = outputs;
		index = new HashMap<>();
		generateIndex();
		
	}

	private void generateIndex(){
		for (int i = 0; i < states.size(); i++) {
			index.put(states.get(i), i);
		}
	}

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
	
	public void moore() {
		
	}
	
	public void mealy() {
		
	}
}
