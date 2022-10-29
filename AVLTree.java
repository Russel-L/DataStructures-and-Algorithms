package ph.edu.upm.cas.dpsm.rbchua;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {	
	private AVLNode<E> root;
	private AVLNode<E> TOKEN = new AVLNode<E>(null);
	
	public AVLTree() {
		root = null;
	}
	
	public AVLTree(AVLNode<E> root) {
		this.root = root;
	}
	
	//Complete this method. This method inserts the key into the AVL Tree
	@Override
	public void insert(E key) {
		root = insertSupport(root, key);
	}
	 
	private AVLNode<E> insertSupport(AVLNode<E> node, E key) {
		AVLNode<E> newNode = new AVLNode<E>(key);
		if (node == null)
			return newNode;
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			AVLNode<E> newLeftNode = insertSupport(node.getLeftChild(), key);
			if (newLeftNode == TOKEN)
				return TOKEN;
			node.setLeftChild(newLeftNode);
		}
		else if (cmp > 0) {
			AVLNode<E> newRightNode = insertSupport(node.getRightChild(), key);
			if (newRightNode == TOKEN)
				return TOKEN;
			node.setRightChild(newRightNode);
		}
		else
			return TOKEN;
		update(node);
		return balance(node);
			
	}
	
	public void AVL_Insert(E key) {
		if (root == null) {
			AVLNode<E> v = new AVLNode<E>(key);
			v.setLeftChild(null); v.setRightChild(null);
			v.setBalanceFactor(0); root = v;
			return;
		}
		AVLNode<E> v = new AVLNode<E>(); AVLNode<E> a = root;
		AVLNode<E> t = new AVLNode<E>(); AVLNode<E> y = root;
		while (true) {
			if (key.compareTo(y.getKey()) < 0) {
				t = y.getLeftChild();
				if (t == null) {
					y.setLeftChild(v);
					break;
				}
			}
			else if (key.compareTo(y.getKey()) > 0) {
				t = y.getRightChild();
				if (t == null) {
					y.setRightChild(v);
					break;
				}
			}
			if (t.getBalanceFactor() != 0) {
				a = t; a.setParent(y);
			} y = t;
		}
		v.setKey(key); v.setLeftChild(null); v.setRightChild(null); v.setBalanceFactor(0);
		AVLNode<E> b = new AVLNode<E>();
		if (key.compareTo(a.getKey()) < 0) {
			y = a.getLeftChild(); b = a.getLeftChild();
		}
		else {
			y = a.getRightChild(); b = a.getRightChild();
		}
		while (y != v) {
			if (key.compareTo(y.getKey()) < 0) {
				y.setBalanceFactor(-1); y = y.getLeftChild();
			}
			else {
				y.setBalanceFactor(1); y = y.getRightChild();
			}
		}
		
		
		int w;
		if (key.compareTo(a.getKey()) < 0)
			w = -1;
		else
			w = 1;
		AVLNode<E> p = new AVLNode<E>();
		if (a.getBalanceFactor() == 0)
			a.setBalanceFactor(w);
		else if (a.getBalanceFactor() == -w)
			a.setBalanceFactor(0);
		else if (a.getBalanceFactor() == w) {
			if ((key.compareTo(a.getKey()) < 0) && (b.getBalanceFactor() == -w))
				Rotate_Right(b, a, p);
			else if ((key.compareTo(a.getKey()) < 0) && (b.getBalanceFactor() == w))
				Rotate_Left_Right(b, a, p);
			else if ((key.compareTo(a.getKey()) > 0) && (b.getBalanceFactor() == w))
				Rotate_Left(b, a ,p);
			else if ((key.compareTo(a.getKey()) > 0) && (b.getBalanceFactor() == -w))
				Rotate_Right_Left(b, a, p);
		}
	}
	
	private void Rotate_Right(AVLNode<E> B, AVLNode<E> a, AVLNode<E> p) {
		
	}
	
	private void Rotate_Left_Right(AVLNode<E> B, AVLNode<E> a, AVLNode<E> p) {
		
	}
	
	private void Rotate_Left(AVLNode<E> B, AVLNode<E> a, AVLNode<E> p) {
		
	}
	
	private void Rotate_Right_Left(AVLNode<E> B, AVLNode<E> a, AVLNode<E> p) {
		
	}
	private void update(AVLNode<E> node) {
		int leftNodeHeight = (node.getLeftChild() == null) ? -1 : node.getLeftChild().height;
		int rightNodeHeight = (node.getRightChild() == null) ? -1 : node.getRightChild().height;
		node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
		node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
	}
	
	
	private AVLNode<E> balance(AVLNode<E> node) {
		if (node.getBalanceFactor() == -2) {
			if (node.getLeftChild().getBalanceFactor() <= 0)
				return leftLeftCase(node);
			else 
				return leftRightCase(node);
		}
		else if (node.getBalanceFactor() == 2) {
			if (node.getRightChild().getBalanceFactor() >= 0)
				return rightRightCase(node);
			else
				return rightLeftCase(node);
		}
		return node;
	}
	
	private AVLNode<E> leftLeftCase(AVLNode<E> node) {
		return rightRotation(node);
	}

	private AVLNode<E> leftRightCase(AVLNode<E> node) {
		node.setLeftChild(leftRotation(node.getLeftChild()));
		return leftLeftCase(node);
	}
		  
	private AVLNode<E> rightRightCase(AVLNode<E>  node) {
	    return leftRotation(node);
	}

	private AVLNode<E>  rightLeftCase(AVLNode<E>  node) {
		node.setRightChild(rightRotation(node.getRightChild()));
		return rightRightCase(node);
	}

	private AVLNode<E>  leftRotation(AVLNode<E>  node) {
		AVLNode<E>  newParent = node.getRightChild();
	    node.setRightChild(newParent.getLeftChild());
	    newParent.setLeftChild(node);
	    update(node);
	    update(newParent);
	    return newParent;
	}

	private AVLNode<E>  rightRotation(AVLNode<E>  node) {
		AVLNode<E>  newParent = node.getLeftChild();
	    node.setLeftChild(newParent.getRightChild());
	    newParent.setRightChild(node);
	    update(node);
	    update(newParent);
	    return newParent;
	}
	  
	//Complete this method. This method delete the key from the AVL Tree
	@Override
	public void delete(E key) {
		root = deleteSupport(root, key);
	}
	
	private AVLNode<E> deleteSupport(AVLNode<E> node, E key) {
		if (node == null)
			return TOKEN;
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			AVLNode<E> newLeftNode = deleteSupport(node.getLeftChild(), key);
			if (newLeftNode == TOKEN)
				return TOKEN;
			node.setLeftChild(newLeftNode);
		}
		else if (cmp > 0) {
			AVLNode<E> newRightNode = deleteSupport(node.getRightChild(), key);
			if (newRightNode == TOKEN)
				return TOKEN;
			node.setRightChild(newRightNode);
		}
		else {
			if (node.getLeftChild() == null)
				return node.getRightChild();
			else if (node.getRightChild() == null)
				return node.getLeftChild();
			else {
				if (node.getLeftChild().height > node.getRightChild().height) {
					E successorKey = findMax(node.getLeftChild());
					node.setKey(successorKey);
					AVLNode<E> replacement = deleteSupport(node.getLeftChild(), successorKey);
					if (replacement == TOKEN)
						return TOKEN;
					node.setLeftChild(replacement);
				}
				else {
					E successorKey = findMin(node.getRightChild());
					node.setKey(successorKey);
					AVLNode<E> replacement = deleteSupport(node.getRightChild(), successorKey);
					if (replacement == TOKEN)
						return TOKEN;
					node.setRightChild(replacement);
				}
			}
		}
		update(node);
		return balance(node);
	}
	
	private E findMax(AVLNode<E> node) {
		while (node.getRightChild() != null)
			node = node.getRightChild();
		return node.getKey();
	}
	
	private E findMin(AVLNode<E> node) {
		while (node.getLeftChild() != null)
			node = node.getLeftChild();
		return node.getKey();
	}
	
	@Override
	public String preorder() {
		StringBuilder preorderTraversal = new StringBuilder();
		preorderSupport(root, preorderTraversal);
		return preorderTraversal.toString();
	} // end preorder
	
	private void preorderSupport(AVLNode<E> root, StringBuilder preorder) {
		if (root != null) {
			preorder.append(root + "  ");
			preorderSupport(root.getLeftChild(), preorder);
			preorderSupport(root.getRightChild(), preorder);
		} // end if		
	} // end preorder
	
	@Override
	public String inorder() {
		StringBuilder inorderTraversal = new StringBuilder();
		inorderSupport(root, inorderTraversal);
		return inorderTraversal.toString();
	} // end inorderSupport
	
	private void inorderSupport(AVLNode<E> root, StringBuilder inorder) {
		if (root != null) {
			inorderSupport(root.getLeftChild(), inorder);
			inorder.append(root + "  ");
			inorderSupport(root.getRightChild(), inorder);
		} // end if
	} // end inorderSupport
	
	@Override
	public String postorder() {
		StringBuilder postorderTraversal = new StringBuilder();
		postorderSupport(root, postorderTraversal);
		return postorderTraversal.toString();
 	} // end postorder
	
	private void postorderSupport(AVLNode<E> root, StringBuilder postorder) {
		if (root != null) {
			postorderSupport(root.getLeftChild(), postorder);
			postorderSupport(root.getRightChild(), postorder);
			postorder.append(root + "  ");
		} // end if
	} // end postorderSupport
}
