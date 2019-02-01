package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester {
	
	// 4157740123

	public static void main (String args[]) {
		List<Integer> a = new ArrayList<Integer>();
		a.add(4);
		a.add(1);
		a.add(5);
		a.add(7);
			
		List<Integer> b = new ArrayList<Integer>();
		b.add(4);
		b.add(0);
		b.add(1);
		b.add(2);
		b.add(3);
		
		Collections.sort(a);
		Collections.sort(b);
		
		System.out.println(a);
		System.out.println(b);
        
		List <Integer> answer = new ArrayList<Integer>();
        int i=0,j=0;
        
        while(i < a.size() && j < b.size()) {
			answer.add(a.get(i) < b.get(i) ? a.get(i++) : b.get(j++));
			System.out.println("while comparision answer ="+answer);
			System.out.println(i + " "+ j);
        }
        System.out.println("outside comparision ="+answer);
        while (i < a.size()) {
        	answer.add(a.get(i++));
        }
        
        while (j < b.size()) {
        	answer.add(b.get(j++));
        }
        Collections.sort(answer);
        System.out.println("final answer = "+answer);
    }

}