package org.example.Service;

import org.example.Dao.EmployeeDao;
import org.example.event.SalaryChangeEvent;
import org.example.model.EmployeeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

@Component
public class SalaryCalculator implements ApplicationEventPublisherAware {
    private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

    private double baseSalary = 100;

    private double TotalCostOfNumOfSales= 200;

    private double Percentage = 1.5;

    private ApplicationEventPublisher eventPublisher;
   private final EmployeeDao employeeDao;


   @Autowired
    public SalaryCalculator(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }


    public void updateSalariedCommissionEmployee(EmployeeEnum employeeEnum, int id, String type, double bSalary) throws IOException, SQLException {

       switch (employeeEnum){
           case SALARIEDCOMMISSION:

               baseSalary = bSalary;
            double salary = baseSalary + (TotalCostOfNumOfSales * Percentage);
            employeeDao.updateEmployee(id,salary);
               this.eventPublisher.publishEvent(new SalaryChangeEvent(this, employeeDao.show(type)));
            break;
           default:
               System.out.println("try to update SALARIEDCOMMISSION");
       }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
