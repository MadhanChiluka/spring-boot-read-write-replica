package com.masterslave.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masterslave.mysql.annotations.DataSource;
import com.masterslave.mysql.entities.Customer;
import com.masterslave.mysql.util.DataSourceType;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @DataSource(value = DataSourceType.SLAVE)
    List<Customer> findAll();
}
