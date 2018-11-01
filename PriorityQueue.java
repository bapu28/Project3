/**
 * @author shaha
 *
 */
public class PriorityQueue<T> {

    private Node<T> head;
    private Node<T> tail;


    /**
     * 
     * @param size
     */
    public PriorityQueue(int size) {
        head = new Node<T>(null);
        tail = new Node<T>(null);

        head.setNext(tail);
        tail.setPrevious(head);

        this.initializeQueue(size);
    }


    /**
     * 
     * @param data
     * @return
     */
    public T insert(T data) {
        if (data != null) {
            Node<T> curr = head;
            while ((curr = curr.getNext()) != tail) {
                if (data.equals(curr.getData())) {
                    this.promote(curr);
                    return null;
                }
            }
        }

        Node<T> node = new Node<T>(data);

        node.setPrevious(head);
        node.setNext(head.getNext());
        head.getNext().setPrevious(node);
        head.setNext(node);

        Node<T> last = tail.getPrevious();
        last.getPrevious().setNext(tail);
        tail.setPrevious(last.getPrevious());

        return last.getData();
    }


    /**
     * 
     * @param size
     */
    private void initializeQueue(int size) {
        for (int i = 0; i < size; i++) {
            Node<T> nullNode = new Node<T>(null);

            head.getNext().setPrevious(nullNode);
            nullNode.setNext(head.getNext());
            nullNode.setPrevious(head);
            head.setNext(nullNode);
        }
    }


    /**
     * 
     * @param node
     */
    private void promote(Node<T> node) {
        this.removeNode(node);

        node.setPrevious(head);
        node.setNext(head.getNext());
        head.getNext().setPrevious(node);
        head.setNext(node);
    }


    /**
     * 
     * @param node
     */
    private void removeNode(Node<T> node) {
        node.getNext().setPrevious(node.getPrevious());
        node.getPrevious().setNext(node.getNext());
    }


    /**
     * 
     * @return
     */
    public Node<T> getHead() {
        return head;
    }


    /**
     * 
     * @param head
     */
    public void setHead(Node<T> head) {
        this.head = head;
    }


    /**
     * 
     * @return
     */
    public Node<T> getTail() {
        return tail;
    }


    /**
     * 
     * @param tail
     */
    public void setTail(Node<T> tail) {
        this.tail = tail;
    }


    /**
     * Internal Class
     * Node inner class for Priority Queue
     * 
     * @author Akshat Shah (akshat98)
     *
     * @param <E>
     *            the data
     * 
     */
    class Node<E> {

        private E data;
        private Node<E> next;
        private Node<E> prev;


        /**
         * Allocate the Node
         * 
         * @param data
         *            the data to be stored in node
         */
        public Node(E data) {
            this.data = data;
        }


        /**
         * Get the data stored in the node
         * 
         * @return data
         */
        public E getData() {
            return data;
        }


        /**
         * Sets the next node in the list
         * 
         * @param next
         *            the node to link to
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }


        /**
         * Get the next node in the list
         * 
         * @return next
         */
        public Node<E> getNext() {
            return next;
        }


        /**
         * Sets the previous Node in the list
         * 
         * @param prev
         *            the node to link to
         */
        public void setPrevious(Node<E> prev) {
            this.prev = prev;
        }


        /**
         * Get the previous node
         * 
         * @return previous
         */
        public Node<E> getPrevious() {
            return prev;
        }

    }

}
