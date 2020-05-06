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
@Table(name = "ums_menu")
public class UmsMenu implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "title")
    private String title;

    @Column(name = "`level`")
    private Integer level;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "`name`")
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "hidden")
    private Integer hidden;

    private static final long serialVersionUID = 1L;
}