# CLP开发文档



## 规范



### Root URL 根URL

* 开发环境：自定义IP
* 生产环境：`icpc.qlu.edu.cn`





### Endpoints 路径

* URL全部小写
* URL节点（资源）全部为单数名词
* 资源若为驼峰命名直接大写变小写请求，例请求`student.schoolId`请求参数为`schoolid`

```js
axios.get('/user', {
    params: {
      schoolid: 12345
    }
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
```





### Format 格式

#### Date 时间日期

前后端时间传递使用Timestamp时间戳格式，时间戳是指1970/1/1 00:00:00 GMT之后的毫秒数，使用长整`long`来储存

##### 前端时间处理

```javascript
// 时间处理
var date = new Date();
date.getFullYear()
	.setFullYear()
	.getMonth()
	.setMonth()
	.getDate()
	.setDate()
	.getHours()
	.setHours()
	.getSeconds()
	.setSeconds()
	.getMilliseconds()
	.setMilliseconds()
	.getTime()	// 获取时间戳，从1970-1-1开始的毫秒数
	.setTime()	// 用时间戳设置日期，也可以在Date的构造函数里直接传入
// 获取当前时间戳
var timestamp = new Date().getTime();
```

##### 后端时间处理

```java
@PostMapping("/time")
@ResponseBody
public String TimeTest(
    @RequestParam("time") Long time	// 使用长整型变量接收前端Timestamp
) {
    Date date = new Date();	// 新建java.util.Date时间类型
    date.setTime(time);		// 使用setTime(Long timestamp)方法设置时间
    System.out.println(date.toString());
    System.out.println(date.getTime());	// 使用getTime()方法获取当前Date类型对象的Long型的Timestamp
    return "success";
}
```



#### Entity 实体

请求实体时如果明确请求单个实体，例如`id`或对应一个的关联查询，收到的直接是实体类对象；如果查询结果有可能是多个实体，后端构造`Pageable`并返回，每页实体数量在10-40个之间，前端给10、20、30、40选项，后端进行校验。下面是一个请求所有提交解答`Pageable`封装对象的例子。

```
POST /submission HTTP/1.1
Host: localhost:10086
Content-Type: application/x-www-form-urlencoded

page=0&size=10	// 以0为第一页的页码参数page与大小size

page=0&size=10&sort=id&sort=start,desc	// 优先使用id排列，第二使用开始时间倒序排列
```

```JSON
// JSON格式返回内容
{
    "content": [	// 实际返回内容（当前页面解答列表）
        {
            "id": 1,
            "result": null,
            "error": null,
            "code": "public void",
            "end": null,
            "start": null,
            "submit": null,
            "userId": 1
        }
    ],
    "pageable": {	// 返回Pageable类型会顺带把请求时候的Pageable一并返回
        "sort": {	// 排序相关参数
            "sorted": false,	// 被排序为true
            "unsorted": true,	// 未被排序为true
            "empty": true	// 未请求排序时为true
        },
        "offset": 0,	// 偏移量，根据当前页面与页面大小返回当前页面第一个元素的位置
        "pageNumber": 0,	// 页码
        "pageSize": 10,	// 页面元素量
        "paged": true,	// 被分页时为true
        "unpaged": false	// 未被分页时为true
    },
    "totalPages": 1,	// 一共多少页
    "last": true,	// 是否为最后一页
    "totalElements": 1,	// 一共多少元素
    "size": 10,	// 页面大小，元素数量
    "number": 0,	// 第几页
    "sort": {	// 排序
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "numberOfElements": 1,	// 一共多少元素
    "first": true,	// 是否为第一页
    "empty": false	// 请求内容是否为空
}
```

提交实体使用JSON传入数据，后端POST请求仅接受`application/json`格式内容，仅传入必须字段，不要上传非必须字段否则后端将报错

```json
// POST /user 请求体
{
    "username": "camwang",
    "password": "1231",
    "nickname": "camwang",
    "email": "camwang@outlook.com",
    "studentId": "201707030118",
    "phone": "13808945470"
}
```



#### Error 错误

错误在返回状态码的情况下带有错误信息，后端抛出错误与前端接收错误信息示例如下，后端错误被定义在`constant`包下的`ExceptionEnum`枚举，抛错通过下方代码实现，选取或者自行添加更多错误

```java
@RequestMapping("/error")
public void exception() {
    throw new ClpException(ExceptionEnum.PARAMETER_ERROR);
}
```

```json
{
    "status": 412,
    "message": "参数错误",
    "timestamp": 1596439497143
}
```

注意，如果开发当中某个调用的库有可能报错，而且该报错我们不可以修复，最好的办法是在`exception`包下的`GlobalExceptionHandler`中定义处理错误的方法进行捕获处理，否则整个后端将可能因一个报错而挂掉



### Methods 方法

* GET（SELECT）：从服务器中取出任意数量资源
* POST（CREATE）：在服务器新建一个资源
* PUT（UPDATE）：在服务器更新资源，例如修改用户信息
* DELETE（DELETE）：在服务器删除资源

下面是三种前端请求、后端接收示例代码

前端API请求代码示例

```javascript
// GET请求，ContentType使用默认application/x-www-form-urlencoded
axios.get('/user', {
    params: {
      schoolid: 12345
    }
  })
  .then(function (response) { console.log(response); })
  .catch(function (error) { console.log(error); });

// 键值数据POST请求，ContentType使用默认application/x-www-form-urlencoded
axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) { console.log(response); })
  .catch(function (error) { console.log(error); });

// 文件传输POST请求，ContentType使用multipart/form-data

axios.post('/user/avatar', {
    file: this.file		// 多个文件时使用数组
  },{
  	headers: {
        'Content-Type': 'multipart/form-data'
    }  
  })
  .then(function (response) { console.log(response); })
  .catch(function (error) { console.log(error); });

```

后端接收代码示例

```java
@RestController	// 返回RESTful而非视图
public class TestController {

    @PostMapping("/user")	// POST请求映射/user
    public String JsonTest(
            @RequestParam("id") Integer id,	// 接收x-www-form-urlencoded的对应参数，类型自动转换
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        System.out.println( id + " " + username + " " + password );
        return "success";	// 返回text/plain;charset=UTF-8类型的success字符串
    }

    @PostMapping("/user/avatar")
    public String UploadTest(
            @RequestParam("image") MultipartFile file	// 接收multipart/form-data文件
    ) {
        System.out.println(file.getName() + file.getSize());
        return "success";
    }
    
    @PostMapping("/user/images")
    public String UploadTest(
            @RequestParam("image") List<MultipartFile> files	// 接收多个文件
    ) {
        System.out.println(file.getName() + file.getSize());
        return "success";
    }
}
```





### StatusCodes 状态码

`200-299`：成功

`300-399`：重定向

`400-499`：用户请求错误

`500-599`：服务器内部错误





### Bean Validation 验证

这个验证分为两个层次

| 验证注解                                     | 验证的数据类型                                               | 说明                                                         |
| -------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| @AssertFalse                                 | Boolean,boolean                                              | 验证注解的元素值是false                                      |
| @AssertTrue                                  | Boolean,boolean                                              | 验证注解的元素值是true                                       |
| @NotNull                                     | 任意类型                                                     | 验证注解的元素值不是null                                     |
| @Null                                        | 任意类型                                                     | 验证注解的元素值是null                                       |
| @Min(value=值)                               | BigDecimal，BigInteger, byte,short, int, long，等任何Number或CharSequence（存储的是数字）子类型 | 验证注解的元素值大于等于@Min指定的value值                    |
| @Max（value=值）                             | 和@Min要求一样                                               | 验证注解的元素值小于等于@Max指定的value值                    |
| @DecimalMin(value=值)                        | 和@Min要求一样                                               | 验证注解的元素值大于等于@DecimalMin指定的value值             |
| @DecimalMax(value=值)                        | 和@Min要求一样                                               | 验证注解的元素值小于等于@DecimalMax指定的value值             |
| @Digits(integer=整数位数, fraction=小数位数) | 和@Min要求一样                                               | 验证注解的元素值的整数位数和小数位数上限                     |
| @Size(min=下限, max=上限)                    | 字符串、Collection、Map、数组等                              | 验证注解的元素值的在min和max（包含）指定区间之内，如字符长度、集合大小 |
| @Past                                        | java.util.Date,java.util.Calendar;Joda Time类库的日期类型    | 验证注解的元素值（日期类型）比当前时间早                     |
| @Future                                      | 与@Past要求一样                                              | 验证注解的元素值（日期类型）比当前时间晚                     |
| @NotBlank                                    | CharSequence子类型                                           | 验证注解的元素值不为空（不为null、去除首位空格后长度为0），不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的首位空格 |
| @Length(min=下限, max=上限)                  | CharSequence子类型                                           | 验证注解的元素值长度在min和max区间内                         |
| @NotEmpty                                    | CharSequence子类型、Collection、Map、数组                    | 验证注解的元素值不为null且不为空（字符串长度不为0、集合大小不为0） |
| @Email(regexp=正则表达式,flag=标志的模式)    | CharSequence子类型（如String）                               | 验证注解的元素值是Email，也可以通过regexp和flag指定自定义的email格式 |
| @Pattern(regexp=正则表达式,flag=标志的模式)  | String，任何CharSequence的子类型                             | 验证注解的元素值与指定的正则表达式匹配                       |

```java
@Data
public class UserDTO implements Serializable {
    
    @NotNull(message = "用户id不能为空")
    private Long userId;
    
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;
    
    @Future(message = "时间必须是将来时间")
    private Date createTime;
    
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;
    
}
```

使用group方式在不同情况下使用不同的验证组合，group首先需要被定义为空的接口（我们定义在view中）

```java
@Data
public class User implements Serializable {
    
    @Range(min = 0, max = 2097152, message = "用户ID范围超限")
    @NotNull(groups = {UserModifiedView.class}, message = "用户ID不能为空")
    private Integer id;

    // 用户名
    @NotEmpty(groups = {UserRegisterView.class, UserModifiedView.class}, message = "用户名不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "用户名长度在4-32字符之间")
    private String username;

    // 密码
    @NotEmpty(groups = {UserRegisterView.class, UserModifiedView.class}, message = "用户名不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "密码长度在4-32字符之间")
    private String password;

    // 昵称
    @NotEmpty(groups = {UserRegisterView.class}, message = "昵称不能为空")
    @Length(groups = {UserRegisterView.class, UserModifiedView.class}, min = 4, max = 32, message = "昵称长度在4-32字符之间")
    private String nickname;

    // 电子邮件
    @NotEmpty(groups = {UserRegisterView.class}, message = "电子邮箱不能为空")
    @Email(groups = {UserRegisterView.class}, message = "电子邮箱格式不正确")
    private String email;
    
    // 是否撤销账户发言权力 - 账户惩罚
    @Column(columnDefinition = "tinyint default 0")
    @Null(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Boolean silenced;
    
    // 提交的题解
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Empty(groups = {UserSecurityView.class}, message = "安全检查失败")
    private Collection<Submission> submissions = new ArrayList<>();
    
}
```

