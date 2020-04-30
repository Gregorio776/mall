package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Gregorio
 * @date 2020/4/28 15:02
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
