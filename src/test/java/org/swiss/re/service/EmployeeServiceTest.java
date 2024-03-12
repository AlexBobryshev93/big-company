package org.swiss.re.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.swiss.re.entity.Employee;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    private static final int TEST_MAX_INTERMEDIARIES = 1;
    private static final int TEST_MIN_SALARY_SURPLUS = 10;
    private static final int TEST_MAX_SALARY_SURPLUS = 20;

    private final EmployeeService subject = new EmployeeService();

    @BeforeEach
    void init() {
        subject.getConfig().setMaxIntermediaries(TEST_MAX_INTERMEDIARIES);
        subject.getConfig().setSalarySurplusLowerBondPercent(TEST_MIN_SALARY_SURPLUS);
        subject.getConfig().setSalarySurplusUpperBondPercent(TEST_MAX_SALARY_SURPLUS);

        subject.addAll(List.of(
                new Employee(123L,"Joe","Doe",60000L,null),
                new Employee(124L,"Martin","Chekov",45000L,123L),
                new Employee(300L, "Alice", "Hasacat", 50000L, 124L),
                new Employee(305L, "Brett", "Hardleaf", 34000L, 300L)
        ));
    }

    @Test
    void testCheckLongReportingLines() {
        var expected = Map.of(305L, 1);
        var result = subject.checkLongReportingLines();

        assertEquals(expected, result);
    }

    @Test
    void testCheckHighEarnings() {
        var expected = Map.of(123L, 6000.0, 300L, 9200.0);
        var result = subject.checkHighEarnings();

        assertEquals(expected, result);
    }

    @Test
    void testCheckLowEarnings() {
        var expected = Map.of(124L, 10000.0);
        var result = subject.checkLowEarnings();

        assertEquals(expected, result);
    }
}
