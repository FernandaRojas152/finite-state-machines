package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


class AutomataTest {
	
	private Automaton a;
	
	@Test
	private void setUp() { 
		
		ArrayList<State> states = new ArrayList<>();
		
		char[] c1 = new char[]{'0'};
		char[] c2 = new char[]{'1'};

		State q0 = new State("A", c1);
		State q1 = new State("B", c1);
		State q2 = new State("C", c1);
		State q3 = new State("D", c2);
		State q4 = new State("E", c2);
		State q5 = new State("F", c1);

		q0.addsuState(q1);
		q0.addsuState(q2);
		q1.addsuState(q3);
		q1.addsuState(q4);
		q2.addsuState(q4);
		q2.addsuState(q3);
		q3.addsuState(q5);
		q3.addsuState(q5);
		q4.addsuState(q5);
		q4.addsuState(q5);
		q5.addsuState(q5);
		q5.addsuState(q5);

		states.add(q0);
		states.add(q1);
		states.add(q2);
		states.add(q3);
		states.add(q4);
		states.add(q5);

		char[] stimuli = new char[]{'0', '1'};
		char[] outputs = new char[]{'a', 'b'};

		a = new Automaton("Moore", states, stimuli, outputs);

		
	}
	
	@Test
	private void setUp1() { 
		
		ArrayList<State> states = new ArrayList<>();
		
		char[] c1 = new char[]{'0'};
		char[] c2 = new char[]{'1'};

		State q0 = new State("A", c1);
		State q1 = new State("B", c2);
		State q2 = new State("C", c1);
		State q3 = new State("D", c1);
		State q4 = new State("E", c1);
		State q5 = new State("F", c1);

		q0.addsuState(q1);
		q0.addsuState(q3);
		q1.addsuState(q2);
		q1.addsuState(q4);
		q2.addsuState(q1);
		q2.addsuState(q5);
		q3.addsuState(q4);
		q3.addsuState(q0);
		q4.addsuState(q5);
		q4.addsuState(q1);
		q5.addsuState(q4);
		q5.addsuState(q2);

		states.add(q0);
		states.add(q1);
		states.add(q2);
		states.add(q3);
		states.add(q4);
		states.add(q5);

		char[] stimuli = new char[]{'0', '1'};
		char[] outputs = new char[]{'0', '1'};

		a = new Automaton("Moore", states, stimuli, outputs);

		
	}
	
	@Test
	public void testMinimizedAutomaton() {
		setUp();
		
		String str;
		String name;
		
		ArrayList<State> aL = a.getMinimizedAutomaton();
		int j = aL.size();
		boolean brk = true;

		for (int i = 0; i < j && brk; i++) {
			str = "Q" + i;
			name = aL.get(i).getName();
			if (!str.equals(name)) {
				brk = false;
			}

		}

		assertTrue(brk,"Not expected");

	}
	
	@Test
	public void testMinimizedAutomaton1() {
		setUp1();
		
		String str;
		String name;
		
		ArrayList<State> aL = a.getMinimizedAutomaton();
		int j = aL.size();
		boolean brk = true;

		
		for (int i = 0; i < j && brk; i++) {
			str = "Q" + i;
			name = aL.get(i).getName();
			if (!str.equals(name)) {
				brk = false;
			}

		}

		assertTrue(brk,"Not expected");

	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
