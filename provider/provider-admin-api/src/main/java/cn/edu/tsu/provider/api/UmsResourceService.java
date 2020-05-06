package cn.edu.tsu.provider.api;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.model.UmsMenu;
import cn.edu.tsu.common.model.UmsResource;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsResourceService{

    /**
     * 根据关键字分页查询资源信息
     * @param categoryId 资源分类
     * @param nameKeyword 名称关键字
     * @param urlKeyword 路径关键字
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return {@link List<UmsResource>}
     */
    CommonPage<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageNum, Integer pageSize);

    int insert(UmsResource umsResource);
    int delete(Long resourceId);
    int update(UmsResource umsResource);

}
