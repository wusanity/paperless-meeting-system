## 项目模块主要分为两个部分
base 主要关于项目的一些配置
modules 主要关于一些模块功能的建立
    模块里包的命名为：constant,controller,enums,mapper,model,service
    

## 使用规范
idea下载Sonar规范：file-settings-plugins，搜索SonarLink，安装。项目扫描：红色和黄色需要修改，部分问题可以自动修改。
一·命名风格
	1.类名使用UpperCamelCase，VO，DTO使用大写
	2.方法名，参数名，成员变量，局部变量使用lowerCamelCase
	3.常量名全部大写，单词间用下划线隔开
    4.包名使用小写，接口层controller,服务service，常量constant，定时任务job，逻辑处理handler，领域模型model，工具utils，自定义异常TicketErrorEnum(例，业务名+ErrorEnum并需要继承全局自定义异常枚举方便)
	5.注释，遵循javadoc格式，/**
							  * 数据列表查询list
							  *
							  * @author wuzhike
							  * @date 2020-03-26
							  */
    禁止使用尾行注释
	6.实现类后缀Impl，枚举后缀Enum，数据传输对象后缀DTO(入参参数)，页面展示对象后缀VO(出参参数)
	7.包结构以com.szsm开始，公共内容放base下，按照业务模块划分包结构
二·代码风格
	1.常量赋值，Long后缀大写L，float后缀小写f
	2.数据库model必须有@Data@Builder@NoArgsConstructor@AllArgsConstructor@Table(name = "satis_ans")，DTO和VO必须有@Data@Builder@NoArgsConstructor@AllArgsConstructor
	3.所有重写方法必须有@Override
	4.不能使用过时类与方法
	5.相同类型包装类比较一律使用equals，同时常量在前，避免出现NPE异常
	6.循环拼接字符串使用append
	7.类的变量与方法仅在本类使用，必须是private
	8.foreach循环中不能对元素进行remove/add操作
	9.使用Comparator必须大于，小于，等于条件都包括做判断
	10.switch每个case都必须有break，default放在最后
	11.if改写 if (condition) {
				 ...
				 return obj;
				}
				// 接着写 else 的业务逻辑代码; 
	12.try catch不能在循环体内
	13.接口参数必须校验，且入参属性大于等于三个使用对象封装，对象内参数校验使用javax.validation.constraints(例如：@NotNull)
	14.非业务逻辑方法抽取到utils中，常用功能使用StringUtils，CollectionUtils，DateUtils
	15.暂未完成代码增加//TODO注释，错误代码增加//FIXME注释
	16.使用try-with-resources对资源对象，流对象进行处理，可不用手动关闭
	17.方法返回值可以为null，远程调用，缓存，数据库查询获取对象后如需做业务处理一律进行NPE检查
	18.单方法内多次增删改操作需要在方法上加入@Transactional(rollbackFor = Exception.class)
 三·分支管理
    分支:mast,ins(测试),product(生产)
    开发代码时，需从mast分支pull代码到本地分支，当需要合并本地分支到mast分支时，在gitlab上提交一个merge requests,由管理员
    同意合并后，才会合并到mast分支上
    地址：https://newgitlab.digitalchina.com/wuzkf/paperless-meeting-system
 四.代码发布
    每次发布代码时，需要打一个Tag包，进行发布，jenkins会自动检测发布，发布成功后，企业微信群会发送一个成功发布的信息
    地址：http://10.3.69.41:8080/job/paperless-meeting-system
 五.代码检测
    1.每次代码要提交合并时，需自己用sonar插件进行检测代码写的情况，减少代码错误，提高代码质量。
    2.每次代码发布成功都会发布到sonarQube上，管理员可以查看每次代码发布的检测结果。
    地址：http://10.3.69.79:9000/dashboard?id=paperless-meeting-system
 六.单元测试
    1.元测试代码必须写在如下工程目录：src/java/test，不允许写在业务代码目录下，单元测试包结构和源码结构必须保持一致。
    2.单元测试文件名字是由"被测试文件名 + Test"组成，并且继承基础单元测试类PaperlessMeetingApplicationTests,
      测试的方法名，命名规则为"test+被测试的方法名"
    3.每个单元测试方法都是单独的，不能互相调用。
    4.核心业务、核心应用、核心模块的增量代码确保单元测试通过(主要为service层的代码)。
    
    
