package com.tmall.dao;

import com.tmall.pojo.Product;
import com.tmall.pojo.Property;
import com.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    PropertyValue getByPropertyAndProduct(Property property, Product product);
}