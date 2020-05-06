package cn.edu.tsu.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author  Gregorio
 * @date  2020/4/29 13:24
 */
@Data
@Table(name = "ums_permission")
public class UmsPermission implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "pid")
    private Long pid;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`value`")
    private String value;

    @Column(name = "icon")
    private String icon;

    @Column(name = "`type`")
    private Integer type;

    @Column(name = "uri")
    private String uri;

    @Column(name = "`status`")
    private Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}