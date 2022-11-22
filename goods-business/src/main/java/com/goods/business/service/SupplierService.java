package com.goods.business.service;

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
}
