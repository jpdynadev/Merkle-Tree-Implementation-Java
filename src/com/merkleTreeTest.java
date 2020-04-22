package com;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class merkleTreeTest {

	@Test
	void test() throws Exception{
		
		List<String> values = new ArrayList<>();
		List<String> testList = new ArrayList<>();
		
		//FirstList values
		values.add("Testing Script");
		values.add("Testing String");
		values.add("This is a test");
		
		//Second test list values
		testList.add("Testing Script");
		testList.add("Testing String");
		testList.add("Not a test?");
		
		MerkleTree firstMerkleTree = new MerkleTree(values);
		String merkleTreeRoot = firstMerkleTree.getRoot();
		MerkleTree secondMerkleTree = new MerkleTree(testList);
		String secondRoot = secondMerkleTree.getRoot();
		assertNotEquals("Success",merkleTreeRoot, secondRoot);
		
	}

}
