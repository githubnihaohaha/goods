package com.goods.business.service.imp;

import com.github.pagehelper.PageHelper;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.SupplierService;
import com.goods.common.model.business.Supplier;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author: liu-wei
 * @date: 2022/11/22,0:46
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    
    @Autowired
    private SupplierMapper supplierMapper;
    
    /**
     * 分页查询所有物资来源信息(模糊查询)
     *
     * @param pageNum    当前页
     * @param pageSize   每页显示条数
     * @param supplierVO 值对象
     * @return PageVo
     */
    @Override
    public PageVO<Supplier> getSupplierListPage(int pageNum, int pageSize, SupplierVO supplierVO) {
        
        Example example = new Example(Supplier.class);
        
        if (supplierVO != null) {
            
            Example.Criteria criteria = example.createCriteria();
            
            if (!StringUtils.isEmpty(supplierVO.getName())) {
                criteria.andLike("name", "%" + supplierVO.getName() + "%");
            }
            if (!StringUtils.isEmpty(supplierVO.getContact())) {
                criteria.andLike("contact", "%" + supplierVO.getContact() + "%");
            }
            if (!StringUtils.isEmpty(supplierVO.getAddress())) {
                criteria.andLike("address", "%" + supplierVO.getAddress() + "%");
            }
            
        }
        
        
        List<Supplier> supplierList = supplierMapper.selectByExample(example);
        
        List<Supplier> page = ListPageUtils.page(supplierList, pageSize, pageNum);
        
        assert page != null : "没有数据!";
        return new PageVO<>(page.size(), page);
    }
}
