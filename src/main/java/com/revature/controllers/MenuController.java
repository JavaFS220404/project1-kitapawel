package com.revature.controllers;

import java.util.List;
import java.util.Scanner;

import com.revature.services.AuthService;

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
						String un;
						String pass;
						
						System.out.println("Please enter username.");
						response = scan.nextLine();
						un = response;
						System.out.println("Please enter password.");
						response = scan.nextLine();
						pass = response;
						login(un, pass);
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

	
	private void login(String username, String password) {		
		AuthService authService = new AuthService();
		authService.login(username, password);
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
