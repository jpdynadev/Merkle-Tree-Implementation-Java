package com;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
	
	private String merkleTreeRoot;
	
	//Constructor takes in values and creates merkle tree
	public MerkleTree(List<String> values) {
		this.merkleTreeRoot = getRootMerkleTree(values).get(0);
	}
	/*
	 * function that returns merkle tree root
	 * @Params List of string values
	 */
	public List<String> getRootMerkleTree(List<String> values){
		if(values.size() == 1) {
			//return root once list has been reduced
			return values;
		}
		List<String> tempList = new ArrayList<>();
		for(int i  = 0; i < values.size() -1; i+=2) {
			try {
				tempList.add(new String((SHA(values.get(i) + values.get(i+1)))));
			} catch (Exception e) {
				//Debug
				e.printStackTrace();
			}
		}
		if(values.size() % 2 != 0) {
			try {
				tempList.add(new String((SHA(values.get(values.size()-1) + values.get(values.size() - 1)))));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return getRootMerkleTree(tempList);
	}
	
	public static String SHA(String value) throws Exception{
		try {
		//Initialize Message Digest to use SHA hashing for bytes
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		//Digest function to convert String to byte array using SHA-256 hash
		byte[] byteValue = md.digest(value.getBytes("UTF-8"));
		
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
	
	//getter for root
	public String getRoot() {
		return this.merkleTreeRoot;
	}

}
