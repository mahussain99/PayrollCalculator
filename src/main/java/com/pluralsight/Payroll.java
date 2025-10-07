package com.pluralsight;


import java.io.*;
import java.util.Scanner;

public class Payroll {
    public static void main(String[] args) {

        //Step 1:  Using Scanner for asking user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of employee file name");
        String employeeFileName = scanner.nextLine();
        System.out.println(" Enter the payroll file name");
        String payrollFileName = scanner.nextLine();

        //String fileName = "employees.csv";
        // BufferedReader to read a text file line-by-line.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(employeeFileName));
            BufferedWriter witter = new BufferedWriter(new FileWriter(payrollFileName));

            // "\\|" ---> only use backslash for Pipe
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                // Created employees object and calling from employee class

                Employees employee = new Employees(id, name, hoursWorked, payRate);
                // payroll out put information are empty now
                String payrollOutPutLine = "";

                /*System.out.printf("Employee ID: %d, Name: %s, Gross Pay: $%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());*/
                // Create a file text file
                payrollOutPutLine = String.format("%d|%s|$%.2f%n",
                        employee.getEmployeeId(), employee.getName(), employee.getGrossPay());

                // Store the all data
                witter.write(payrollFileName);
            }

            reader.close();
            witter.close();
            scanner.close();


        } catch (IOException e) {
            System.err.println("Error reading file: " + employeeFileName);
            System.out.println("Error reading file : " + payrollFileName);
        }
    }
}
