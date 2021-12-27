import static org.junit.Assert.*;

import org.junit.Test;


public class PublicTests {

	@Test
	public void testBasicConstructorsAndGetters() {
		
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		MyDouble d = new MyDouble(555.729);
		
		ComplexNumber x = new ComplexNumber(a, b);
		assertTrue(x.getReal().compareTo(a) == 0 && x.getImag().compareTo(b) == 0);
		
		ComplexNumber z = new ComplexNumber(d);
		assertTrue(z.getReal().compareTo(d) == 0 && z.getImag().compareTo(new MyDouble(0)) == 0);
	}
	
	@Test
	public void testCopyConstructor() {
		
		MyDouble a = new MyDouble(5.7), b = new MyDouble(-3.7);
		
		ComplexNumber x = new ComplexNumber(a, b);
		ComplexNumber y = new ComplexNumber(x);
		assertTrue(x != y);     // Check to be sure they are not aliased!
		assertTrue(y.getReal().compareTo(a) == 0 && y.getImag().compareTo(b) == 0);
	}
	//The tests that should be included are :
	//testAdd,
	@Test
	public void testAdd() {
		
		MyDouble real = new MyDouble(5.7), imag = new MyDouble(-3.7);
		
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber(num1);
		ComplexNumber sum = new ComplexNumber(new MyDouble(11.4),new MyDouble(-7.4));
		assertTrue((num1.add(num2)).equals(sum));
	}
	
	//testSubtract, 
	@Test
	public void testSubtract() {
		
		MyDouble real = new MyDouble(5.7), imag = new MyDouble(-3.7);
		
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber(new MyDouble(2.5),new MyDouble(3.7));
		ComplexNumber diff = new ComplexNumber(new MyDouble(3.2),new MyDouble(-7.4));
		assertTrue((num1.subtract(num2)).equals(diff));
	}
	
	//testMult, 
	@Test
	public void testMult() {
		
		MyDouble real = new MyDouble(5.7), imag = new MyDouble(-3.7);
		
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber(new MyDouble(-2),new MyDouble(-2));
		ComplexNumber product = new ComplexNumber(new MyDouble(-18.8),new MyDouble(-4));
		assertTrue((num1.multiply(num2)).equals(product));
	}
	//testDiv, 
	@Test
	public void testDiv() {
		
		MyDouble real = new MyDouble(16), imag = new MyDouble(-2);
		
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber(new MyDouble(3),new MyDouble(-2));
		ComplexNumber divisor = new ComplexNumber(new MyDouble(4),new MyDouble(2));
		assertTrue((num1.divide(num2)).equals(divisor));
	}
	//testEqComp (equals and compareTo together), 
	@Test
	public void testEqComp() {
		
		MyDouble real = new MyDouble(16), imag = new MyDouble(-2);
		
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber(num1);
		ComplexNumber num3 = new ComplexNumber(new MyDouble(-2),new MyDouble(-3.5));
		
		
		assertTrue(num1.equals(num2));
		assertTrue(num1.getReal().compareTo(num2.getReal())==0);
		assertTrue(num1.getImag().compareTo(num2.getImag())==0);
		assertTrue(num1.getReal().compareTo(num3.getReal())>0);
		assertTrue(num1.getImag().compareTo(num3.getImag())>0);
		assertTrue(num3.getReal().compareTo(num1.getReal())<0);
		assertTrue(num3.getImag().compareTo(num1.getImag())<0);
		assertFalse(num1.equals(num3));
	}
	//testNorm, 
	@Test
	public void testNorm() {
		ComplexNumber num1 = new ComplexNumber
				(new MyDouble(3),new MyDouble(4));
		ComplexNumber num2 = new ComplexNumber
				(new MyDouble(-3),new MyDouble(-4));
		
		assertTrue(ComplexNumber.norm(num1).equals(new MyDouble (5)));
		assertTrue(ComplexNumber.norm(num1).equals(new MyDouble (5)));
	}
	//testParse
	@Test
	public void testParse() {
		MyDouble real = new MyDouble(2), imag = new MyDouble(2);
		ComplexNumber num1 = new ComplexNumber(real,imag);
		ComplexNumber num2 = new ComplexNumber
				(new MyDouble(2),new MyDouble(-2));
		ComplexNumber num3 = new ComplexNumber
				(new MyDouble(-2),new MyDouble(2));
		ComplexNumber num4 = new ComplexNumber
				(new MyDouble(-2),new MyDouble(-2));
		assertTrue(ComplexNumber.parseComplexNumber("2+2i").equals(num1));
		assertTrue(ComplexNumber.parseComplexNumber("2-2i").equals(num2));
		assertTrue(ComplexNumber.parseComplexNumber("-2+2i").equals(num3));
		assertTrue(ComplexNumber.parseComplexNumber("-2-2i").equals(num4));
		
		
	}
}
