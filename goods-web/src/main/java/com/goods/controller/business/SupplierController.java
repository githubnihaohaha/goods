package com.goods.controller.business;

import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Supplier;
import com.goods.common.response.ResponseBean;
import com.goods.common.vo.business.ProductCategoryTreeNodeVO;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    
    
}
