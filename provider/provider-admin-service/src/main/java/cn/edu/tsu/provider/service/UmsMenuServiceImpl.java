package cn.edu.tsu.provider.service;

import cn.edu.tsu.common.dto.CommonPage;
import cn.edu.tsu.common.dto.ums.UmsMenuNode;
import cn.edu.tsu.common.model.UmsMenu;

import javax.annotation.Resource;
import cn.edu.tsu.provider.mapper.UmsMenuMapper;
import cn.edu.tsu.provider.api.UmsMenuService;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
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
    public UmsMenu findByName(String name) {
        if(StringUtil.isEmpty(name)){
            return null;
        }
        Example e = new Example(UmsMenu.class);
        e.createCriteria().andEqualTo("name",name);
        return umsMenuMapper.selectOneByExample(e);
    }

    @Override
    public UmsMenu findById(Long menuId) {
        UmsMenu umsMenu = umsMenuMapper.selectByPrimaryKey(menuId);
        System.out.println(umsMenu);
        return umsMenu;
    }

    @Override
    public int updateHidden(Long menuId, Integer hidden) {
        UmsMenu u =new UmsMenu();
        u.setId(menuId);
        u.setHidden(hidden);
        Example e =new Example(UmsMenu.class);
        e.createCriteria().andEqualTo("id",menuId);

        return umsMenuMapper.updateByPrimaryKeySelective(u);
    }

    @Override
    public int update(UmsMenu umsMenu) {
        return umsMenuMapper.updateByPrimaryKey(umsMenu);
    }

    @Override
    public int insert(UmsMenu umsMenu) {
        UmsMenu umsMenu1 = findByName(umsMenu.getName());
        if(umsMenu1==null){
            umsMenu.setCreateTime(new Date());
            // TODO level 计算
            if(umsMenu.getParentId().equals(0L)){
                umsMenu.setLevel(0);
            }else {
                umsMenu.setLevel(1);
            }
            return umsMenuMapper.insert(umsMenu);
        }
        return 0;
    }

    @Override
    public int delete(Long menuId) {
        // TODO 删除策略 1.父菜单删除后子菜单全部删除  2.父菜单删除后将子菜单移到父菜单的父菜单上
        //
        return umsMenuMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = umsMenuMapper.selectAll();
        return menuList.stream()
                .filter(menu->menu.getParentId().equals(0L))
                .map(menu->covertMenuNode(menu,menuList)).collect(Collectors.toList());
    }

    @Override
    public CommonPage<UmsMenu> list(Long parentId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example e = new Example(UmsMenu.class);
        e.setOrderByClause("sort desc");
        e.createCriteria().andEqualTo("parentId",parentId);
        return CommonPage.restPage(umsMenuMapper.selectByExample(e));
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
