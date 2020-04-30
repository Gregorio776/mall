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
@Table(name = "ums_resource_category")
public class UmsResourceCategory implements Serializable {
    @Column(name = "id")
    private Long id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "`name`")
    private String name;

    @Column(name = "sort")
    private Integer sort;

    private static final long serialVersionUID = 1L;
}