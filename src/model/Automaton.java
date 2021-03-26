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

	/**
	 * 
	 * @return
	 */
	private ArrayList<ArrayList<State>> FPartition(){
		ArrayList<ArrayList<State>> list1 = getFstPartition();
		ArrayList<ArrayList<State>> list2 = new ArrayList<>();
		ArrayList<State> nCurrent;
		boolean con = true;

		while (con) {
			int c = 0;
			for (ArrayList<State> a : list1) {
				for (State state : a){
					state.setVisited(false);
					state.changeCurrent();
				}
			}
			for (ArrayList<State> current : list1) {
				for (int i = 0; i < current.size(); i++) {			
					if (current.get(i).isVisited() == false) {
						current.get(i).setVisited(true);
						current.get(i).setCurrent(c);
						nCurrent = new ArrayList<>();
						nCurrent.add(current.get(i));
						for (int j = i+1; j < current.size(); j++) {
							if (current.get(j).isVisited() == false) { 
								if (samePlace(current.get(i), current.get(j))) {
									current.get(j).setCurrent(c);
									current.get(j).setVisited(true);
									nCurrent.add(current.get(j));
								}
							}
						}
						list2.add(nCurrent);
						c++;
					}
				}
			}
			if (!list1.equals(list2)) {
				list1 = new ArrayList<>(list2);
				list2 = new ArrayList<>();
			}else{
				con = false;
			}
		}
		return list2;
	} 

	private boolean samePlace(State s1, State s2){
		boolean ans = true;
		int m1, m2;
		for (int i = 0; i < s1.getSuState().size() && ans; i++) {
			m1 = s1.getSuState().get(i).getPrevC();
			m2 = s2.getSuState().get(i).getPrevC();

			if (m1 != m2) {
				ans = false;
			}
		}
		return ans;
	}

	public void moore() {

	}

	public void mealy() {

	}
}
