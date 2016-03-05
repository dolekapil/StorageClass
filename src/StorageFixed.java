

public class StorageFixed <E, V> implements Storage<E, V>{
	
	public Node startStorage;
	public static int countStorage;
	public final int MAX_STORAGE = 100;
	public StorageFixed() {
		startStorage = new Node(null);
		countStorage = 0;
	}
	public String toString(){
		Node currentStorage = startStorage; 
		String storageString ="";
		while(currentStorage.next!=null){
			currentStorage=currentStorage.nextElement();
			storageString = storageString + currentStorage.value+" ";		
		}

		return storageString;
	}
	
	public boolean add(E e){
		if(countStorage > MAX_STORAGE){
			return false;
		}
		else{
			Node tempStorage = new Node(e);
			Node currentStorage = startStorage;
			while(currentStorage.nextElement()!=null){
				
				currentStorage = currentStorage.nextElement();
			}
			currentStorage.setNextElement(tempStorage);
			countStorage++;
			return true;
		}
	}


	public boolean add(int index, E element) {
		if(index<=countStorage){
			Node currentStorage = startStorage;
			Node tempStorage = new Node(element);
			int counter = 0;
			while(counter<index){	
				currentStorage = currentStorage.nextElement();
				counter++;
			}
			Node beforeNode = currentStorage;
			Node afterNode = currentStorage.nextElement();
			beforeNode.next = tempStorage;
			tempStorage.next = afterNode;
			countStorage++;
			return true;
		}
		else{
			return false;
		}
	}

	public void addElement(E obj) {
		Node tempStorage = new Node(obj);
		Node currentStorage = startStorage;
		while(currentStorage.nextElement()!=null){
			
			currentStorage = currentStorage.nextElement();
		}
		currentStorage.setNextElement(tempStorage);
		countStorage++;
		
	}


	public void addElement(Object obj, Object elem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int capacity() {
		return countStorage;
	}

	@Override
	public void clear() {
		Node currentStorage = startStorage;
		while(currentStorage.nextElement()!=null){
			
			currentStorage.value = null;
			currentStorage = currentStorage.nextElement();
		}
		currentStorage.value = null;
	}

	@Override
	public E firstElement() {
		
		return startStorage.nextElement().value;
	}

	
	public E get(int index) {
		int storageCounter = 0 ;
		Node currentStorage = startStorage;
		while(storageCounter <= index){
			currentStorage=currentStorage.nextElement();
			storageCounter++;
		}
		return currentStorage.value;
	}

	
	public E lastElement() {
		
		Node currentStorage = startStorage;
		while(currentStorage.nextElement()!=null){
			currentStorage = currentStorage.nextElement();
		}
		return currentStorage.value;
	}
	public Object clone(){
		Node tempNode = startStorage.nextElement();
		StorageFixed<E, V> cloneObject = new StorageFixed<>();
		while(tempNode.value!=null){
			cloneObject.addElement(tempNode.value);
			tempNode=tempNode.nextElement();
		}
		System.out.println(cloneObject.get(1));
		return cloneObject;
	}
	
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
	public static void main(String[] args){
		try{
		Storage<String, String> aStorageString = new StorageFixed<String, String>();
		aStorageString.addElement("kapil");
		aStorageString.addElement("kapil2");
		System.out.println(aStorageString);
		System.out.println();
		aStorageString.add(1,"middle");
		System.out.println(aStorageString);
		System.out.println();
		System.out.println(aStorageString.lastElement());
		System.out.println(aStorageString.firstElement());
		System.out.println(aStorageString.get(1));
		System.out.println(aStorageString.capacity());
		//aStorageString.clear();
		System.out.println(aStorageString);
		System.out.println();
		System.out.println();
		System.out.println(aStorageString.clone());
		}
		catch(NullPointerException e){
			System.out.println("Storage is empty.");
		}
	}

}
