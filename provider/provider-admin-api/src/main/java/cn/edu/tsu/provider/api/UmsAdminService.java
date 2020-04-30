package cn.edu.tsu.provider.api;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsAdmin;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/28 15:39
 */
public interface UmsAdminService {
    UmsAdmin selectByName(String username);
    CommonPage<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);
    int reg(UmsAdmin umsAdmin);
    int update(UmsAdmin umsAdmin);
    int delete(Long id);
    UmsAdmin findById(Long id);
    int updateStatus(Long adminId,Integer status);
}

