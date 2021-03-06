package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsResource;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsResourceMapper;
import cn.edu.tsu.provider.api.UmsResourceService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service(version = "1.0.0")
public class UmsResourceServiceImpl implements UmsResourceService{

    @Resource
    private UmsResourceMapper umsResourceMapper;

    @Override
    public CommonPage<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example e = new Example(UmsResource.class);
        Example.Criteria criteria = e.createCriteria();
        if(categoryId!=null){
            criteria.andEqualTo("categoryId",categoryId);
        }
        if(!StringUtils.isEmpty(nameKeyword)){
            criteria.andLike("name",'%'+nameKeyword+'%');
        }
        if(!StringUtils.isEmpty(urlKeyword)){
            criteria.andLike("url",'%'+urlKeyword+'%');
        }
        return CommonPage.restPage(umsResourceMapper.selectByExample(e));
    }

    @Override
    public int insert(UmsResource umsResource) {
        return umsResourceMapper.insertSelective(umsResource);
    }

    @Override
    public int delete(Long resourceId) {
        return umsResourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    public int update(UmsResource umsResource) {
        return umsResourceMapper.updateByPrimaryKeySelective(umsResource);
    }
}
