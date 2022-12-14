package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Week2.Node;

public class TestWeek2 {
	
	public static void main(String[] args) {

//		List<Integer> state = new ArrayList<>();
//		state.add(1);
////   	state.add(1);
////		state.add(0);
//		state.add(3);
//		state.add(0);
////		state.add(2);
//		state.add(2);
//	
//		
//		System.out.println(state);
//		System.out.println(Node.isValid(state));

		Integer[] arrState = { 1 };
		List<Integer> state = new ArrayList<>(Arrays.asList(arrState));
		
		System.out.println(state);

		Node n1 = new Node(5, state);
		System.out.println(n1.place(2));
	

	}


}
