package com.goods.business.converter;

import com.goods.common.model.business.ProductCategory;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: liu-wei
 * @date: 2022/11/21,15:22
 */
public class ProductCategoryConverter {
    
    /**
     * 将实体类集合转换为vo对象
     *
     * @param productCategoryList 实体类List
     * @return List<ProductCategoryTreeNodeVO>
     */
    public static List<ProductCategoryTreeNodeVO> converterToProductCategoryTreeNodeVO(
            List<ProductCategory> productCategoryList) {
        
        // 返回的结果
        List<ProductCategoryTreeNodeVO> voList = new ArrayList<>();
        
        // 遍历这个list,将它的信息复制到treeNode对象中
        if (!CollectionUtils.isEmpty(productCategoryList)) {
            for (ProductCategory category : productCategoryList) {
                
                ProductCategoryTreeNodeVO treeNodeVO = new ProductCategoryTreeNodeVO();
                BeanUtils.copyProperties(category, treeNodeVO);
                
                voList.add(treeNodeVO);
            }
        }
        return voList;
    }
}
