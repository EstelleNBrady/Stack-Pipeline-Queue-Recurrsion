package edu.uwm.cs351.money;
import java.util.NoSuchElementException;
//Estelle Brady
//CS 351 - 401
//Collaborated with Julian Morano, Lucas Patron, Miguel Garcia
/**
 * A LIFO Container of Coins
 */
public class DefaultContainer implements Container {
	protected Coin head; // the only field.  Do not declare any other fields
	
	/**
	 * Report an invariant error.
	 * @param message message to print
	 */
	protected boolean report(String message) {
		System.out.println("Invariant error: " + message);
		return false;
	}
	
	/**
	 * Return true if the data structure invariant is established.
	 * @return the value of the predicate for the invariant
	 */
	protected boolean wellFormed() { 
		
// 1. No cycles allowed (use Tortoise and Hare)
		if (head != null) {
			// This check uses the "tortoise and hare" algorithm attributed to Floyd.
			Coin fast = head.next;
			for (Coin p = head; fast != null && fast.next != null; p = p.next) {
				if (p == fast) return report("list is cyclic!");
				fast = fast.next.next;
			}
		}
// 2. Every coin in this container must be owned by this container
	//if there is at least one container in the list

			for(Coin p =head; p!=null; p=p.next) {
				//why doesn't Instanceof Default Container work?
				//if the owner is not an instance of this container, return report
				if(p.owner != this)
					return report("Every coin in the container must be owned");
			}
		//if nothing wrong, return true
		return true;
	}
	
	/**
	 * Start adding a coin.  We first check that we can add it
	 * and then take over ownership.
	 * @param c coin to take possession of, must be one we can add
	 */
	protected void takeOwnership(Coin c) {
		if (!canAdd(c)) throw new IllegalArgumentException("cannot add " + c);
		c.owner = this;
	}

	/**
	 * Get ready to return a coin.  We remove ownership.
	 * @param c coin to relinquish
	 */
	protected void relinquish(Coin c) {
		c.owner = null;
	}

	@Override//implementation
	public boolean isEmpty() {
		assert wellFormed() : "invariant failed at start of isEmpty";	
		return head==null;
	}

	@Override//implementation
	public int size() {
		//since we don't have a varible for count we must find it with a loop
		assert wellFormed() : "invariant failed at the start of size";	
		int count = 0;
		for(Coin p = head; p!= null; p=p.next) {
			count++;
		}
		assert wellFormed() : "invariant failed at the end of size";	
		return count;
	}

	/**
	 * Indicate whether the given coin can be added
	 * @param c coin potentially to add, may not be null
	 * @return whether this coin could be added.
	 */
	@Override//implementation
	public boolean canAdd(Coin c) {	
		//if the coin is null and if it's not in a container yet
		assert wellFormed() : "invariant failed at the start of canAdd";	

		return c!= null && c.owner == null;
	}
	
	/**
	 * Add a coin to the container, and we will then own it.
	 * @param c coin to add
	 * @throw IllegalArgumentException if the coin cannot be added.
	 */
	@Override//implementation
	public void add(Coin c) {
		// TODO Auto-generated method stub
		assert wellFormed() : "invariant failed at the start of add";	
		takeOwnership(c);
		
		//if there are no coins
		if(isEmpty()) {
			c.next = null;
			head = c;
		}else {
			Coin hold = head;
			head = c;
			head.next = hold;
		}	
		assert wellFormed() : "invariant failed at the end of add";	
	}
	
	/**
	 * Remove and return a coin from this container, no longer owning it.
	 * @return coin, will not be null, and not owned by any container
	 * @throws NoSuchElementException if the container is empty
	 */
	
	@Override
	public Coin remove() throws NoSuchElementException {
		assert wellFormed() : "invariant failed at the start of remove";
		if(isEmpty()) 
			throw new NoSuchElementException("There is no element to remove");
	
		Coin holder = head;
		head = head.next;
		relinquish(holder);
		
		assert wellFormed() : "invariant failed at the end of remove";
		return holder;	
	}	
	
	/**
	 * returns a string of head
	 */
	public String toString(){
		
		return this.head + "";
	}
}
