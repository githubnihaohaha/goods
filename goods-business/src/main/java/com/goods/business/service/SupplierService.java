package com.goods.business.service;

import com.goods.common.error.BusinessException;
import com.goods.common.model.business.Supplier;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;

/**
 * @author: liu-wei
 * @date: 2022/11/22,0:46
 */
public interface SupplierService {
    /**
     * 分页查询所有物资来源信息
     *
     * @param pageNum    当前页
     * @param pageSize   每页显示条数
     * @param supplierVO 值对象
     * @return PageVo
     */
    PageVO<Supplier> getSupplierListPage(int pageNum, int pageSize, SupplierVO supplierVO);
    
    /**
     * 添加方法
     *
     * @param supplierVO 供应商值对象
     * @return
     */
    void saveSupplierInfo(SupplierVO supplierVO);
    
    /**
     * 通过id查询供应商Supplier
     *
     * @param id primary Key
     * @return Supplier实体类
     */
    Supplier getSupplierById(Long id) throws BusinessException;
    
    /**
     * 更新实体类信息
     *
     * @param supplier
     */
    void updateSupplierInfo(Supplier supplier);
    
    /**
     * 通过主键移除供应商信息
     *
     * @param id primaryKey
     */
    void deleteSupplierById(Long id);
}
