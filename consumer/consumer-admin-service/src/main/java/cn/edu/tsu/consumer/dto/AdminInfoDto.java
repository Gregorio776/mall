package cn.edu.tsu.consumer.dto;

import cn.edu.tsu.common.model.UmsMenu;
import cn.edu.tsu.common.model.UmsRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 13:57
 */
@Data
public class AdminInfoDto implements Serializable {
    private String username;
    private List<UmsMenu> menus;
    private List<UmsRole> roles;
    private String icon;
}
