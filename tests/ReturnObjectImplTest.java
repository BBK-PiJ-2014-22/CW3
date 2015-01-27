package tests;

import static org.junit.Assert.*;
import DataStructures.*;

import org.junit.Before;
import org.junit.Test;

public class ReturnObjectImplTest {

	@Test
	public void testEqualsObjectTrueSame() {
		ReturnObject first = new ReturnObjectImpl(1, ErrorMessage.NO_ERROR);
		assertEquals(first, first);
	}

	@Test
	public void testEqualsObjectTrueDiff() {
		ReturnObject first = new ReturnObjectImpl(1, ErrorMessage.NO_ERROR);
		ReturnObject second = new ReturnObjectImpl(1, ErrorMessage.NO_ERROR);
		assertEquals(first, second);
	}
	
	@Test
	public void testEqualsObjectFalse() {
		ReturnObject first = new ReturnObjectImpl(1, ErrorMessage.NO_ERROR);
		ReturnObject second = new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		assertTrue(!first.equals(second));
	}
	

/*	@Test
	public void testReturnObjectImplObjectErrorMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnObjectImplObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testReturnObjectImplErrorMessage() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testHasError() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetError() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReturnValue() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}
 	*/
}
