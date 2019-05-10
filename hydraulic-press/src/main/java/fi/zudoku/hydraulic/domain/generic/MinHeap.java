package fi.zudoku.hydraulic.domain.generic;

import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;

/**
 * Custom implementation of a minimum heap, using a binary tree.
 */
public class MinHeap {
    
    private BinaryTree<HuffmanNode> binaryTree = new BinaryTree<>();
    
    /**
     * Adds a value into the heap.
     * @param a HuffmanNode to add. The heap uses the HuffmanNode.getAmount() sort the elements.
     */
    public void add(HuffmanNode a) {
        binaryTree.add(a.getAmount(), a);
    }
    /**
     * Calculates how many elements are in the heap
     * @return heap size
     */
    public int size(){
        return binaryTree.size();
    }
    
    /**
     * Finds and removes the element with the least value.
     * @return HuffmanNode with smalles getValue() or null
     */
    public HuffmanNode poll() {
        BinaryTreeNode<HuffmanNode> minNode = binaryTree.findMinNode();
        if (minNode != null) {
            binaryTree.remove(minNode.getKey());
            return minNode.getResult();
        }
        return null;
    }
    /**
     * Since HuffmanNode can change its value (leafnodes)
     * We need a way to reorder the heap, based on updated values.
     * This does just that.
     */
    public void reOrder() {
        BinaryTree<HuffmanNode> replacementBinaryTree = new BinaryTree<>();
        
        while(binaryTree.getRootNode() != null) {
            HuffmanNode minNode = poll();
            replacementBinaryTree.add(minNode.getAmount(), minNode);
        }
        
        binaryTree = replacementBinaryTree;
    }
}
