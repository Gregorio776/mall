package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsAdmin;

import cn.edu.tsu.provider.api.UmsAdminService;
import cn.edu.tsu.provider.mapper.UmsAdminMapper;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * @author Gregorio
 * @date 2020/4/28 15:39
 */
@Service(version = "1.0.0")
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public UmsAdmin selectByName(String username) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",username);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public CommonPage<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        Example e =new Example(UmsAdmin.class);
        Example.Criteria criteria =e.createCriteria();
        if(!StringUtils.isEmpty(keyword)){
            criteria.andLike("username","%"+keyword+"%");
            e.or(e.createCriteria().andLike("nickName","%"+keyword+"%"));
        }
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(e);

        return CommonPage.restPage(umsAdmins);
    }

    @Override
    public int reg(UmsAdmin umsAdmin) {
        UmsAdmin res = selectByName(umsAdmin.getUsername());
        if(res!=null){
            return 0;
        }
        umsAdmin.setCreateTime(new Date());
        return umsAdminMapper.insertSelective(umsAdmin);
    }

    @Override
    public int update(UmsAdmin umsAdmin) {
        UmsAdmin res = findById(umsAdmin.getId());
        if(res==null){
            return 0;
        }
        umsAdmin.setCreateTime(res.getCreateTime());
        umsAdmin.setId(res.getId());
        umsAdmin.setLoginTime(res.getLoginTime());
        return umsAdminMapper.updateByPrimaryKeySelective(umsAdmin);
    }

    @Override
    public int delete(Long id) {
        return umsAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UmsAdmin findById(Long id) {
        return umsAdminMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long adminId, Integer status) {
        UmsAdmin u =new UmsAdmin();
        u.setId(adminId);
        u.setStatus(status);
        return umsAdminMapper.updateByPrimaryKeySelective(u);
    }


}

