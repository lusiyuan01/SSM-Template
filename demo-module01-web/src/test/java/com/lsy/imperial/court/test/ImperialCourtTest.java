package com.lsy.imperial.court.test;

import com.lsy.imperial.court.entity.Emp;
import com.lsy.imperial.court.entity.EmpExample;
import com.lsy.imperial.court.mapper.EmpMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring-persist.xml"})
public class ImperialCourtTest {

    @Autowired
    private DataSource dataSource;

    private Logger logger = LoggerFactory.getLogger(ImperialCourtTest.class);

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testEmpMapper() {
        List<Emp> empList = empMapper.selectByExample(new EmpExample());
        for (Emp emp : empList) {
            System.out.println("emp = " + emp);
        }
    }

}