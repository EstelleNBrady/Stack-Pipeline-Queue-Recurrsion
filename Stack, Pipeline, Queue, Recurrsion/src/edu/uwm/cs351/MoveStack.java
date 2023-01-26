package edu.uwm.cs351;

import edu.uwm.cs351.money.Bank;
import edu.uwm.cs351.money.Coin;
import edu.uwm.cs351.money.DefaultContainer;
import edu.uwm.cs351.money.Stack;
import edu.uwm.cs351.money.Type;

//Estelle Brady
//CS 351 - 401
//Collaborated with Julian Morano, Lucas Patron, Miguel Garcia

//used this site to help with my helper method
//https://www.geeksforgeeks.org/java-program-for-tower-of-hanoi/

//used this site to help me know how to print a stack
//https://stackoverflow.com/questions/26748947/how-to-print-a-stack-in-java

/**
 * A variation on Towers of Hanoi
 */

public class MoveStack extends DefaultContainer{


	/**
	 * Move all the coins from
	 * one stack to another, in the same order,
	 * with the help of another stack.
	 * @param from stack to get coins from, must not be null
	 * @param to stack to which the coins should be added, must not be null
	 * and must be empty.
	 * @param helper stack which can be used to hold coins temporarily.
	 * 	It must not be the same as the "to" stack, must not be null and 
	 * must not already have coins in it.
	 */
	
	//only one coin can be moved at a time
	//can only take a coin from the top of the stack
	//no disk can be placed on a smaller disk
	public static void doMove(Stack from, Stack to, Stack helper) {
		// TODO: check arguments and then call recursive helper method to do work
		
		
		
		//from - no null
		//to - cannot be same as helper, no null, be empty
		//helper - cannot be same as to, no null, be empty
		if(to.isEmpty() == false || helper.isEmpty() == false || to == helper){
			throw new IllegalArgumentException("");
		}
		if(from == null || to == null || helper == null) {
			throw new NullPointerException("from is null");
		}
		
		//we can set the n to the size of A or from
		int n = from.size();
		helperM(n,from, to, helper);
		
		}

	
	private static void helperM(int n, Stack from, Stack to, Stack helper) {
		///add to 1 and move from the other
		
		//if it is not at least greater than 0, return
		if(!(n > 0))
			return;
		
		else
		helperM(n-1, from, helper, to);
		
		//get a coin from removing
		Coin a = from.remove();
		to.add(a);
		

		helperM(n-1, helper, to, from);
	}

	// TODO: Helper method

	
	// coins to request.  We only have $2.00, so we
	// use a lot of pennies
	private static final Type[] coinsToStack = {
			Type.HALFDOLLAR, Type.DOLLAR, Type.QUARTER, 
			Type.NICKEL, Type.NICKEL, 
			Type.PENNY, Type.PENNY, Type.PENNY, Type.PENNY, Type.PENNY, 
			Type.DIME, 
	};

	public static void main(String[] args) {
		Bank b = Bank.getInstance();
		// TODO
		// 1. Create a stack and place in it coins
		// withdrawn from the bank (all the ones in coinsToStack).
		
		//before move print
		System.out.println("Before the Moves");
		System.out.println("******************");
		System.out.println("");
		System.out.println("Stack A:");
		System.out.println("");
		
		//created a new stack
		Stack A = new Stack();
		
		//looping through, withdrawing from the bank and printing stack
		for(int i = 0; i< coinsToStack.length; i++) {
			Coin a =b.withdraw(coinsToStack[i]);
			//add the coins into the stack from the bank
			A.add(a);
			//print stack A
			System.out.println(A);
		}
		System.out.println("");
		
		
		
		// 2. Create two other stacks.
		Stack B = new Stack();
		Stack C = new Stack();
		
		//if we have only added to the A stack, by default, the rest are still empty
		System.out.println("Stack B: EMPTY! ");
		System.out.println("");
		System.out.println("Stack C: EMPTY! ");
		System.out.println("");
		
		
		// 3. Call doMove to move coins from first stack to second stack
		// using a third stack as the helper stack.
		doMove(A, B, C);
		
		//prints for after we do the switch
		System.out.println("");
		System.out.println("After the Moves");
		System.out.println("******************");
		System.out.println("");
		
		
		
//printing out A stack
		System.out.println("Stack A:");
		System.out.println("");
		
		if(A.isEmpty())	System.out.println("EMPTY!");
		System.out.println("");
		
		while(!(A.isEmpty()))
		{
		   Coin Acoin = A.remove();
		   System.out.println(Acoin);
		}
		
		
		
//printing out B stack
		System.out.println("Stack B:");
		System.out.println("");
		
		if(B.isEmpty())	System.out.println("EMPTY!");
		System.out.println("");
		
		while(!(B.isEmpty()))
		{
		   Coin Bcoin = B.remove();
		   System.out.println(Bcoin);
		}
		System.out.println("");
		
		
		
//printing out C stack
		System.out.println("Stack C:");
		System.out.println("");
		
		if(C.isEmpty())	System.out.println("EMPTY!");
		System.out.println("");
		
		while(!(C.isEmpty()))
		{
		   Coin Ccoin = C.remove();
		   System.out.println(Ccoin);
		}

	}
}
		

		
		
	

