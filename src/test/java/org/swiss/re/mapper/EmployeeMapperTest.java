package org.swiss.re.mapper;

import org.junit.jupiter.api.Test;
import org.swiss.re.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class EmployeeMapperTest {

    private final EmployeeMapper subject = new EmployeeMapper();

    @Test
    void testMap() {
        var values = List.of("124","Martin","Chekov","45000","123");
        var expectedEmployee = new Employee(124L,"Martin","Chekov",45000L,123L);

        var result = subject.map(values);

        assertEquals(expectedEmployee, result);
    }

    @Test
    void testMapWhenManagerIdIsNotPresent() {
        var values = List.of("123", "Joe", "Doe", "60000");
        var expectedEmployee = new Employee(123L,"Joe","Doe",60000L,null);

        var result = subject.map(values);

        assertEquals(expectedEmployee, result);
    }

    @Test
    void testMapWhenDataIsCorrupted() {
        var values = List.of("Id", "firstName", "lastName", "salary", "managerId");

        var result = subject.map(values);

        assertNull(result);
    }
}
