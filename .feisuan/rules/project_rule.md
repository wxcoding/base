
# 开发规范指南

为保证代码质量、可维护性、安全性与可扩展性，请在开发过程中严格遵循以下规范。

## 一、项目基础信息

- **工作区路径**：`E:\guanwx\Workspace\personal\test`
- **构建工具**：Maven
- **Java 版本**：JDK 17.0.17
- **Spring Boot 版本**：3.5.7
- **代码作者**：Administrator
- **第一语言**：中文（所有注释、文档需使用中文）

## 二、目录结构规范

项目遵循标准的 Maven 多模块结构，具体目录树如下：

```text
test
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── guanwx
    │   │           ├── aop          # AOP 切面相关（如日志、权限校验）
    │   │           ├── base         # 基础通用类
    │   │           │   └── thread   # 线程池、异步任务配置
    │   │           ├── controller   # 控制层，处理 HTTP 请求
    │   │           ├── entity       # 数据库实体类 (MyBatis-Plus Entity)
    │   │           ├── llm          # 大模型相关逻辑 (通义千问/OpenAI SDK)
    │   │           ├── mapper       # 数据访问层 (MyBatis-Plus Mapper)
    │   │           └── service      # 业务逻辑层
    │   └── resources
    │       └── mapper               # MyBatis XML 映射文件存放目录
    └── test
        └── java
            └── com
                └── guanwx
```

**规范约束：**
1. 包名统一以 `com.guanwx` 为根。
2. `mapper` 包下仅存放接口，XML 文件统一放置在 `resources/mapper` 下。
3. `llm` 包专门用于封装 AI 大模型相关的调用逻辑，禁止在 Service 层直接硬编码 SDK 调用。
4. `aop` 包用于存放全局切面，如操作日志、异常处理等。

## 三、技术栈与依赖规范

### 1. 核心框架
- **主框架**：Spring Boot 3.5.7
- **持久层**：MyBatis-Plus 3.5.14 (Spring Boot 3 Starter)
- **数据库驱动**：MySQL Connector/J 8.0.33

### 2. AI/LLM 集成
- **通义千问 SDK**：`com.alibaba:dashscope-sdk-java:2.22.6`
- **OpenAI 兼容 SDK**：`com.theokanning.openai-gpt3-java:service:0.18.2`
- **JSON 处理**：`org.json:json:20230227`
- **AOP 支持**：`org.aspectj:aspectjweaver:1.9.2`

### 3. 通用工具
- **Lombok**：用于简化 Getter/Setter/Builder 代码。
- **Spring Boot DevTools**：用于开发热部署（仅 Runtime）。

## 四、分层架构规范

| 层级        | 职责说明                         | 开发约束与注意事项                                               |
|-------------|----------------------------------|----------------------------------------------------------------|
| **Controller** | 处理 HTTP 请求与响应，定义 API 接口 | 不得直接访问数据库或包含复杂业务逻辑；需返回统一响应结构       |
| **Service**    | 实现业务逻辑、事务管理与数据校验   | 必须通过 Mapper 层访问数据库；返回 DTO 而非 Entity             |
| **Mapper**     | 数据库访问与持久化操作             | 继承 `BaseMapper`；复杂 SQL 编写在 XML 中，简单查询使用默认方法 |
| **Entity**     | 映射数据库表结构                   | 使用 MyBatis-Plus 注解（如 `@TableName`）；不得直接返回给前端   |
| **LLM Service**| 封装大模型调用                   | 封装 Prompt 构建、Token 管理、重试机制；屏蔽底层 SDK 差异       |

### 接口与实现分离

- 所有 Service 接口需定义在 `service` 包下，具体实现类放在 `service.impl` 子包中。
- Mapper 接口直接放在 `mapper` 包下，无需 impl。

## 五、安全与性能规范

### 1. 输入校验
- 使用 `@Valid` 与 JSR-303 校验注解（如 `@NotBlank`, `@Size` 等）。
- **注意**：Spring Boot 3.x 中校验注解位于 `jakarta.validation.constraints.*`。
- 禁止手动拼接 SQL 字符串，防止 SQL 注入攻击（MyBatis-Plus 默认防注入，但自定义 XML 需注意）。

### 2. 事务管理
- `@Transactional` 注解仅用于 **Service 层**方法。
- 避免在循环中频繁提交事务，影响性能。
- 大模型调用涉及外部网络请求，建议将 LLM 调用逻辑与核心业务事务解耦，或设置合理的超时时间。

### 3. LLM 调用规范
- 所有大模型 SDK 调用必须封装在 `llm` 包中。
- 禁止在 Controller 或普通 Service 中直接 `new` SDK 客户端，需通过依赖注入获取。
- 注意 `dashscope-sdk-java` 和 `openai-gpt3-java` 的日志依赖冲突，已在 POM 中排除 `slf4j-simple`，开发时请确保使用 SLF4J 兼容的实现。

## 六、代码风格规范

### 1. 命名规范

| 类型       | 命名方式             | 示例                  |
|------------|----------------------|-----------------------|
| 类名       | UpperCamelCase       | `UserServiceImpl`     |
| 方法/变量  | lowerCamelCase       | `saveUser()`          |
| 常量       | UPPER_SNAKE_CASE     | `MAX_LOGIN_ATTEMPTS`  |

### 2. 注释规范

- **语言要求**：所有类、方法、字段必须添加 **中文 Javadoc** 注释。
- **格式要求**：
  ```java
  /**
   * 用户服务实现类
   * @author Administrator
   * @date 2026-05-24
   */
  ```
- 复杂业务逻辑（特别是 LLM 提示词构建、正则表达式）必须添加行内注释说明意图。

### 3. 类型命名规范（阿里巴巴风格）

| 后缀 | 用途说明                     | 示例         |
|------|------------------------------|--------------|
| DTO  | 数据传输对象                 | `UserDTO`    |
| DO   | 数据库实体对象 (MyBatis Entity)| `UserDO`     |
| BO   | 业务逻辑封装对象             | `UserBO`     |
| VO   | 视图展示对象                 | `UserVO`     |
| Query| 查询参数封装对象             | `UserQuery`  |

### 4. 实体类简化工具

- 使用 Lombok 注解替代手动编写 getter/setter/构造方法：
  - `@Data`
  - `@NoArgsConstructor`
  - `@AllArgsConstructor`
  - `@TableName("table_name")` (MyBatis-Plus 特有)

## 七、扩展性与日志规范

### 1. 接口优先原则

- 所有业务逻辑通过接口定义（如 `UserService`），具体实现放在 `impl` 包中（如 `UserServiceImpl`）。

### 2. 日志记录

- 使用 `@Slf4j` 注解代替 `System.out.println`。
- **LLM 调试**：在 `llm` 包中调用大模型时，建议开启 DEBUG 级别日志记录 Request/Response，以便排查 Prompt 和 Token 问题。
- **AOP 日志**：利用 `aop` 包中的切面统一记录 Controller 层的入参和出参，避免在 Controller 中重复编写日志代码。

## 八、编码原则总结

| 原则       | 说明                                       |
|------------|--------------------------------------------|
| **SOLID**  | 高内聚、低耦合，增强可维护性与可扩展性     |
| **DRY**    | 避免重复代码，提高复用性                   |
| **KISS**   | 保持代码简洁易懂                           |
| **YAGNI**  | 不实现当前不需要的功能                     |
| **OWASP**  | 防范常见安全漏洞，如 SQL 注入、XSS 等      |
| **LLM Safety** | 大模型输出需进行二次校验，防止注入攻击或敏感信息泄露 |
