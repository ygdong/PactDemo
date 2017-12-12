#目的：
1. 配置 pact maven 插件
2. 使用这个插件读取客户端生成的 pact.json 文件，验证Provider

#运行
### 方式一：直接运行集成测试：
mvn verify
```
spring-boot 的 start/stop 分别绑定了 pre/post-integretion-test
而 pact:vefiry 绑定了 integraion-test
```

### 方式二：先运行服务， 再使用 pact:verify 测试 provider 服务
1. 通过spring-boot maven 插件或直接运行 Application.main()
2. mvn pact:verify





