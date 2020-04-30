package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.dto.ums.UmsMenuNode;
import cn.edu.tsu.common.model.UmsMenu;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsMenuMapper;
import cn.edu.tsu.provider.api.UmsMenuService;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Service(version = "1.0.0")
public class UmsMenuServiceImpl implements UmsMenuService{

    @Resource
    private UmsMenuMapper umsMenuMapper;

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return umsMenuMapper.getMenuList(adminId);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = umsMenuMapper.selectAll();
        return menuList.stream()
                .filter(menu->menu.getParentId().equals(0L))
                .map(menu->covertMenuNode(menu,menuList)).collect(Collectors.toList());
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example e = new Example(UmsMenu.class);
        e.setOrderByClause("sort desc");
        e.createCriteria().andEqualTo("parentId",parentId);
        return umsMenuMapper.selectByExample(e);
    }

    /**
     * 将菜单列表转换成树形
     * @param umsMenu 父节点
     * @param list 所有节点
     * @return 父节点
     */
    private UmsMenuNode covertMenuNode(UmsMenu umsMenu,List<UmsMenu> list){
        UmsMenuNode menuNode = new UmsMenuNode();
        BeanUtils.copyProperties(umsMenu,menuNode);
        List<UmsMenuNode> menuNodes = list.stream()
                .filter(m->m.getParentId().equals(menuNode.getId()))
                .map(m->covertMenuNode(m,list))
                .collect(Collectors.toList());
        menuNode.setChildren(menuNodes);
        return menuNode;
    }


}
