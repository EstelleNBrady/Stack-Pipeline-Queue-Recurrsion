package edu.uwm.cs351.money;

import java.util.NoSuchElementException;

/**
 * A FIFO container of coins.
 */
//Estelle Brady
//CS 351 - 401
//Collaborated with Julian Morano, Lucas Patron, Miguel Garcia

//added at tail and removed from the head
public class Pipeline extends DefaultContainer {
	protected Coin tail; // Add only this field
	
	// TODO: Add what is necessary to get correct semantics
	//well formed
	//add 
	//remove
	
	@Override//implemented
	protected boolean wellFormed() {
		
		if(super.wellFormed() == false)	return false;
		else
			//checks if tail is reachable from head
			if(super.wellFormed() == true) {
				Coin p;
				Coin lag = null;
				for(p=head; p!=null; p=p.next) {
					lag = p;
				}
				if(lag!=tail)
					return report("tail could not be found");
			}
		return true; 
	}

		
	@Override //implemented
	public void add(Coin c) {
		// TODO Auto-generated method stub
		assert wellFormed() : "invariant failed at the start of add in pipeline";	
		takeOwnership(c);
	
		//if it is empty, add the coin
		if(isEmpty()) {
			//we want to set head and tail to c
			head = tail =c;
			
		}else {
			tail.next = c;
			tail = c;
		}
		//we want to make sure we have nothing after tail
		tail.next = null;
		assert wellFormed() : "invariant failed at the end of add in pipeline";	
	}
	
	@Override//implemented
	public Coin remove() throws NoSuchElementException {
		assert wellFormed() : "invariant failed at the start of remove";
		if(isEmpty()) 
			throw new NoSuchElementException("There is no element to remove");
		Coin temp = head;
		//if there is more than 1 element
		if(!(head == tail)) {
			head = head.next;	
		}
		//if there is 1 element
		else {
			head = tail = null;
		}
		
		relinquish(temp);
		assert wellFormed() : "invariant failed at the end of remove";
		return temp;	
	}	

}
