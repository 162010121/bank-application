package com.bank.rest.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {
	
	public static void main(String[] args) {
		
		List<List<Integer>> listOfLists = Arrays.asList(
		    Arrays.asList(1, 2, 3),
		    Arrays.asList(4, 5, 6),
		    Arrays.asList(7, 8, 9)
		);

		List<Integer> list = listOfLists.stream().flatMap(a-> a.stream()).collect(Collectors.toList());
		System.out.println(list);
		
		
		// Given a list of strings, find the first string that has more than 5 characters.
		List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry");
		
	     String word= words.stream().filter(a-> a.length()>5).collect(Collectors.toList()).getFirst();
	     System.out.println(word);
	}

}
