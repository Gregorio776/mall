package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.model.UmsMenu;
import cn.edu.tsu.provider.api.UmsMenuService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 21:03
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Reference(version = "1.0.0")
    private UmsMenuService umsMenuService;

    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable(name = "parentId") Long parentId,
                                                  @RequestParam(name = "pageNum")Integer pageNum,
                                                  @RequestParam(name="pageSize")Integer pageSize){

        return CommonResult.success(umsMenuService.list(parentId, pageNum, pageSize));
    }

    @GetMapping("/{menuId}")
    public CommonResult<UmsMenu> info(@PathVariable long menuId){
        UmsMenu byId = umsMenuService.findById(menuId);
        return CommonResult.success(byId);
    }

    @PostMapping("/updateHidden/{menuId}")
    public CommonResult<?> updateHidden(@PathVariable Long menuId,Integer hidden){
        int i = umsMenuService.updateHidden(menuId, hidden);
        if(i>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @PostMapping("/delete/{menuId}")
    public CommonResult<Void> delete(@PathVariable long menuId){
        int delete = umsMenuService.delete(menuId);
        if(delete>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }



    @PostMapping("/update/{menuId}")
    public CommonResult<Void> update(@PathVariable long menuId,@RequestBody UmsMenu umsMenu){
        umsMenu.setId(menuId);
        int update = umsMenuService.update(umsMenu);
        if(update>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody UmsMenu umsMenu){
        int insert = umsMenuService.insert(umsMenu);
        if(insert>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
}
