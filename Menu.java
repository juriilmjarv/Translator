/**
 * Tester class creatse better user interface.
 * 
 * @author (Juri Ilmjarv) 
 * @version (a version number or a date)
 */
public class Menu
{
    Tree myTree;
    private String word;
    private String spanish;
    
    /**
     * Main method to run the whole program.
     */
    public static void main (String [] args)
    {
        Menu myTree = new Menu();
        myTree.processUserChoice();
    }

    /**
     * Constructor for objects of class Tester
     */
    public Menu()
    {
        myTree = new Tree();
    }

    /**
     * Displays menu with choices to user.
     */
    public void displayMenu()
    {
      System.out.println("");
      System.out.println("Please choose one of the following choices!");
      System.out.println("");
      System.out.println("1. ENGLISH - SPANISH");
      System.out.println("2. SPANISH - ENGLISH");
      System.out.println("3. PRINT DICTIONARY");
      System.out.println("4. EXIT PROGRAM");
      System.out.println("");
    }
    
    /**
     * Processes choice made by user and after choice was made, decides
     * what action to do.
     */
    public void processUserChoice()
    {
        int choice = 0;
  
        while (true)
        {
           displayMenu();
           choice = Genio.getInteger();
            if (choice == 1)
            {
                englishSpanish();
            }   
                else if (choice == 2)
                {
                    spanishEnglish();
                }    
                    else if (choice == 3)
                    {
                        print();
                    }
                       else if (choice == 4)
                       { 
                         System.out.println("GOODBYE!");
                         break;
                        }
     
        }
        
    }
    
    /**
     * Prompts user to enter english word and spanish eord, which will be added to tree.
     * NB! all added words must be written LOWER CASE letters.
     */
    public void add()
    {
       System.out.println("English word:");
       word = Genio.getString();
       System.out.println("Spanish word:");
       spanish = Genio.getString();
       System.out.println("Thanks!");
       myTree.addToDictionary(word, spanish);
       myTree.writeToFile();
       return;       
    }
    
    public void addSpanish()
    {
       System.out.println("Spanish word:");
       word = Genio.getString();
       System.out.println("English word:");
       spanish = Genio.getString();
       System.out.println("Thanks!");
       myTree.addToDictionary(word, spanish);
       myTree.writeToFile();
       return;       
    }
    
    
    /**
     * If tree is empty displays error message. If not, lets user to
     * search for spanish translation by inserting english word.
     * Gives time taken to translate if word was found, otherwise asks user, whether user wants
     * to add new word to dictioanry.
     */
    public void translate()
    {
       long startTime = System.nanoTime();
       myTree.readFromFile();
       String eng;
       System.out.println("");
       System.out.println("English word: ");
       System.out.print("");
       eng = Genio.getString();
       
       if(myTree.isEmptyTree())
       {
           System.out.println("Tree is empty!");
       } 
         else if(myTree.searchWord(eng) != null)
         {
             long endTime = System.nanoTime();
        
             long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
             System.out.println(" ");
             System.out.println("0."+duration+"ms time taken to search and display");
             return;
         }
          else
          {
              System.out.println("Word not found!");
               System.out.println("Would you like to add one? 'y' or 'n' ?");
               String decide = Genio.getString();
               if(decide.equals("y"))
               {                   
                   add();
                  
               }
                else if(decide.equals("n"))
                {
                    return;
                }
          }
         
    }
    
    /**
     * If tree is empty displays error message. If not, lets user to
     * search for english translation by inserting spanish word.
     */
    public void translateSpaEng()
    {
       long startTime = System.nanoTime();
       myTree.readFromAFile();
       System.out.println("");
       System.out.println("Spanish word: ");
       
       String spa = Genio.getString();
       if(myTree.isEmptyTree())
       {
           System.out.println("Tree is empty!");
       }
         else if(myTree.searchWord(spa) != null)
         {
             long endTime = System.nanoTime();
        
             long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
             System.out.println(" ");
             System.out.println("0."+duration+"ms time taken to search and display");
             return;
         }
          else
          {
               System.out.println("Word not found!");
               System.out.println("Would you like to add one? 'y' or 'n' ?");
               String decide = Genio.getString();
               if(decide.equals("y"))
               {                   
                   addSpanish();
                  
               }
                else if(decide.equals("n"))
                {
                    return;
                }
          }
         
         
    }
    
    
    
    /**
     * Method to delete english word. If tree is empty shows error message. If not, lets user to search
     * for word and deletes if found. otherwise word was not found.
     */
    
    public void delete()
    {
       if(myTree.isEmptyTree())
       {
           System.out.println("Tree is empty!");
       } 
         else
         {
           System.out.println("Word to delete: ");
           String word = Genio.getString();
        
           myTree.deleteWord(word);
           
           myTree.writeToFile();
         }
    }
    
    
    /**
     * Method to delete spanish word.If tree is empty shows error message. If not, lets user 
     * to search for word and deletes if found. otherwise word was not found.
     */
    public void deleteSpanish()
    {
       if(myTree.isEmptyTree())
       {
           System.out.println("Tree is empty!");
       } 
         else
         {
           myTree.readFromAFile();
           
           System.out.println("Word to delete: ");
           String word = Genio.getString();
        
           myTree.deleteWord(word);
           
           myTree.writeToFile();
           myTree.readFromAFile();
         }
    }
    
    
    
    /**
     * If tree is empty, displays error message. If not, displays dictionary to user.
     */
    public void print()
    {
                      
        if(myTree.isEmptyTree())
        {
           System.out.println("Tree is empty!");
        }
          else
          {
            myTree.printInorderTree();
                
           }
                     
    }
    
    
    /**
     * Menu fo English- spanish or spanish- english translator.
     */
    public void translateMenu()
    {
        System.out.println("");
        System.out.println("1. TRANSLATE");
        System.out.println("2. ADD TO DICTIONARY");
        System.out.println("3. DELETE FROM DICTIONARY");
        System.out.println("4. FINISH");
        System.out.println("");
    }
    
    /**
     * English spanish translator.
     */
    public void englishSpanish()
    {
        int choice2 = 0;
                                  
            while(true)
            {
             System.out.println("");
             System.out.println("English - Spanish translator.");
             translateMenu();             
             choice2 = Genio.getInteger();
             if(choice2 == 1)
             {
                translate();
             }
              else if(choice2 == 2)
              {
                  add();
              }
                else if(choice2 == 3)
                {
                    delete();
                }
                 else if(choice2 == 4)
                 {
                     break;
                 }
            
            }
          
    }
    
    
   /**
    * spanish- english translator.
    */
    public void spanishEnglish()
    {
        int choice2 = 0;
              
       
                    
            while(true)
            {
             System.out.println("");
             System.out.println("Spanish - English translator.");
             translateMenu();
             choice2 = Genio.getInteger();
             if(choice2 == 1)
             {
                translateSpaEng();
                myTree.readFromAFile();
             }
              else if(choice2 == 2)
              {
                  add();
              }
                else if(choice2 == 3)
                {
                    deleteSpanish();
                }
                 else if(choice2 == 4)
                 
                 {
                     break;
                 }
            
            }
          
    }
  }
    
