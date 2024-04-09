public class DoublyLinkedList <Vocab>{
    private class Node <Vocab>{
        private Vocab vocab;
        private Node<Vocab> next;
        private Node<Vocab> prev;

        public Node(){
            this.vocab=null;
            this.next=null;
            this.prev=null;

        }
        public Node(Vocab vocab, Node<Vocab> next, Node<Vocab> prev){
            this.vocab=vocab;
            this.next=next;
            this.prev=prev;
        }
    }
    private Node<Vocab> head;
    private Node<Vocab> tail;
    private int size;

    public DoublyLinkedList(){
        this.head=null;
        this.tail=null;
        this.size=0;
    }


    /*public Vocab getMethod (int index){
        Node<Vocab> position = head;
        if (position == null) {
            System.out.println( "The list is empty.");
        } else {
            int i = 1;
            while ((position != null) && index != i) {
                position = position.next;
                i++;
            }
            if (position == null) {
                System.out.println("Through the existing topics, none corresponds to the index entered");
            }
            else {
                return position.vocab;
            }
        }
     */

   /* public String displayTopic (int index) {
        Node<Vocab> position = head;
        if (position == null) {
            return "The list is empty.";
        } else {
            int i = 0;
            while ((position != null) && index != i) {
                position = position.next;
                i++;
            }
            if (position == null) {
                return ("Through the existing topics, none corresponds to the index entered");
            }
            else {
                return position.displayVocab();
            }
        }
    }
*/
    public void removeTopic (int index) {
        Node<Vocab> position = head;
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

    public void insertTopicBefore (int index, Vocab newVocab) {
        Node<Vocab> position = head;
        if (position==null){
            head = new Node<Vocab>(newVocab, null, null);
            return;
        }
        int i = 0;
        while ((position != null) && index!=i) {
            position = position.next;
            i++;
        }
        if (position==null){
            System.out.println("Through the existing topics, none corresponds to the index entered");
        }
        else {
            Node<Vocab> newNode = new Node<Vocab>(newVocab, position, position.prev );
            position.prev=newNode;
        }
    }

    public void insertTopicAfter (int index, Vocab newVocab){
        Node<Vocab> position = head;
        if (position==null){
            head = new Node<Vocab>(newVocab, null, null);
            return;
        }
        int i = 0;
        while ((position != null) && index!=i) {
            position = position.next;
            i++;
        }
        if (position==null){
            System.out.println("Through the existing topics, none corresponds to the index entered");
        }
        else {
            Node<Vocab> newNode = new Node<Vocab>(newVocab, position.next, position);
            (position.next).prev = newNode;
        }
    }

}
