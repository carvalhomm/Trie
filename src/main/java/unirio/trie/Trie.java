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
		String[] words = income.trim().split("\\s+");
		String wanted = words[words.length - 1];
		return this.getSuggestions(wanted);
	}
	
	public void insertWord(String income) {
		if (income == null) {
			return;
		}
		String[] words = income.trim().split("\\s+");
		for (String word : words) {
			System.out.println("inserting word --> " + word);
			this.findInsertionNode(word, 0);
		}
	}
	
	private String[] getSuggestions(String search) {
		String[] suggestions = new String[3];
		Trie searchRoot = this.findNode(search, 0);
		boolean finished = false;
		if (searchRoot != null && searchRoot.maxChild > 0) {
			for (Trie child : searchRoot.children) {
				if  (finished) {
					break;
				}
				String[] childSuggestions = child.getChildWords(child.key);
				if (childSuggestions != null) {
					for (int i = 0; i <= childSuggestions.length - 1; i++) {
						if (suggestions[i] != null) {
							continue;
						}
						suggestions[i] = childSuggestions[i];
						if (i == 2 && suggestions[i] != null) {
							finished = true;
						}
					}
				}
			}
		}
		return suggestions;
	}
	
	private String[] getChildWords(String word) {
		String[] suggestions = new String[3];
		if (this.maxChild > 0) {
			suggestions[0] = word + this.key;
			return suggestions;
		}
		for (Trie child : this.children) {
			String[] childSuggestions = child.getChildWords(word + child.key);
			if (childSuggestions[2] != null) {
				return childSuggestions;
			} else {
				for (int i = 0; i <= childSuggestions.length - 1; i++) {
					if (suggestions[i] != null) {
						continue;
					}
					suggestions[i] = childSuggestions[i];
				}
			}
		}
		return suggestions;
	}
	
	private Trie findNode(String search, int wordIndex) {
		if (wordIndex > search.length() - 1) {
			return null;
		}
		for (Trie childNode : this.children) {
			Trie childRef = childNode.nodeKeyIsEqual(search, wordIndex);
			if (childRef != null) {
				Trie node = childNode.findNode(search, wordIndex + 1);
				return node != null ? node : this;
			} else {
				continue;
			}
		}
		return this;
	}
	
	private void increaseChildrenSize() {
		int newSize = this.children.length + 1;
		Trie[] temp = this.children.clone();
		this.children = new Trie[newSize];
		for (int i = 0; i <= temp.length - 1; i++) {
			this.children[i] = temp[i];
		}
	}
	
	private Trie nodeKeyIsEqual(String word, int wordIndex) {
		return this.getKey().equals(word.substring(wordIndex, wordIndex + 1)) ? this : null;
	}
	
	private void findInsertionNode(String word, int wordIndex) {
		if (wordIndex > word.length() - 1) {
			return;
		}
		for (Trie childNode : this.children) {
			Trie childRef = childNode.nodeKeyIsEqual(word, wordIndex);
			if (childRef != null) {
				childNode.findInsertionNode(word, wordIndex + 1);
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
