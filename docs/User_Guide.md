# 用户指南

## 快速开始

### 1. 系统初始化

首先创建一个家庭管理系统：

```java
// 创建家庭
Household household = new Household(1, "友谊西路127号");

// 创建系统
HomeSphereSystem system = new HomeSphereSystem(household);
```

### 2. 用户管理

#### 创建管理员用户
```java
// 创建管理员
User admin = new User(1, "admin", "111111", "管理员", "admin@nwpu.edu.cn");
household.addUser(admin);
household.setAdmin(admin);
```

#### 注册新用户
```java
// 注册普通用户
User newUser = system.register("hxt", "123456", "航小天", "htx@nwpu.edu.cn");
```

#### 用户登录
```java
// 登录
boolean success = system.login("hxt", "123456");
if (success) {
    System.out.println("登录成功");
} else {
    System.out.println("登录失败");
}
```

### 3. 设备管理

#### 创建制造商
```java
Manufacturer acManufacturer = new Manufacturer(1, "AC Corp", "WiFi, ZigBee");
Manufacturer lightManufacturer = new Manufacturer(2, "Light Inc", "WiFi");
```

#### 创建设备
```java
// 创建空调
AirConditioner ac = new AirConditioner(1, "Living Room AC", acManufacturer);
ac.setCurrTemp(25.0);
ac.setTargetTemp(22.0);

// 创建智能灯泡
LightBulb light = new LightBulb(2, "Kitchen Light", lightManufacturer);
light.setBrightness(80);
light.setColorTemp(4000);

// 创建智能门锁
SmartLock lock = new SmartLock(3, "Front Door Lock", acManufacturer);
lock.setLocked(true);

// 创建浴室秤
BathroomScale scale = new BathroomScale(4, "Bathroom Scale", lightManufacturer);
scale.setBodyMass(70.5);
scale.setBatteryLevel(85);
```

### 4. 房间管理

#### 创建房间
```java
// 创建客厅
Room livingRoom = new Room(1, "Living Room", 25.5);
livingRoom.addDevice(ac);
livingRoom.addDevice(light);

// 创建卧室
Room bedroom = new Room(2, "Bedroom", 18.0);
bedroom.addDevice(lock);

// 添加房间到家庭
household.addRoom(livingRoom);
household.addRoom(bedroom);
```

### 5. 设备控制

#### 基本控制
```java
// 开启空调
ac.powerOn();

// 设置目标温度
ac.setTargetTemp(20.0);

// 调节灯泡亮度
light.setBrightness(50);

// 设置灯泡色温
light.setColorTemp(3000);

// 解锁门锁
lock.setLocked(false);
```

#### 查看设备状态
```java
// 显示所有设备
system.displayDevices();

// 显示所有房间
system.displayRooms();

// 显示所有用户
system.displayUsers();
```

### 6. 自动化场景

#### 创建场景
```java
// 创建晚安模式场景
AutomationScene eveningScene = new AutomationScene(1, "晚安模式", "关闭灯光调节至适宜温度");

// 添加设备动作
DeviceAction lightAction = new DeviceAction("powerOff", "", light);
DeviceAction acAction = new DeviceAction("setTemperature", "26.0", ac);

eveningScene.addAction(lightAction);
eveningScene.addAction(acAction);

// 添加场景到家庭
household.addAutoScene(eveningScene);
```

#### 触发场景
```java
// 手动触发场景
system.manualTrigSceneById(1);

// 或者直接触发
eveningScene.manualTrig();
```

#### 查看场景
```java
// 显示所有自动化场景
system.displayAutoScenes();
```

### 7. 能源监控

#### 查看能源报告
```java
// 设置时间范围
Date startTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // 24小时前
Date endTime = new Date(); // 现在

// 显示能源报告
system.displayEnergyReportings(startTime, endTime);
```

#### 获取设备功率
```java
// 开启设备以获取功率信息
ac.powerOn();
light.powerOn();

// 获取功率
double acPower = ac.getPower();
double lightPower = light.getPower();

System.out.println("空调功率: " + acPower + "W");
System.out.println("灯泡功率: " + lightPower + "W");
```

## 高级功能

### 1. 自定义设备动作

```java
// 创建自定义动作
DeviceAction customAction = new DeviceAction("setBrightness", "30", light);
customAction.execute();
```

### 2. 批量设备控制

```java
// 获取所有设备
List<Device> devices = household.listAllDevices();

// 批量关闭所有设备
for (Device device : devices) {
    device.powerOff();
}
```

### 3. 设备状态监控

```java
// 检查设备在线状态
if (ac.isOnline()) {
    System.out.println("空调在线");
}

// 检查电源状态
if (ac.isPowerStatus()) {
    System.out.println("空调已开启");
}
```

### 4. 用户权限管理

```java
// 检查用户权限
if (currentUser.isAdmin()) {
    System.out.println("管理员权限");
} else {
    System.out.println("普通用户权限");
}
```

## 常见问题

### Q: 如何添加新的设备类型？
A: 继承 `Device` 基类，实现必要的接口（如 `EnergyReporting`），并在 `DeviceAction` 中添加相应的命令处理。

### Q: 如何实现定时任务？
A: 可以结合Java的定时任务框架（如 `ScheduledExecutorService`）来实现定时触发自动化场景。

### Q: 如何保存和加载系统状态？
A: 可以实现序列化接口，将系统状态保存到文件或数据库中。

### Q: 如何扩展能源监控功能？
A: 实现 `EnergyReporting` 接口，添加更详细的能耗统计和分析功能。

## 最佳实践

### 1. 设备管理
- 为每个设备设置唯一的ID
- 合理设置设备名称，便于识别
- 定期检查设备电池状态

### 2. 场景设计
- 场景名称要清晰易懂
- 动作顺序要合理
- 避免冲突的设备操作

### 3. 用户管理
- 设置强密码
- 定期更换密码
- 合理分配权限

### 4. 能源管理
- 定期查看能源报告
- 优化设备使用时间
- 关注高耗能设备

## 故障排除

### 1. 设备无法控制
- 检查设备是否在线
- 确认设备电源状态
- 验证控制命令是否正确

### 2. 场景无法触发
- 检查场景ID是否正确
- 确认场景中的设备是否可用
- 验证设备动作参数

### 3. 用户登录失败
- 检查用户名和密码
- 确认用户是否已注册
- 验证用户权限

### 4. 能源报告异常
- 检查时间范围设置
- 确认设备是否支持能源监控
- 验证设备功率设置
