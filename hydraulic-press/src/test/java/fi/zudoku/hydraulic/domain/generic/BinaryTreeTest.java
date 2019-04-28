package fi.zudoku.hydraulic.domain.generic;

import junit.framework.Assert;
import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void testAddWorksForBinaryTree() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        
        binaryTree.add((byte) 5, "test");
        
        Assert.assertEquals("test", binaryTree.getRootNode().getResult());
    }
    
    @Test
    public void testAddInsertsLeftForLessThanValue() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        
        binaryTree.add((byte) 5, "test");
        binaryTree.add((byte) 3, "macaw");
        
        Assert.assertNotNull(binaryTree.getRootNode().getLeft());
        Assert.assertEquals("macaw", binaryTree.getRootNode().getLeft().getResult());
        
        Assert.assertNull(binaryTree.getRootNode().getRight());
    }
    
    @Test
    public void testAddInsertsRightForMoreThanValue() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        
        binaryTree.add((byte) 5, "test");
        binaryTree.add((byte) 8, "macaw");
        
        Assert.assertNotNull(binaryTree.getRootNode().getRight());
        Assert.assertEquals("macaw", binaryTree.getRootNode().getRight().getResult());
        
        Assert.assertNull(binaryTree.getRootNode().getLeft());
    }
    
    @Test
    public void testFindWorksForBinaryTree() {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        
        binaryTree.add((byte) 5, "test");
        
        Assert.assertEquals("test", binaryTree.find((byte) 5));
    }
    
}
