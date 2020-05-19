package APP.Controllers;

import java.util.Date;

import DBCLS.Rental;

public class Dummy {
	
	public static void main(String[] args) {
		Date d1 = new Date();
		Boolean resBoolean = Rental.addNewRental(1, "A01", d1, d1, d1, 100.00f, 1001, "Test");
		System.out.println(resBoolean);
	}

}
