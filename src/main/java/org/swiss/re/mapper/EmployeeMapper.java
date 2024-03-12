package org.swiss.re.mapper;

import org.swiss.re.entity.Employee;

import java.util.List;

public class EmployeeMapper {

    /**
     *
     * @param values stands for Employee data previously parsed from CSV.
     * @return Employee entity or null value in case of corrupted data.
     */
    public Employee map(List<String> values) {
        try {
            var id = Long.parseLong(values.get(0));
            var firstName = values.get(1);
            var lastName = values.get(2);
            var salary = Long.parseLong(values.get(3));

            Long managerId = null;
            if (values.size() > 4) {
                managerId = Long.parseLong(values.get(4));
            }
            return new Employee(id, firstName, lastName, salary, managerId);
        } catch (RuntimeException e) {
            // log.warn imitation
            System.out.println("Error occurred due to corrupted data from CSV: " + e.getMessage()
                    + "\n Can't instantiate an Employee from: " + values);
            return null;
        }
    }
}
