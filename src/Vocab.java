// -----------------------------------------------------
// Assignment 3
// Question: Vocab class
// Written by: Hugo Moslener (id: 40241091) - Mohamed Ali Bahi (id: 40282763)
// Due April 15th 2024
// This is the Vocab class, it contains the LinkedList class for its words, and a string topic for its topic name.
// -----------------------------------------------------
import java.util.ArrayList;

public class Vocab {
    // Initialize variables
    private String topic; // Topic of the vocab
    private LinkedList words; // Words associated to the topic

    // Parameterized Ctor (modified the parameters, only left topic, and made this.words equal to an empty LinkedList)
    public Vocab(String topic){
        this.topic = topic;
        this.words = new LinkedList();
    }

    // Add a word
    public String addAWord(String newWord){
        if(!words.addAWord(newWord)){ // return for when word already exists
            return "sorry, the word: ’"+newWord+"’ is already listed";
        }
        return "Success!";
    }

    // Remove a word
    public boolean removeAWord(String word){
        return words.removeAWord(word); // returns a boolean based on if the operation was successful
    }

    // Change a word
    public String changeAWord(String word, String newWord){
        if(words.changeAWord(word, newWord).equals("-2")){ // return for when word already exists
            return "sorry, the word: ’"+newWord+"’ is already listed";
        } else if(words.changeAWord(word, newWord).equals("-1")){ // return for when the word was not found
            return "sorry, the word: '"+word+"' is not in this list";
        }
        return "Success!";
    }

    // Display a Vocab object
    public String displayVocab(){
        String displayedWords = words.displayWords();
        if(displayedWords == null){
            displayedWords = "No words in this topic.";
        }
        return "Topic: " + this.topic + "\n" + displayedWords; // this will return null if there are no vocab words to display (empty linkedList)
    }

    // Find similarly named words (make sure to check if NULL and if the "letter" is actually a letter (use ASCII values imo))
    public ArrayList<String> findSameLetterWords(String letter){
        return words.findSameLetterWords(letter); // this will return NULL if there are no similar-lettered words
    }
    public String getTopic() {
        return topic;
    }

    public ArrayList <String> getWords(){
        return words.getWords();
    }
}