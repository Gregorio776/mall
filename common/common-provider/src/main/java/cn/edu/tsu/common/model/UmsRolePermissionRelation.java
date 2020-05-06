package cn.edu.tsu.common.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 * @author  Gregorio
 * @date  2020/4/30 13:10
 */
@Data
@Table(name = "ums_role_permission_relation")
public class UmsRolePermissionRelation implements Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_")
    private Long permission;

    private static final long serialVersionUID = 1L;
}