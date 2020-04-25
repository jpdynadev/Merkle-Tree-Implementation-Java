# Merkle-Tree-Implementation-Java
Merkle tree implementation using Java

This is a merkle tree implementation using Java and Maven for dependency control.

The project is comprised of a merkle object that takes in a list of string or "hashes", and a hash function (MessageDigest object in this instance)

When the Merkle object is initialized it will create a root attribute from the list given by calling ```createMerkleTree()``` function.

The createMerkleTree function looks as follows:

```
public List<String> createMerkleTree(List<String> leafHashes) throws Exception{
	List<String> tempList = new ArrayList<>();
```
Temporary list to store new leafs formed from combining two hashes


```
	if(leafHashes == null) {
		throw new Exception("List cannot be empty");
	}
```
Throws exception if list is null


```
	if(leafHashes.size() == 1) {
		return leafHashes;
	}
```
Since this solution is recursive, we are going to break once we only have one leaf left (the root)


```
	if(leafHashes.size() % 2 != 0) {
		leafHashes.add(leafHashes.get(leafHashes.size()-1));
	}
```
for uneven lists, adding last element to tail 


```
	for(int i = 0; i < leafHashes.size() -1; i+=2) {
		tempList.add(createHash(leafHashes.get(i) + leafHashes.get(i+1)));
	}
	return createMerkleTree(tempList);
}
```

The second part of this class is the createHash function:

``` 
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
```


Also included in this project is a junit test case that will create two lists:

```
		//FirstList values
		firstList.add("Testing Script");
		firstList.add("Testing String");
		firstList.add("This is a test");
		
		//Second test list values
		secondList.add("Testing Script");
		secondList.add("Testing String");
		secondList.add("Not a test?");
```

Then it will test to make sure that the root is not the same for both the values:

We are using SHA-256 for this example but another such as md5 could also be used.

```
MessageDigest md = MessageDigest.getInstance("SHA-256");
		Merkle firstMerkleTree = new Merkle(firstList, md);
		String firstRoot = firstMerkleTree.getRoot();
		System.out.println(firstRoot);
		Merkle secondMerkleTree = new Merkle(secondList, md);
		String secondRoot = secondMerkleTree.getRoot();
		System.out.println(secondRoot);
		assertNotEquals(firstRoot, secondRoot);
```

