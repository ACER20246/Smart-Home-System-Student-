# 开发者指南

## 项目结构

```
zuoye2/
├── src/                          # 源代码目录
│   ├── HomeSphereSystem.java     # 系统主控制器
│   ├── Household.java            # 家庭管理
│   ├── Device.java              # 设备基类
│   ├── AirConditioner.java      # 空调设备
│   ├── LightBulb.java           # 智能灯泡
│   ├── SmartLock.java           # 智能门锁
│   ├── BathroomScale.java       # 浴室秤
│   ├── User.java                # 用户管理
│   ├── Room.java                # 房间管理
│   ├── AutomationScene.java     # 自动化场景
│   ├── DeviceAction.java        # 设备动作
│   ├── Manufacturer.java        # 制造商
│   ├── RunningLog.java          # 运行日志
│   ├── EnergyReporting.java     # 能源报告接口
│   ├── HomeSphereSystemTest.java # 单元测试
│   └── Main.java                # 主程序入口
├── docs/                        # 文档目录
│   ├── API_Reference.md         # API参考文档
│   ├── User_Guide.md            # 用户指南
│   └── Developer_Guide.md       # 开发者指南
├── out/                         # 编译输出目录
└── README.md                    # 项目说明文档
```

## 设计模式

### 1. 继承层次结构

```
Device (抽象基类)
├── AirConditioner
├── LightBulb
├── SmartLock
└── BathroomScale
```

### 2. 接口设计

```java
// 能源报告接口
public interface EnergyReporting {
    double getPower();
    double getReport(Date startTime, Date endTime);
}
```

### 3. 组合模式

- `Household` 包含多个 `Room`
- `Room` 包含多个 `Device`
- `AutomationScene` 包含多个 `DeviceAction`

## 核心类设计

### Device 基类

```java
public abstract class Device {
    // 核心属性
    private int deviceId;
    private String name;
    private boolean isOnline;
    private boolean powerStatus;
    private Manufacturer manufacturer;
    private Room belongToRoom;
    private List<RunningLog> runningLogs;
    
    // 核心方法
    public abstract void powerOn();
    public abstract void powerOff();
    public abstract boolean isPowerStatus();
}
```

### 设计原则

1. **单一职责原则** - 每个类只负责一个功能
2. **开闭原则** - 对扩展开放，对修改关闭
3. **里氏替换原则** - 子类可以替换父类
4. **接口隔离原则** - 接口设计要精简
5. **依赖倒置原则** - 依赖抽象而不是具体实现

## 扩展开发

### 1. 添加新设备类型

#### 步骤1：创建设备类
```java
public class SmartTV extends Device implements EnergyReporting {
    private int volume;
    private String channel;
    private double power = 100.0;
    
    public SmartTV(int deviceId, String name, Manufacturer manufacturer) {
        super(deviceId, name, manufacturer);
    }
    
    // 实现设备特定功能
    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    
    // 实现能源报告接口
    @Override
    public double getPower() {
        return power;
    }
    
    @Override
    public double getReport(Date startTime, Date endTime) {
        return power;
    }
}
```

#### 步骤2：更新DeviceAction
```java
// 在DeviceAction.execute()方法中添加新的命令处理
case "setVolume":
    if(device instanceof SmartTV){
        int volume = Integer.parseInt(parameters);
        ((SmartTV) device).setVolume(volume);
    }
    break;
case "setChannel":
    if(device instanceof SmartTV){
        ((SmartTV) device).setChannel(parameters);
    }
    break;
```

#### 步骤3：添加测试用例
```java
@Test
public void testSmartTV() {
    SmartTV tv = new SmartTV(5, "Living Room TV", manufacturer);
    tv.setVolume(50);
    tv.setChannel("CCTV-1");
    
    assertEquals(50, tv.getVolume());
    assertEquals("CCTV-1", tv.getChannel());
}
```

### 2. 添加新功能模块

#### 示例：添加定时任务功能

```java
public class ScheduledTask {
    private String taskName;
    private Date scheduledTime;
    private AutomationScene scene;
    private boolean isActive;
    
    public ScheduledTask(String taskName, Date scheduledTime, AutomationScene scene) {
        this.taskName = taskName;
        this.scheduledTime = scheduledTime;
        this.scene = scene;
        this.isActive = true;
    }
    
    public void execute() {
        if (isActive && new Date().after(scheduledTime)) {
            scene.manualTrig();
        }
    }
}
```

#### 在HomeSphereSystem中添加定时任务管理
```java
private List<ScheduledTask> scheduledTasks = new ArrayList<>();

public void addScheduledTask(ScheduledTask task) {
    scheduledTasks.add(task);
}

public void executeScheduledTasks() {
    for (ScheduledTask task : scheduledTasks) {
        task.execute();
    }
}
```

### 3. 添加数据持久化

#### 使用序列化保存系统状态
```java
public class SystemPersistence {
    public static void saveSystem(HomeSphereSystem system, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(system);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static HomeSphereSystem loadSystem(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (HomeSphereSystem) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
```

## 测试开发

### 1. 单元测试结构

```java
public class HomeSphereSystemTest {
    private HomeSphereSystem system;
    private Household household;
    // 测试数据初始化
    
    @Before
    public void setUp() {
        // 初始化测试环境
    }
    
    @After
    public void tearDown() {
        // 清理测试环境
    }
    
    @Test
    public void testSpecificFunction() {
        // 测试具体功能
    }
}
```

### 2. 测试覆盖范围

- **功能测试** - 验证所有功能正常工作
- **边界测试** - 测试边界条件
- **异常测试** - 测试异常情况处理
- **集成测试** - 测试模块间交互

### 3. 测试最佳实践

```java
// 使用断言验证结果
@Test
public void testDevicePowerControl() {
    ac.powerOn();
    assertTrue(ac.isPowerStatus());
    
    ac.powerOff();
    assertFalse(ac.isPowerStatus());
}

// 测试异常情况
@Test(expected = IllegalArgumentException.class)
public void testInvalidTemperature() {
    ac.setTargetTemp(-50.0); // 应该抛出异常
}

// 测试边界条件
@Test
public void testBrightnessBoundaries() {
    light.setBrightness(0);
    assertEquals(0, light.getBrightness());
    
    light.setBrightness(100);
    assertEquals(100, light.getBrightness());
}
```

## 性能优化

### 1. 内存管理

```java
// 使用对象池减少GC压力
public class DevicePool {
    private Queue<Device> availableDevices = new LinkedList<>();
    
    public Device getDevice() {
        return availableDevices.poll();
    }
    
    public void returnDevice(Device device) {
        availableDevices.offer(device);
    }
}
```

### 2. 并发处理

```java
// 使用线程安全的集合
private List<Device> devices = Collections.synchronizedList(new ArrayList<>());

// 使用锁保护关键操作
private final Object lock = new Object();

public void updateDevice(Device device) {
    synchronized(lock) {
        // 更新设备状态
    }
}
```

### 3. 缓存机制

```java
// 实现设备状态缓存
public class DeviceCache {
    private Map<Integer, Device> cache = new HashMap<>();
    
    public Device getDevice(int deviceId) {
        return cache.computeIfAbsent(deviceId, id -> loadDevice(id));
    }
    
    private Device loadDevice(int deviceId) {
        // 从数据库或文件加载设备
        return null;
    }
}
```

## 代码规范

### 1. 命名规范

```java
// 类名使用PascalCase
public class HomeSphereSystem {}

// 方法名和变量名使用camelCase
public void displayUsers() {}
private String userName;

// 常量使用UPPER_SNAKE_CASE
private static final int MAX_DEVICES = 100;
```

### 2. 注释规范

```java
/**
 * 智能家居系统主控制器
 * 提供用户管理、设备控制、场景管理等功能
 * 
 * @author 开发者姓名
 * @version 1.0
 * @since 2024-01-01
 */
public class HomeSphereSystem {
    
    /**
     * 用户登录功能
     * 
     * @param loginName 登录用户名
     * @param loginPassword 登录密码
     * @return 登录成功返回true，失败返回false
     */
    public boolean login(String loginName, String loginPassword) {
        // 实现代码
    }
}
```

### 3. 异常处理

```java
public void addDevice(Device device) {
    if (device == null) {
        throw new IllegalArgumentException("设备不能为空");
    }
    
    try {
        devices.add(device);
    } catch (Exception e) {
        logger.error("添加设备失败", e);
        throw new RuntimeException("添加设备失败", e);
    }
}
```

## 部署指南

### 1. 编译项目

```bash
# 编译所有源文件
javac -d out src/*.java

# 运行主程序
java -cp out Main

# 运行测试
java -cp out:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore HomeSphereSystemTest
```

### 2. 打包部署

```bash
# 创建JAR文件
jar cfm HomeSphereSystem.jar MANIFEST.MF -C out .

# 运行JAR文件
java -jar HomeSphereSystem.jar
```

### 3. 配置文件

```properties
# config.properties
system.name=HomeSphere System
system.version=1.0
max.devices=1000
max.users=50
log.level=INFO
```

## 贡献指南

### 1. 代码提交规范

- 提交信息要清晰明确
- 每次提交只包含一个功能或修复
- 提交前运行所有测试

### 2. 代码审查

- 所有代码必须经过审查
- 遵循代码规范和最佳实践
- 确保测试覆盖率

### 3. 文档更新

- 新功能必须更新文档
- API变更要更新API文档
- 用户指南要及时更新
