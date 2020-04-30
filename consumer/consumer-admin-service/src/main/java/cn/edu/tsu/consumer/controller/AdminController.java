package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.dto.ResponseResult;
import cn.edu.tsu.common.model.UmsAdmin;
import cn.edu.tsu.common.model.UmsMenu;
import cn.edu.tsu.common.model.UmsRole;
import cn.edu.tsu.consumer.dto.RoleUpdateDto;
import cn.edu.tsu.provider.api.UmsAdminRoleRelationService;
import cn.edu.tsu.provider.api.UmsAdminService;
import cn.edu.tsu.provider.api.UmsMenuService;
import cn.edu.tsu.provider.api.UmsRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gregorio
 * @date 2020/4/28 15:50
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;
    @Reference(version = "1.0.0")
    private UmsMenuService umsMenuService;
    @Reference(version = "1.0.0")
    private UmsRoleService umsRoleService;

    @Reference(version = "1.0.0")
    private UmsAdminRoleRelationService umsAdminRoleRelationService;
    @GetMapping("/info")
    public ResponseResult<?> info(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UmsAdmin umsAdmin = umsAdminService.selectByName(authentication.getName());
        List<UmsMenu> menuList = umsMenuService.getMenuList(umsAdmin.getId());
        List<UmsRole> roleList = umsRoleService.getRoleList(umsAdmin.getId());
        List<String> roles = new ArrayList<>();
        for(UmsRole r:roleList){
            roles.add(r.getName());
        }
        Map<String,Object> data = new HashMap<>();
        data.put("username",authentication.getName());
        data.put("menus",menuList);
        data.put("roles",roles);
        data.put("icon",umsAdmin.getIcon());

        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"success",data);
    }

    @GetMapping("/list")
    public CommonResult<?> list(@RequestParam(name = "pageNum") Integer pageNum,
                                     @RequestParam(name = "pageSize") Integer pageSize,
                                     @RequestParam(name = "keyword",required = false) String keyword){
        return CommonResult.success(umsAdminService.list(keyword, pageSize, pageNum));

    }

    @PostMapping("/updateStatus/{adminId}")
    public CommonResult<?> updateStatus(@PathVariable Long adminId,Integer status){
        int res = umsAdminService.updateStatus(adminId, status);
        if(res>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed("failed");
    }

    @PostMapping("/register")
    public CommonResult<?> reg(@RequestBody UmsAdmin umsAdmin){
        umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
        int reg = umsAdminService.reg(umsAdmin);
        if(reg>0){
            return CommonResult.success(null,"注册成功");
        }
        return CommonResult.failed("注册失败");
    }

    @PostMapping("/role/update")
    public CommonResult<?> roleUpdate(RoleUpdateDto roleUpdateDto){
        umsAdminRoleRelationService.updateAdminRoles(roleUpdateDto.getAdminId(),roleUpdateDto.getRoleIds());
        return CommonResult.success(null,"success");
    }

    @GetMapping("/delete/{adminId}")
    public CommonResult<?> delete(@PathVariable Long adminId){
        int delete = umsAdminService.delete(adminId);
        if(delete>0){
            return CommonResult.success(null,"删除成功");
        }
        return CommonResult.failed();
    }

    @GetMapping("/role/{adminId}")
    public CommonResult<List<UmsRole>> adminRole(@PathVariable Long adminId){
        return CommonResult.success(umsRoleService.getRoleList(adminId));
    }

    @PostMapping("/update/{id}")
    public CommonResult<?> update(@RequestBody UmsAdmin umsAdmin,@PathVariable Long id){
        umsAdmin.setId(id);
        int update = umsAdminService.update(umsAdmin);
        if(update>0){
            return CommonResult.success(null,"更新成功");
        }
        return CommonResult.failed();
    }

}
