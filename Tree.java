import java.io.*;

/**
 * Tree class contains methods that are processing data.
 * 
 * @author () 
 * @version (1)
 */
public class Tree
{
    TreeNode root;
    int lang; //int to define language of the tree

    /**
     * Constructor for objects of class Tree
     */
    public Tree()
    {
        root = null;
    }

    public TreeNode getRoot()
    {
        return root;
    }

    public void setRoot(TreeNode newRoot)
    {
        root = newRoot;
    }

    /**
     * Check if tree is empty, by checking if root is null.
     */
    public boolean isEmptyTree()
    {
        if(root == null)
        {
            return true;
        } 
        else
        {
            return false;
        }
    }

    /**
     * Add to Dictionary method.
     */
    public boolean addToDictionary(String eng, String spanish)
    {
        TreeNode parent = root;
        TreeNode previous = root;
        TreeNode newTree = new TreeNode(eng, spanish);

        if (root == null)
        {
          root = newTree;
          return true;
        }
          else
          {            
            while (parent != null)
            {
                if(parent.word.equals(eng) && parent.spanishWord.equals(spanish))
                {
                  System.out.println("Oops! This word is already in Tree!");
                  return false;
                }
                
                previous = parent;

                if (eng.compareTo(parent.word) < 0)
                {
                    parent = parent.getLeft();
                }
                else
                {
                    parent = parent.getRight();
                }
            }

            if (eng.compareTo(previous.word) > 0)
            {
                previous = previous.setRightChild(newTree);
            }
            else
            {
                previous = previous.setLeftChild(newTree);
            }
            return true;
          }

    }
      
    /**
     * Writes to file. 
     */
    public void writeToTextFile(TreeNode saveNode)
    {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
                
        try
        {
            outputStream = new FileOutputStream("engDictionary.txt");
            printWriter = new PrintWriter(outputStream);
            
            write(saveNode, printWriter);
            
            
            printWriter.flush();
        }
        catch (IOException e)
        {
            System.out.println("Error! Something went wrong");
        }
        finally
        {
            if (printWriter != null)
            {
                printWriter.close(); 
            }
        }
        
    }
    
    /**
     * Traverses tree in preorder. 
     */
    public boolean write(TreeNode saveNode, PrintWriter printWriter)
    {
      if(saveNode == null)
      {
          return false;
      }
          
       printWriter.print(saveNode.word + "\t" + saveNode.spanishWord + "\n");
       write(saveNode.getLeft(), printWriter);
       write(saveNode.getRight(), printWriter);
      return true;
    }
    
    public void writeToFile()
    {
        writeToTextFile(root);
    }
    
    /**
     * reads from .txt file for english- spanish translation. Splits contents into two. after data
     * is taken puts everything into tree. 
     */
    public void readFromFile()
    {
        root=null;
        String nextLine;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;  

        try
        {
            fileReader = new FileReader("engDictionary.txt"); //reads from BinaryTree.txt
            bufferedReader = new BufferedReader(fileReader);
            nextLine = bufferedReader.readLine();

            while (nextLine!=null)
            {
                String[] list;
                list=nextLine.split("\t"); //extracts the items that the string contains

                String englishWord = list[0];
                String spanishWord = list[1];

                addToDictionary(englishWord, spanishWord);
                nextLine = bufferedReader.readLine();         
            }
            writeToFile();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Sorry, your file is not there!");
        }
        catch (IOException e)
        {
            System.out.println("Sorry, there has been a problem opening or reading from the file");
        }

        finally 
        {
            if (bufferedReader != null)
            {
                try 
                {
                    bufferedReader.close(); 
                }
                catch (IOException e) 
                {
                    System.out.println("An error occurred when attempting to close the file");
                }
            }
        }
    }
    
    /**
     * reads from file for spanish- english translation. Swaps the data the way that spanish
     * becomes first and english second in the list variable. 
     */
    public void readFromAFile() // For spanish-english translator
    {
        root=null;
        String nextLine;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;  

        try
        {
            fileReader = new FileReader("engDictionary.txt");
            bufferedReader = new BufferedReader(fileReader);
            nextLine = bufferedReader.readLine();

            while (nextLine!=null)
            {
                String[] list;
                list=nextLine.split("\t"); 
                
                String spanishWord = list[0];
                String englishWord = list[1];
               

                addToDictionary(englishWord, spanishWord);
                nextLine = bufferedReader.readLine();         
            } 
            writeToFile();
        }
        catch (FileNotFoundException e) 
        {
            System.out.println("Sorry, your file is not there!");
        }
        catch (IOException e)
        {
            System.out.println("Sorry, there has been a problem opening or reading from the file");
        }

        finally 
        {
            if (bufferedReader != null)
            {
                try 
                {
                    bufferedReader.close(); 
                }
                catch (IOException e) 
                {
                    System.out.println("An error occurred when attempting to close the file");
                }
            }
        }
    }
    
    
/**
 * Translation method. Prompts user to enter one word. Then looks for its translation.
 * Check if tree is empty. 
 */
    public TreeNode searchWord(String eng)
    {
     TreeNode marker = root;
     boolean found = false;
                   
     while(marker != null && !found)
     {
         if(isEmptyTree())
         {
             System.out.println("Tree is empty!");
             found = false;
         }
         if(marker.word.equals(eng))
         {
             found = true;
         }
           else 
           {
               if(eng.compareTo(marker.word) < 0)
               {
                   marker = marker.getLeft();                 
               }
               else
               {
                   marker = marker.getRight();                   
               }               
         }         
     }
     
     if(found == true)
     {
       System.out.println("");
       System.out.print("\b" + marker.getWord() + " = " + marker.getSpanishWord());
       System.out.println("");
       return marker;
     }
       else
       {
         return null;
       } 
       
   
     
    }
    
    /**
     * Does the inorder traveresal. If tree is empty returns.
     */
    public void inorderTraversal(TreeNode node)
    {
        if (node == null)
        {
            return;
        }
        inorderTraversal(node.getLeft());
        System.out.println(node.getSummaryData());
        inorderTraversal(node.getRight());
        return;
    }

    public void printInorderTree()
    {
        inorderTraversal(root);
    }

    /**
     * Method for deleting from dictionary. Check three different cases. 1. deleting node with only 
     * one child. 2. Deleting node with no children. 3. Deleting tree with two childeren. 
     */
    public boolean deleteWord(String a) 
    {
       
          TreeNode current = root;
          TreeNode prev = null;
          boolean found = false;
        if(isEmptyTree() == true)
            {
                System.out.println("The tree is empty!");
                return false;
            }
        while(found==false)
        {
            if(current == null)
            {
                System.out.println("Word not found!");
                return false;
            }
            else if(isEmptyTree() == true)
            {
                System.out.println("The tree is empty!");
                return false;
            }
            else if(a.equals(current.word))
            {
                found = true;
                 System.out.println("English word " + "'" + current.word + "'" +" with translation " + "'" + current.spanishWord + "'" + " DELETED successfully!");
            }
            else 
                {
                    if(a.compareTo(current.word) < 0)
                    {
                        prev = current;
                        current = current.getLeft();                 
                    }
                    else
                    {
                        prev = current;
                        current = current.getRight();
                    }               
                }   
        }
        if(current.getLeft() == null && current.getRight() == null) //if word has no children
        {
            if(a.compareTo(prev.word) < 0)
                    {
                        prev.setLeftChild(null);
                        return true;
                    }
                    else
                    {
                        prev.setRightChild(null);
                        return true;
                    }
        }
        if(current.getLeft() != null && current.getRight() == null) //if word has left child
        {
            if(a.compareTo(current.word) < 0)
            {
                prev.setLeftChild(current.getLeft());
                return true;
            }
            else
            {
                prev.setRightChild(current.getLeft());
                return true;
            }
        }
        else if(current.getLeft() == null && current.getRight() != null) //if word has right child
        {
            if(a.compareTo(current.word) < 0)
            {
                prev.setLeftChild(current.getRight());
                return true;
            }
            else
            {
                prev.setRightChild(current.getRight());
                return true;
            }
        }
        else if(current.getLeft() != null && current.getRight() != null) //if word has 2 children
        {
            TreeNode nodeToDel = current;
            current = nodeToDel.getLeft(); 
            prev=null;
            while(current.getRight() != null) //current becomes the rightmost child of the child left to the node to delete
            {
                prev = current;
                current = current.getRight();
            }

            nodeToDel.word = current.word; //changing the node to delete fields to that of the current
            nodeToDel.spanishWord = current.spanishWord;
            
            if(prev == null)
            {
                nodeToDel.setLeftChild(current.getLeft());
            }
            else 
            {
                prev.setRightChild(current.getLeft());
            }

            return true;
        }
     
        return false;
    }
  
}


