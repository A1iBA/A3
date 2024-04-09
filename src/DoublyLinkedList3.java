public class DoublyLinkedList3<T>{

    //attributes
    private Node<T> head;
    private Node<T> tail;
    private int size;

    //default constructor
    public DoublyLinkedList3() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void setTail(){

    }

    //get the node at a specific index
    public Node<T> getNode (int index){
        if (head == null){
            return null;
        }
        Node<T> node = head;
        for (int i = 0; i<index && i <size; i++ ){
            node = node.next;
            if (node==null){
                System.out.println(i);
                return null;
            }
        }
        return node;
    }
    public boolean addTopicAfter(int index, T newTopic){
        if (head==null){
            head = new Node<T>(newTopic, null, null);
            size=1;
            return true;
        }
        Node<T> node = getNode(index);
        if (node==null){
            return false;
        }
        Node<T> newNode = new Node<T>(newTopic, node.next, node);
        node.next=newNode;
        size++;
        return true;
    }

    //Add a node before a topic method
    public boolean addTopicBefore(int index, T newTopic){
        if (head==null){
            head = new Node<T>(newTopic, null, null);
            size=1;
            return true;
        }
        Node<T> node = getNode(index);
        if (node==null){
            return false;
        }
        Node<T> newNode = new Node<T>(newTopic, node, node.prev);
        node.prev=newNode;
        size++;
        if (index==0){
            head = newNode;
        }

        return true;
    }

    public void removeNode(int index){
        if (head==null){
            System.out.println("No topic to remove");
            return;
        }
        Node<T> node = getNode(index);
        if (node==null){
            System.out.println("Invalid index input");
            return;
        }
        if (node.prev==null){
            head = head.next;
            head.prev=null;
            size = size-1;
            return;
        }



        Node<T> nodePrev = node.prev;
        Node<T> nodeNext = node.next;

        (nodePrev).next = nodeNext;
        if (nodeNext!=null){
            (nodeNext).prev = nodePrev;
        }
        size = size-1;
        System.out.println(size);
    }

    //method that returns the vocab object of a node
    public T get(int index) {
        Node<T> node = getNode(index);
        if (node == null) {
            return null;
        }
        return node.item;
    }

    public int getSize(){
        return this.size;
    }




    //Node class inside the DoublyLinkedList
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
}