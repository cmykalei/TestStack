/**
 * Stack class.
 * A 'stack' is a data structure that follows 'Last-in-first-out' order.
 * 
 * The Stack class implements a dynamic linked-list of nodes and public methods 
 * to perform stack operations. Also contains private inner-class Node to hold 
 * the attributes of an item on a stack.
 * 
 * @see     Stack.Node
 */
public class Stack{

    Node head;

    /**
     * Stack constructor.
     * Creates an empty stack and sets its head Node variable to null.
     */
    public Stack(){
        
        head = null;
    }

    /**
     * Node class.
     * A 'node' holds the attributes of an item on a stack.
     * 
     * Node is a private inner-class of Stack, and holds a variable to store its 
     * String value and another to point to the next Node on the stack. 
     * 
     * @see     Stack#Stack()
     */
    private class Node{

        String value; 
        Node next;

        /**
         * Node constructor.
         * Takes a String argument and sets it to this member's value.
         * 
         * @param x Specifies the String value of an item on a stack.
         */
        private Node(String x){

            value = x;
            next = null;
        }
    }

    /**
     * The push method adds a String value to the top of this stack.
     * 
     * Strips the value passed in to remove excessive spacing, then checks if
     * the value is blank or exceeds the maximum length. If not then constructs
     * a new Node with the valid value and appoints that Node as head.
     * 
     * @param x specifies the String value to push onto this stack.
     * @see     Node#Node(String x)
     */
    public void push(String x){

        String value = x.strip();
        if(value.equals("")){
            System.out.println("Could not push: This String is blank");
            return;
        }
        else if(value.length() > 50){
             System.out.println("Could not push: This String exceeds max length 50");
             return;
        }
        else{
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
            return;     
        }
    }

    /**
     * The pop method removes the value at the top of this stack.
     * 
     * If the head node is null then prints a message to the System and 
     * returns null. If not, then retrieves the value of the head before
     * removing it and appointing the next node as the current head.
     *  
     * @return  The String value that was removed.
     */
    public String pop(){
        
        if(head == null){
            System.out.println("Could not pop: This stack appears empty");
            return null;
        }

        String x = head.value;
        if(head.next == null){
            head = null;
            return x;
        }
        head = head.next;  
        return x;
    }

    /**
     * The peek method looks at value at the top this stack.
     * 
     * If the head is null then prints a message to the System and 
     * returns null. Else return the value of head.
     * 
     * @return  The String value of at the top of this stack.
     */
    public String peek(){

        if(head == null){
            System.out.println("Could not peek: This stack appears empty");
            return null;
        }
        return head.value;
    }

    /**
     * Determines if this stack is empty by checking if its head node is null.
     * 
     * @return  The boolean result 'true' if head is null, or 'false' otherwise.
     */
    public boolean isEmpty(){

        if(head == null){
            return true;
        }
        return false;   
    }

    /**
     * Determines the length of this stack given by its count of nodes.
     * 
     * If the head node is null then returns 0. If not, then loops from the head 
     * to count each next node until the next is null.
     * 
     * @return  The int count of items currently on this stack.
     */
    public int length(){
            
        if(head == null){
            return 0;
        }

        Node current = head;
        int count = 1;
        while(current.next != null){
            current = current.next;
            count++;
        }   
        return count;
    }

    /**
     * The dump method removes and reveals all values on this stack.
     * 
     * If the stack length is 0 then prints a message to the System and 
     * returns null. If not, then loops from the length and calls pop() at 
     * each iteration to remove and print the value.
     * 
     * @see     #pop()
     * @see     #length()
     */
    public void dump(){

        int i = length();
        if(i == 0){
            System.out.println("Could not dump: This stack has 0 items");
            return;
        }

        while(i > 0){
            System.out.println(pop());
            i--;
        }
        return;
    }

}