package org.swiss.re.dao;

import org.swiss.re.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
This class imitates the behavior of real life DAOs in production environment.
 */
public class EmployeeDAO {

    private final List<Employee> employees = new ArrayList<>();

    /**
     *
     * @return List<Employee> as a new collection.
     * This implementation doesn't prevent changes of the internal state of elements.
     */
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }

    /**
     *
     * @param entities stands for new entities that have to be persisted.
     * @return List<Employee> of entities that have been actually saved.
     * In case of collision by ID the new entity is just skipped.
     */
    public List<Employee> saveAll(List<Employee> entities) {
        var existingEmployees = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));
        var employeesToAdd = entities.stream()
                .filter(employee -> !existingEmployees.containsKey(employee.getId()))
                .toList();
        employees.addAll(employeesToAdd);

        return employeesToAdd;
    }

}
