package com.goods.business.mapper;

import com.goods.common.model.business.ProductCategory;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;

/**
 * @author: liu-wei
 * @date: 2022/11/21,14:33
 */
@Repository
public interface ProductCategoryMapper extends Mapper<ProductCategory> {
}
