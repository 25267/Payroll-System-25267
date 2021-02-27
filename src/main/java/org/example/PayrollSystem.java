package org.example;

import org.example.Config.ConfigFile;
import org.example.Dao.EmployeeDao;
import org.example.Service.SalaryCalculator;
import org.example.model.Employee;
import org.example.model.EmployeeEnum;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class PayrollSystem {
    private static Boolean bool = true;
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                ConfigFile.class
        );

        EmployeeDao employeeDao = context.getBean("employeeDao", EmployeeDao.class);

        SalaryCalculator salaryCalculator = context.getBean("salaryCalculator", SalaryCalculator.class);

        while (bool) {
            System.out.println("1. To Return all Employees");
            System.out.println("2. To return  Employee by their type ");
            System.out.println("3. To update SalariedCommission Employee's salary");
            System.out.println("0. To Quit");
            String choice = read.readLine();

            switch (choice){
                case "1":
                    for(Employee employee : employeeDao.getEmployeeList()){
                        System.out.println(employee.getId() + "," + employee.getType() + "," + employee.getSalary());

                    }
                    break;
                case "2":
                    System.out.println("Enter employee type");
                    String type = read.readLine();
                    System.out.println("type: " + employeeDao.show(type).getType());
                    System.out.println("salary: " + employeeDao.show(type).getSalary());
                    break;
                case "3":
                    System.out.println("Enter new base salary for Employee");
                    double bSalary = Double.parseDouble(read.readLine());
                    salaryCalculator.updateSalariedCommissionEmployee(EmployeeEnum.SALARIEDCOMMISSION,
                            employeeDao.show("SalariedCommission").getId(), employeeDao.show("SalariedCommission").getType(), bSalary);
                    break;

                default: bool =false;
            }

        }

    }

}
