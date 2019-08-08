package com.cloud.framework.base.common;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
public interface BaseMapper<T, PK extends Serializable> { 
  
   
    /**
     * 添加对象
     * @param entity
     * @return
     */
    public int insert(T entity); 
    /**
     * 添加对象,空属性不插入null
     * @param entity
     * @return
     */
    public int  insertSelective(T entity);
  
    /** 
     * 更新对象,传过来的实体对象中不为空的字段进行更新,为null的字段不更新为null
     *  
     * @param entity 
     *            要更新的实体对象 
     */
    public int  update(T entity); 
    
    /**
  	 * 查询总数量
  	 * 
  	 * @param
  	 * @return
  	 */
      public int findnum(@Param(value = "map") Map<String, Object> map);
    /**
     * 更新对象,传过来的实体对象中为null的字段,数据库也更新为null;
     * @param enity
     * @return
     */
      /**
  	 * 查询所属二级栏目类型
  	 * 
  	 * @param
  	 * @return
  	 */
    public int updateNull(T enity);
  
    /** 
     * 根据id删除对象 
     *  
     * @param id 
     */
    public int  deleteById(PK id); 
  
    /** 
     * 根据条件集合删除对象 
     *  
     * @paramid
     */
  
    public int deleteByCondition(Map<String, Object> map);
    
    
    /** 
     * 根据条件集合删除对象 
     *  
     * @param entity 
     */
    public int deleteByProperty(T entity);
    
    
    /** 
     * 根据id进行对象查询 
     *  
     * @param id 
     * @return 
     */
    public T selectById(PK id); 
    

    /** 
     *查所有
     *  
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数
     */
    public List<T> findAll(); 
    
    
    /**
     * 根据条件查询一条数据
     * @param map 
     * @return T
     */
    public T findOneByCondition(Map<String, Object> map);
    
    /**
     * 根据条件查询一条数据
     * @param entity
     * @return T
     */
    public T findOneByProperty(T entity);
 
    /** 
     * 根据属性和属性值进行集合查询 
     *  
     * @param map
     *            
     * @return 返回泛型参数类型的对象集合
     */
    public List<T> findList(Map<String, Object> map);
  
  
    /** 
     * 根据条件集合进行分页查询 
     *  
     * @param map 
     *            查询条件 
     * @param start 开始记录数
     *            
     * @param size 一页的条数
     *            页面大小 
     * @return 
     */
    public List<T> queryPageByCondition(@Param(value = "map") Map<String, Object> map, @Param(value = "start") Integer start, @Param(value = "size") Integer size);
    
    /**
     * 根据条件进行分页查询
     * @param entity 实体对象
     * @param start 开始记录数
     * @param size 每页的记录数
     * @return
     */
    public List<T> queryPageByProperty(@Param(value = "entity") T entity, @Param(value = "start") Integer start, @Param(value = "size") Integer size);
    
    /**
     * 所有记录分页
     * @param start 开始记录数
     * @param size 每页的记录数
     * @return
     */
    public List<T> queryPage(@Param(value = "start") Integer start, @Param(value = "size") Integer size);
   
    
   
      
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
     * @param map 
     *            进行查询的条件集合 
     * @return 返回泛型参数类型的对象集合，如何取到泛型类型参数
     */
    public List<T> queryList(Map<String, Object> map, String orderBy, String sortBy);
      
    /** 
     * 根据条件集合进行指定类型单一对象查询 
     *  
     * @param map 
     *            进行查询的条件集合 
     * @return 返回泛型参数类型的对象，
     */
    public T queryOne(Map<String, Object> map); 
  
    /** 
     * 根据条件进行数量的查询 
     *  
     * @param map 
     * @return 返回符合条件的泛型参数对应表中的数量 
     */
    public Long count(Map<String, Object> map); 
  
    /** 
     * 该方法只有在主键为int时才有用，如果主键为其他数据类型进行使用，则会抛出异常 
     *  
     * @name selectMaxId 
     * @active 查询实体对应表最大Id 
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
    public T updateOrSave(T t, PK id); 
  
    /** 
     * 根据泛型类型，执行最原始的sql 
     *  
     * @param mapperId 
     * @paramObject
     * @return 返回泛型类型对象，如果返回多个结果集会抛出异常
     *         
     */
    public T selectOne(String mapperId, Object obj); 
  
    /** 
     * 根据泛型类型，执行最原始的sql 
     *  
     * @param mapperId 
     * @paramObject
     * @return 返回泛型类型对象集合
     */
    public List<T> selectList(String mapperId, Object obj); 
  
    /** 
     * 取得泛型类型 
     *  
     * @return 
     */
    public Class<T> getEntityClass();
	public int updateOne(T entity);




  
}