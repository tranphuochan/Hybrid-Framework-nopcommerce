package javaAccessModifier02;

import javaAccessModifier01.Car;

	public class Manager {

	public static void main(String[] args) {
		Car car = new Car();
		//System.out.println(car.carName);
				//car.viewCar();
				
		//System.out.println(car.carColor);
				//car.viewCarColor();
		
		//System.out.println(car.carSpeed);
		//car.viewCarSpeed();	
		
		System.out.println(car.carCountry);
		car.viewCarCountry();
		
	}
}
