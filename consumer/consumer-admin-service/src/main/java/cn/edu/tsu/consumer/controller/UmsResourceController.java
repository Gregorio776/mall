package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.model.UmsResource;
import cn.edu.tsu.provider.api.UmsResourceService;
import cn.edu.tsu.provider.api.UmsRoleService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/30 10:48
 */
@RestController
@RequestMapping("/resource")
public class UmsResourceController {

    @Reference(version = "1.0.0")
    private UmsResourceService umsResourceService;

    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){

        return CommonResult.success(umsResourceService.list(categoryId, nameKeyword, urlKeyword, pageNum, pageSize));
    }

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody UmsResource umsResource){
        int res = umsResourceService.insert(umsResource);
        if(res>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @PostMapping("/delete/{resourceId}")
    public CommonResult<?> delete(@PathVariable Long resourceId){
        int delete = umsResourceService.delete(resourceId);
        if(delete>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @PostMapping("/update/{resId}")
    public CommonResult<?> update(@PathVariable Long resId,@RequestBody UmsResource umsResource){
        umsResource.setId(resId);
        int update = umsResourceService.update(umsResource);
        if(update>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }

}
