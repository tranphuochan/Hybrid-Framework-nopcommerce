package javaAccessModifier01;

public class Car {

	// Thuộc tính - Biến
	private String carName = "Toyota Cross";
	String carColor = "Red";
	protected String carSpeed = "200km/h";
	public String carCountry = "Japan";

	// Phương thức - Hàm
	private void viewCar() {
		System.out.println(carName);
	}

	void viewCarColor() {
		System.out.println(carColor);
	}

	protected void viewCarSpeed() {
		System.out.println(carSpeed);

	}

	public void viewCarCountry() {
		System.out.println(carCountry);
	}

	protected static void setCarSpeed() {

	}

	public static void main(String[] args) {
		Car car = new Car();
		System.out.println(car.carName);
		System.out.println(car.carColor);
		System.out.println(car.carSpeed);
		System.out.println(car.carCountry);
		car.viewCar();
		car.viewCarColor();
		car.viewCarSpeed();
		car.viewCarCountry();
	}
}
