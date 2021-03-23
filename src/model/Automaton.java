package model;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 
 * @author Fernanda, Balanta, Yimar
 *
 */
public class Automaton {
	private ArrayList<State> states;
	private char[] stimuli;
	private HashMap<State, Integer> index;
	
	private void generateIndex(){
		for (int i = 0; i < states.size(); i++) {
			index.put(states.get(i), i);
		}
	}
	
	
	public void moore() {
		
	}
	
	public void mealy() {
		
	}
}
