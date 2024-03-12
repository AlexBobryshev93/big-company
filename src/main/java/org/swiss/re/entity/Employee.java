package org.swiss.re.entity;

import java.util.Objects;

/**
 * Current implementation allows to substitute this class with a record (Java 14).
 * Intentionally left as a class to partially imitate the entity layer.
 */
public class Employee {

    private Long id; // Assumed not null as an employee identifier
    private String firstName;
    private String lastName;
    private Long salary; // Assumed not null
    private Long managerId;

    public Employee(Long id, String firstName, String lastName, Long salary, Long managerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getSalary() {
        return salary;
    }

    public Long getManagerId() {
        return managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        var employee = (Employee) o;
        return id.equals(employee.id)
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && salary.equals(employee.salary)
                && Objects.equals(managerId, employee.managerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, salary, managerId);
    }
}
