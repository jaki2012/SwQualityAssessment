# SwQualityAssessment
** **
基于前后端分离思想的软件质量管理后台，使用Spring + SpringMVC + Hibernate框架。
- Restful API风格设计
- Redis缓存，用户Token验证机制

## API接口用法：
** **
### 众包后端登录
**简要描述：** 

- 众包后端登录

**请求URL：** 
- ` http://xx.com/api/user/login `
  
**请求方式：**
- POST 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|username |是  |string |用户名   |
|password |是  |string | 密码    |

 **返回示例**

``` 
  {
    "error_code": 0,
	"message":"成功",
    "data": {
	  "userid":1
      "token": "11bcb04ee1d74616b1ca9bd7ffd3438e""
    }
  }
```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----   |
|message |string   |登录结果信息  |
|userid |int   |登录用户的id  |
|token |string   |登录返回的token  |
** **
### 提交代码分析作业
**简要描述：** 

- 提交代码分析作业

**请求URL：** 
- ` http://xx.com/api/task/ `
  
**请求方式：**
- POST 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|authorization |是  |string |用户登录token   |
|projectid |是  |string |项目ID   |
|projectname |是  |string | 项目名称    |
|projectversion | 是  |string | 项目版本    |
|projectgit     |是  |string | 项目Git地址    |

 **返回示例**

``` 
  {
    "error_code": 0,
    "data": {
      "taskid": "1",
      "starttime": "1436864169",
    }
  }
```

 **返回参数说明** 
|参数名|类型|说明|
|:-----  |:-----|-----              |
|taskid |int   |生成一个任务/作业编号  |
** **
### 获取以往分析作业情况
**简要描述：** 

- 获取以往分析作业情况

**请求URL：** 
- ` http://xx.com/api/tasks`
  
**请求方式：**
- GET 

**参数：** 

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|authorization |是  |string |用户登录token   |
|taskid |是  |int[] |包含一个或多个任务id的数组   |

 **返回示例**

``` 
  {
    "error_code": 0,
    "data": {
      "results":[
	  “1”:{
	     taskState:1, //已完成
	     metrics:XXX,
		 predicetdResult:Y,
		 staticDefect:XXX
	  },
	  “2”:{
	     taskState:2, //进行中
	     Metrics:null,
		 PredicetdResult:null,
		 StaticDefect:null
	  },
	  ]
    }
  }
```

 **返回参数说明** 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|results |JSONArray   |分析结果，taskstate代表当前的作业分析情况，如果完成了，则返回相应的分析结果 |


##关于作者
```
   Jaki
   Novemser
   Yokoyang
   __________All from Tongji labsse409
```
