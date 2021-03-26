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
	
	private HashMap<State, Integer> ind;
	
	public Automaton(String type, ArrayList<State> states, char[] stimuli, char[] outputs) {
		
		
		this.type = type;
		this.states = states;
		this.stimuli = stimuli;
		this.outputs = outputs;
		ind = new HashMap<>();
		generateIndex();
		
	}

	private void generateIndex(){
		for (int i = 0; i < states.size(); i++) {
			ind.put(states.get(i), i);
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
	
	public ArrayList<State> getLinked(){
		dfs();
		ArrayList<State> connectedStates = new ArrayList<>();
		for (State s : states) {
			if (s.isVisited()) {
				connectedStates.add(s);
			}
		}
		return connectedStates;
	}
	
	private void dfs(){

		for (State s : states) {
			s.setVisited(false);
		}

		Stack<State> st = new Stack<State>();
		boolean[] visited = new boolean[states.size()];
		State begin = states.get(0);
		st.push(begin);

		while (!st.isEmpty()){
			State curr = st.pop();
			int i = ind.get(curr);
			visited[i] = true;
			states.get(i).setVisited(true);

			for (State s : curr.getSuState()) {
				if (!visited[ind.get(s)]) {
					st.push(s);
				}
			}
			
		}
	}
	
	private ArrayList<ArrayList<State>> getFstPartition(){ 
		ArrayList<State> tSta = getLinked();

		for (State state : tSta) {
			state.setVisited(false);
		}

		ArrayList<ArrayList<State>> list = new ArrayList<>();
		ArrayList<State> block;
		String c;
		String c1;
		int enumerate = 0;

		for (int i = 0; i < tSta.size()-1; i++) {
			
			if (!tSta.get(i).isVisited()) {
				tSta.get(i).setVisited(true);
				tSta.get(i).setCurrent(enumerate);
				tSta.get(i).changeCurrent();
				block = new ArrayList<>();
				block.add(tSta.get(i));
				
				for (int j = i+1; j < tSta.size(); j++) {
					if (!tSta.get(j).isVisited()) {
						
						c = String.valueOf(tSta.get(i).getResult());
						c1 = String.valueOf(tSta.get(j).getResult());

						if (c.equals(c1)) {
							tSta.get(j).setVisited(true);
							tSta.get(j).setCurrent(enumerate);
							tSta.get(j).changeCurrent();
							block.add(tSta.get(j));
						}

					}
				}
				list.add(block);
				enumerate++;
			}
		}
		
		return list;
	}

	
	public void moore() {
		
	}
	
	public void mealy() {
		
	}
}
