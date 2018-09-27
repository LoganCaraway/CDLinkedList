//--------------------------------------------------//
//-Author: Logan Caraway
//-File: CDLinkedList.java
//-Purpose: It's a generic circular doubly linked list
//--------------------------------------------------//
package cdlinkedlist;
import java.io.PrintWriter;

public class CDLinkedList<D> {
    //--------------------------------------------------//
    private class Node<D> {
        private final D data;
        private Node<D> next;
        private Node<D> prev;
        
        public Node (D d, Node<D> p, Node<D> n) {
            data = d;
            prev = p;
            next = n;
        }
        
        @Override
        /*Returns the data item contained in this Node as a String*/
        public String toString() {return data.toString();}
        
        @Override
        /*Checks whether or not the given object is a Node containting the same data as this Node*/
        public boolean equals(Object o) {
            if (o == null) return false;
            if (getClass() != o.getClass()) return false;
            
            D other_data = (D) ((Node)o).getData();
            
            if (!data.equals(other_data)) return false;
            return true;
        }
        
        /*Returns the data contained in this node*/
        public D getData(){return data;}
        
        /*Returns the Node following this Node*/
        public Node<D> getNext() {return next;}
        
        /*Returns the Node preceding this Node*/
        public Node<D> getPrevious() {return prev;}
        
        /*Sets the Node following this Node*/
        public void setNext(Node<D> n) {next = n;}
        
        /*Sets the Node preceding this Node*/
        public void setPrevious(Node<D> p) {prev = p;}
    }
    //--------------------------------------------------//
    private Node<D> head;
    
    
    /*----------Empty-constructor----------*/
    public CDLinkedList() {}
    
    
    /*Add a Node to the end of the list containing the given data D*/
    public void add(D d) {
        if (head == null) {
            head = new Node<>(d, null, null);
            head.setPrevious(head);
            head.setNext(head);
        } else {
            Node<D> current = new Node<>(d, head.getPrevious(), head);
            head.getPrevious().setNext(current);
            head.setPrevious(current);
        }
    }
    
    /*Returns the length of the list*/
    public int listLength() {
        int length = 0;
        
        if (head != null) {
            Node<D> current = head;
            
            do {
                length++;
                current = current.getNext();
            } while (current != head);
        }
        return length;
    }
    
    /*Returns the first Node of the list*/
    public Node getHead() {return head;}
    
    /*Returns the last Node of the list*/
    public Node getTail() {return head.getPrevious();}
    
    @Override
    /*Returns a String containing the data of every Node contained in the list separated by " | "*/
    public String toString() {
        //if the list is empty, return an empty string
        if (head == null) return "";
        Node current = head;
        StringBuilder str = new StringBuilder("| ");
        
        do {
            //appends the string version of data in the current node to the string to be returned
            str.append(current.toString());
            str.append(" | ");
            //incriment
            current = current.getNext();
        } while (current != head);
        return str.toString();
    }
    
    @Override
    /*Checks whether or not the given object is a list containing Nodes with the same data in the same order as this list*/
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        
        CDLinkedList other = (CDLinkedList) o;
        
        Node current = head;
        Node other_current = other.getHead();
        
        do {
            if (!current.equals(other_current)) return false;
            //incriment
            current = current.getNext();
            other_current = other_current.getNext();
        } while (current != head);
        return true;
    }
    
    /*Prints the list in order*/
    public void printInOrder() {
        if (head != null) {
            Node<D> current = head;
            
            do {
                System.out.println(current.toString());
                //incriment
                current = current.getNext();
            } while (current != head);
            System.out.println();
        }
    }
    
    /*Prints the list in reverse*/
    public void printInReverse() {
        if (head != null) {
            Node<D> current = head.getPrevious();
            
            do {
                System.out.println(current.toString());
                //incriment
                current = current.getPrevious();
            } while (current != head.getPrevious());
            System.out.println();
        }
    }
    
    /*Prints the list to file in order*/
    public void printToFile(PrintWriter fout) {
        if (head != null) {
            Node<D> current = head;
            
            do {
                fout.println(current.toString());
                //incriment
                current = current.getNext();
            } while (current != head);
            System.out.println();
        }
    }
    
    /*Removes the first Node in the list containing the given data d*/
    public void removeNodeByData(D d) {
        if (head != null) {
            Node<D> current = head;
        
            do {
                //if the correct item is found
                if (current.getData() == d) {

                    if (current == head) head = head.getNext();

                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                    break;
                }
                //incriment
                current = current.getNext();
            } while ((current != head));
        }
    }
}
