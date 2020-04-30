package cn.edu.tsu.consumer.controller;

import cn.edu.tsu.common.dto.CommonResult;
import cn.edu.tsu.common.model.UmsResourceCategory;
import cn.edu.tsu.provider.api.UmsResourceCategoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
