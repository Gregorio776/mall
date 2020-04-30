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
        List<UmsMenu> list = umsMenuService.list(parentId, pageNum, pageSize);
        CommonPage<UmsMenu> page = CommonPage.restPage(list);
        return CommonResult.success(page);
    }
}
