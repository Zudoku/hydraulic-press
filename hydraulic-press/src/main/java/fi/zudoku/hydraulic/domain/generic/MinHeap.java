package fi.zudoku.hydraulic.domain.generic;

import fi.zudoku.hydraulic.domain.huffman.data.HuffmanNode;

public class MinHeap {
    
    private BinaryTree<HuffmanNode> binaryTree = new BinaryTree<>();
    
    public void add(HuffmanNode a) {
        binaryTree.add(a.getAmount(), a);
    }
    
    public int size(){
        return binaryTree.size();
    }
    
    public HuffmanNode poll() {
        BinaryTreeNode<HuffmanNode> minNode = binaryTree.findMinNode();
        if (minNode != null) {
            binaryTree.remove(minNode.getKey());
            return minNode.getResult();
        }
        return null;
    }
    
    public void reOrder() {
        BinaryTree<HuffmanNode> replacementBinaryTree = new BinaryTree<>();
        
        while(binaryTree.getRootNode() != null) {
            HuffmanNode minNode = poll();
            replacementBinaryTree.add(minNode.getAmount(), minNode);
        }
        
        binaryTree = replacementBinaryTree;
    }
}
