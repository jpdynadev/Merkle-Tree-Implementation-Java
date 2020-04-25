package com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Merkle {
private List<String> leafs;
private MessageDigest hash;
private String root;

public Merkle(List<String> leafs, MessageDigest hash) throws Exception {
	this.leafs = leafs;
	this.hash = hash;
	this.root = createMerkleTree(leafs).get(0);
}

public String getRoot() {
	return this.root;
}
public MessageDigest getHash() {
	return this.hash;
}

public List<String> createMerkleTree(List<String> leafHashes) throws Exception{
	List<String> tempList = new ArrayList<>();
	if(leafHashes == null) {
		throw new Exception("List cannot be empty");
	}
	if(leafHashes.size() == 1) {
		return leafHashes;
	}
	if(leafHashes.size() % 2 != 0) {
		leafHashes.add(leafHashes.get(leafHashes.size()-1));
	}
	for(int i = 0; i < leafHashes.size() -1; i+=2) {
		tempList.add(createHash(leafHashes.get(i) + leafHashes.get(i+1)));
	}
	return createMerkleTree(tempList);
}
public String createHash(String leaf) throws Exception {
	try {
	byte[] byteValue = this.hash.digest(leaf.getBytes("UTF-8"));
	
	//Byte array to BigInteger representation to handle overspill
	BigInteger numValue = new BigInteger(1, byteValue);
	
	//StringBuilder to convert BigInteger to StringValue
	StringBuilder sb = new StringBuilder(numValue.toString(16));
	
	while(sb.length() < 64) {
		sb.insert(0, '0');
	}
			
	return sb.toString();
	}catch(Exception e) {
		//Debug exception
		System.out.println(e.getLocalizedMessage());
		throw new Exception(e);
	}
}
}
