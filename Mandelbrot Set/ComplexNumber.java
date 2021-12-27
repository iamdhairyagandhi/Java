
public class ComplexNumber {

	/* STUDENTS: You may NOT add any further instance or static variables! */
	private final MyDouble real; // To be initialized in constructors
	private final MyDouble imag; // To be initialized in constructors

	/* STUDENTS: Put your methods here, as described in the project description. */
	// A standard constructor that takes two parameters representing the real and
	// imaginary components
	public ComplexNumber(MyDouble realIn, MyDouble imagIn) {
		real = realIn;
		imag = imagIn;
	}

	// A constructor that takes one parameter representing the 
	//real component and imag=0.0;
	public ComplexNumber(MyDouble realIn) {
		real = realIn;
		imag = new MyDouble(0.0);
	}

	// A copy constructor
	public ComplexNumber(ComplexNumber copyIn) {
		real = copyIn.real;
		imag = copyIn.imag;
	}

	// getter for real part
	public MyDouble getReal() {
		return real;
	}

	// getter for imaginary part
	public MyDouble getImag() {
		return imag;
	}

	// Return a ComplexNumber that is equal to the sum of the current 
	// object and the parameter
	public ComplexNumber add(ComplexNumber input) {
		return new ComplexNumber(real.add(input.real), imag.add(input.imag));
	}

	// Return a ComplexNumber that is computed by subtracting the value 
	// of parameter from the current object.
	public ComplexNumber subtract(ComplexNumber input) {
		return new ComplexNumber(real.subtract(input.real), 
								imag.subtract(input.imag));
	}

	// Return a ComplexNumber that represents the product of the 
	// current object and the parameter
	public ComplexNumber multiply(ComplexNumber input) {
		// (a+bi)*(c+di)=(ac-bd)+(bc+ad)i
		MyDouble rp, ip; // rp=realpart & ip=imaginarypart
		rp = ((real.multiply(input.real)).subtract(imag.multiply(input.imag)));
		ip = ((imag.multiply(input.real)).add(real.multiply(input.imag)));

		return new ComplexNumber(rp, ip);
	}

	// Return the quotient computed by dividing the current object
	// by the parameter
	public ComplexNumber divide(ComplexNumber input) {
		// (a+bi)/(c+di)=((ac+bd)/(c^2+d^2))+((bc-ad)/(c^2+d^2))i
		MyDouble rp, ip, denom; // rp=realpart & ip=imaginarypart 
								//	& denom = denominator
		rp = ((real.multiply(input.real)).add(imag.multiply(input.imag)));
		ip = ((imag.multiply(input.real)).subtract(real.multiply(input.imag)));
		denom = ((input.real.multiply(input.real))
				.add(input.imag.multiply(input.imag)));
		rp = rp.divide(denom);
		ip = ip.divide(denom);
		return new ComplexNumber(rp, ip);
	}

	// returns true if both fields match
	public boolean equals(ComplexNumber input) {
		if (real.equals(input.real) && imag.equals(input.imag)) {
			return true;
		} else {
			return false;
		}
	}

	// compare the norm of the current object with the norm of the 
	//parameter
	/*
	 * If the norms are equal, this method returns 0; if the norm of 
	 * the current object is less than the norm of the parameter, 
	 * this method returns -1; if the norm of the current object 
	 * is greater than the norm of the parameter, this method returns 1.
	 */
	public int compareTo(ComplexNumber input) {
		if ((norm(this).compareTo(norm(input))) == 0) {
			return 0;
		} else if ((norm(this).compareTo(norm(input))) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	public String toString() {
		if (imag.toString().indexOf('-') == -1) {
			return real.toString() + "+" + imag.toString() + "i";
		} else {
			return real.toString() + "" + imag.toString() + "i";
		}
	}

	// Static methods
	// Returns a MyDouble object representing the norm (sqrt(a^2+b^2)) of the
	// complex number
	public static MyDouble norm(ComplexNumber input) {
		MyDouble asqrd, bsqrd;
		asqrd = (input.real).multiply(input.real);
		bsqrd = (input.imag).multiply(input.imag);
		return (((asqrd).add(bsqrd)).sqrt());
	}
	//this method takes one parameter (a String) and returns a ComplexNumber. 
	//The parameter is a String that represents a complex number
	
	public static ComplexNumber parseComplexNumber(String input){
		String expression=input.replaceAll(" ","");
		MyDouble realPart, imagPart;
		if(expression.indexOf('+')!=-1){ //+ instead of - in stringIn
			realPart = new MyDouble(Double.parseDouble(expression.substring
					(0, expression.indexOf('+'))));
			imagPart = new MyDouble(Double.parseDouble(expression.substring
					(expression.indexOf('+'), expression.indexOf('i'))));
		}else{ //- instead of + in stringIn
			realPart = new MyDouble(Double.parseDouble(expression.substring
					(0, expression.indexOf('-', 1))));
			imagPart = new MyDouble(Double.parseDouble(expression.substring
					(expression.indexOf('-', 1), expression.indexOf('i'))));
		}
		return new ComplexNumber(realPart,imagPart);

	}
}
