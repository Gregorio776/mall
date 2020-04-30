package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.model.UmsResourceCategory;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsResourceCategoryMapper;
import cn.edu.tsu.provider.api.UmsResourceCategoryService;
import org.apache.dubbo.config.annotation.Service;

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
}
