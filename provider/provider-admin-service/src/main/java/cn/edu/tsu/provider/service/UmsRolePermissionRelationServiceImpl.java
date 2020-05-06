package cn.edu.tsu.provider.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsRolePermissionRelationMapper;
import cn.edu.tsu.provider.api.UmsRolePermissionRelationService;
/**
 * @author  Gregorio
 * @date  2020/4/30 13:10
 */
@Service
public class UmsRolePermissionRelationServiceImpl implements UmsRolePermissionRelationService{

    @Resource
    private UmsRolePermissionRelationMapper umsRolePermissionRelationMapper;

}
