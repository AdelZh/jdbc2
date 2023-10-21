package org.example;

import org.example.config.Config;
import org.example.dao.EmployeeDao;
import org.example.dao.impl.EmployeeDaoImpl;
import org.example.dao.impl.JobDaoImpl;
import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.JobService;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.service.impl.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args ) {

        Config.getConnection();

        JobService jobService=new JobServiceImpl();
        EmployeeService employeeService=new EmployeeServiceImpl();

        Scanner scanner=new Scanner(System.in);
        while (true){
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    employeeService.creatEmployee("Job", List.of(
                            "id serial primary key",
                            "position varchar(50)",
                            "profession varchar(50)",
                            "description varchar(50)",
                            "experience int"
                    ));
                            break;
                case 2:
                    List<String> employeeColumns = List.of(
                            "id INT PRIMARY KEY",
                            "firstName VARCHAR(255)",
                            "lastName VARCHAR(255)",
                            "age INT",
                            "email VARCHAR(255)",
                            "job_id integer references job(id)"
                    );
                    employeeService.creatEmployee("Employee", employeeColumns);
                    break;
                case 3:
                    Job job11=new Job(1L,"Mentor", "Java", "Backend Developer", 1);
                    Job job1=new Job(2L,"Management", "JavaScript", "Frontend developer", 2);
                    Job job2=new Job(3L,"Instructor", "Java", "Backend Developer", 3);

                    jobService.addJob(job11);
                    jobService.addJob(job1);
                    jobService.addJob(job2);
                    break;
                case 4:
                    System.out.println(jobService.getJobById(1L));
                    break;
                case 5:
                    Employee employee=new Employee(1L, "Adel", "Smith", 19, "adel@gmail.com", 1);
                    Employee employee1=new Employee(2L, "Sam", "Smith", 19, "alina@gmail.com", 2);
                    Employee employee2=new Employee(3L, "Alina", "Smith", 19, "sam@gmail.com", 3);
                    employeeService.addEmployee(employee);
                    employeeService.addEmployee(employee1);
                    employeeService.addEmployee(employee2);
                    break;
                case 6:
                    System.out.println(jobService.getJobByEmployeeId(3L));
                    break;
                case 7:
                   jobService.deleteDescriptionColumn();
                     case 8:
                    System.out.println(employeeService.findByEmail("adel@gmail.com"));
                    break;
                case 9:
                    employeeService.cleanTable();
                    break;
                case 10:
                    System.out.println(employeeService.getEmployeeByPosition("Mentor"));
                    break;
                case 11:
                    System.out.println(employeeService.getAllEmployees());
                    break;
                case 12:
                    System.out.println(employeeService.getEmployeeById(1L));
                    break;
                case 13:
                    Employee updatedEmployee = new Employee();
                    updatedEmployee.setId(1L);
                    updatedEmployee.setFirstName("New First Name");
                    updatedEmployee.setEmail("newemail@example.com");

                   employeeService.updateEmployee(1L, updatedEmployee);
                   break;
                case 14:
                    employeeService.dropTable();
                    break;
                case 15:
                    jobService.deleteDescriptionColumn();

            }


        }
    }
}




