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

		Stack<State> stack = new Stack<State>();
		boolean[] visited = new boolean[states.size()];
		State start = states.get(0);
		stack.push(start);

		while (!stack.isEmpty()){
			State current = stack.pop();
			int ind = index.get(current);
			visited[ind] = true;
			states.get(ind).setVisited(true);

			for (State s : current.getSuState()) {
				if (!visited[index.get(s)]) {
					stack.push(s);
				}
			}
			
		}
	}
	
	private ArrayList<ArrayList<State>> getFstPartition(){ 

		ArrayList<State> cSta = getLinked();

		for (State state : cSta) {
			state.setVisited(false);
		}

		ArrayList<ArrayList<State>> list = new ArrayList<>();
		ArrayList<State> block;
		String c1;
		String c2;
		int enumerate = 0;

		for (int i = 0; i < cSta.size()-1; i++) {
			
			if (!cSta.get(i).isVisited()) {

				cSta.get(i).setVisited(true);
				cSta.get(i).setCurrent(enumerate);
				cSta.get(i).changeCurrent();
				block = new ArrayList<>();
				block.add(cSta.get(i));
				

				for (int j = i+1; j < cSta.size(); j++) {
					if (!cSta.get(j).isVisited()) {
						
						c1 = String.valueOf(cSta.get(i).getResult());
						c2 = String.valueOf(cSta.get(j).getResult());

						if (c1.equals(c2)) {
							cSta.get(j).setVisited(true);
							cSta.get(j).setCurrent(enumerate);
							cSta.get(j).changeCurrent();
							block.add(cSta.get(j));
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
