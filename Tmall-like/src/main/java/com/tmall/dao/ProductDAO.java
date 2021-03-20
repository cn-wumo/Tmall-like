package com.tmall.dao;

import com.tmall.pojo.Category;
import com.tmall.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product,Integer> {
    @Query(nativeQuery = true, value = "select * from product where " +
            "cid = :#{#category.id}")
    Page<Product> findByCategory(@Param("category") Category category, Pageable pageable);
    @Query(nativeQuery = true, value = "select * from product where " +
            "cid = :#{#category.id} order by id desc")
    List<Product> findByCategoryOrderById(@Param("category") Category category);
    @Query(nativeQuery = true, value = "select * from product where " +
            "name like :keyword")
    List<Product> findByNameLike(@Param("keyword")String keyword, Pageable pageable);

    @Query(nativeQuery = true, value = "insert into product values(:#{#bean.name},:#{#bean.subTitle},:#{#bean.originalPrice}," +
            ":#{#bean.promotePrice},:#{#bean.stock},:#{#bean.category.id},:#{#bean.createDate})")
    Product save(@Param("bean")Product bean);

    @Query(nativeQuery = true, value = "delete from product where id=:#{#bean.id}")
    Object deleteById(@Param("bean")Product bean);

    @Query(nativeQuery = true, value = "update product set name=:#{#bean.name},subTitle=:#{#bean.subTitle},originalPrice=:#{#bean.originalPrice}," +
            "promotePrice=:#{#bean.promotePrice},stock=:#{#bean.stock},cid=:#{#bean.category.id},createDate=:#{#bean.createDate}" +
            " where id=:#{#bean.id}")
    Object update(@Param("bean")Product bean);

}