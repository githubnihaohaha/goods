package com.goods.business.service;

import com.goods.common.error.BusinessException;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;

import java.util.List;

/**
 * @author: liu-wei
 * @date: 2022/11/21,14:39
 */
public interface ProductCategoryService {
    
    /**
     * 分页查询产品类别
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示条数
     * @return Map
     */
    PageVO<ProductCategoryTreeNodeVO> getCategoryTree(int pageNum, int pageSize);
    
    /**
     * 返回所有物资类别
     *
     * @return
     */
    List<ProductCategoryTreeNodeVO> getParentCategoryTree();
    
    /**
     * 添加方法
     *
     * @param productCategoryVO 值对象
     */
    void saveProductCategory(ProductCategoryVO productCategoryVO) throws BusinessException;
    
    /**
     * 通过id查询类别对象
     *
     * @param id
     * @return
     */
    ProductCategory getProductCategoryById(Long id);
    
    /**
     * 更新类别信息
     *
     * @param productCategoryVO
     */
    void updateCategory(ProductCategory productCategoryVO);
    
    /**
     * 通过id移除节点(判断是否有子节点,有则不能删除)
     * @param id
     */
    void deleteById(Long id) throws BusinessException;
}
