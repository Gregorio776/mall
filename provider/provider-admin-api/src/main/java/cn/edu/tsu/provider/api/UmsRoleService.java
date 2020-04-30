package cn.edu.tsu.provider.api;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsRole;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsRoleService{

    List<UmsRole> getRoleList(Long AdminId);

    List<UmsRole> getAll();

    CommonPage<UmsRole> list(Integer pageNum,Integer pageSize,String keyword);

}
