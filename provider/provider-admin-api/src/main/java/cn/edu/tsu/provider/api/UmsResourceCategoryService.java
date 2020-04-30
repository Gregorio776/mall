package cn.edu.tsu.provider.api;

import cn.edu.tsu.common.model.UmsResourceCategory;

import java.util.List;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
public interface UmsResourceCategoryService{

    List<UmsResourceCategory> findAll();
}
