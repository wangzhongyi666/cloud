package com.cloud.framework.base.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 底层基本的dao的接口
 * 
 * @author
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseService<T, PK extends Serializable> {

	/**
	 * 添加对象
	 * 
	 * @param record
	 * @return
	 */
	int insert(T record);

	int insertSelective(T entity);
	  /**
  	 * 查询总数量
  	 * 
  	 * @paramrecord
  	 * @return
  	 */
      public int findnum(Map<String, Object> map);
      
      
	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @return
	 */
     /**
      * 查询所属栏目内容
  	 * 
  	 * @param DdUserMeunDto
  	 * @return
  	 */
     /**
      * 查询手机端所属栏目内容
  	 * 
  	 * @param
  	 */
	public int update(T entity);
	public int updateOne(T entity);
	/**
	 * 根据条件集合删除对象
	 * 
	 * @paramid
	 */
	public int deleteByCondition(Map<String, Object> condition);

	/**
	 * 根据属性和属性值删除对象
	 * 
	 * @paramid
	 */
	public int deleteByProperty(T entity);

	/**
	 * 根据ID删除对象
	 * 
	 * @paramid
	 * @return
	 */
	public int deleteById(PK id);

	/**
	 * 根据id进行对象查询
	 * 
	 * @paramid
	 * @return
	 */
	public T selectById(PK id);

	/**
	 * 根据任意属性和属性值进行对象查询，如果返回多个对象，将抛出异常
	 * 
	 * @paramproperty
	 *            进行对象匹配的属性
	 * @paramvalue
	 *            进行对象匹配的属性值
	 * @return 返回泛型参数类型对象
	 */
	public T findOneByCondition(Map<String, Object> map);

	/**
	 * 根据任意（单一）属性和属性值进行集合查询
	 * 
	 * @paramproperty
	 *            进行对象匹配的属性
	 * @paramvalue
	 *            进行对象匹配的属性值
	 * @return 返回泛型参数类型的对象集合
	 */
	// public List<T> findList(String property, Object value);
	public List<T> findList(Map<String, Object> map);

	/**
	 * 根据传入的泛型参数类型查询该类型对应表中的所有数据，返回一个集合对象
	 * 
	 * @return 返回泛型参数类型的对象集合
	 */
	public List<T> findAll();

	/**
	 * 根据条件集合进行分页查询
	 * 
	 * @param condition
	 *            查询条件
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            页面大小
	 * @return 返回Pager对象
	 */
	public List<T> queryPageByCondition(Map<String, Object> condition, Integer currentPage, Integer pageSize);

	/**
	 * 根据条件集合进行分页查询
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public List<T> queryPageByProperty(T entity, Integer currentPage, Integer pageSize);

	public List<T> queryPage(Integer currentPage, Integer pageSize);

	/**
	 * 根据任意属性和属性值进行对象模糊查询
	 * 
	 * @param property
	 *            进行对象匹配的属性
	 * @param value
	 *            进行对象匹配的模糊属性值
	 * @return
	 */
	public List<T> like(String property, Object value);

	/**
	 * 根据条件集合进行指定类型对象集合查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数，请参看{@link#getEntityClass()}
	 */
	public List<T> queryList(Map<String, Object> condition, String orderBy, String sortBy);

	/**
	 * 根据条件集合进行指定类型单一对象查询
	 * 
	 * @param condition
	 *            进行查询的条件集合
	 * @return 返回泛型参数类型的对象
	 */
	public T queryOne(Map<String, Object> condition);

	/**
	 * 根据条件进行数量的查询
	 * 
	 * @param condition
	 * @return 返回符合条件的泛型参数对应表中的数量
	 */
	public Long count(Map<String, Object> condition);

	/**
	 * 该方法只有在主键为int时才有用，如果主键为其他数据类型进行使用，则会抛出异常
	 * 
	 * @name selectMaxId
	 * @active 查询实体对应表最大Id
	 * @time 上午10:04:05
	 * @exception/throws 如果主键类型不为int，会抛出类型转换异常
	 * @return 返回泛型参数对应表的主键最大值
	 */
	public Integer selectMaxId();

	/**
	 * 更新或保存，涉及到Mabatis使用的bean只是一个简单的值对象，不能进行id的注解，不知道哪个是主键，所以，必须同时指定t的主键值
	 * 
	 * @param t
	 *            要更新或保存的对象
	 * @param id
	 *            要更新或保存的对象的id
	 * @return 返回更新或保存的对象。
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
}