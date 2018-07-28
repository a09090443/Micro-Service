package com.zipe.utils.jaxb;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		JaxbUtil util = new JaxbUtil(Head.class);
		try {
			Head head = util.fromFileConvertObject(new File("/home/zipe/tmp/file1.xml"));
			System.out.println("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
