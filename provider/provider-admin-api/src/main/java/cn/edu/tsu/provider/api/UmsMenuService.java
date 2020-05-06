package cn.edu.tsu.provider.api;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.ums.UmsMenuNode;
import cn.edu.tsu.common.model.UmsMenu;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsMenuService{

    List<UmsMenu> getMenuList(Long adminId);
    UmsMenu findByName(String name);
    UmsMenu findById(Long menuId);
    int updateHidden(Long meunId,Integer hidden);
    int update(UmsMenu umsMenu);
    int insert(UmsMenu umsMenu);
    int delete(Long menuId);

    List<UmsMenuNode> treeList();

    /**
     * 分页查询菜单列表
     * @param parentId 父Id
     * @param pageNum 页号
     * @param pageSize 页大小
     * @return list
     */
    CommonPage<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize);
}
