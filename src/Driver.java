import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Driver {

    //Creating my scanner
    static Scanner userInput = new Scanner(System.in);

    //Creating my doubly linked list
    private static DoublyLinkedList3<Vocab> doublyLinkedList3 = new DoublyLinkedList3<>();

    //method that will display the existing topics and their position
    public static String displayTopics(){
        String topics ="";
        if (doublyLinkedList3.getSize()==0){
            return "List is empty";
        }
        for (int i =0; i<doublyLinkedList3.getSize(); i++){
            if (doublyLinkedList3.get(i) == null){
                return String.valueOf((doublyLinkedList3.getSize()));
            }
            topics += (i+1) + " "+ doublyLinkedList3.get(i).getTopic() + "\n";
        }
        return "------------------------------\n" + "       Pick a topic       \n" + "------------------------------\n"+  topics + "0 Exit";
    }

    //method that will display the words of an existing topic based on the user's choice
    public static String  displayWords(int i){
        int index = i-1;
        if (doublyLinkedList3.getSize()==0){
            return "Error";
        }
        if (index >= doublyLinkedList3.getSize() || i<0){
            return "Enter a valid index.";
        }
        if (i==0){
            return "";
        }
        return doublyLinkedList3.get(index).displayVocab();
    }

    //method that inserts a topic after an existing topic based on the user's input for the index
    public static void insertAfter(int i){
        int index = i-1;
        if (index >= doublyLinkedList3.getSize() || i<0){
            System.out.println("Enter a valid index.");
        }
        if (i==0){
            return;
        }
        Vocab topic = createTopic();
        doublyLinkedList3.addTopicAfter(index, topic);
    }


    //method that inserts a topic before an existing topic based on the user's input for the index
    public static void insertBefore(int i){
        int index = i-1;
        if (index >= doublyLinkedList3.getSize() || i<0){
            System.out.println("Enter a valid index.");
        }
        if (i==0){
            return;
        }
        Vocab topic = createTopic();
        doublyLinkedList3.addTopicBefore(index, topic);
    }

    //method that creates a topic
    public static Vocab createTopic(){
        System.out.println("Enter a topic name:");
        String topicName = userInput.nextLine();
        Vocab topic = new Vocab(topicName);
        System.out.println("Enter a word - to quit press Enter: ");
        String words = userInput.nextLine();
        String [] wordsArr = words.split(" ");
        for (int i =0 ; i< wordsArr.length; i++){
            topic.addAWord(wordsArr[i]);
        }
        return topic;
    }

    //remove a topic method
    public static void removeTopic(int i){
        int index = i-1;
        if (index >= doublyLinkedList3.getSize() || i<0){
            System.out.println("Enter a valid index.");
        }
        if (i==0){
            return;
        }
        doublyLinkedList3.removeNode(index);
    }

    //Method to read a file and create topics with words out of it
    public static void fileTopics(){
        System.out.println("Enter the name of the input file:");
        String inputFile = userInput.nextLine();
        try {
            Scanner streamObject = new Scanner(new FileInputStream(inputFile));
            String line;
            Vocab topic = null;
            while (streamObject.hasNextLine()){
                line = streamObject.nextLine();
                if (line.isEmpty()){
                    continue;
                }
                if (line.charAt(0)== '#'){
                    topic = new Vocab(line.substring(1));
                    doublyLinkedList3.addTopicBefore(0,topic);
                }
                else {
                    topic.addAWord(line);
                }
            }
            streamObject.close();
        }
        catch (IOException e){
            System.out.println("Exception found: " + e.getMessage());
        }
    }
    public static void writeInFile(){
        System.out.println("Enter the name of the output file:");
        String outputFile = userInput.nextLine();
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(outputFile));
            for (int i =0; i< doublyLinkedList3.getSize(); i++){
                writer.println("#"+doublyLinkedList3.get(i).getTopic());
                ArrayList<String> words = doublyLinkedList3.get(i).getWords();
                for (int index = 0 ; index< words.size(); index++){
                    writer.println(words.get(index));
                }
                writer.println("");
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error has occured: " + e.getMessage());
        }
    }

    public static void modifyWord(int i){
        int index = i-1;
        if (index >= doublyLinkedList3.getSize() || i<0){
            System.out.println("Enter a valid index.");
        }
        if (i==0){
            return;
        }
        String modifChoice = modifyMenu();
        userInput.nextLine();
        if (modifChoice==null){
            return;
        }
        if (modifChoice.equals("a")){
            System.out.println("Type a word and press Enter, or press Enter to end input");
            String newWord = userInput.nextLine();
            doublyLinkedList3.get(index).addAWord(newWord);
        }
        else if (modifChoice.equals("r")){
            System.out.println("Enter Your Choice:");
            String newWord = userInput.nextLine();
            doublyLinkedList3.get(index).removeAWord(newWord);
        }
        else if (modifChoice.equals("c")){
            System.out.println("Enter the word: ");
            String oldWord = userInput.nextLine();
            System.out.println(("Enter new word: "));
            String newWord = userInput.nextLine();
            doublyLinkedList3.get(index).changeAWord(oldWord,newWord);
        }
        else{
            System.out.println("There's an error in the function");
        }
    }

    public static String modifyMenu (){
        System.out.println("-----------------------------\n" +
                "      Modify Topics Menu      \n" +
                "-----------------------------\n" +
                "a add a word\n" +
                "r remove a word\n" +
                "c change a word\n" +
                "0 Exit");
        String input = userInput.next();
        if (!input.equals("a") && !input.equals("r") && !input.equals("c")){
            return null;
        }
        return input;
    }


    public static void main(String[] args) {
        //creating default topics with words inside
        Vocab topic1 = new Vocab("Sports");
        topic1.addAWord("Hockey");
        topic1.addAWord("Ball");
        topic1.addAWord("Puck");
        topic1.addAWord("Racing");
        topic1.addAWord("Running");
        topic1.addAWord("Running shoes");
        topic1.addAWord("Tennis racket");

        Vocab topic2 = new Vocab("Food");
        topic2.addAWord("Candy");
        topic2.addAWord("Burger");
        topic2.addAWord("Cheese");
        topic2.addAWord("Hot-dog");

        doublyLinkedList3.addTopicBefore(0,topic1);
        doublyLinkedList3.addTopicAfter(0, topic2);

        int choice;
        do {
            System.out.println("-----------------------------\n" +
                            "Vocabulary Control Center\n" +
                            "-----------------------------\n" +
                            "1 browse a topic\n" +
                            "2 insert a new topic before another one\n" +
                            "3 insert a new topic after another one\n" +
                            "4 remove a topic\n" +
                            "5 modify a topic\n" +
                            "6 search topics for a word\n" +
                            "7 load from a file\n" +
                            "8 show all words starting with a given letter\n" +
                            "9 save to file\n" +
                            "0 exit\n" +
                            "-----------------------------\n" +
                            "Enter Your Choice:"
            );
            choice=userInput.nextInt();
            if (choice==1){
                int input;
                do {
                    System.out.println(displayTopics());
                    input = userInput.nextInt();
                    System.out.println(displayWords(input));
                }
                while (input!=0);
            }
            else if (choice==2){
                System.out.println(displayTopics());
                int input = userInput.nextInt();
                userInput.nextLine();
                insertBefore(input);
            }
            else if (choice==3){
                System.out.println(displayTopics());
                int input = userInput.nextInt();
                userInput.nextLine();
                insertAfter(input);
            }
            else if (choice==4){
                System.out.println(displayTopics());
                int input = userInput.nextInt();
                userInput.nextLine();
                removeTopic(input);
            }
            else if (choice==5){
                System.out.println(displayTopics());
                int input = userInput.nextInt();
                userInput.nextLine();
                modifyWord(input);
            }
            else if (choice==6){

            }
            else if (choice==7){
                userInput.nextLine();
                fileTopics();
            }
            else if (choice==8){

            }
            else if (choice==9){
                userInput.nextLine();
                writeInFile();
            }
            else {
                System.out.println("Please enter a valide option");
            }
        }

        while (choice!=0);

    }
}