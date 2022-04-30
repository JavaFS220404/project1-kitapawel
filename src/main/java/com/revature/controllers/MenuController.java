package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

public class MenuController {
	
	public static Scanner scan = new Scanner(System.in);
	//public HomeService homeService = new HomeService();
	//private AvengerService avengerService = new AvengerService();
	
	
	public void welcomeMenu() {

		printWelcomeMenu();

		String response = scan.nextLine();

		menuLoop:
			while (!response.equals("0")) {
				switchLoop:
					switch (response) {
					case "1":
						// RUN AUTH SERVICE
						mockLogin();
						break switchLoop;
					default:
						System.out.println("That is not a valid input. Please try again.");
						printWelcomeMenu();
						response = scan.nextLine();
						break switchLoop;
					}
			}				
	}

	private void printWelcomeMenu() {
		System.out.println("Welcome to the Employee Reimbursment System. \n"+ ""
				+ "What would you like to do? \n"
				+ "1) Login to the application \n"
				+ "0) Quit the application");		
	}

	
	private void mockLogin() {
		System.out.println("What type of user are you? \n"
				+ "1) EMPLOYEE \n"
				+ "2) FINANCE MANAGER \n"
				+ "0) Quit");
		String response = scan.nextLine();
		switch (response) {
			case"1":
				employeeMenu();
				break;
			case "2":
				financeManagerMenu();
				break;
			default:
				System.out.println("Wrong user.");
				response = scan.nextLine();
				break;
		}
	}
	
	private void mockOptionMenu() {
		System.out.println("Choose one option: \n"
				+ "1) a \n"
				+ "2) b \n"
				+ "0) c");
		String response = scan.nextLine();
		switch (response) {
			case"1":
				System.out.println("a");
				break;
			case "2":
				System.out.println("b");
				break;
			default:
				System.out.println("c");
				response = scan.nextLine();
				break;
		}
	}
	
	private void employeeMenu() {
		System.out.println("EMPLOYEE MENU");
		mockOptionMenu();
	}
	private void financeManagerMenu() {
		System.out.println("FINANCE MANAGER MENU");
		mockOptionMenu();
	}	
	
	
}
