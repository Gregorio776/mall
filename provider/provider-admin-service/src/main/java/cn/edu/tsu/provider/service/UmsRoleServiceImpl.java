package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsRole;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsRoleMapper;
import cn.edu.tsu.provider.api.UmsRoleService;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service(version = "1.0.0")
public class UmsRoleServiceImpl implements UmsRoleService{

    @Resource
    private UmsRoleMapper umsRoleMapper;


    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsRoleMapper.getRoleList(adminId);
    }

    @Override
    public List<UmsRole> getAll() {
        return umsRoleMapper.selectAll();
    }

    @Override
    public CommonPage<UmsRole> list(Integer pageNum, Integer pageSize,String keyword) {
        PageHelper.startPage(pageNum,pageSize);
        Example e =new Example(UmsRole.class);
        Example.Criteria criteria =e.createCriteria();
        if(!StringUtils.isEmpty(keyword)){
            criteria.andLike("name","%"+keyword+"%");
        }
        List<UmsRole> umsRoles = umsRoleMapper.selectByExample(e);
        return CommonPage.restPage(umsRoles);
    }


}
