package com.tmall.dao;

import com.tmall.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}