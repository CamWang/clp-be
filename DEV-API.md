# CLP开发文档



## 规范

### Root URL

* 开发环境：自定义IP
* 生产环境：`icpc.qlu.edu.cn`

/

/user

/submission



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

#### Error 错误

错误在返回状态码的情况下带有错误信息，后端抛出错误与前端接收错误信息示例如下，后端错误被定义在`constant`包下`ClpException`的枚举类型下抛错通过下方代码实现，选取或者自行添加更多错误

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



### Methods 方法

* GET（SELECT）：从服务器中取出任意数量资源
* POST（CREATE）：在服务器新建一个资源
* PUT（UPDATE）：在服务器更新资源（客户端需要提供完整资源）
* PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）
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



