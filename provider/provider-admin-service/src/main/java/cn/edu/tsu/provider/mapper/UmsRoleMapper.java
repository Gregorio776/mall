package cn.edu.tsu.provider.mapper;

import cn.edu.tsu.common.model.UmsRole;
import org.apache.ibatis.annotations.Property;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsRoleMapper extends MyMapper<UmsRole> {

    List<UmsRole> getRoleList(Long adminId);


}