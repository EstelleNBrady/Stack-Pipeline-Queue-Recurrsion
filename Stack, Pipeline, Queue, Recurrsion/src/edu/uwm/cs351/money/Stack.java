package edu.uwm.cs351.money;

/**
 * A LIFO container of coins
 * that requires a coin cannot be placed on smaller ones
 */
//Estelle Brady
//CS 351 - 401
//Collaborated with Julian Morano, Lucas Patron, Miguel Garcia

public class Stack extends DefaultContainer {
	// TODO
	// Add the least amount that gives the required semantics.
	
	@Override //implementation
	protected boolean wellFormed() { 
		if(super.wellFormed() == false)	return false;
		else
			//checks to make sure that the coins are in the right order based off of size.
			for(Coin p = head; p!=null && p.next != null; p=p.next) {
				if(p.getType().getSize() > p.next.getType().getSize()) {
					return report("The is a bigger coin on top of a smaller coin");
				}
			}
		return true;
	}
	
	@Override //implementation
	public boolean canAdd(Coin c) {	
		//if the coin is null and if it's not in a container yet
		assert wellFormed() : "invariant failed at the start of canAdd";	
		boolean adding = false;
		//if we have more than 1 element, we must check sizes
		if(head != null) {
			if(super.canAdd(c) == true && head.getType().getSize() >= c.getType().getSize())
				adding = true;
		}
		else {
			//otherwise we are just checking our super and then we can add regardless if it is true
			if(super.canAdd(c) == true)
				adding = true;
		}
		assert wellFormed() : "invariant failed at the end of canAdd";
		return adding;
	}


}
