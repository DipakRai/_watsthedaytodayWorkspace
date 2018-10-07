package watsthedaytoday;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	*/
	public boolean addWord(String word)
	{
		boolean addWordStatus=false;
		//word = word.toLowerCase(); don't convert to lower case insert as they are ...very naive way of handling case mismatch Harry!
		//word = word.toLowerCase();
		//CidssLogger.log("@## inside addWord after converting to lower case = "+word);
		if (!word.isEmpty()) {
			TrieNode next = null;
			TrieNode currentNode = null;
			currentNode = root;
			char [] wordChar =word.toCharArray();
			char ch;
			for (int i=0; i< word.length(); i++) {
				ch=Character.toLowerCase(wordChar[i]);
				////CidssLogger.log("@## currentNode before calling insert ="+currentNode);
				next = currentNode.insert(ch);
				//CidssLogger.log("@## after calling insert returned node next  ="+next);
				if (null != next) {// ch node does not exist: a new node with ch has been created and returned:move currNode to this and check for next char
					currentNode = next;
					if(i==wordChar.length-1){
						currentNode.setEndsWord(true);
						boolean allUpperCase =true;
						for (int j = 0; j < wordChar.length; j++) {
							if (Character.isLowerCase(word.charAt(j))){
								allUpperCase=false;
								break;
							}
						}
						if(allUpperCase){
							currentNode.setAllCaps(true);
						} else if (!Character.isLowerCase(wordChar[0])){
							currentNode.setStartsWithCaps(true);
						}
						//CidssLogger.log("currentNode=next ="+currentNode.toString());
						size++;
						addWordStatus=true;
					}
					//counter++;
				} else { // node ch exists as a child of the currentNode: move currentNode to the child of currentNode
					if(i==wordChar.length-1 && !currentNode.getChild(ch).endsWord()){ // the word should not exist already in the trie
						//CidssLogger.log("@#@ node already exists for="+ch);
						////CidssLogger.log("@# currentNode.getChild(ch) when node for ch already exists="+currentNode.getChild(ch).toString());
						currentNode.getChild(ch).setEndsWord(true);
						boolean allUpperCase =true;
						for (int j = 0; j < wordChar.length; j++) {
							if (Character.isLowerCase(word.charAt(j))){
								allUpperCase=false;
								break;
							}
						}
						if(allUpperCase){
							currentNode.getChild(ch).setAllCaps(true);
						} else if (!Character.isLowerCase(wordChar[0])){
							currentNode.getChild(ch).setStartsWithCaps(true);
						}
						size++;
						addWordStatus=true;
					}
					//CidssLogger.log("@# currentNode.getChild(ch) when node for ch already exists="+currentNode.getChild(ch).toString());
					////CidssLogger.log("@## entered when next is null ....");
					////CidssLogger.log("@# currentNode when nexy was null="+currentNode.toString());
					currentNode = currentNode.getChild(ch);
					////CidssLogger.log("@## currentNode.getChild(ch) for ch= "+ch+" is ="+currentNode);
				}
				////CidssLogger.log( " currentNode ="+currentNode.getText() + " #$#$ " +currentNode.getValidNextCharacters().toString());
			}
			return addWordStatus;
		} else {
			return addWordStatus;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		
		TrieNode currentNode=null;
		TrieNode childNode=null;
		currentNode=root;
		s=s.toLowerCase();
		////CidssLogger.log(" @#@# s="+s);
		if(!s.trim().isEmpty()){
			for(char ch:s.toCharArray()){
				////CidssLogger.log("ch="+ch);
				if(currentNode.getValidNextCharacters().contains(ch)){
					childNode=currentNode.getChild(ch);
					currentNode=childNode;
					////CidssLogger.log("@#@ currentNode ="+currentNode.toString());
				} else {
					return false;
				}
			}
			if(currentNode.endsWord()){
				return true;
			} else {
				return false;
			}				
		} else {
			return false;
		}
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 //CidssLogger.log(" Did i enter here...inside predictCompletions... ");
    	 List<String> completionList = new ArrayList<String>();	    	 //    Create a list of completions to return (initially empty)
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 TrieNode currentNode=null;
    	 TrieNode next=null;
    	 currentNode=root;
    	 char [] prefixChar=null;
    	 //prefix=prefix.toLowerCase(); This is not required to handle matching lower upper cases.
    	 prefixChar=prefix.toCharArray();
    	 char ch='\0';
    	 for(int i=0;i<prefix.length();i++){
    		 //CidssLogger.log("@#@ prefix.length ="+prefix.length());
					ch=prefixChar[i];
					////CidssLogger.log("ch for prefix ="+ch);
					next = currentNode.getChild(ch);
					
					////CidssLogger.log("next for ch "+ch +" is ="+next.toString());
					if (null != next) {
						//CidssLogger.log("!@#@next ="+next.getText());
						currentNode = next;
						if(i==prefix.length()-1){//Once the stem is found, perform a breadth first search to generate completions  using the following algorithm:
							////CidssLogger.log(" @#@ prefix.equalsIgnoreCase(currentNode.getText()) ="+prefix.equalsIgnoreCase(currentNode.getText()));
							////CidssLogger.log(" @#@ prefix.equalsIgnoreCase(currentNode.getText()) ="+currentNode.getText().equalsIgnoreCase(prefix));
							////CidssLogger.log(" !@#@@ stem currentNode text="+currentNode.getText());
							queue.add(currentNode);//Create a queue (LinkedList) and add the node that completes the stem to the back of the queue
						}				
					} else { // Find the stem in the trie.  If the stem does not appear in the trie, return an empty list
						////CidssLogger.log("!@#@ else next ="+next.getText());
						return completionList;
					}
			}
	    	 int count=0;
	    	 TrieNode wordNode=null;
	    	 //CidssLogger.log(" @#$#@ First part of algo is over ....");
	    	 //CidssLogger.log(" @#$@#$#@ queue size ="+queue.size());
	    	 while(!queue.isEmpty()&&count<numCompletions){	    	 //    While the queue is not empty and you don't have enough completions:
	    		 //CidssLogger.log(" count ="+count);
	    		 wordNode=queue.remove();
	    		 for (Character c : wordNode.getValidNextCharacters()) {	  //  Add all of its child nodes to the back of the queue
						//CidssLogger.log(" c ="+c + "  @#$ wordNode.getChild(c) ="+wordNode.getChild(c));	
						queue.add(wordNode.getChild(c));
					}
	    		 //CidssLogger.log(" wordNode removed from the queue ="+wordNode.getText());
				if (wordNode.endsWord()) {// If it is a word, add it to the completions list
					String text=null;
					String updatedText=null;
					Character firstChar=null;
					/* algorithm to handle the case while predicting auto complete
					 * check if wordNode startsWithCaps
					 *				take the first char of wordNode.getText()
					 *   			convert it to CAPS =updatedText
					 *   			add updatedText to completionList
					 * else check if the wordNode has all CAPS
					 * 			String.toUpperCase (wordNode.getText()) =updatedText
					 * 			add updatedText to completionList
					 * */
					
					text=wordNode.getText();
					if (wordNode.isAllCaps()) {
						updatedText = text.toUpperCase();
					} else if (wordNode.isStartsWithCaps()) {
						firstChar = text.charAt(0);
						updatedText = text.replaceFirst(firstChar.toString(), firstChar.toString().toUpperCase());
					} else {
						updatedText = text;
					}
					//CidssLogger.log(" @#@# updatedText ="+updatedText);
					completionList.add(updatedText);
					count++;
					//CidssLogger.log(" @#$#@ wordNode.getValidNextCharacters() ="+wordNode.getValidNextCharacters());
					//CidssLogger.log(" added words to queue and queue size ="+queue.size());
				}
	    	 }
	    	 //CidssLogger.log(" Final completion list ="+completionList.toString());
         return completionList;
     }
     
    // For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		////CidssLogger.log(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}