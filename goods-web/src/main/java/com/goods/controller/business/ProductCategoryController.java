package com.goods.controller.business;

import com.goods.business.service.ProductCategoryService;
import com.goods.common.error.BusinessCodeEnum;
import com.goods.common.error.BusinessException;
import com.goods.common.model.business.ProductCategory;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.ProductCategoryVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

/**
 * @author: liu-wei
 * @date: 2022/11/21,14:29
 */

@RestController
@RequestMapping("/business/productCategory")
public class ProductCategoryController {
    
    @Autowired
    private ProductCategoryService productCategoryService;
    
    /**
     * 分页查询物资类别
     *
     * @param pageNum
     * @param pageSize
     * @return 分页后的物资类别
     */
    @GetMapping("/categoryTree")
    public ResponseBean getProductCategory(@RequestParam int pageNum,
                                           @RequestParam int pageSize) {
        
        PageVO<ProductCategoryTreeNodeVO> pageVO = productCategoryService.getCategoryTree(pageNum, pageSize);
        
        return ResponseBean.success(pageVO);
    }
    
    
    /**
     * 查询所有的类别
     *
     * @return all ProductCategory
     */
    @GetMapping("/getParentCategoryTree")
    public ResponseBean getParentCategoryTree() {
        
        List<ProductCategoryTreeNodeVO> voList = productCategoryService.getParentCategoryTree();
        
        return ResponseBean.success(voList);
    }
    
    /**
     * 添加物资类别
     *
     * @param productCategoryVO 物资类别值对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public ResponseBean addProductCategory(
            @RequestBody ProductCategoryVO productCategoryVO) throws BusinessException {
        
        productCategoryService.saveProductCategory(productCategoryVO);
        
        return ResponseBean.success();
    }
    
    
    /**
     * 根据id查询类别
     *
     * @param id 类别id
     * @return
     */
    @GetMapping("/edit/{id}")
    public ResponseBean editById(@PathVariable Long id) {
        
        ProductCategory item = productCategoryService.getProductCategoryById(id);
        
        return ResponseBean.success(item);
    }
    
    
    /**
     * 修改商品类别信息
     *
     * @param productCategoryVO 值传递对象
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseBean updateCategory(@PathVariable Long id,
                                       @RequestBody ProductCategoryVO productCategoryVO) throws BusinessException {
    
        if (productCategoryVO == null) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);
        }
        
        productCategoryService.updateCategory(productCategoryVO);
        
        return ResponseBean.success();
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseBean deleteById(@PathVariable Long id){
        productCategoryService.deleteById(id);
        return ResponseBean.success();
    }
    
    
}
