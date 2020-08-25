package com.szsm.videomeeting;

import com.szsm.videomeeting.modules.kk.mapper.PersonMapper;
import com.szsm.videomeeting.modules.kk.model.entity.Person;
import com.szsm.videomeeting.modules.kk.service.PersonService;
import com.szsm.videomeeting.modules.system.mapper.RoleMapper;
import com.szsm.videomeeting.modules.system.mapper.UserRoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoMeetingConsoleApplicationTests {

	@Autowired
	private PersonMapper personMapper;

	@Autowired
	private PersonService personService;

	@Autowired
	private UserRoleMapper userRoleMapper;


	@Autowired
	private RoleMapper roleMapper;



	@Test
	public void testOne(){

		/**
		 * // 插入一条记录（选择字段，策略插入）
		 boolean save(T entity);
		 // 插入（批量）
		 boolean saveBatch(Collection<T> entityList);
		 // 插入（批量）
		 boolean saveBatch(Collection<T> entityList, int batchSize);
		 */
		Person person = Person.builder().name("zhangsan").age(33).build();
		Person person1 = Person.builder().name("lisi").age(34).build();
//		Person person2 = Person.builder().name("wangwu").build();
//		Person person3 = Person.builder().name("zhaoliu").build();
//		List<Person> list = new ArrayList<>();
		boolean save = personService.save(person);
		System.out.println(save);
//		list.add(person);
//		list.add(person1);
//		list.add(person2);
//		list.add(person3);
//		boolean b = personService.saveBatch(list);
//		System.out.println(b);

		/**
		 * // TableId 注解存在更新记录，否插入一条记录
		 boolean saveOrUpdate(T entity);
		 // 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
		 boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
		 // 批量修改插入
		 boolean saveOrUpdateBatch(Collection<T> entityList);
		 // 批量修改插入
		 boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
		 */

//		boolean b1 = personService.saveOrUpdateBatch(list);
//		System.out.println(b1);

		/**
		 * // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
		 boolean update(Wrapper<T> updateWrapper);
		 // 根据 whereEntity 条件，更新记录
		 boolean update(T entity, Wrapper<T> updateWrapper);
		 // 根据 ID 选择修改
		 boolean updateById(T entity);
		 // 根据ID 批量更新
		 boolean updateBatchById(Collection<T> entityList);
		 // 根据ID 批量更新
		 boolean updateBatchById(Collection<T> entityList, int batchSize);
		 */
//		UpdateWrapper<Person> updateWrapper = new UpdateWrapper<Person>();
//		updateWrapper.lambda().eq(Person::getName, "lisi");
//		updateWrapper.lambda().gt(Person::getId, 26L);
//		boolean lisi = personService.update(updateWrapper);
//		System.out.println(lisi);
		//
//		boolean update = personService.update(
//				Person.builder().name("lisi").age(34).build()
//				, new UpdateWrapper<Person>().lambda().eq(Person::getId,29L));
//		System.out.println(update);

		/**
		 * // 无条件分页查询
		 IPage<T> page(IPage<T> page);
		 // 条件分页查询
		 IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
		 // 无条件分页查询
		 IPage<Map<String, Object>> pageMaps(IPage<T> page);
		 // 条件分页查询
		 IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
		 */
		/*Page page = new Page(1,10);

		Page page1 = personService.page(page,new QueryWrapper<Person>().lambda().eq(Person::getAge,34).eq(Person::getName,"zhangsan"));
		System.out.println(page1);*/
//		Person wangwu1 = Person.builder().name("wangwu").build();
//		int wangwu = personMapper.insert(wangwu1);
//		System.out.println(wangwu1.getId());

	}

	/*@Test
	public void testSelect() {
		*//*System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		for (User user : userList) {
			System.out.println(user);
		}*//*

		Person user = new Person();
		int result = 0;
		user.setName("zhangsan");
		result = userMapper.insert(user);
		System.out.println(result);

		// 更新
		user.setAge(18);
		result = userMapper.updateById(user);

		// 单个查询
		Person user1 = userMapper.selectById(user.getId());
		System.out.println(user1);

		// 多个查询
		List<Person> userList = userMapper.selectList(new EntityWrapper<Person>().eq("name", "zhangsan"));
		for (Person user2 : userList) {
			System.out.println(user2);
		}

		// 分页
		List<Person> users = userMapper.selectPage(
				new Page<Person>(1, 10),
				new EntityWrapper<Person>()
		);
		for (Person user3 : users) {
			System.out.println(user3);
		}

	}

	@Test
	public void testSelect2() {

		// 初始化 成功标识
		boolean result = false;
// 初始化 User
		Person user = new Person();

// 保存 User
		user.setName("Tom");
		result = user.insert();

// 更新 User
		user.setAge(18);
		result = user.updateById();

// 查询 User
		Person exampleUser = user.selectById();

// 查询姓名为‘张三’的所有用户记录
		List<Person> userList1 = user.selectList(
				new EntityWrapper<Person>().eq("name", "张三")
		);

// 删除 User
		result = user.deleteById();

		// 分页查询 10 条姓名为‘张三’的用户记录
		List<Person> userList = user.selectPage(
				new Page<Person>(1, 10),
				new EntityWrapper<Person>().eq("name", "张三")
		).getRecords();
	}

	@Test
	public void testSelect3() {
		Page<Person> page = new Page<>();
		Person person = Person.builder()
				.name("zhangsan")
				.build();
		page.setCurrent(1);
		page.setSize(10);
		EntityWrapper<Person> ew1 = new EntityWrapper<Person>();
		ew1.setEntity(person);
		page.setRecords(userMapper.selectPage(page, ew1));
		System.out.println(page);
	}

	@Test
	public void testSelect4() {
		*//**
	 * 乐观锁的验证
	 *//*
		long id = 9;
		int version = 1;
		Person u = new Person();
		u.setId(id);
		u.setVersion(version);
		u.setName("lisi");

		if(userMapper.updateById(u)==1){
			System.out.println("Update successfully");
		}else{
			System.out.println("Update failed due to modified by others");
		}
	}

	@Test
	public void testSelect5() {
		*//**
	 * 逻辑删除的验证
	 *//*
		Integer integer = userMapper.deleteById(15L);
		System.out.println(integer);
	}

	@Test
	public void testSelect6() {

		*//*List<UserRole> userRoles = userRoleMapper.listByRole(1);
		for (UserRole userRole : userRoles) {
			System.out.println(userRole);
			System.out.println(userRole.getCreateTime());
		}


		System.out.println("----------------");
		List<Role> list = roleMapper.list();
		for (Role role : list) {
			System.out.println(role);
			List<UserRole> userRoleList = role.getUserRoleList();
			for (UserRole userRole : userRoleList) {
				System.out.println(userRole);
			}
		}*//*

		UserRole userRole = userRoleMapper.selectById(1);
		System.out.println(userRole);
	}
*/

}
