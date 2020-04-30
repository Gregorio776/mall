package cn.edu.tsu.provider.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsPermissionMapper;
import cn.edu.tsu.provider.api.UmsPermissionService;
/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService{

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

}
