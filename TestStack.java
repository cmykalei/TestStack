import org.junit.jupiter.api.*;
import org.junit.platform.commons.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.io.*;

public class TestStack{

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	private final String MAXSTRING = "This String exceeds the max length by one character";

	@BeforeEach
	public void setOut(){
		System.setOut(new PrintStream(outStream));
	}

	@AfterEach
	public void tearDown(){
		System.setOut(standardOut);
	}

	/**
	 * Test asserts that method isEmpty() returns 'true' when called on a new 
	 * instance of a stack.
	 * 
	 * @see 	Stack#isEmpty()
	 */
	@Test
	@DisplayName("isEmpty() returns 'true' when the stack is empty")
	public void isEmptyReturnsTrue(){	

		Stack stack = new Stack();

		boolean actual = stack.isEmpty();

		assertTrue(actual);
	}

	/**
	 * Test asserts that method length() returns int 0 if called on a new
	 * instance of an empty stack.
	 * 
	 * @see 	Stack#length()
	 */
	@Test
	@DisplayName("length() returns 0  when there are no items on the stack")
	public void lengthReturnsZero(){

		Stack stack = new Stack();

		int expected = 0;
		int actual = stack.length();

		assertEquals(expected, actual);
	}

	/**
	 * Tests asserts that method peek() returns null when called on a new
	 * instance of an empty stack.
	 * 
	 * @see 	Stack#peek()
	 */
	@Test
	@DisplayName("peek() returns null when there are no items on the stack")
	public void peekReturnsNullString(){

		Stack stack = new Stack();

		String actual = stack.peek();

		assertNull(actual);
	}

	/**
	 * Tests asserts that method peek() prints the expected message to the 
	 * System when called on new instance of an empty stack.
	 * 
	 * @see 	Stack#peek()
	 */
	@Test
	@DisplayName("peek() prints the correct message if the value is null")
	public void peekPrintsNullMessage(){

		Stack stack = new Stack();

		stack.peek();
		String actual = outStream.toString();	

		assertEquals("Could not peek: This stack appears empty\n", actual);
	}

	/**
	 * Tests asserts that method pop() returns null when called on a new
	 * instance of an empty stack.
	 * 
	 * @see 	Stack#pop()
	 */
	@Test
	@DisplayName("pop() returns null when there are no items on the stack")
	public void popReturnsNullString(){

		Stack stack = new Stack();

		String actual = stack.pop();

		assertNull(actual);
	}

	/**
	 * Tests asserts that method pop() prints the expected message to the 
	 * System when called on new instance of an empty stack.
	 * 
	 * @see 	Stack#pop()
	 */
	@Test
	@DisplayName("pop() prints the correct message if the value is null")
	public void popPrintsNullMessage(){

		Stack stack = new Stack();

		stack.pop();
		String actual = outStream.toString();	

		assertEquals("Could not pop: This stack appears empty\n", actual);
	}

	@Test
	@DisplayName("dump() prints the correct message if the stack is empty")
	public void dumpPrintsNullMessage(){

		Stack stack = new Stack();

		stack.dump();
		String actual = outStream.toString();

		assertEquals("Could not dump: This stack has 0 items\n", actual);
	}

	/**
	 * Test asserts that method isEmpty() returns 'false' when called on a stack 
	 * after a valid String was pushed onto it.
	 * 
	 * @see		Stack#push(String x)
	 * @see 	Stack#isEmpty()	
	 */
	@Test
	@DisplayName("isEmpty() returns 'false' after a value is pushed on the stack")
	public void isEmptyReturnsFalse(){

		Stack stack = new Stack();
		stack.push("A");

		boolean actual = stack.isEmpty();

		assertFalse(actual);
	}

	/**
	 * Test asserts that method length() returns the int count of one item.
	 * 
	 * @see		Stack#push(String x)
	 * @see 	Stack#length()	
	 */
	@Test
	@DisplayName("length() counts an item after its value is pushed onto the stack")
	public void lengthCountsItem(){

		Stack stack = new Stack();
		stack.push("A");

		int expected = 1;
		int actual = stack.length();

		assertEquals(expected, actual);	
	}

	/**
	 * Tests asserts that method push(String x) successfully strips a String 
	 * value of invalid spacing before attempting to push.
	 * 
	 * @see		Stack#push(String x)
	 * @see 	Stack#peek()
	 */
	@ParameterizedTest
	@DisplayName("push(String x) 'strips' Strings from invalid white spacing:")
	@MethodSource("provideSpacedStrings")
	public void pushStripsStrings(String string){

		Stack stack = new Stack();

		stack.push(string);
		String actual = stack.peek();

		assertEquals(string.strip(), actual);
	}

	/**
	 * Test asserts that method push(String x) adds the valid String value to 
	 * the top of the stack.
	 * 
	 * @see 	Stack#push(String x)
	 * @see 	Stack#peek()
	 */
	@ParameterizedTest
	@DisplayName("push(String x) adds Strings to the stack following LIFO order")
	@MethodSource("provideStrings")
	public void pushAddsStrings(String string){

		Stack stack = new Stack();
		String head = string + " as head";
		stack.push(string);

		stack.push(head);
		String actual = stack.peek();

		assertEquals(head, actual);
	}

	/**
	 * Tests asserts that method push(String x) prints a message to the
	 * System if a blank String was passed in.
	 * 
	 * @see		Stack#push(String x)
	 */
	@DisplayName("push(String x) prints a message when the String is blank:")
    @ParameterizedTest
    @MethodSource("provideBlanks")
	public void pushNotifiesBlank(String blank){

		Stack stack = new Stack();

		stack.push(blank);
		String actual = outStream.toString();

		assertEquals("Could not push: This String is blank\n", actual);
	}

	/**
	 * Tests asserts that method push(String x) stops a blank string from
	 * being added to the stack.
	 * 
	 * @see		Stack#push(String x)
	 */
	@DisplayName("push(String x) stops pushing when a value is blank :")
	@ParameterizedTest
	@MethodSource("provideBlanks")
	public void pushStopsBlank(String blank){

		Stack stack = new Stack();

		stack.push(blank);
		String actual = stack.peek();
		
		assertNull(actual);
	}

	/**
	 * Tests asserts that method push(String x) prints a message to the
	 * System if the value passed to push exceeds maximum length of 50.
	 * 
	 * @see		Stack#push(String x)
	 */
	@Test
	@DisplayName("push(String x) prints a message if the value is too long")
	public void pushNotifiesMaximum(){

		Stack stack = new Stack();

		stack.push(MAXSTRING);
		String actual = outStream.toString();

		assertEquals("Could not push: This String exceeds max length 50\n", actual);
	}

	/**
	 * Tests asserts that method push(String x) stops a blank string from
	 * being added to the stack.
	 * 
	 * @see		Stack#push(String x)
	 */
	@Test
	@DisplayName("push(String x) stops pushing if the value is too long")
	public void pushStopsMaximum(){

		Stack stack = new Stack();
		
		stack.push(MAXSTRING);
		String actual = stack.peek();
		
		assertNull(actual);
	}

	/**
	 * Test asserts that method pop() successfuly removes the Node and value
	 * from the stack when called. Confirms removal by checking if the stack
	 * isEmpty() returns true.
	 * 
	 * @see 	Stack#pop()
	 * @see 	Stack#isEmpty()
	 */
	@Test
	@DisplayName("pop() completely removes the item when popping it off the stack")
	public void popRemovesValue(){
		
		Stack stack = new Stack();
		stack.push("A");

		stack.pop();
		boolean actual = stack.isEmpty();

		assertTrue(actual);
	}

	/**
	 * Test asserts that method pop() returns the the correct String value of
	 * the item being removed from the top of the stack.
	 * 
	 * @see 	Stack#pop()
	 */
	@Test
	@DisplayName("pop() returns the correct value when popping if off the stack")
	public void popReturnsHead(){
		
		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");

		String expected = "C";
		String actual = stack.pop();

		assertEquals(expected, actual);
	}

	/**
	 * Test asserts that method pop() removes stack items correctly so that 
	 * when the head item is removed, the next most recent item takes its place 
	 * at the top of the stack.
	 * 
	 * @see 	Stack#pop()
	 */
	@Test
	@DisplayName("pop() maintains LIFO order of stack when removing an item")
	public void popMaintainsOrder(){
		
		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");			
		stack.pop();

		String expected = "B";
		String actual = stack.pop();
		
		assertEquals(expected, actual);
	}

	/**
	 * Test asserts that method length() returns the expected count of items 
	 * after a value was removed the stack.
	 * 
	 * @see 	Stack#length()
	 */
	@Test
	@DisplayName("length() returns the expected count of stack items")
	public void lengthReturnsExpected(){

		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");

		int expected = 3;
		int actual = stack.length();
		
		assertEquals(expected, actual);	
	}

	/**
	 * Test asserts that method dump() completely removes all items when called
	 * on a non-empty stack.
	 * 
	 * @see Stack#dump()
	 * @see Stack#isEmpty()
	 */
	@Test
	@DisplayName("dump() completely removes all items from a stack")
	public void dumpClearsStack(){

		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");

		stack.dump();
		boolean actual = stack.isEmpty();

		assertTrue(actual);
	}

	/**
	 * Test asserts that method dump() prints the stack as expected.
	 * 
	 * @see 	Stack#dump()
	 */
	@Test
	@DisplayName("dump() prints all items from a stack in the correct order")
	public void dumpPrintsStack(){

		Stack stack = new Stack();
		stack.push("A");
		stack.push("B");
		stack.push("C");

		stack.dump();
		String expected = "C\nB\nA\n";
		String actual = outStream.toString();
		
		assertEquals(expected, actual);
	}


	/**
	 * Method provideStrings defines an array of Strings to pass as arguments
	 * for parameterised tests.
	 * 
	 * @return 	The array of Strings containing valid stack values.
	 */
	static String[] provideStrings(){
		String[] strings = {"Hello world", "abc", "42"};
		return strings;
	}

	/**
	 * Method provideSpacedStrings defines an array of Strings with invalid
	 * spacing present to pass as arguments for parameterised tests.
	 * 
	 * @return 	The array of Strings containing invalid stack values.
	 */
	static String[] provideSpacedStrings(){
		String[] spaces = {" spaced ", "\ttabbed\t", "\nlinebreak\n", "\rreturn\r"};
		return spaces;
	}

	/**
	 * Method provideBlanks defines an array of blank Strings using spaces, 
	 * tab, new line, and return.
	 * 
	 * @return 	The array of Strings containing blank spaces.
	 */
	static String[] provideBlanks(){
		String[] spaces = {" ", "\t", "\n", "\r"};
		return spaces;
	}

}