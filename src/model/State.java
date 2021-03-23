package model;

import java.util.ArrayList;

/**
 * 
 * @author Fernanda, Balanta, Yimar
 *
 */
public class State {
	private boolean visited;
	private String name;
	private char[] result;
	private int current;
	private int prevC;
	private ArrayList<State> prevState;
	
	public State(String name, char[] result) {
		this.name= name;
		visited= false;
		this.result= result;
		prevState= new ArrayList<State>();
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char[] getResult() {
		return result;
	}

	public void setResult(char[] result) {
		this.result = result;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPrevC() {
		return prevC;
	}

	public void setPrevC(int prevC) {
		this.prevC = prevC;
	}

	public ArrayList<State> getPrevState() {
		return prevState;
	}

	public void setPrevState(ArrayList<State> prevState) {
		this.prevState = prevState;
	}
}
