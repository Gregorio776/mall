package cn.edu.tsu.provider.mapper;

import cn.edu.tsu.common.model.UmsMenu;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsMenuMapper extends MyMapper<UmsMenu> {
    List<UmsMenu> getMenuList(Long adminId);
}