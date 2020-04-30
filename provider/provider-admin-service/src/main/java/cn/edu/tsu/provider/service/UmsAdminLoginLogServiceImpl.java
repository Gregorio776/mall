package cn.edu.tsu.provider.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsAdminLoginLogMapper;
import cn.edu.tsu.provider.api.UmsAdminLoginLogService;
/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

}
