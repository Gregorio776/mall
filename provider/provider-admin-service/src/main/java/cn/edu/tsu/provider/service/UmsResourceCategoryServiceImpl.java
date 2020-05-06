package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.model.UmsResourceCategory;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsResourceCategoryMapper;
import cn.edu.tsu.provider.api.UmsResourceCategoryService;
import org.apache.dubbo.config.annotation.Service;

import java.util.Date;
import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service(version = "1.0.0")
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService{

    @Resource
    private UmsResourceCategoryMapper umsResourceCategoryMapper;

    @Override
    public List<UmsResourceCategory> findAll() {
        return umsResourceCategoryMapper.selectAll();
    }

    @Override
    public int insert(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return umsResourceCategoryMapper.insertSelective(umsResourceCategory);
    }

    @Override
    public int delete(Long rcId) {
        return umsResourceCategoryMapper.deleteByPrimaryKey(rcId);
    }

    @Override
    public int update(UmsResourceCategory umsResourceCategory) {
        return umsResourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory);
    }
}
