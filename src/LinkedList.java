// -----------------------------------------------------
// Assignment 3
// Question: LinkedList class
// Written by: Hugo Moslener (id: 40241091) - Mohamed Ali Bahi (id: 40282763)
// Due April 15th 2024
// This is the LinkedList class, it is used by the Vocab class and enables modification of a word list
// -----------------------------------------------------

import java.util.ArrayList;
public class LinkedList {
    private Node head;
    private int size;

    // Ctor
    public LinkedList() {
        this.head = null;
    }

    // Add a word

    /**
     * The addAWord method adds a node to the list, sorting it by ASCII at the same time. Returns false if the word already exists.
     * @param newWord : a new word entered by the user
     * @return boolean : to confirm if the function worked or not
     */
    // pos stands for POSITION
    public boolean addAWord(String newWord) {
        if (!checkIfExist(newWord)) {
            return false;
        }
        Node pos = head;
        if (pos == null) {
            head = new Node(newWord, null);
            size++;
            return true;
        }
        if (pos.data.compareTo(newWord) >= 0) {
            head = new Node(newWord, head);
            size++;
            return true;
        }
        while (pos.next != null && pos.next.data.compareTo(newWord) <= 0) {
            pos = pos.next;
        }
        if (pos.next == null) {
            pos.next = new Node(newWord, null);
            size++;
            return true;
        }
        pos.next = new Node(newWord, pos.next);
        size++;
        return true;

    }

    // Remove a word

    /**
     * The removeAWord method is removes a word from a list, if it is in the list. If not, then returns false.
     * @param word : word the user wants removed
     * @return : to confirm if the function worked or not
     */
    public boolean removeAWord(String word) {
        if (head != null) {
            if (size == 1) {
                if (head.data.equals(word)) {
                    head = null;
                    size--;
                    return true;
                }
            } else if (head.data.equals(word)) {
                head = head.next;
                size--;
                return true;
            } else {
                Node pos = head;
                while (pos.next != null && !pos.next.data.equals(word)) {
                    pos = pos.next;
                }
                if (pos.next != null) {
                    pos.next = pos.next.next;
                    size--;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // Change a word

    /**
     * The changeAWord method changes a word to another word according to the user. The new word is sorted by ASCII when entered,
     * it returns (-1) if the word that they want to replace does not exist in the list, and (-2) if the word they want to change to exists already.
     * @param word : word the user wants changed
     * @param newWord : word the user wants to replace the old one with
     * @return : a string with relevant information for the program, not meant for the user
     */
    public String changeAWord(String word, String newWord) {
        if (!checkIfExist(newWord)) {
            return "-2";
        }
        if (head != null) {
            Node pos = head;
            while (pos != null && !pos.data.equals(word)) {
                pos = pos.next;
            }
            if (pos != null) {
                removeAWord(pos.data);
                addAWord(newWord);
                //pos.data = newWord;
                return "0";
            }
        }
        return "-1";
    }

    // Display words

    /**
     * The displayWords method displays the words in the list in a format. The format is the following: 4 words per line, all evenly spaced. They are also numbered.
     * @return : a string of all the words found in this list
     */
    public String displayWords() {
        if (head != null) {
            final String space = "                   ";
            String words = "";
            int globalCount = 0;
            int localCount = 0;
            Node pos = head;
            while (pos != null) {
                if (pos.data.length() >= space.length()) {
                    words += ++globalCount + ": " + pos.data + "   ";
                } else {
                    words += ++globalCount + ": " + pos.data + space.substring(0, (space.length() - pos.data.length()));
                }
                if (globalCount >= 10) {
                    words = words.substring(0, words.length() - 1);
                }
                if (++localCount % 4 == 0) {
                    words += "\n";
                    localCount = 0;
                }
                pos = pos.next;
            }
            return words;
        }
        return null;
    }

    // Finding words that start with a specific letter

    /**
     * The findSameLetterWords method looks thought the whole list and puts words that start with the same letter as inputted
     * into a string ArrayList. If there are no matching words, then it returns null.
     * @param letter : is a string that contains only a letter that the user
     * @return : an ArrayList<String> with words starting with the same letter
     */
    public ArrayList<String> findSameLetterWords(String letter) {
        if (head != null) {
            ArrayList<String> words = new ArrayList<>(0);
            Node pos = head;
            int counter = 0;
            while (counter++ < size) {
                while (pos != null && !pos.data.substring(0, 1).equals(letter)) {
                    pos = pos.next;
                }
                if (pos == null) {
                    break;
                }
                words.add(pos.data);
                pos = pos.next;
            }
            return words;
        }
        return null;
    }

    // Check if word already exists

    /**
     * The checkIfExist method checks if the word passed in is in the list.
     * @param word : a String containing a word that need to be checked for its appearance in the list
     * @return : boolean if exists or not
     */
    public boolean checkIfExist(String word) {
        Node pos = head;
        while (pos != null && !pos.data.equals(word)) {
            pos = pos.next;
        }
        return pos == null;
    }

    public ArrayList<String> getWords(){
        Node node = head;
        ArrayList<String> list = new ArrayList<String>();
        while (node!=null){
            list.add(node.data);
            node=node.next;
        }
        return list;
    }

    private class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
