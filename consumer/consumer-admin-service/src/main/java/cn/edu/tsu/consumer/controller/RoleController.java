package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.model.UmsRole;
import cn.edu.tsu.provider.api.UmsRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 15:54
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Reference(version = "1.0.0")
    private UmsRoleService umsRoleService;

    @GetMapping("/listAll")
    public CommonResult<?> listAll(){
        return CommonResult.success(umsRoleService.getAll());
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(name = "pageNum") Integer pageNum,
                                                  @RequestParam(name="pageSize") Integer pageSize,
                                                  @RequestParam(name = "keyword",required = false) String keyword){
        CommonPage<UmsRole> list = umsRoleService.list(pageNum, pageSize,keyword);
        return CommonResult.success(list);
    }


}
