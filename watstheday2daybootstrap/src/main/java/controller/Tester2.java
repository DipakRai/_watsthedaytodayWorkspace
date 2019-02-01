package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester2 {

	public static List<Integer> mergeArrays(List<Integer> a, List<Integer> b) {
		Collections.sort(a);
		Collections.sort(b);
		System.out.println(a);
		System.out.println(b);
		List<Integer> answer = new ArrayList<Integer>();
		int i = 0, j = 0;
		while (i < a.size() && j < b.size()) {
			answer.add(a.get(i) < b.get(i) ? a.get(i++) : b.get(j++));
			System.out.println(answer);
			System.out.println(i + " " + j);
		}
		System.out.println(answer);
		while (i < a.size()) {
			answer.add(a.get(i));
			i++;
			System.out.println(i);
		}
		while (j < b.size()) {
			answer.add(b.get(j));
			j++;
			System.out.println(j);
		}
		return answer;
	}
}