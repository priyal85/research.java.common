package com.test;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

public class Singleton {
	private static Singleton INSTANCE;
	
	private static ThreadLocal<List<String>> lists = new ThreadLocal<List<String>>() {
		@Override
		protected List<String> initialValue() {
			return new ArrayList<String>();
		}
	};

	public static Singleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		return INSTANCE;
	}

	private Singleton() {
	}
	
	public void addValue(String value) {
		lists.get().add(value);
	}
	
	public void printValues() {
		List<String> list = lists.get();

		System.out.println("Thread : " + Thread.currentThread().getName() + "\nValues : " + Joiner.on(",").join(list)+"\n");

	}
	
	
}
