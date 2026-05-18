package com.example.junit_testing.pojo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest
{

    @Test
    public void testConstructor()
    {
        Employee e = new Employee(1L,"nandini",450000);
        assertEquals(1L,e.getId());
        assertEquals("nandini",e.getName());
        assertEquals(450000,e.getSalary());
    }

    @Test
    public void testGetterAndSetter()
    {
        Employee e = new Employee();
        e.setId(2L);
        e.setName("nandini");
        e.setSalary(40000);

        assertEquals(2L,e.getId());
        assertEquals("nandini",e.getName());
        assertEquals(40000,e.getSalary());
    }
}

