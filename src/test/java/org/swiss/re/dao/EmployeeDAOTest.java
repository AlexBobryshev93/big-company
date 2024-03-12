package org.swiss.re.dao;

import org.junit.jupiter.api.Test;
import org.swiss.re.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeDAOTest {

    private final EmployeeDAO subject = new EmployeeDAO();

    @Test
    void test() {
        var employees = subject.findAll();
        assertTrue(employees.isEmpty());

        subject.saveAll(List.of(
                new Employee(123L,"Joe","Doe",60000L,null),
                new Employee(124L,"Martin","Chekov",45000L,123L),
                new Employee(305L, "Brett", "Hardleaf", 34000L, 300L)
        ));
        employees = subject.findAll();
        assertEquals(3, employees.size());

        subject.saveAll(List.of(
                new Employee(123L,"Joe","Doe",60000L,null),
                new Employee(300L, "Alice", "Hasacat", 50000L, 124L)
        ));
        employees = subject.findAll();
        assertEquals(4, employees.size());
    }
}
