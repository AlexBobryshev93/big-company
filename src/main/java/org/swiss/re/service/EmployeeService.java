package org.swiss.re.service;

import org.swiss.re.config.AppConfig;
import org.swiss.re.dao.EmployeeDAO;
import org.swiss.re.entity.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeeService {

    private final EmployeeDAO employeeDao = new EmployeeDAO();
    private final AppConfig config = new AppConfig();

    public AppConfig getConfig() {
        return config;
    }

    public void addAll(List<Employee> employees) {
        employeeDao.saveAll(employees);
    }

    /**
     * Finds Employees who have salaries exceeding the average salary of their direct subordinates by
     * more than the configured limit.
     * @return Map<Long, Double> with Employee ID as a key and the corresponding surplus amount as a value.
     */
    public Map<Long, Double> checkHighEarnings() {
        var employees = employeeDao.findAll();
        var subordinates = employees.stream()
                .collect(Collectors.toMap(Function.identity(),
                        employee -> employees.stream()
                                .filter(subordinate -> subordinate.getManagerId() != null
                                        && subordinate.getManagerId().equals(employee.getId()))
                                .toList())
                );
        var result = new HashMap<Long, Double>();

        subordinates.forEach((employee, subs) -> {
            var subsAverageSalary = subs.stream().mapToLong(Employee::getSalary).average();
            if (subsAverageSalary.isPresent()) {
                var limit = subsAverageSalary.getAsDouble() * (100 + config.getSalarySurplusUpperBondPercent()) / 100;
                if (employee.getSalary() > limit) {
                    result.put(employee.getId(), employee.getSalary() - limit);
                }
            }
        });
        return result;
    }

    /**
     * Finds Employees who have salaries not exceeding the average salary of their direct subordinates by
     * at least the required lower limit.
     * @return Map<Long, Double> with Employee ID as a key and the corresponding amount below the limit as a value.
     */
    public Map<Long, Double> checkLowEarnings() {
        var employees = employeeDao.findAll();
        var subordinates = employees.stream()
                .collect(Collectors.toMap(Function.identity(),
                        employee -> employees.stream()
                                .filter(subordinate -> subordinate.getManagerId() != null
                                        && subordinate.getManagerId().equals(employee.getId()))
                                .toList())
                );
        var result = new HashMap<Long, Double>();

        subordinates.forEach((employee, subs) -> {
            var subsAverageSalary = subs.stream().mapToLong(Employee::getSalary).average();
            if (subsAverageSalary.isPresent()) {
                var limit = subsAverageSalary.getAsDouble() * (100 + config.getSalarySurplusLowerBondPercent()) / 100;
                if (employee.getSalary() < limit) {
                    result.put(employee.getId(), limit - employee.getSalary());
                }
            }
        });
        return result;
    }

    /**
     * Finds Employees who have reporting lines longer than the configured limit.
     * @return Map<Long, Integer> with Employee ID as a key and the corresponding number, how many intermediaries
     * between him and the CEO the configured limit is exceeded by as a value.
     */
    public Map<Long, Integer> checkLongReportingLines() {
        var employees = employeeDao.findAll()
                .stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        var result = new HashMap<Long, Integer>();

        employees.values().forEach(employee -> {
            var count = 0;
            var reportsTo = employees.get(employee.getManagerId());
            while (reportsTo != null) {
                count++;
                reportsTo = employees.get(reportsTo.getManagerId());
            }
            if (count - 1 > config.getMaxIntermediaries()) {
                result.put(employee.getId(), count - 1 - config.getMaxIntermediaries());
            }
        });
        return result;
    }
}
