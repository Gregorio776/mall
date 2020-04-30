package cn.edu.tsu.provider.api;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 13:24
 */
public interface UmsAdminRoleRelationService {

    int updateAdminRoles(Long adminId, List<Long> roleIds);
}

