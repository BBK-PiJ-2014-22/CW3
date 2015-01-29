package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datastructures.StackImpl;
import datastructures.ImprovedStackImpl;
import datastructures.Stack;

public class StackImplEqualsTest {

	@Test
	public void testStackEqualStack() {
		StackImpl stack1 = new StackImpl();
		StackImpl stack2 = new StackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 5);
		assert(stack1.equals(stack2));
	}
	
	@Test
	public void testStackNotEqualStack() {
		StackImpl stack1 = new StackImpl();
		StackImpl stack2 = new StackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 4);
		assert(!(stack1.equals(stack2)));
	}
	
	@Test
	public void testStackNotEqualImprovedStack() {
		StackImpl stack1 = new StackImpl();
		ImprovedStackImpl stack2 = new ImprovedStackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 4);
		assert(!(stack1.equals(stack2)));
	}
	
	@Test
	public void testStackEqualImprovedStack() {
		StackImpl stack1 = new StackImpl();
		ImprovedStackImpl stack2 = new ImprovedStackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 5);
		assert(!(stack1.equals(stack2)));
	}
	
	@Test
	public void testImprovedStackNotEqualImprovedStack() {
		ImprovedStackImpl stack1 = new ImprovedStackImpl();
		ImprovedStackImpl stack2 = new ImprovedStackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 4);
		assert(!(stack1.equals(stack2)));
	}
	
	@Test
	public void testImprovedStackEqualImprovedStack() {
		ImprovedStackImpl stack1 = new ImprovedStackImpl();
		ImprovedStackImpl stack2 = new ImprovedStackImpl();
		buildStack(stack1, 5);
		buildStack(stack2, 5);
		assert(!(stack1.equals(stack2)));
	}

	private void buildStack(Stack stack, int number){
		for (int i = 0; i < number ; i++)
			stack.push(i);
	}
}
