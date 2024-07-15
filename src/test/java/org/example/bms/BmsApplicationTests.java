package org.example.bms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
//import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class BmsApplicationTests {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        System.out.println(((DynamicRoutingDataSource) dataSource).getDataSources());
        connection.close();

//        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
//        int minIdle = druidDataSource.getMinIdle();
//        System.out.println("Actual minimum idle connections in Druid pool:" + minIdle);

        String sql = "select * from author";
        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
        System.out.println(list_maps);
    }

}
