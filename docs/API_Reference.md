# API 参考文档

## HomeSphereSystem 类

### 构造函数
```java
public HomeSphereSystem(Household household)
```
创建智能家居系统实例。

**参数：**
- `household` - 家庭管理对象

### 方法

#### login
```java
public boolean login(String loginName, String loginPassword)
```
用户登录功能。

**参数：**
- `loginName` - 登录用户名
- `loginPassword` - 登录密码

**返回值：**
- `boolean` - 登录成功返回true，失败返回false

#### register
```java
public User register(String loginName, String loginPassword, String userName, String email)
```
用户注册功能。

**参数：**
- `loginName` - 登录用户名
- `loginPassword` - 登录密码
- `userName` - 用户显示名称
- `email` - 用户邮箱

**返回值：**
- `User` - 新创建的用户对象

#### logoff
```java
public void logoff()
```
用户登出功能。

#### getCurrentUser
```java
public User getCurrentUser()
```
获取当前登录用户。

**返回值：**
- `User` - 当前用户对象，未登录时返回null

#### displayUsers
```java
public void displayUsers()
```
显示所有用户列表。

#### displayRooms
```java
public void displayRooms()
```
显示所有房间列表。

#### displayDevices
```java
public void displayDevices()
```
显示所有设备列表。

#### displayAutoScenes
```java
public void displayAutoScenes()
```
显示所有自动化场景列表。

#### displayEnergyReportings
```java
public void displayEnergyReportings(Date startTime, Date endTime)
```
显示能源使用报告。

**参数：**
- `startTime` - 开始时间
- `endTime` - 结束时间

#### manualTrigSceneById
```java
public void manualTrigSceneById(int sceneId)
```
手动触发指定ID的自动化场景。

**参数：**
- `sceneId` - 场景ID

## Household 类

### 构造函数
```java
public Household(int houseHoldId, String address)
```
创建家庭管理对象。

**参数：**
- `houseHoldId` - 家庭ID
- `address` - 家庭地址

### 方法

#### 房间管理
```java
public void addRoom(Room room)
public void removeRoom(int roomId)
public List<Room> getRooms()
```

#### 用户管理
```java
public void addUser(User user)
public void removeUser(int userId)
public List<User> getUsers()
```

#### 自动化场景管理
```java
public void addAutoScene(AutomationScene autoScene)
public void removeAutoScene(int sceneId)
public List<AutomationScene> getAutoScenes()
```

#### 设备管理
```java
public List<Device> listAllDevices()
```
获取所有房间中的所有设备。

#### 管理员设置
```java
public void setAdmin(User admin)
```
设置家庭管理员。

## Device 抽象类

### 构造函数
```java
public Device(int deviceId, String name, Manufacturer manufacturer)
```

### 属性访问器
```java
public int getDeviceId()
public String getName()
public boolean isOnline()
public boolean isPowerStatus()
```

### 电源控制
```java
public void powerOn()
public void powerOff()
```

## AirConditioner 类

### 构造函数
```java
public AirConditioner(int deviceId, String name, Manufacturer manufacturer)
```

### 温度控制
```java
public void setCurrTemp(double temp)
public double getCurrTemp()
public void setTargetTemp(double temp)
public double getTargetTemp()
```

### 能源报告
```java
public double getPower()
public double getReport(Date startTime, Date endTime)
```

## LightBulb 类

### 构造函数
```java
public LightBulb(int deviceId, String name, Manufacturer manufacturer)
```

### 亮度控制
```java
public void setBrightness(int brightness)
public int getBrightness()
```

### 色温控制
```java
public void setColorTemp(int colorTemp)
public int getColorTemp()
```

### 能源报告
```java
public double getPower()
public double getReport(Date startTime, Date endTime)
```

## SmartLock 类

### 构造函数
```java
public SmartLock(int deviceId, String name, Manufacturer manufacturer)
```

### 锁定控制
```java
public void setLocked(boolean isLocked)
public boolean isLocked()
```

### 电池管理
```java
public void setBatteryLevel(int batteryLevel)
public int getBatteryLevel()
```

## BathroomScale 类

### 构造函数
```java
public BathroomScale(int deviceId, String name, Manufacturer manufacturer)
```

### 体重管理
```java
public void setBodyMass(double bodyMass)
public double getBodyMass()
```

### 电池管理
```java
public void setBatteryLevel(int batteryLevel)
public int getBatteryLevel()
```

## User 类

### 构造函数
```java
public User(int userId, String logName, String password, String userName, String email)
```

### 属性访问器
```java
public int getUserId()
public String getLoginName()
public String getLoginPassword()
public String getUserName()
public String getEmail()
public boolean isAdmin()
```

### 管理员设置
```java
public void setAdmin()
```

## Room 类

### 构造函数
```java
public Room(int roomId, String roomName, double area)
```

### 属性访问器
```java
public int getRoomId()
public String getName()
public double getArea()
```

### 设备管理
```java
public void addDevice(Device device)
public void removeDevice(int deviceId)
public List<Device> getDevices()
```

## AutomationScene 类

### 构造函数
```java
public AutomationScene(int sceneId, String name, String description)
```

### 属性访问器
```java
public int getSceneId()
public String getName()
public String getDescription()
```

### 动作管理
```java
public void addAction(DeviceAction action)
public void removeAction(DeviceAction action)
public List<DeviceAction> getActions()
```

### 场景触发
```java
public void manualTrig()
```

## DeviceAction 类

### 构造函数
```java
public DeviceAction(String command, String parameters, Device device)
```

### 属性访问器
```java
public String getCommand()
public String getParameters()
public Device getDevice()
```

### 动作执行
```java
public void execute()
```

支持的命令：
- `powerOn` - 开启设备
- `powerOff` - 关闭设备
- `setTemperature` - 设置温度（空调）
- `setBrightness` - 设置亮度（灯泡）
- `setColorTemp` - 设置色温（灯泡）
- `setLocked` - 设置锁定状态（门锁）

## Manufacturer 类

### 构造函数
```java
public Manufacturer(int manufacturerId, String name, String protocols)
```

### 属性访问器
```java
public int getManufacturerId()
public String getName()
public String getProtocols()
```

### 设备管理
```java
public void addDevice(Device device)
public void removeDevice(Device device)
public List<Device> getDevices()
```

## RunningLog 类

### 构造函数
```java
public RunningLog(Date dateTime, String event, Type type, String note)
```

### 枚举类型
```java
public enum Type {
    INFO,   // 信息级
    WARN,   // 警告级
    ERROR   // 错误级
}
```

### 属性访问器
```java
public Date getDateTime()
public String getEvent()
public Type getType()
public String getNote()
```

## EnergyReporting 接口

### 方法

#### getPower
```java
public double getPower()
```
获取设备当前功率。

**返回值：**
- `double` - 功率值（瓦特）

#### getReport
```java
public double getReport(Date startTime, Date endTime)
```
获取指定时间段的能耗报告。

**参数：**
- `startTime` - 开始时间
- `endTime` - 结束时间

**返回值：**
- `double` - 能耗值（千瓦时）
