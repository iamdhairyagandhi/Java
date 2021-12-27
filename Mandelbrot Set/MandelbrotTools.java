import java.awt.Color;

public class MandelbrotTools {

	/* STUDENTS: Put your "isBig" and "divergence" methods here. */
	// Method computes the norm (a^2+b^2) and compares it to DIVERGENCE_BOUNDRY
	public static boolean isBig(ComplexNumber input) {
		// a^2+b^2
		MyDouble asqrd, bsqrd;
		asqrd = ((input.getReal()).multiply(input.getReal()));
		bsqrd = ((input.getImag()).multiply(input.getImag()));

		if ((asqrd.add(bsqrd)).compareTo(Controller.DIVERGENCE_BOUNDARY) > 0) {
			return true;
		} else {
			return false;
		}
	}

	// error! need help ugh!
	public static int divergence(ComplexNumber z0) {
		ComplexNumber znew = new ComplexNumber(z0);

		if (isBig(z0) == true) {
			return 0;
		}
		for (int counter = 1; counter < Controller.LIMIT; counter++) {
			if (isBig(znew) == false) {
				znew = znew.multiply(znew).add(z0);
			} else {
				return counter;
			}
		}
		return -1;

	}

	/*
	 * This method selects a non-black color for a point which DIVERGED when tested
	 * with the Mandelbrot recurrence, based on how many terms in the sequence were
	 * computed before the terms got "too big".
	 * 
	 * The parameter represents the index of the term in the sequence which was
	 * first to be "too big". This value could be anything from 0 to
	 * Controller.LIMIT. The return value is the Color to be used to color in the
	 * point.
	 * 
	 * STUDENTS: IF you want to have some fun, write code for the else-if clause
	 * below which says "modify this block to create your own color scheme". When
	 * someone runs the program and selects "Student Color Scheme", the code you
	 * have written below will determine the colors.
	 */
	public static Color getColor(int divergence) {
		Color returnValue;

		if (Controller.colorScheme == Controller.RED_AND_WHITE_BANDS) {
			returnValue = (divergence % 2 == 0) ? Color.WHITE : Color.RED;
		}

		else if (Controller.colorScheme == Controller.CRAZY_COLORS) {
			int value = divergence * 2;
			int redAmount = (value % 5) * (255 / 5);
			int greenAmount = (value % 7) * (255 / 7);
			int blueAmount = (value % 9) * (255 / 9);
			returnValue = new Color(redAmount, greenAmount, blueAmount);
		}

		else if (Controller.colorScheme == Controller.STUDENT_DEFINED) {

			/***************************************************************
			 * Modify this block to create your own color scheme! *
			 ***************************************************************/
			returnValue = Color.WHITE; // take this out and return something useful

		} else
			throw new RuntimeException("Unknown color scheme selected!");
		return returnValue;
	}

}
