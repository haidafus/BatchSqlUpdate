package com.spring.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.object.BatchSqlUpdate;
import org.springframework.jdbc.object.GenericSqlQuery;
import org.springframework.stereotype.Repository;

import com.spring.person.Person;
@Repository
public class BatchSqlUpdateExample implements IBatchSqlUpdateExample {

    @Autowired
    private DataSource dataSource;
    private BatchSqlUpdate updateSql;
    private BatchSqlUpdate insertSql;
    private GenericSqlQuery<Person> loadSqlQuery;

    @PostConstruct
    private void postConstruct() {
        initLoadSql();
        initInsertSql();
        initUpdateSql();
    }

    private void initInsertSql() {
        insertSql = new BatchSqlUpdate();
        insertSql.setDataSource(dataSource);
        // do you forgot primary key?
        insertSql.setSql("insert into PERSON (first_name, last_name, address) values (?, ?, ?)");
        insertSql.declareParameter(new SqlParameterValue(Types.VARCHAR, "first_name"));
        insertSql.declareParameter(new SqlParameterValue(Types.VARCHAR, "last_name"));
        insertSql.declareParameter(new SqlParameterValue(Types.VARCHAR, "address"));
    }

    private void initUpdateSql() {
        updateSql = new BatchSqlUpdate();
        updateSql.setDataSource(dataSource);
        updateSql.setSql("update PERSON set address = ? where id = ?");
        updateSql.declareParameter(new SqlParameterValue(Types.VARCHAR, "address"));
        updateSql.declareParameter(new SqlParameterValue(Types.BIGINT, "id"));
    }

    void initLoadSql() {
        loadSqlQuery = new GenericSqlQuery<>();
        loadSqlQuery.setDataSource(dataSource);
        loadSqlQuery.setSql("select * from Person");
        loadSqlQuery.setRowMapper(new PersonRowMapper());
    }
    

   

	public void runExmaple() {
        //inserting records
        insertSql.update("Dana", "Whitley", "464 Gorsuch Drive");
        insertSql.update("Robin", "Cash", "64 Zella Park");
        insertSql.flush();
        System.out.printf("Records inserted, update count %s%n",
                Arrays.toString(insertSql.getRowsAffected()));
        insertSql.reset();

        //loading all person
        List<Person> list = loadSqlQuery.execute();
        System.out.printf("Records loaded: %s%n", list);

        //updating
        updateSql.update("121 Marswood dr", 1);
        updateSql.update("121 Marswood dr", 2);
        updateSql.flush();
        System.out.printf("Records inserted, update count %s%n",
                Arrays.toString(updateSql.getRowsAffected()));
        updateSql.reset();

        //loading again
        System.out.println("Loading again after update");
        List<Person> list2 = loadSqlQuery.execute();
        System.out.printf("Records loaded after update: %s%n", list2);
    }
}