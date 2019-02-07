/**
 *Treenode class for seting and geting nodes.
 * 
 * @author (Juri Ilmjarv) 
 * @version (1)
 */
public class TreeNode
{
   public String word;
   public String spanishWord;

    public TreeNode leftChild;
    public TreeNode rightChild;
    
    /**
     * Constructor for objects of class TreeNodes
     */
    public TreeNode()
    {
        word = "";
        spanishWord = "";
        leftChild = null;
        rightChild = null;
    }
    
    
    public TreeNode (String word, String spanishWord)
    {
        this.word = word;
        this.spanishWord = spanishWord;
        
        leftChild = null;
        rightChild = null;
    }

   public String getWord()
   {
       return word;
   }
   
   public String getSpanishWord()
   {
       return spanishWord;
   }
   
   public TreeNode getLeft()
   {
      return leftChild;
   }
   
   public TreeNode getRight()
   {
      return rightChild;
   }
    
    public TreeNode setRightChild(TreeNode rightChild)
    {
        this.rightChild = rightChild;
        return rightChild;
    }
    
     public TreeNode setLeftChild(TreeNode leftChild)
    {
        this.leftChild = leftChild;
        return leftChild;
    }
    
    
    public String getSummaryData()
    {
        String data;		
        data = word + "\t" +  spanishWord;		
        return data;
    }
    
}
