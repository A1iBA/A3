import java.util.Scanner;

public class DoublyLinkedList2 <T>{
    private class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        public Node() {
            this.item = null;
            this.next = null;
            this.prev = null;

        }
        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList2 (){
        this.head=null;
        this.tail=null;
        this.size=0;
    }


    public void removeTopic (int index) {
        Node<T> position = head;
        if (position == null) {
            System.out.println("The list is empty.");
        }
        else {
            int i = 0;
            while ((position != null) && index!=i) {
                position = position.next;
                i++;
            }
            if (position==null){
                System.out.println("Through the existing topics, none corresponds to the index entered");
            }
            else {
                (position.prev).next=position.next;
                (position.next).prev=position.prev;
            }
        }
    }



    public void insertTopicBefore (int index, T newVocab) {
        Node<T> position = head;
        if (position==null){
            head = new Node<T>(newVocab, null, null);
            size=1;
        }
        int i = 1;
        while ((position != null) && index!=i) {
            position = position.next;
            i++;
        }
        if (position==null){
            System.out.println("Through the existing topics, none corresponds to the index entered");
        }
        else {
            Node<T> newNode = new Node<T>(newVocab, position, position.prev );
            position.prev=newNode;
            size++;
        }
    }

    public void insertTopicAfter (int index, T newVocab){
        Node<T> position = head;
        if (position==null){
            head = new Node<T>(newVocab, null, null);
            size=1;
            return;
        }
        position = getNode(index);
        if (position == null){
            System.out.println("Error");
            return;
        }
        Node<T> newNode = new Node<T>(newVocab, position.next, position);
        position.next = newNode;
        if (position.next!=null) {
            (position.next).prev = newNode;
        }
        size++;
    }

    public Node<T> getNode(int index){
        Node<T> position = head;
        if (position == null) {
            System.out.println("The list is empty.");
        }
        else {
            /*int i = 1;
            while ((position != null) && index != i) {
                position = position.next;
                i++;
            }
             */
            for(int i=0; i<=index && position != null; i++) {
                position = position.next;
            }
            if (position == null) {
                System.out.println("Through the existing topics, none corresponds to the index entered");
            } else {
                return position;
            }
        }
        return null;
    }

    public T getMethod (int index) {
        if (getNode(index)==null){
            return null;
        }
        return getNode(index).item;
    }
    public int getSize(){
        return this.size;
    }

}
