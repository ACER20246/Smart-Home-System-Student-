# 智能家居系统 (HomeSphere System)

## 项目概述

HomeSphere System 是一个基于Java的智能家居管理系统，支持设备管理、用户管理、自动化场景控制、能源监控等功能。该系统采用面向对象设计，具有良好的扩展性和可维护性。

## 系统架构

### 核心组件

1. **HomeSphereSystem** - 系统主控制器
2. **Household** - 家庭管理单元
3. **Device** - 设备基类
4. **User** - 用户管理
5. **Room** - 房间管理
6. **AutomationScene** - 自动化场景

### 设备类型

- **AirConditioner** - 空调设备
- **LightBulb** - 智能灯泡
- **SmartLock** - 智能门锁
- **BathroomScale** - 浴室秤

## 功能特性

### 1. 用户管理
- 用户注册和登录
- 管理员权限控制
- 用户信息管理

### 2. 设备管理
- 设备添加和移除
- 设备状态控制（开关、参数设置）
- 设备分类管理

### 3. 房间管理
- 房间创建和管理
- 设备分配到房间
- 房间信息维护

### 4. 自动化场景
- 场景创建和配置
- 设备动作组合
- 手动触发场景

### 5. 能源监控
- 设备功耗监控
- 能源使用报告
- 节能分析

## 类结构说明

### 核心类

#### HomeSphereSystem
系统主控制器，提供以下功能：
- `login(String loginName, String loginPassword)` - 用户登录
- `register(String loginName, String loginPassword, String userName, String email)` - 用户注册
- `displayUsers()` - 显示用户列表
- `displayRooms()` - 显示房间列表
- `displayDevices()` - 显示设备列表
- `displayAutoScenes()` - 显示自动化场景
- `displayEnergyReportings(Date startTime, Date endTime)` - 显示能源报告
- `manualTrigSceneById(int sceneId)` - 手动触发场景

#### Household
家庭管理单元：
- 管理用户列表
- 管理房间列表
- 管理自动化场景
- 提供设备汇总功能

#### Device (抽象基类)
设备基类，所有智能设备继承此类：
- 设备基本信息（ID、名称、制造商）
- 电源状态管理
- 在线状态管理
- 运行日志记录

### 具体设备类

#### AirConditioner
空调设备，支持：
- 温度控制（当前温度、目标温度）
- 模式设置
- 风速调节
- 能源报告功能

#### LightBulb
智能灯泡，支持：
- 亮度调节（0-100）
- 色温调节
- 能源报告功能

#### SmartLock
智能门锁，支持：
- 锁定状态控制
- 电池电量监控

#### BathroomScale
浴室秤，支持：
- 体重测量
- 电池电量监控

### 管理类

#### User
用户信息管理：
- 用户基本信息
- 登录凭据
- 管理员权限

#### Room
房间管理：
- 房间基本信息
- 设备列表管理
- 房间面积记录

#### AutomationScene
自动化场景：
- 场景配置
- 设备动作组合
- 场景触发

#### DeviceAction
设备动作：
- 动作命令定义
- 参数设置
- 动作执行

## 接口说明

### EnergyReporting
能源报告接口，实现设备功耗监控：
- `getPower()` - 获取设备当前功率
- `getReport(Date startTime, Date endTime)` - 获取指定时间段的能耗报告

## 使用示例

### 基本使用流程

```java
// 1. 创建家庭
Household household = new Household(1, "友谊西路127号");

// 2. 创建系统
HomeSphereSystem system = new HomeSphereSystem(household);

// 3. 注册用户
User admin = new User(1, "admin", "111111", "管理员", "admin@nwpu.edu.cn");
household.addUser(admin);
household.setAdmin(admin);

// 4. 创建设备
Manufacturer acManufacturer = new Manufacturer(1, "AC Corp", "WiFi, ZigBee");
AirConditioner ac = new AirConditioner(1, "Living Room AC", acManufacturer);

// 5. 创建房间并添加设备
Room livingRoom = new Room(1, "Living Room", 25.5);
livingRoom.addDevice(ac);
household.addRoom(livingRoom);

// 6. 用户登录
system.login("admin", "111111");

// 7. 控制设备
ac.powerOn();
ac.setTargetTemp(22.0);

// 8. 创建自动化场景
AutomationScene eveningScene = new AutomationScene(1, "晚安模式", "关闭灯光调节至适宜温度");
DeviceAction lightAction = new DeviceAction("powerOff", "", light);
DeviceAction acAction = new DeviceAction("setTemperature", "26.0", ac);
eveningScene.addAction(lightAction);
eveningScene.addAction(acAction);
household.addAutoScene(eveningScene);

// 9. 触发场景
system.manualTrigSceneById(1);
```

## 测试说明

项目包含完整的单元测试 `HomeSphereSystemTest.java`，测试覆盖：
- 家庭管理功能
- 用户管理功能
- 设备管理功能
- 自动化场景功能
- 能源监控功能

### 运行测试

```bash
# 编译所有源文件
javac -cp . src/*.java

# 运行测试（需要JUnit库）
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore HomeSphereSystemTest
```

## 扩展性

### 添加新设备类型

1. 继承 `Device` 基类
2. 实现必要的接口（如 `EnergyReporting`）
3. 添加设备特定的属性和方法
4. 在 `DeviceAction` 中添加相应的命令处理

### 添加新功能

1. 在相应的管理类中添加方法
2. 更新 `HomeSphereSystem` 主控制器
3. 添加相应的测试用例

## 技术特点

- **面向对象设计** - 清晰的类层次结构
- **接口抽象** - 能源监控等功能通过接口实现
- **可扩展性** - 易于添加新设备类型和功能
- **测试驱动** - 完整的单元测试覆盖
- **日志记录** - 设备运行状态记录

## 依赖要求

- Java 8 或更高版本
- JUnit 4.12（用于测试）
- 无其他外部依赖

## 项目结构

```
src/
├── HomeSphereSystem.java      # 系统主控制器
├── Household.java             # 家庭管理
├── Device.java               # 设备基类
├── AirConditioner.java       # 空调设备
├── LightBulb.java           # 智能灯泡
├── SmartLock.java           # 智能门锁
├── BathroomScale.java       # 浴室秤
├── User.java                # 用户管理
├── Room.java                # 房间管理
├── AutomationScene.java     # 自动化场景
├── DeviceAction.java        # 设备动作
├── Manufacturer.java        # 制造商
├── RunningLog.java          # 运行日志
├── EnergyReporting.java     # 能源报告接口
├── HomeSphereSystemTest.java # 单元测试
└── Main.java               # 主程序入口
```

## 开发团队

- 项目名称：智能家居系统 (HomeSphere System)
- 开发语言：Java
- 设计模式：面向对象设计
- 测试框架：JUnit 4

## 版本历史

- v1.0 - 初始版本，包含基本功能
- 支持设备管理、用户管理、自动化场景
- 支持能源监控和报告功能
