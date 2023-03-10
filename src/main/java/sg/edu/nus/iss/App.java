package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.io.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
    //     Thread thread1 = new Thread(new Runnable() {
        
    //     @Override
    //     public void run() {
    //         for (int i = 0; i<20; i++) {
    //             System.out.println(Thread.currentThread().getName() + 
    //             "\tRunnable ..." + i);
    //         }
    //     }
    // });
    // thread1.start();
    String task1= "task1";
    String task2 = "task2";
    String task3 = "task3";
    MyRunnableImplementation mRI = new MyRunnableImplementation(task1);
    MyRunnableImplementation mRI2 = new MyRunnableImplementation(task2);
    MyRunnableImplementation mRI3 = new MyRunnableImplementation(task3);
    MyRunnableImplementation mRI4 = new MyRunnableImplementation("task4");
    MyRunnableImplementation mRI5 = new MyRunnableImplementation("task5");


    // Thread thread2 = new Thread(mRI);
    // thread2.start();

    // Thread thread3 = new Thread(mRI);
    // thread3.start();

    //executor acting like a single thread executor
    //executor service executes the threadpool
    // ExecutorService executorService = Executors.newSingleThreadExecutor();
    // executorService.execute(mRI);
    // executorService.execute(mRI2);
    // executorService.shutdown();

    // ExecutorService executorService = Executors.newFixedThreadPool(3);
    // executorService.execute(mRI);
    // executorService.execute(mRI2);
    // executorService.execute(mRI3);
    // executorService.execute(mRI4);
    // executorService.execute(mRI5);
    // executorService.shutdown();

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(mRI);
    executorService.execute(mRI2);
    executorService.execute(mRI3);
    executorService.execute(mRI4);
    executorService.execute(mRI5);
    executorService.shutdown();

    MyRunnableInterface<Integer> addOperation  = (a, b) -> {
        return a+b;
    };
    MyRunnableInterface<Integer> multiplyOperation = (a, b) -> {
        return a*b;
    };
    MyRunnableInterface<Integer> minusOperation = (a, b) -> {
        return a-b;
    };
    MyRunnableInterface<String> concatenateString = (a, b) -> {
        return a+b;
    };
    MyMessageInterface printString = (a) -> {
        System.out.println(a);
    };

    System.out.println("addOperation: " + addOperation.process(1, 1));
    System.out.println("multiplyOperation: " + multiplyOperation.process(2, 5));
    System.out.println("minusOperation: " + minusOperation.process(10, 5));
    System.out.println("concatenateString: " + concatenateString.process("The fox", " Jumps over the wall"));
    printString.printMessage("Let's take a break at 12pm");

//list of employees
List<Employee> employees = new ArrayList<Employee>();
employees.add(new Employee(1, "Adam", "Cheng", 50000));
employees.add(new Employee(2, "Betty", "Zheng", 7000));
employees.add(new Employee(3, "Charlie", "Ng", 80000));
employees.add(new Employee(4, "Dan", "Tan", 50000));
employees.add(new Employee(5, "Esther", "Cheng", 9000));
// employees.forEach(emp -> {
//     System.out.println(emp);
//  });

List<Employee> filteredEmployees = employees.stream().filter(emp -> emp.getLastName().
contains("Ch")).collect(Collectors.toList());
//filteredEmployees.forEach(emp -> System.out.println(emp));

// employees.sort(Comparator.comparing(e -> e.getFirstName()));
// employees.sort(Comparator.comparing(Employee::getFirstName));
// employees.sort(Comparator.comparing(Employee::getFirstName).reversed());
Comparator<Employee> compare = Comparator.comparing(e ->e.getFirstName());
employees.sort(compare.reversed());


// employees.forEach(emp -> {
//     System.out.println(emp);
//  });

 Comparator<Employee> groupByComparator = Comparator.comparing(Employee::getFirstName).thenComparing(Employee::getLastName);
 employees.sort(groupByComparator);
 employees.forEach(emp -> 
 System.out.println(emp));

} 

}
