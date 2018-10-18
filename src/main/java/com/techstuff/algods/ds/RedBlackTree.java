package com.techstuff.algods.ds;

import java.util.ArrayList;
import java.util.List;

import com.techstuff.algods.util.Tuple2;

public class RedBlackTree<T extends Comparable<T>> {
    
    private Node root;
    
    private Node sentinel = new Node(null, Color.BLACK);
    
    public RedBlackTree(List<T> sequence) {
        this.root = sentinel;
        for(T elem : sequence) {
        		insert(elem);
        }
    }
    
    private void leftRotate(Node x) {
        Node pivot = x.right;
        x.setRight(pivot.left);
        if(x.parent == sentinel) {
            root = pivot;
            pivot.parent = sentinel;
        } else if(x == x.parent.left) {
            x.parent.setLeft(pivot);
        } else {
            x.parent.setRight(pivot);
        }
        pivot.setLeft(x);
    }
    
    private void rightRotate(Node x) {
        Node pivot = x.left;
        x.setLeft(pivot.right);
        if(x.parent == sentinel) {
            this.root = pivot;
            pivot.parent = sentinel;
        } else if(x == x.parent.left) {
            x.parent.setLeft(pivot);
        } else {
            x.parent.setRight(pivot);
        }
        pivot.setRight(x);
    }
    
    public void insert(T data) {
        Node newNode = new Node(data);
        Node current = root;
        Node previous = sentinel;
        while(current != sentinel) {
            previous = current;
            if(current.data.compareTo(data) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if(previous == sentinel) {
            this.root = newNode;
        } else if(previous.data.compareTo(data) < 0) {
            previous.setRight(newNode);
        } else {
            previous.setLeft(newNode);
        }
        insertFixup(newNode);
    }
    
    private void insertFixup(Node newNode) {
        Node current = newNode;
        while(current.parent.color == Color.RED) {
            if(current.parent == current.parent.parent.left) {
                if(current.parent.parent.right.color == Color.RED) {
                    current.parent.color = Color.BLACK;
                    current.parent.parent.right.color = Color.BLACK;
                    current.parent.parent.color = Color.RED;
                    current = current.parent.parent;
                } else {
                    if(current == current.parent.right) {
                        current = current.parent;
                        leftRotate(current);
                    }
                    current.parent.color = Color.BLACK;
                    current.parent.parent.color = Color.RED;
                    rightRotate(current.parent.parent);
                }
            } else {
                if(current.parent.parent.left.color == Color.RED) {
                    current.parent.color = Color.BLACK;
                    current.parent.parent.left.color = Color.BLACK;
                    current.parent.parent.color = Color.RED;
                    current = current.parent.parent;
                } else {
                    if(current == current.parent.left) {
                        current = current.parent;
                        rightRotate(current);
                    }
                    current.parent.color = Color.BLACK;
                    current.parent.parent.color = Color.RED;
                    leftRotate(current.parent.parent);
                }
            }
        }
        this.root.color = Color.BLACK;
    }
    
    public Node search(T data) {
    		Node current = root;
    		while(current != sentinel && !current.data.equals(data)) {
    			if(current.data.compareTo(data) < 0) {
    				current = current.right;
    			} else {
    				current = current.left;
    			}
    		}
    		return current;
    }
    
    public void transplant(Node x, Node y) {
    		if(x.parent == sentinel) {
    			root = y;
    			root.parent = y.parent;
    		} else if(x == x.parent.left) {
    			x.parent.setLeft(y);
    		} else {
    			x.parent.setRight(y);
    		}
    }
    
    public void delete(T data) {
    		Node deleteNode = search(data);
    		Node y = deleteNode;
    		Node x = sentinel;
    		Color oldColor = y.color;
    		if(deleteNode.left == sentinel) {
    			x = deleteNode.right;
    			transplant(deleteNode, deleteNode.right);
    		} else if(deleteNode.right == sentinel) {
    			x = deleteNode.left;
    			transplant(deleteNode, deleteNode.left);
    		} else {
    			Node successor = deleteNode.right;
    			while(successor.left != sentinel) {
    				successor = successor.left;
    			}
    			oldColor = successor.color;
    			x = successor.right;
    			transplant(successor, successor.right);
    			transplant(deleteNode, successor);
    			successor.setLeft(deleteNode.left);
    			successor.setRight(deleteNode.right);
    			y = successor;
    			y.color = deleteNode.color;
    		}
    		if(oldColor == Color.BLACK) {
    			deleteFixup(x);
    		}
    }
    
    // x pointer is considered extra black. So we fix double blacks
    public void deleteFixup(Node x) {
    		while(x != root && x.color == Color.BLACK) {
    			if(x == x.parent.left) {
    				Node sibling = x.parent.right;
    				if(sibling.color == Color.RED) {
    					sibling.color = Color.BLACK;
    					x.parent.color = Color.RED;
    					leftRotate(x.parent);
    					sibling = x.parent.right;
    				}
    				if(sibling.left.color == Color.BLACK && sibling.right.color == Color.BLACK) {
    					sibling.color = Color.RED;
    					x = x.parent;
    				} else {
    					if(sibling.right.color == Color.BLACK) {
    						sibling.left.color = Color.BLACK;
    						sibling.color = Color.RED;
    						rightRotate(sibling);
    						sibling = x.parent.right;
    					}
    					sibling.color = sibling.parent.color;
    					x.parent.color = Color.BLACK;
    					sibling.right.color = Color.BLACK;
    					leftRotate(x.parent);
    					x = root;
    				}
    			} else {
    				Node sibling = x.parent.left;
    				if(sibling.color == Color.RED) {
    					sibling.color = Color.BLACK;
    					x.parent.color = Color.RED;
    					rightRotate(x.parent);
    					sibling = x.parent.left;
    				}
    				if(sibling.left.color == Color.BLACK && sibling.right.color == Color.BLACK) {
    					sibling.color = Color.RED;
    					x = x.parent;
    				} else {
    					if(sibling.left.color == Color.BLACK) {
    						sibling.right.color = Color.BLACK;
    						sibling.color = Color.RED;
    						leftRotate(sibling);
    						sibling = x.parent.left;
    					}
    					sibling.color = sibling.parent.color;
    					x.parent.color = Color.BLACK;
    					sibling.left.color = Color.BLACK;
    					rightRotate(x.parent);
    					x = root;
    				}
    			}
    		}
    		x.color = Color.BLACK;
    }
    
    public List<Tuple2<T, Color>> getInorder() {
        return getInorder(root, new ArrayList<Tuple2<T, Color>>());
    }
    
    private List<Tuple2<T, Color>> getInorder(Node current, List<Tuple2<T, Color>> list) {
        if(current == sentinel) {
            return list;
        }
        getInorder(current.left, list);
        list.add(new Tuple2<>(current.data, current.color));
        getInorder(current.right, list);
        return list;
    }
    
    public List<Tuple2<T, Color>> getLevelorder() {
        List<Tuple2<T, Color>> list = new ArrayList<>();
        Queue<Node>  queue = new Queue<>(getSize());
        if(root != sentinel) {
            queue.enqueue(root);
        }
        while(!queue.isEmpty()) {
            Node current = queue.dequeue();
            if(current.left != sentinel) {
                queue.enqueue(current.left);
            }
            if(current.right != sentinel) {
                queue.enqueue(current.right);
            }
            list.add(new Tuple2<>(current.data, current.color));
        }
        return list;
    }
    
    public int getSize() {
        return getSize(root);
    }
    
    private int getSize(Node current) {
        if(current == sentinel) {
            return 0;
        }
        return getSize(current.left) + getSize(current.right) + 1;
    }
    
    public void print() {
		printLevel(root, 0, "root");
	}
	
	private void printLevel(Node current, int i, String pos) {
		if(current == null) {
			return;
		}
		for(int j = 0 ; j < i ; j++) {
			System.out.print(" ");
		}
		System.out.println(current.data + " " + pos);
		printLevel(current.left, i + 1, "left");
		printLevel(current.right, i + 1, "right");
	}
    
    private class Node {
        
        private Color color;
        
        private T data;
        
        private Node parent;
        
        private Node left;
        
        private Node right;
        
        public Node(T data) {
            this(data, Color.RED);
        }
        
        public Node(T data, Color color) {
            this.data = data;
            this.color = color;
            this.parent = RedBlackTree.this.sentinel;
            this.left = RedBlackTree.this.sentinel;
            this.right = RedBlackTree.this.sentinel;
        }
        
        public void setLeft(Node child) {
            this.left = child;
            child.parent = this;
        }
        
        public void setRight(Node child) {
            this.right = child;
            child.parent = this;
        }
    }
    
    static enum Color {
        RED,
        BLACK
    }

}
