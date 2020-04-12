package unirio.trie;

public class Trie {
	private String key;
	private int maxChild = -1;
	private Trie[] children;

	public Trie() {
		this.key = null;
		this.maxChild = 0;
		this.children = new Trie[1];
	}
	
	public Trie(String key) {
		this.key = key;
		this.children = new Trie[1];
		this.maxChild = 0;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public String[] getNextWords(String income) {
		String[] suggestions = new String[3];
		String[] words = income.trim().split("\\s+");
		String wanted = words[words.length - 1];
		return suggestions;
	}
	
	public void insertWord(String income) {
		if (income == null) {
			return;
		}
		String[] words = income.trim().split("\\s+");
		for (String word : words) {
			System.out.println("inserting word --> " + word);
			this.findNode(word, 0);
		}
	}
	
	private void increaseChildrenSize() {
		int newSize = this.children.length + 1;
		Trie[] temp = this.children.clone();
		this.children = new Trie[newSize];
		for (int i = 0; i <= temp.length - 1; i++) {
			this.children[i] = temp[i];
		}
	}
	
	private void findNode(String word, int wordIndex) {
		if (wordIndex > word.length() - 1) {
			return;
		}
		for (Trie childNode : this.children) {
			if (childNode.getKey().equals(word.substring(wordIndex, wordIndex + 1))) {
				childNode.findNode(word, wordIndex + 1);
			} else {
				continue;
			}
			break;
		}
		this.createChild(word.substring(wordIndex));
	}
	
	private Trie createChild(String key) {
		if (this.maxChild == this.children.length - 1) {
			this.increaseChildrenSize();
		}
		this.children[this.maxChild++] = createWordCascade(key);
		return this;
	}
	
	private Trie createWordCascade(String key) {
		System.out.println("word --> " + key.length() + " // " + key);
		if (key.length() == 1) {
			return new Trie(key);
		} else {
			String actualKey = key.substring(0, 1);
			Trie child = new Trie(actualKey);
			String keyBreak = key.substring(1, key.length() - 1);
			System.out.println("word break --> " + keyBreak);
			return child.createChild(keyBreak);
		}
	}
}
