package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.model.UmsResourceCategory;
import cn.edu.tsu.provider.api.UmsResourceCategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/30 11:44
 */
@RestController
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    @Reference(version = "1.0.0")
    private UmsResourceCategoryService umsResourceCategoryService;
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll(){
        List<UmsResourceCategory> all = umsResourceCategoryService.findAll();
        return CommonResult.success(all);
    }

    @PostMapping("/create")
    public CommonResult<?> create(@RequestBody UmsResourceCategory umsResourceCategory){
        int insert = umsResourceCategoryService.insert(umsResourceCategory);
        if(insert>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }
    @PostMapping("/delete/{id}")
    public CommonResult<?> delete(@PathVariable Long id){
        int delete = umsResourceCategoryService.delete(id);
        if(delete>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();

    }
    @PostMapping("/update/{id}")
    public CommonResult<?> update(@PathVariable Long id,@RequestBody UmsResourceCategory umsResourceCategory){
        umsResourceCategory.setId(id);
        int update = umsResourceCategoryService.update(umsResourceCategory);
        if(update>0){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

}
