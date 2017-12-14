#使用 Pact 实现契约测试示例

### 结构
本项目分成两个子模块，两个子模块都是可以独立运行的服务，因为配置使用的不同的端口，所以可以同时启动不会有端口冲突。

- consumer: 消费方服务，SpringBoot 实现，缺省在 8080 端口；
- provider: 提供方服务，SpringBoot 实现， 缺省在 9090 端口;


### 运行：
``` 
mvn verify
```
1. test 阶段会完成对客户端的测试，并生成契约文件；
2. pre-integration-test 阶段会启动 provider 端的服务；
3. integretion-test 阶段会调用 pact:verify 对provider做验证；
4. post-integration-test 阶段会停止 provider 端的服务；

也可以到两个子模块下，完成上述步骤；参考子模块下的 README.md 说明。




