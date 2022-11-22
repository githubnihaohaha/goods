package com.goods.business.service.imp;

import com.github.pagehelper.PageHelper;
import com.goods.business.mapper.SupplierMapper;
import com.goods.business.service.SupplierService;
import com.goods.common.error.BusinessCodeEnum;
import com.goods.common.error.BusinessException;
import com.goods.common.model.business.Supplier;
import com.goods.common.utils.ListPageUtils;
import com.goods.common.vo.business.SupplierVO;
import com.goods.common.vo.system.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
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
    
    /**
     * 添加方法
     *
     * @param supplierVO 供应商值对象
     * @return
     */
    @Override
    public void saveSupplierInfo(SupplierVO supplierVO) {
        
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(supplierVO, supplier);
        
        supplier.setCreateTime(new Date());
        supplier.setModifiedTime(new Date());
        
        supplierMapper.insertSelective(supplier);
        
    }
    
    /**
     * 通过id查询供应商Supplier
     *
     * @param id primary Key
     * @return Supplier实体类
     */
    @Override
    public Supplier getSupplierById(Long id) throws BusinessException {
        Supplier supplier = supplierMapper.selectByPrimaryKey(id);
        if (supplier == null) {
            throw new BusinessException(BusinessCodeEnum.PARAMETER_ERROR);
        }
        return supplier;
    }
    
    /**
     * 更新实体类信息
     *
     * @param supplier
     */
    @Override
    public void updateSupplierInfo(Supplier supplier) {
        
        if (supplier != null) {
            supplier.setModifiedTime(new Date());
            supplierMapper.updateByPrimaryKeySelective(supplier);
        }
        
    }
    
    /**
     * 通过主键移除供应商信息
     *
     * @param id primaryKey
     */
    @Override
    public void deleteSupplierById(Long id) {
        
        supplierMapper.deleteByPrimaryKey(id);
        
    }
    
    
}
