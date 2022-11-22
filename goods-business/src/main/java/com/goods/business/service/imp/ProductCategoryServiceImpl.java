package com.goods.business.service.imp;

import com.goods.business.converter.ProductCategoryConverter;
import com.goods.business.mapper.ProductCategoryMapper;
import com.goods.business.service.ProductCategoryService;
import com.goods.common.error.BusinessCodeEnum;
import com.goods.common.error.BusinessException;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.utils.CategoryTreeBuilder;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author: liu-wei
 * @date: 2022/11/21,14:40
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    
    @Resource
    private ProductCategoryMapper productCategoryMapper;
    
    
    /**
     * 分页查询产品类别
     * 1. 开启分页
     * 2. 获得所有实体类对象并将他转换为值传递VO对象
     * 3. 将VO对象转换为树结构
     * 4. 封装数据返回
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示条数
     * @return PageVO
     */
    @Override
    public PageVO<ProductCategoryTreeNodeVO> getCategoryTree(int pageNum, int pageSize) {
        
        
        List<ProductCategoryTreeNodeVO> treeNode = getProductCategoryTreeNodeVOS();
        
        
        List<ProductCategoryTreeNodeVO> PageVo = ListPageUtils.page(treeNode, pageSize, pageNum);
        
        
        return new PageVO<>(PageVo.size(), treeNode);
    }
    
    
    /**
     * 返回所有物资类别
     *
     * @return
     */
    @Override
    public List<ProductCategoryTreeNodeVO> getParentCategoryTree() {
        List<ProductCategoryTreeNodeVO> treeNodeVOList = getProductCategoryTreeNodeVOS();
        
        return treeNodeVOList;
    }
    
    /**
     * 添加方法
     *
     * @param productCategoryVO 值对象
     */
    @Override
    public void saveProductCategory(ProductCategoryVO productCategoryVO) throws BusinessException {
        
        if (productCategoryVO == null) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);
        }
        
        ProductCategory category = new ProductCategory();
        BeanUtils.copyProperties(productCategoryVO, category);
        
        category.setCreateTime(new Date());
        category.setModifiedTime(new Date());
        
        productCategoryMapper.insert(category);
    }
    
    /**
     * 通过id查询类别对象
     *
     * @param id
     * @return
     */
    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }
    
    /**
     * 更新类别信息
     *
     * @param productCategory
     */
    @Override
    public void updateCategory(ProductCategory productCategory) {
        
        if (productCategory != null) {
            // 设置修改时间
            productCategory.setModifiedTime(new Date());
            
            // 只更新对象中有数据的字段,没有传递数据的字段不会被更新
            productCategoryMapper.updateByPrimaryKeySelective(productCategory);
        }

//     productCategoryMapper.updateByPrimaryKeySelective(productCategory);
        
    
    }
    
    /**
     * 通过id移除节点(判断是否有子节点,有则不能删除)
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) throws BusinessException {
        
        
        ProductCategory category = new ProductCategory();
        category.setPid(id);
        
        
        if (productCategoryMapper.selectCount(category) > 0) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);
        }
        
        productCategoryMapper.deleteByPrimaryKey(id);
        
    }
    
    
    /**
     * 查询所有物资类别并封装为数结构
     *
     * @return
     */
    private List<ProductCategoryTreeNodeVO> getProductCategoryTreeNodeVOS() {
        // 查询数据库中的所有数据
        List<ProductCategory> categoryList = productCategoryMapper.selectAll();
        
        // 转vo对象
        List<ProductCategoryTreeNodeVO> nodeVOList =
                ProductCategoryConverter.converterToProductCategoryTreeNodeVO(categoryList);
        
        // 获得层级树
        return CategoryTreeBuilder.build(nodeVOList);
    }
    
}
