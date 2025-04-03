package autoComplete;

import java.util.ArrayList;
import java.util.Map;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    public PrefixTree(){
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        if (this.contains(word)){
            return;
        }
        TreeNode current = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)){
                TreeNode node = new TreeNode();
                node.letter = ch;
                current.children.put(ch, node);
                current = current.children.get(ch);
            }
            else{
                current = current.children.get(ch);
            }
        }
        current.isWord = true;
        this.size ++;
    }

    /**
     * Checks whether the word has been added to the tree
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word){
        TreeNode current = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if (!current.children.containsKey(ch)){
                return false;
            }
            current = current.children.get(ch);    
        }
        if (current.isWord == true){
            return true;
        }
        return false; 
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself).
     * The order of the list can be arbitrary.
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix){
        ArrayList<String> results = new ArrayList<>();
        TreeNode current = root;
        for (int i = 0; i < prefix.length(); i++){
                char ch = prefix.charAt(i);
                if (!current.children.containsKey(ch)){
                    return results;
                }
                current = current.children.get(ch);    //get node at end of prefix
            }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        performTraversal(current, sb, results);
        return results;
    }

    /**
    * Helper function to perform a preorder traversal to find words that start with a given prefix
    * @param node  The current tree node to visit
    * @param results The list of words that start with a given prefix
    * @param sb  A stringbuilder that keeps track of full words
    */
    public void performTraversal(TreeNode node, StringBuilder sb, ArrayList<String> results){
        if (node.isWord){
            results.add(sb.toString());
        }
        for (Map.Entry<Character, TreeNode> entry : node.children.entrySet()){
            sb.append(entry.getKey());
            performTraversal(entry.getValue(), sb, results);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
