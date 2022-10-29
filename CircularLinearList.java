package ph.edu.upm.cas.dpsm.rbchua;

public class CircularLinearList<E> {
	private Node<E> last;
	
	//this method should print the elements of the list from left to right
	public String toString() {
		if (last == null) { // if list is empty
			return "( )";
		} else if (last.getNext() == last) {
			return "( " + last.getKey() + " )";
		} else {
			StringBuilder stringl = new StringBuilder("( ");
			Node<E> current = last.getNext();
			while (true) {
				stringl.append(current.getKey());
				if (current != last) {
					stringl.append(", ");
				} else {
					break;
				}
				current = current.getNext();
			}
			stringl.append(" )");
			return stringl.toString();
		}
	}
	

	public void leftInsert(E key) {
		Node<E> newNode = new Node<E>(key);
		if (last == null) {
			newNode.setNext(newNode);
			last = newNode;
		}
		else {
			newNode.setNext(last.getNext());
			last.setNext(newNode);
		}
	}
	
	public void rightInsert(E key) {
		leftInsert(key);
		last = last.getNext();
	}
	
	public E deleteLeft(){
		if (last == null)
			return null;
		else {
			Node<E> current = last.getNext();
			last.setNext(current.getNext());
			if (current == last)
				last = null;
			return current.getKey();
		}
	}

	public E deleteRight(){
		if (last == null)
			return null;
		else {
			E previous = null;
			Node<E> current = last.getNext();
			while (current.getNext() != last)
				current = current.getNext();
			if (current == last) {
				previous = current.getKey();
				last = null;
			}
			else {
				current.setNext(last.getNext());
				previous = last.getKey();
				last = current;
			}
			return previous;
		}
	}
	
	
	public static void main(String[] args){
		
		CircularLinearList<String> list = new CircularLinearList<String>();
		list.leftInsert("Hello");
		list.rightInsert("World");
		
		System.out.println(list); //Should print : (Hello, World)
		
		list.leftInsert("Computer"); list.rightInsert("Science");
		System.out.println(list);
		
		System.out.println(list.deleteLeft()); //Should print : Computer
		  
		System.out.println(list.deleteRight()); //Should print : Science
		
		System.out.println(list);
		 		

	}
}
