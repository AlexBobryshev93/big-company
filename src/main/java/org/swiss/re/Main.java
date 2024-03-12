package org.swiss.re;

import org.swiss.re.config.ParseConfig;
import org.swiss.re.csv.CSVParser;
import org.swiss.re.mapper.EmployeeMapper;
import org.swiss.re.service.EmployeeService;

public class Main {

    public static void main(String[] args) {
        var config = new ParseConfig();
        var parser = new CSVParser(config);
        var mapper = new EmployeeMapper();
        var employeeService = new EmployeeService();

        var records = parser.parseCSV();
        var employees = records.stream()
                .skip(config.getSkipLines())
                .map(mapper::map)
                .toList();
        employeeService.addAll(employees);

        var lowEarnings = employeeService.checkLowEarnings();
        System.out.println("Employees having too low salaries: ");
        lowEarnings.forEach((key, value) -> System.out.println("ID" + key + " (by " + value + " below the limit)"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> \n");

        var highEarnings = employeeService.checkHighEarnings();
        System.out.println("Employees having too high salaries: ");
        highEarnings.forEach((key, value) -> System.out.println("ID" + key + " (by " + value + " over the limit)"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> \n");

        var longLines = employeeService.checkLongReportingLines();
        System.out.println("Employees having too long reporting lines: ");
        longLines.forEach((key, value) -> System.out.println("ID" + key + " (by " + value + " over the limit)"));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> \n");
    }

}