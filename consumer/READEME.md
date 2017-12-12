
## 此应用结构：
直接运行 Applicaiton, 服务暴露了两个资源: / 和 /foos , 支持GET请求
/foos 功能的完成依赖于另一个服务, 当前应用是 consumer, 另一端是 provider/producer
application.properties 配置了 provider 的地址，测试时应改为 mock service 的URL。


## 目的:
1. 示例在服务的 consumer 端， 如何使用 pact 来完成测试
 - 使用 @Rule 来标记 mock server 实例 
 - 在 @Pact 标注的方法中，定义mock server 的 stub 行为
2. 测试完成后，可以在 target/pacts 下看到生成的 json 文件，此文件用以对服务端做测试。 


#运行 
mvn test 
```
会调用：
 ApplicationTest, 它会启动consumer 服务， 然后再运行自己的测试方法，测试方法是通过 rest 接口访问 consumer 服务；
 ConsumerPortTest, 它不启动 consumer 服务，直接测试 ConsumerPort 的被测方法。
```




