import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Task - a task that has to be completed as part of the game.
 * 
 * This class is part of the "FRIENDS: An Interactive Videogame" application. 
 * "FRIENDS: An Interactive Videogame" is an interactive application based on the TV show "Friends". 
 *
 *  A "Task" is a sequence of objectives for the Player which have to be completed as part of the game. 
 *  They can involve interacting with Characters and Items, and are broken down into smaller steps called "Subtasks". 
 *  Every time a Player completes a task, a "monetary reward" is provided.
 *  
 *  There are 2 types of tasks:
 *  -Character-related Tasks-: Involve talking to characters around different rooms 
 *  -Item-related Tasks-: Involve grabbing items around different rooms
 *  
 *  Every Task can have the following attributes: 
 *  - a description/explanation of what the task is
 *  - the monetary reward value for completing the task
 *  - a list of smaller steps that have to be completed in order to carry out a task (subtasks)
 *  - the current subtask the player is working on
 *  - items or characters associated with the task, depending on its type
 *  - dialogues (for character-related tasks)
 *  
 * @Ana Clara Monteiro 
 * @version 27/11/2024
 */
public class Task
{
    private String description; 
    private Item itemRelated;
    private ArrayList<Subtask> subTasks;
    private int moneyCompletion;
    private Subtask currentSubtask;
    private boolean hasCharacters;
    private Iterator<Subtask> subTasksIterator;
    String[] dialogueSwitches;
    
    
    /**
     * Creates a Task described as "taskDescription" if it's related to an Item
     * @param 
     * @param taskDescription   String   description of the task that's going to be printed to the terminal
     * @param item   Item   Item with which the Player has to interact with in order to complete the task 
     * @param subtasksNum   how many subtasks this task consist of 
     */
    public Task(String taskDescription, int moneyForTask, Item[] itemsArray)
    {
        description = taskDescription;
        moneyCompletion = moneyForTask;
        subTasks = new ArrayList<>();
        for(int i = 0; i< itemsArray.length; i++){  
            //create Subtask objects and adds them in an ArrayList 
            Subtask temporarySubTask = new Subtask(itemsArray[i]);
            subTasks.add(temporarySubTask);
        }
        subTasksIterator = subTasks.iterator();
        currentSubtask = subTasksIterator.next();
        //subtasks = subtasksNum;
    }
    
    /**
     * Creates a Task described as "taskDescription" if it's related to a Character
     * @param taskDescription   String     description of the task that's going to be printed to the terminal
     * @param character   Character    Character with which the Player has to interact with in order to complete the task 
     * @param subtasksNum   how many subtasks this task consist of 
     */
    public Task(String taskDescription,  int moneyForTask, Character[] charactersArray, String[] charactersDialogue)
    {
        dialogueSwitches = charactersDialogue;
        description = taskDescription;
        moneyCompletion = moneyForTask;
        subTasks = new ArrayList<>();
        for(int i = 0; i< charactersArray.length; i++){  
            //create Subtask objects and adds them in an ArrayList 
            Subtask temporarySubTask = new Subtask(charactersArray[i]);
            subTasks.add(temporarySubTask);
        }
        subTasksIterator = subTasks.iterator();
        currentSubtask = subTasksIterator.next();
        getCurrentNeedsToTalk().setSpeech(dialogueSwitches[getIndexOfCurrentSubtask()]);
    }

    /**
     * Returns description of the task
     */
    public String getDescription()
    {
        return description;
    }
    
     /**
     * returns the Character that has to be talked to in order to complete the current part of a Task (Subtask)
     */
    
    public Character getCurrentNeedsToTalk()
    {
        return currentSubtask.getCharacterRelated();      
    }
    
    public Item getCurrentNeedsGrab()
    {
        return currentSubtask.getItemRelated();      
    }
    
    public String getCurrentTaskStringLong()
    {
        return (description + currentSubtask.getDescription().toUpperCase());
    }
    
    /**
     * Checks if the item grabbed by the player is the item needed to complete the task
     * @return returns true if the item is in the backpack and false if it is not
     */
    public boolean WasFound(Item item)
    {
        return true;
    }
    
    public boolean WasFound(Character character)
    {
        return true;
    }

    /**
     * Changes the current subtask in this Task to the next subtask
     */
    public void switchToNextSubtask()
    {
        currentSubtask = subTasksIterator.next();
        if(getCurrentNeedsToTalk() !=null){
            getCurrentNeedsToTalk().setSpeech(dialogueSwitches[getIndexOfCurrentSubtask()]);
        }
    }
    
    private void setCharacterSpeech(){
        
    }
    
    public boolean hasNextSubtask()
    {
        return subTasksIterator.hasNext();
    }
    
    public Subtask getCurrentSubtask()
    {
        return currentSubtask;
    }
    
    /**
     * Returns the number of subtasks the task contains
     * @return int  num of Subtasks in Task
     */
    public int getNumOfSubtasks()
    {
        return subTasks.size();
    }
    
    /**
     * Returns the index the current subtask is in in the ArrayList of all Tasks
     * @return int  index of Subtask
     */
    public int getIndexOfCurrentSubtask()
    {
        return subTasks.indexOf(currentSubtask);
    }
    
    /**
     * Returns the monetary amount received get after completing a Task
     * @return int moneyCompletion  amount of money for this Task
     */
    public int getRewardValue()
    {
        return moneyCompletion;
    }
}
