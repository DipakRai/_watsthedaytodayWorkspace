package watsthedaytoday;

import java.util.HashMap;
import java.util.Set;

class TrieNode {
	private HashMap<Character, TrieNode> children; 
	private String text;  // Maybe omit for space
	private boolean isWord;
	private boolean isAllCaps;
	private boolean startsWithCaps;
	
	/** Create a new IntTrieNode */
	public TrieNode()
	{
		children = new HashMap<Character, TrieNode>();
		text = "";
		isWord = false;
	}
	
	/** Create a new IntTrieNode given a text String to store in it */
	public TrieNode(String text)
	{
		this();
		this.text = text;
	}
	
	/** Return the IntTrieNode that is the child when you follow the 
	 * link from the given Character 
	 * @param c The next character in the key
	 * @return The IntTrieNode that character links to, or null if that link
	 *   is not in the trie.
	 */
	public TrieNode getChild(Character c)
	{
		return children.get(c);
	}
	
	/** Inserts this character at this node.
	 * Returns the newly created node, if c wasn't already
	 * in the trie.  If it was, it does not modify the trie
	 * and returns null.
	 * @param c The character that will link to the new node
	 * @return The newly created IntTrieNode, or null if the node is already 
	 *     in the trie.
	 */
	public TrieNode insert(Character c)
	{

		if (children.containsKey(c)) {
			return null;
		}
		TrieNode next = new TrieNode(text + c);
		children.put(c, next);
		return next;
	}
	
	/** Return the text string at this node */
    public String getText()
	{
		return text;
	}
	
    /** Set whether or not this node ends a word in the trie. */
	public void setEndsWord(boolean b)
	{
		isWord = b;
	}
	
	/** Return whether or not this node ends a word in the trie. */
	public boolean endsWord()
	{
		return isWord;
	}
	
	/** Return the set of characters that have links from this node */
	public Set<Character> getValidNextCharacters()
	{
		return children.keySet();
	}

	public boolean isAllCaps() {
		return isAllCaps;
	}

	public void setAllCaps(boolean isAllCaps) {
		this.isAllCaps = isAllCaps;
	}

	public boolean isStartsWithCaps() {
		return startsWithCaps;
	}

	public void setStartsWithCaps(boolean startsWithCaps) {
		this.startsWithCaps = startsWithCaps;
	}

	@Override
	public String toString() {
		return "IntTrieNode [children=" + children + ", text=" + text + ", isWord=" + isWord + ", isAllCaps="
				+ isAllCaps + ", startsWithCaps=" + startsWithCaps + "]";
	}

	
}