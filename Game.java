import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.List;
import java.util.Collection;
/**
 *  This class is the main class of the "FRIENDS: An Interactive Videogame" application based on the TV show "Friends".  
 *  Users can:
 *  - walk around the Friends basic set. (13 rooms)
 *  - interact with items in the rooms (grabbing and dropping items)
 *  - interact with the main characters of the series (talking to them)
 *  The game consists of completing tasks and collecting enough money to "save central perk from closing down"
 * Each task has a certain amount of subtasks that you have to complete that involve different rooms, items and characters
 *  
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class:
 *  - Creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  
 *  - Evaluates and executes the commands that the parser returns.
 *  - Prints all of the what needs to be displayed to the terminal
 * 
 * @author  Michael Kölling, David J. Barnes and Ana Clara Monteiro
 * @version 27/11/2024
 */
public static void main(String[] args) 
{
        Game game = new Game();
        game.play();
}

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Task currentTask;
    private Player currentPlayer;
    private Character currentCharacter;
    private HashMap<String, Room> allRooms;
    private HashMap<String, Character> charactersAndNames;
    private HashMap<String, Item> itemsAndNames;
    private ArrayList<Task> tasksArray;
    private Iterator<Task> tasksIterator;
    private ArrayList<Room> keepingTrackRoomsEntered;
    private boolean canGetFinale;
    
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        allRooms = new HashMap<>();//map a room with a keyword to find it
        tasksArray = new ArrayList<>();
        charactersAndNames = new HashMap<>();
        itemsAndNames = new HashMap<>();
        keepingTrackRoomsEntered = new ArrayList<>();
        createRooms();
        createPlayers();
        createItems();
        createCharacters();
        createTasks();
    }
    
    /**
     * Create all the rooms and link their exits together. 
     * Add Rooms to a map that maps the Room name with the Room object
     */
    private void createRooms()
    {
        Room centralPerk, commonHall, girlsApartment, guysApartment, monicaBedroom, rachelBedroom, chandlerBedroom, joeyBedroom, rossEntranceHall, rossApartment, phoebeEntranceHall, phoebeApartment, mysteryCellar;
      
        // create the rooms
        centralPerk = new Room("in Central Perk coffee house");
        allRooms.put("central perk", centralPerk); //we use this array when creating the items in createItem() method
        commonHall = new Room("in the Hall");
        allRooms.put("common hall", commonHall);
        girlsApartment = new Room("inside Monica's and Rachel's apartment");
        allRooms.put("girls apartment", girlsApartment);        
        guysApartment = new Room("inside Chandler's and Joey's apartment");
        allRooms.put("guys apartment", guysApartment);  
        monicaBedroom = new Room("in Monica's bedroom");
        allRooms.put("monicas bedroom", monicaBedroom);  
        rachelBedroom = new Room("in Rachel's bedroom");
        allRooms.put("rachels bedroom", rachelBedroom);  
        chandlerBedroom = new Room("in Chandler's bedroom");
        allRooms.put("chandlers bedroom", chandlerBedroom);  
        joeyBedroom = new Room("in Joey's bedroom");
        allRooms.put("joeys bedroom", joeyBedroom);  
        rossEntranceHall = new Room("outside Ross's apartment");
        allRooms.put("ross entrance hall", rossEntranceHall);  
        rossApartment = new Room("inside Ross's apartment");
        allRooms.put("ross apartment", rossApartment);  
        phoebeEntranceHall = new Room("outside Phoebe's apartment");
        allRooms.put("phoebes apartment", phoebeEntranceHall);  
        phoebeApartment = new Room("inside Phoebe's apartment");
        allRooms.put("phoebes apartment", phoebeApartment);  
        mysteryCellar = new RandomRoom("in the Mystery Cellar");               //random Room (no exits)
        allRooms.put("mystery cellar", mysteryCellar);                                    
        
        // initialise room exits
        centralPerk.setExit("up", commonHall);
        centralPerk.setExit("south", rossEntranceHall);
        centralPerk.setExit("south-west", phoebeEntranceHall);
        centralPerk.setExit("down", mysteryCellar);
        
        commonHall.setExit("down", centralPerk);
        commonHall.setExit("east", girlsApartment);
        commonHall.setExit("west", guysApartment);
        
        girlsApartment.setExit("west", commonHall);
        girlsApartment.setExit("north-east", monicaBedroom);
        girlsApartment.setExit("south-east", rachelBedroom);
        
        rachelBedroom.setExit("south-west", girlsApartment);
        
        monicaBedroom.setExit("north-west", girlsApartment);
        
        guysApartment.setExit("east", commonHall);
        guysApartment.setExit("north-west", chandlerBedroom);
        guysApartment.setExit("south-west", joeyBedroom);
        
        chandlerBedroom.setExit("south-east", guysApartment);
        
        joeyBedroom.setExit("north-east", guysApartment);
        
        rossEntranceHall.setExit("north", centralPerk);
        rossEntranceHall.setExit("up", rossApartment);
        rossEntranceHall.setExit("west", phoebeEntranceHall);
        
        rossApartment.setExit("down", rossEntranceHall);
        
        phoebeEntranceHall.setExit("east", rossEntranceHall);
        phoebeEntranceHall.setExit("north-east", centralPerk);
        phoebeEntranceHall.setExit("up", phoebeApartment);
        
        phoebeApartment.setExit("down", phoebeEntranceHall);
        
        currentRoom = centralPerk;  // start game in the coffee house
        
        Room.setRooms(allRooms);
    }
    
      /**
     * Creates all the items, setting them to their respective rooms. 
     */
    private void createItems()
    {
        //create items for each room
        Item sofaCentralPerk, paymentBarCentralPerk, coffeePotBarCentralPerk, mugBarCentralPerk, foosballTableGuysApartment, hugsyJoeyBedroom, pieCommonHall, candlesPhoebeApartment, guitarPhoebesApartment, flowersGirlsApartment, sofaGirlsApartment, lampRossEntranceHall;
        
        Room centralPerk = allRooms.get("central perk"); //get room from array of all rooms to add their items to them 
        sofaCentralPerk = new Item("orange-sofa", 100, "orange"); 
        itemsAndNames.put(sofaCentralPerk.getName(), sofaCentralPerk);
        centralPerk.addItem(sofaCentralPerk);
        paymentBarCentralPerk = new Item("table", 1000, "brown");
        itemsAndNames.put(paymentBarCentralPerk.getName(), paymentBarCentralPerk);
        centralPerk.addItem(paymentBarCentralPerk);
        coffeePotBarCentralPerk = new Item("coffee-pot", 5, "brown");
        itemsAndNames.put(coffeePotBarCentralPerk.getName(), coffeePotBarCentralPerk);
        centralPerk.addItem(coffeePotBarCentralPerk);
        mugBarCentralPerk = new Item("mug", 2, "white");
        itemsAndNames.put(mugBarCentralPerk.getName(), mugBarCentralPerk);
        centralPerk.addItem(mugBarCentralPerk);
        
        Room guysApartment = allRooms.get("guys apartment");
        foosballTableGuysApartment = new Item("foosball table", 100, "green");
        itemsAndNames.put(foosballTableGuysApartment.getName(), foosballTableGuysApartment);
        guysApartment.addItem(foosballTableGuysApartment);
        
        Room girlsApartment = allRooms.get("girls apartment");
        flowersGirlsApartment = new Item("flowers", 5, "pink");
        itemsAndNames.put(flowersGirlsApartment.getName(), flowersGirlsApartment);
        girlsApartment.addItem(flowersGirlsApartment);
        sofaGirlsApartment = new Item("sofa", 100, "purple");
        itemsAndNames.put(sofaGirlsApartment.getName(), sofaGirlsApartment);
        girlsApartment.addItem(sofaGirlsApartment);
        
        Room joeyBedroom = allRooms.get("joeys bedroom");
        hugsyJoeyBedroom = new Item("hugsy", 2, "black");
        itemsAndNames.put(hugsyJoeyBedroom.getName(), hugsyJoeyBedroom);
        joeyBedroom.addItem(hugsyJoeyBedroom);
        
        Room commonHall = allRooms.get("common hall");
        pieCommonHall = new Item("smushed-pie", 1, "orange");
        itemsAndNames.put(pieCommonHall.getName(), pieCommonHall);
        commonHall.addItem(pieCommonHall);
        
        Room phoebeApartment = allRooms.get("phoebes apartment");
        candlesPhoebeApartment = new Item("candles", 1, "yellow");
        itemsAndNames.put(candlesPhoebeApartment.getName(), candlesPhoebeApartment);
        phoebeApartment.addItem(candlesPhoebeApartment);
        guitarPhoebesApartment = new Item("guitar", 10, "brown");
        itemsAndNames.put(guitarPhoebesApartment.getName(), guitarPhoebesApartment);
        phoebeApartment.addItem(guitarPhoebesApartment);
        
        Room rossEntranceHall = allRooms.get("ross entrance hall");
        lampRossEntranceHall = new Item("lamp", 1, "yellow");
        itemsAndNames.put(lampRossEntranceHall.getName(), lampRossEntranceHall);
        rossEntranceHall.addItem(lampRossEntranceHall);
        
    }
    
    /**
     *  Creates the players in the game
     */
    private void createPlayers()
    {
        Player player1;
        player1= new Player("Ana");
        currentPlayer = player1;
    }
    
    /**
     *  Creates the characters of the game, linking them to their respective Rooms.
     */
    private void createCharacters()
    {
        Character monica, rachel, phoebe, chandler, joey, ross, gunther, janice, paulo;
        monica = new Character("monica", allRooms.get("girls apartment"), "This apartment is so dirty, I can't concentrate on anything you are saying");
        charactersAndNames.put(monica.getName(), monica);
        rachel = new Character("rachel", allRooms.get("rachels bedroom"), "I am so tired, I just want to get some sleep");
        charactersAndNames.put(rachel.getName(), rachel);
        phoebe = new Character("phoebe", allRooms.get("phoebes apartment"), "Hi! I just got finished with a massage client, but I'll be right down to the coffee house");
        charactersAndNames.put(phoebe.getName(), phoebe);
        chandler = new Character("chandler", allRooms.get("central perk"), "I'm not that good with the advice... can I interest you in a sarcastic comment?");
        charactersAndNames.put(chandler.getName(), chandler);
        joey = new Character("joey", allRooms.get("guys apartment"), "How you doin?");
        charactersAndNames.put(joey.getName(), joey);
        ross = new Character("ross", allRooms.get("ross apartment"), "Hello.... I can't believe Rachel is with that Paulo guy...");
        charactersAndNames.put(ross.getName(), ross);
        gunther = new Character("gunther", allRooms.get("central perk"), "Dude I already told you what's going on, let me work my shift");
        charactersAndNames.put(gunther.getName(), gunther);
        janice = new Character("janice", allRooms.get("central perk"), "OH. MY. GOD");
        charactersAndNames.put(janice.getName(), janice);
        paulo = new Character("paulo", allRooms.get("girls apartment"), "Ah yes, parlo solo italiano");
        charactersAndNames.put(paulo.getName(), paulo);
    }
    
    /**
     *  Creates the Tasks of the game and set the current task to the first time created. 
     */
    private void createTasks()
    {
        //task 1 (cp all): cover rachel's shift coffee pot (grab), (mug)(grab)
        //task 2 (cp all): talk to chandler (chandler), talk to janice (janice), talk to chandler (chandler)
        //task 3 (ra, ga): talk to ross (ross), talk to paulo (paulo)
        Task task0, task1, task2, task3;
        Item[] task0SubtasksItems = {itemsAndNames.get("coffee-pot"), itemsAndNames.get("mug")};       //item Player has to grab for each subtask
        task0 = new Task("Cover Rachel's shift: ", 50 , task0SubtasksItems);
        tasksArray.add(task0);
        
        Character[] task1SubtasksCharacters = {charactersAndNames.get("chandler"), charactersAndNames.get("janice"), charactersAndNames.get("chandler")}; //each character the PLayer has to talk to to finish the task
        String [] task1SubtasksCharactersDialogue = {"I need to break up with Janice. I'm the WORST at breakups and Phoebe is not here to help me. Will you talk to Janice for me pleaseeeeeee?", "OH. MY. GOD. Chandler wants to break up with me? AGAIN? I can't believe this! Thank you for telling me at least", "Thank you so much for that. I know, I have no dignity, but maybe this money will make up for it?"}; //the dialogue each of the characters you talk to to complete the task will have for this specific task
        task1 = new Task("help Chandler break up with Janice: ", 100 , task1SubtasksCharacters, task1SubtasksCharactersDialogue);
        tasksArray.add(task1);
        
        Character[] task2SubtasksCharacters = {charactersAndNames.get("ross"), charactersAndNames.get("paulo"), charactersAndNames.get("rachel"), charactersAndNames.get("ross")}; //each character the PLayer has to talk to to finish the task
        String [] task2SubtasksCharactersDialogue = {"Rachel and I are meant to be together. She's not meant to be with that Paulo guy... I'd pay someone to break them up..." , "Break up with Rachelle? For you? claro que si bellisima", "Paulo told you he'd break up with me for you? I can't believe he would do that! I am SO DONE with him! (starts crying)", "You got them to break up? Thank you so much for this, I have to go talk to Rachel."}; //the dialogue each of the characters you talk to to complete the task will have for this specific task
        task2 = new Task("help Ross out with Rachel: ", 250 , task2SubtasksCharacters, task2SubtasksCharactersDialogue);
        tasksArray.add(task2);
        
        
        tasksIterator = tasksArray.iterator();
        currentTask = tasksIterator.next();
    }
    
    /**
     *  When the Player "talk-to" a Character, this checks if by talking to that Character a part of a Task is completed
     */
    private void checkIfTalkCompletesTask()
    {
        if(currentTask.getCurrentNeedsToTalk()!=null){
            String currentNeedTalkCharacter = currentTask.getCurrentNeedsToTalk().getName();      //name of the character that has to be talked to in order to complete the current part of the task
            if(currentNeedTalkCharacter.equals(currentCharacter.getName())){      //currentNeedTalker equals the character the Player just talked to  
                subTaskCompletion();
                switchToNextInTask();
            }
        }
    }
    
    /**
     *  When the Player grabs an Item, this checks if by grabbing that Item a part of a Task is completed
     *  @param grabbedItem (Item)   
     */
    private void checkIfGrabCompletesTask(Item grabbedItem)
    {
        String currentNeedsGrab = currentTask.getCurrentNeedsGrab().getName();      //name of the character that has to be talked to in order to complete the current part of the task
        if(currentNeedsGrab.equals(grabbedItem.getName())){      //currentNeedTalker equals the character the Player just talked to  
            subTaskCompletion();
            switchToNextInTask();
        }
    }
    
    /**
     *  Prints out a message of completing a subtask. Also prints
     *  how many subtasks you have completed for that Task  
     */
    private void subTaskCompletion()
    {
        System.out.println("You just completed a subtask");
        System.out.println("Task progress: " + (currentTask.getIndexOfCurrentSubtask()+1) + "/" + currentTask.getNumOfSubtasks() + "\n");
    }
    
    /**
     *  Goes to next subtask. If all subtasks are completed, it completes the task (giving the respective reward to the player) and goes to the next task. If all tasks are completed, you won the game!
     */
    private void switchToNextInTask(){
        if(currentTask.hasNextSubtask()){ 
            currentTask.switchToNextSubtask();
            System.out.println("Next subtask...");
            System.out.println(currentTask.getCurrentTaskStringLong());
        }
        else{
            if(tasksIterator.hasNext()){
                currentPlayer.addMoney(currentTask.getRewardValue());
                currentTask = tasksIterator.next();
                System.out.println("~~" + currentTask.getRewardValue() + " coins have been added to your pocket. ~~  \n ~~ You now have " + currentPlayer.getMoney() + " coins ~~ \n");
                System.out.println("---NEXT TASK--");
                printTaskDescription();
            }
            else{
                System.out.println("--YOU COMPLETED ALL OF THE TASKS-- \n 'get-finale' has been enabled.");
                canGetFinale= true;
            }
        }
    }
    
    /**
     * Decides what random ending the Player gets and prints out finale to the terminal
     * finale 1: becoming a broke gambler, the coffee house does not get saved
     * finale 2: saving central perk
     */
    private void winGame()
    {
        currentRoom = allRooms.get("central perk");                      //central perk is where the finale is
        System.out.println("After collecting the money, you decide to head back o Central Perk and see if this money will save the coffee house. \n You talk to gunther:");
        System.out.println("gunther: oh, you actually recollected the money. I did not think you took me seriously because.. let's be honest... you're not \n successfull. But since you went through all of this trouble to collect the money, I'll try my best to convince the boss to keep it open");
        System.out.println("(gunther walks into storage) \n ... \n (gunther walks out of storage)");
        Random random = new Random();
        int finaleNum = random.nextInt(2);
        if(finaleNum == 0){
            System.out.println("I talked to the manager and he told me that he does not care about you and your sad group of losers. This is becoming a casino though, so maybe consider gambling \n that money here. Who knows, If you win big you might be able to reopen the coffee house someday...");
            System.out.println("\n FINALE: YOU BECAME A BROKE GAMBLER");
            System.out.println("Central Perk closed, only to become \"Gambling Nights\", a night-time casino. The gang all reassured you that Central Perk was not the only thing that connected \n all of you, and that they would continue making an effort to see you even if you lived far away. \n THat turned out to be a lie. You continued seeing them less and less, and in hopes to win enough money to reopen the coffee house someday, you became a compulsive gambler ");
            
        }
        else{
            System.out.println("I talked to the manager and he told me that we can keep the coffee house! Great news for me, I get to see Rachel everyday");
            System.out.println("\n FINALE: YOU SAVED CENTRAL PERK");
            System.out.println("You and the rest of your friends continue to enjoy coffee in central perk... and the story goes on as usual");
        }
    }
    
    /**
     * Prints a description of the current task and the task's reward
     */
    private void printTaskDescription()
    {
        System.out.println(currentTask.getCurrentTaskStringLong());
        System.out.println("REWARD: " + currentTask.getRewardValue());
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    private void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Friends Interactive Adventure!");
        System.out.println("If you are a Friend's fanatic, this is the game for you! \n");
        System.out.println("You have just found out that Central Perk is planning on going back back to being a bar that closing permanently at daytime.");
        System.out.println(" --------------------------YOUR MISSION: SAVE CENTRAL PERK--------------------------");
        System.out.println("Central Perk is yours and the gangs favorite place to be! If the coffee house closes, who knows if the group will survive");
        System.out.println("You ask Gunther if there is anything you can do to save Central Perk and he says that if you raise 1000 dollars by the end of the day, his boss will consider it \n --COMPLETE ALL OF THE TASKS AND SAVE UP THE REQUIRED MONEY TO WIN ---");
        
        System.out.println("\nFIRST TASK: " + currentTask.getCurrentTaskStringLong());
        System.out.println();
        System.out.println(" -- Type 'help' if you need help. --- \n");
        
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("grab")){
            grabItem(command);
        }
        else if(commandWord.equals("drop")){
            dropItem(command);
        }
        else if(commandWord.equals("talk-to")){
            talkTo(command);
        }
        else if(commandWord.equals("backtrack")){
            backTrack();
        }
        else if (commandWord.equals("get-finale")) {
            wantToQuit = getFinale();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:
    /**
     * Print out some help information.
     * Here we print:
     * - A list of the command words
     * - The current task, t
     * - The Item in the Player's Backpack,
     * - the money in Player's pocket
     * - The Room description (with exits and items in room)
    */ 
    private void printHelp() 
    {
        System.out.println("\nYour command words are:");
        parser.showCommands();
        System.out.println("\nYour current task is: " + currentTask.getDescription());
        System.out.println("Items in your backpack: " + currentPlayer.getStringItemsWithPlayer());
        System.out.println("Money in pocket: " + currentPlayer.getMoney());
        System.out.println(currentRoom.getLongDescription());
    }
    
    /** 
     * Try to go to another Room in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message and display the possible exits.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            System.out.println("Exits: " + currentRoom.getExitString());
            return;
        }

        String direction = command.getSecondWord();
        String thisRoom = currentRoom.getShortDescription();
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getNextRoom(direction);
        
        if (nextRoom == null) {
            System.out.println("There's no room there, are you sure that's the right direction?");
        }
        else {
            Room currentRoom = (Room) nextRoom;
            addTrackRooms(currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    /** 
     * adds Room to an ArrayList that keeps track of the Rooms the Player has entered
     */
    private void addTrackRooms(Room roomEntered)
    {
        keepingTrackRoomsEntered.add(roomEntered);
    }
    
    /** 
     * Checks if the requirements (Tasks) to be able to complete the game have been met. 
     * If they have, this command wins the game 
     * If they haven't, it lets you know you still have Tasks to complete
    */
    private boolean getFinale()
    {
        if (canGetFinale){
            winGame();
            return true;
        }
        else{
            System.out.println("You have not yet collected all of the money to save Central Perk.... complete all of the tasks to use this command");
            return false;
        }
    }
    /** 
     * Tries to grab item. If the item exists, it checks if if the item's weight exceeds the limit of weight that the Player can carry. 
     * If it passes the limit, it does not pick up the item. 
     */
    private void grabItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Grab what?");
            System.out.println("These are the items in the room:");
            currentRoom.getItems();
            return;
        }
        else{
        
            String itemString = " " + command.getSecondWord() + " "; //The spaces ensures that when I compare it to the string with all of the items it doesnt accidentally select another item that contains this word
            String allItemsInCurrentRoom = " " + currentRoom.getItems() + " ";
            
            Item currentItem = currentRoom.findItemByName(itemString.trim()); //find item thrugh name insterted
            
            if(currentItem!= null){ 
                if(currentPlayer.checkIfItemCanAdd(currentItem)){  //true if maxWeight not exceeded by adding the item, false if maxWeight exceedes by grabbing the item
                    currentPlayer.addItemPlayer(currentItem); 
                    System.out.println("\n" + itemString + "has been grabbed");
                    checkIfGrabCompletesTask(currentItem);
                }
                else{
                    if(currentItem.getWeight() > currentPlayer.maxBackpackWeight()){     //if the item's weight is more than a player could carry even if their backpack was completely empty
                       System.out.println(itemString + "is too heavy for you to carry" ); 
                    }
                    else{
                        if(allItemsInCurrentRoom.contains(currentItem.getName())){
                            System.out.println("You cannot grab the same object twice");
                        }
                        else{
                            System.out.println("Your backpack cannot carry this much weight. Try dropping another item before picking this one up");
                        }
                    }
                }
            }
            else{
                System.out.println(itemString + "literally does not exist");
            }
        }
    }
    
    /** 
     * "drop" was entered. Drops an item if that item has been grabbed previously. 
     * @return true, if this command drops the item, false otherwise.
     */
    private boolean dropItem(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Drop what?");
            System.out.println("The current items in your backpack are: ");
            System.out.println(currentPlayer.getStringItemsWithPlayer());
            return false;
        }
        else {
            String itemString = " " + command.getSecondWord() + " "; //The spaces ensures that when I compare it to the string with all of the items it doesnt accidentally select another item that contains this word
            String allItemsInBackpack = " " + currentPlayer.getStringItemsWithPlayer() + " ";
            Item currentItem = currentPlayer.findItemPlayer(itemString.trim()); //find item thrugh name insterted
            if(currentItem != null){
                currentPlayer.dropItem(currentItem.getName());
                System.out.println(currentItem.getName() + " has been dropped");
            }
            else{
                System.out.println(command.getSecondWord() + " is not an Item in your backpack");
                System.out.println("The current items in your backpack are: " + currentPlayer.getStringItemsWithPlayer());
            }
            
            boolean possesionOfItem = currentPlayer.isItemWithPlayer(itemString.trim()); //find item thrugh name insterted
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "talk-to (character's name)"  was entered. 
     * it Prints the character's dialogue.  
     * If the command has no second word, display the people in the Room the Player can "talk-to". 
     * @return true, if this command talks to a character, false otherwise.
     */
    private boolean talkTo(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Talk to who?");
            System.out.println("The people in this room are: ");
            System.out.println(currentRoom.getStringCharactersInRoom());
            return false;
        }
        else{
            String characterString = " " + command.getSecondWord() + " ";
            currentCharacter = charactersAndNames.get(characterString.trim());
            if(currentCharacter != null){
                if(currentCharacter.getRoom() == currentRoom){
                    System.out.println(characterString + ": "  + currentCharacter.getSpeach());
                    checkIfTalkCompletesTask();                              //checks if talking to this Character was part of your current task. If it was, you completed part of a task!
                }
                else{
                    System.out.println(characterString.trim() + " is not " + currentRoom.getShortDescription());
                }
            }
            else{
                System.out.println("This person does not exist in the game... are you shure you typed their name right?");
            }
            return true;
        }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /** 
     * "Back" was entered. The Player will be transported to the previous Room they were in. 
     * If there is no previous Room, it displays a message and stays in the same Room.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean backTrack()
    {
        if(keepingTrackRoomsEntered.isEmpty()) {
            System.out.println("You can't backtrack any further.");
        }
        else {
            keepingTrackRoomsEntered.remove(keepingTrackRoomsEntered.size() - 1);
            if(keepingTrackRoomsEntered.isEmpty()){
                currentRoom = allRooms.get("central perk");
            }
            else{ 
                currentRoom = keepingTrackRoomsEntered.get(keepingTrackRoomsEntered.size() - 1);
            }
            System.out.println(currentRoom.getLongDescription());
        }
        return true;  // signal that we want to quit
    }
    
    /** 
     * Returns how many Rooms there are in this game.
     */
    private int getNumRooms()
    {
        return allRooms.size();
    }
    
    
}
