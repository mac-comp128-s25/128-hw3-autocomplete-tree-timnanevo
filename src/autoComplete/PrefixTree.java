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
        //TODO: complete me
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
        //TODO: complete me
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
        if(this.contains(prefix)){
            results.add(prefix);
        }
        for (int i = 0; i < prefix.length(); i++){
                char ch = prefix.charAt(i);
                if (!current.children.containsKey(ch)){
                    return null;
                }
                current = current.children.get(ch);    //get node at end of prefix
            }
        TreeNode temp = current;
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        for (int i = 0; i < this.size(); i++){
            temp.children.keySet();
        }
        
        //TODO: complete me
        return null;
    }

    public void preOrderTraversal(TreeNode node, StringBuilder sb){
        sb.append(node.toString());

    }

//  /**
// * Perform a preorder traversal.
// *
// * @param node  The local root
// * @param depth The depth
// * @param sb    The string buffer to save the output
// */
// private void preOrderTraverse(Node<E> node, int depth,
//                              StringBuilder sb) {
//    for (int i = 1; i < depth; i++) {
//        sb.append("  ");
//    }
//    if (node == null) {
//        sb.append("null\n");
//    } else {
//        sb.append(node.toString());
//        sb.append("\n");
//        preOrderTraverse(node.left, depth + 1, sb);
//        preOrderTraverse(node.right, depth + 1, sb);
//    }
// }


    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
