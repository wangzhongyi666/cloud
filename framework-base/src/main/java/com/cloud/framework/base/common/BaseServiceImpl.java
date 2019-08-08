package com.cloud.framework.base.common;

import com.cloud.framework.base.utils.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {

	private BaseMapper<T, PK> baseMapper;
	// public abstract BaseMapper<T,PK> baseMapper;
	public Log log = LogFactory.getLog(this.getClass().getSimpleName());

	public void setBaseMapper(BaseMapper<T, PK> baseMapper) {
		this.baseMapper = baseMapper;
	}

	public T selectById(PK id) {
		return baseMapper.selectById(id);
	}
	  /**
  	 * 查询总数量
  	 * 
  	 * @paramrecord
  	 * @return
  	 */
      public int findnum(Map<String, Object> map){
    	  return baseMapper.findnum(map);
      }
	/**
	 * 保存单一对象，如果要保存多个对象集合
	 * 
	 * @param entity
	 */
	public int insert(T entity) {
		return baseMapper.insert(entity);
	}

	public int insertSelective(T entity) {
		return baseMapper.insertSelective(entity);

	}

	@Override
	public int deleteByProperty(T entity) {

		return baseMapper.deleteByProperty(entity);
	}

	@Override
	public T findOneByCondition(Map<String, Object> map) {

		return baseMapper.findOneByCondition(map);
	}

	@Override
	public List<T> findList(Map<String, Object> map) {

		return baseMapper.findList(map);
	}

	/**
	 * 更新对象,如果属性为空，则不会进行对应的属性值更新,如果有属性要更新为null，
	 * 
	 * @param entity
	 *            要更新的实体对象
	 */
	public int update(T entity) {
		return baseMapper.update(entity);
	}
	
	
	public int updateOne(T entity) {
		return baseMapper.updateOne(entity);
	}

	/**
	 * 根据id删除对象
	 * 
	 * @paramid
	 */
	public int deleteById(PK id) {
		return baseMapper.deleteById(id);
	}

	/**
	 * 根据条件集合删除对象
	 * 
	 * @paramid
	 */
	public int deleteByCondition(Map<String, Object> condition) {
		return baseMapper.deleteByCondition(condition);

	}

	/**
	 * 根据传入的泛型参数类型查询该类型对应表中的所有数据，返回一个集合对象
	 * 
	 * @return 返回泛型参数类型的对象集合
	 */
	public List<T> findAll() {
		return baseMapper.findAll();
	}

	/**
	 * 根据条件集合进行分页查询
	 * 
	 * @paramcondition
	 *            查询条件
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public List<T> queryPageByCondition(Map<String, Object> map, Integer currentPage, Integer pageSize) {
		int start = Page.countOffset(pageSize, currentPage);
		return baseMapper.queryPageByCondition(map, start, pageSize);
	}

	/**
	 * 根据条件集合进行分页查询
	 * 
	 * @param实体对象
	 *            查询条件
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            页面大小
	 * @return 返回实体对象集合
	 */
	public List<T> queryPageByProperty(T entity, Integer currentPage, Integer pageSize) {
		// 开始记录数
		int start = Page.countOffset(pageSize, currentPage);

		return baseMapper.queryPageByProperty(entity, start, pageSize);
	}

	public List<T> queryPage(Integer currentPage, Integer pageSize) {
		int start = Page.countOffset(pageSize, currentPage);
		return baseMapper.queryPage(start, pageSize);
	}

	/**
	 * 根据任意属性和属性值进行对象模糊查询
	 * 
	 * @param property
	 *            进行对象匹配的属性
	 * @param value
	 *            进行对象匹配的模糊属性值
	 * @return
	 */
	public List<T> like(String property, Object value) {
		return baseMapper.like(property, value);
	}

	/**
	 * 根据条件集合进行指定类型对象集合查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象集合
	 */
	public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy) {
		return baseMapper.queryList(condition, orderBy, sortBy);
	}

	/**
	 * 根据条件集合进行指定类型单一对象查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象
	 */
	public T queryOne(Map<String, Object> condition) {
		return baseMapper.queryOne(condition);
	}

	/**
	 * 根据条件进行数量的查询
	 * 
	 * @param condition
	 * @return 返回符合条件的泛型参数对应表中的数量
	 */
	public Long count(Map<String, Object> condition) {
		return baseMapper.count(condition);
	}

	/**
	 * 该方法只有在主键为int时才有用，如果主键为其他数据类型进行使用，则会抛出异常
	 * 
	 * @name selectMaxId
	 * @active 查询实体对应表最大Id
	 * @time 上午10:04:05
	 * @exception/throws 如果主键类型不为int，会抛出类型转换异常
	 * @return 返回泛型参数对应表的主键最大值
	 */
	public Integer selectMaxId() {
		return baseMapper.selectMaxId();
	}
}
