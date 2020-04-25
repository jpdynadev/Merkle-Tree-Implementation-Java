package com;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class merkleTreeTest {

	@Test
	void test() throws Exception{
		
		List<String> firstList = new ArrayList<>();
		List<String> secondList = new ArrayList<>();
		
		//FirstList values
		firstList.add("Testing Script");
		firstList.add("Testing String");
		firstList.add("This is a test");
		
		//Second test list values
		secondList.add("Testing Script");
		secondList.add("Testing String");
		secondList.add("Not a test?");
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		Merkle firstMerkleTree = new Merkle(firstList, md);
		String firstRoot = firstMerkleTree.getRoot();
		System.out.println(firstRoot);
		Merkle secondMerkleTree = new Merkle(secondList, md);
		String secondRoot = secondMerkleTree.getRoot();
		System.out.println(secondRoot);
		assertNotEquals(firstRoot, secondRoot);
		
	}

}
