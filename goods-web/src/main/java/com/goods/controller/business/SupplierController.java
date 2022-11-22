package com.goods.controller.business;

import com.goods.business.service.SupplierService;
import com.goods.common.error.BusinessException;
import com.goods.common.model.business.Supplier;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: liu-wei
 * @date: 2022/11/21,23:46
 */

@RestController
@RequestMapping("/business/supplier")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;
    
    /**
     * 分页查询所有物资来源信息
     *
     * @param pageNum    当前页
     * @param pageSize   每页显示条数
     * @param supplierVO 值对象
     * @return PageVo
     */
    @GetMapping("/findSupplierList")
    public ResponseBean findSupplierList(@RequestParam int pageNum,
                                         @RequestParam int pageSize,
                                         SupplierVO supplierVO) {
        
        PageVO<Supplier> pageVO = supplierService.getSupplierListPage(pageNum, pageSize, supplierVO);
        
        return ResponseBean.success(pageVO);
    }
    
    
    /**
     * 添加方法
     *
     * @param supplierVO 供应商值对象
     * @return
     */
    @PostMapping("/add")
    public ResponseBean saveSupplierInfo(@RequestBody SupplierVO supplierVO) {
        supplierService.saveSupplierInfo(supplierVO);
        return ResponseBean.success();
    }
    
    
    /**
     * 获取指定id的供应商信息
     *
     * @param id primary Key
     * @return supplier对象
     */
    @GetMapping("/edit/{id}")
    public ResponseBean editInfo(@PathVariable Long id) throws BusinessException {
        Supplier item = supplierService.getSupplierById(id);
        return ResponseBean.success(item);
    }
    
    /**
     * 更新供应商信息
     *
     * @param supplier
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseBean updateSupplierInfo(@RequestBody Supplier supplier) {
        
        supplierService.updateSupplierInfo(supplier);
        
        return ResponseBean.success();
        
    }
    
    
    /**
     * 根据id移除供应商信息
     *
     * @param id primary Key
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseBean deleteSupplier(@PathVariable Long id) {
        
        supplierService.deleteSupplierById(id);
        return ResponseBean.success();
    }
    
    
}
