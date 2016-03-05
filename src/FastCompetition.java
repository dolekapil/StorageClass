/*
 * FastCompetition.java
 *
 * Version: 1.1: FastCompetiton.java,v 1.1 2015/09/28 22:32:23
 *
 * Revisions: 
 * 		Revision 1.1 Kapil 2015/09/28 23:34:31
 *      Initial version
 */

/**
 * In this program, we have created our own storage class by implementing
 * linked list, we can perform various operations on our storage class
 * like sorting, adding elements, removing elements, checking if element
 * is present in the storage. we have tried our best to make it as fast
 * as possible to sort the elements.
 *
 * @author      Kapil Dole
 */
public class FastCompetition<E> implements Competition<E> {

    public Node startStorage;
    public static int countStorage;
    public final int MAX_STORAGE;
    
    /**
     *  This is default constructor of our class.
     */
    
    public FastCompetition() {
        startStorage = new Node(null);
        countStorage = 0;
        MAX_STORAGE = 1000;
    }
    
    /**
     *  This is parameterized constructor of our class where we
     *  can initialize max storage of our class.
     *
     *  @param    max    maximum elements that can be stored. 
     */
    
    public FastCompetition(int max){
        MAX_STORAGE=max;
        startStorage = new Node(null);
    }

    /**
     *  Here we have override toString method, to display our storage class
     *  elements.
     *
     *  @return               		   None.
     */
    public String toString(){
        Node currentStorage = startStorage;
        String storageString ="";
        while(currentStorage.next!=null){
            currentStorage=currentStorage.nextElement();
            storageString = storageString + currentStorage.value+" ";
        }
        return storageString;
    }

    /**
     *  In this method, we are adding elements at the end of storage.
     *
     *  @param        element          element to add in storage.
     *
     *  @return       true        	   if element is added.
     *  @return		  false			   if element can not be inserted.
     */
    public boolean add(E element) {
        if(countStorage > MAX_STORAGE){
            return false;
        }
        else{
            Node tempStorage = new Node(element);
            Node currentStorage = startStorage;
            while(currentStorage.nextElement()!=null){
                currentStorage = currentStorage.nextElement();
            }
            currentStorage.setNextElement(tempStorage);
            countStorage++;
            return true;
        }
    }

    /**
     *  In this method, we are checking whether the given element
     *  is present in the storage class or not.
     *
     *  @param        obj              element to check.
     *
     *  @return       true    		   if element is present.
     *  @return		  false			   if element is not in class.
     */
    public boolean contains(Object obj) {
        if (startStorage.nextElement() == null) {
            return false;
        }
        Node currentStorage = startStorage.nextElement();
        while(currentStorage.value!=null){
            if((currentStorage.value).equals(obj)){
                return true;
            }
            currentStorage=currentStorage.nextElement();
            if (currentStorage == null) {
                return false;
            }
        }
        return false;
    }


    /**
     *  In this method, we are deleting element from the storage class.
     *
     *  @param        obj              element to delete.
     *
     *  @return       true    		   if element is deleted.
     *  @return		  false			   if element is not in class.
     */
    public boolean remove(Object obj) {
        Node tempStorage = startStorage;
        Node currentStorage = startStorage.nextElement();
        while(tempStorage.next!=null){
            if((currentStorage.value).equals(obj)){
                tempStorage.next=currentStorage.next;
                countStorage--;
                return true;
            }
            currentStorage=currentStorage.nextElement();
            tempStorage=tempStorage.nextElement();
        }
        return false;
    }

    /**
     *  In this method, we are returning element at specific index in 
     *  storage class.
     *
     *  @param        index            index location of element.
     *
     *  @return         E      		   element at particular index.
     */
    public E elementAt(int index) {
        int storageCounter = 0 ;
        Node currentStorage = startStorage;
        while(storageCounter <= index){
            currentStorage=currentStorage.nextElement();
            storageCounter++;
        }
        return currentStorage.value;
    }

    /**
     *  In this method, we are sorting elements in our storage class.
     *  
     *  @return			Competition		Storage class object.
     */
    @SuppressWarnings("unchecked")
    public Competition<E> sort() {

        Node currentStorage = startStorage.nextElement();
        String[] unsorted = new String[countStorage];
        for(int counter=0;counter<countStorage;counter++){
            unsorted[counter]=currentStorage.value.toString();
            currentStorage=currentStorage.nextElement();
        }
        
        mergeStorage(unsorted,0,countStorage-1);

        int oldCount = countStorage;
        this.startStorage = new Node(null);
        countStorage = 0;
        for(int counter=0; counter<oldCount;counter++){
            this.add((E) unsorted[counter]);
        }
        return this;
    }

    /**
     *  In this method, we applying recursive merge sort to sort the elements.
     *  
     *  @param		unsorted		unsorted array of elements.
     *  @param		leftStorage		lower end of storage.
     *  @param		rightStorage	upper end of storage.
     */
    public void mergeStorage(String[] unsorted, int leftStorage, int rightStorage){
    	if(leftStorage<rightStorage){
    	int centerStorage = (leftStorage+rightStorage)/2;
    	mergeStorage(unsorted, leftStorage, centerStorage);
    	mergeStorage(unsorted, centerStorage+1, rightStorage);
    	merge(unsorted,leftStorage,centerStorage,rightStorage);
    	}
    }
    
    
    /**
     *  In this method, we are merging the split parts to get sorted elements.
     *     
     *  @param		unsorted		unsorted elements in storage class.
     *  @param		left			lower end of storage.
     *  @param		middle			mid point of storage.
     *  @param		right			upper end of storage.
     */
    
    public void merge(String[] unsorted,int left, int middle, int right){
    	String[] tempString=new String[countStorage];
    	for(int counter=0;counter<countStorage;counter++){
    		tempString[counter]=unsorted[counter];
    	}
    	int min=left, min2=left, center=middle+1;
    	while(min<=middle && center<=right){
    		if(tempString[min].compareTo(tempString[center])<0){
    			unsorted[min2]=tempString[min];
    			min++;
    		}
    		else{
    			unsorted[min2]=tempString[center];
    			center++;
    		}
    		min2++;
    	}
    	
    	while(min<=middle){
    		unsorted[min2]=tempString[min];
    		min++;
    		min2++;
    	}
    }
    
    /**
     *  This method gives us size of our storage class.
     *  
     *  @return			integer		size of storage class.
     */
    public int size() {
        return countStorage;
    }

    /**
     *  This is an inner class, used for implementing linked list.
     */
    public class Node {
        public E value;
        public Node next;

        public Node(E value, Node next){
            this.next=next;
            this.value=value;
        }
        public Node(E value){
            next=null;
            this.value=value;
        }
        public Node nextElement(){
            return next;
        }
        public void setNextElement(Node next){
            this.next=next;
        }
    }
}
