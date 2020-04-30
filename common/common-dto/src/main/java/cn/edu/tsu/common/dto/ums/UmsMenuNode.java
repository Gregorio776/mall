package cn.edu.tsu.common.dto.ums;

import cn.edu.tsu.common.model.UmsMenu;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Gregorio
 * @date 2020/4/29 20:39
 */
@Getter
@Setter
public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;
}
