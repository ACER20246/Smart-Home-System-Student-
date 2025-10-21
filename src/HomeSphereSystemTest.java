import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

public class HomeSphereSystemTest {
	private HomeSphereSystem system;
	private Household household;
	private Manufacturer acManufacturer;
	private Manufacturer lightManufacturer;
	private AirConditioner ac;
	private LightBulb light;
	private SmartLock lock;
	private BathroomScale scale;
	private Room livingRoom;
	private Room bedroom;
	private User admin;
	private User regularUser;
	private AutomationScene eveningScene;

	@Before
	public void setUp() {
		household = new Household(1, "友谊西路127号");
		system = new HomeSphereSystem(household);
		// 创建制造商
		acManufacturer = new Manufacturer(1, "AC Corp", "WiFi, ZigBee");
		lightManufacturer = new Manufacturer(2, "Light Inc", "WiFi");

		// 创建设备
		ac = new AirConditioner(1, "Living Room AC", acManufacturer);
		light = new LightBulb(2, "Kitchen Light", lightManufacturer);
		lock = new SmartLock(3, "Front Door Lock", acManufacturer);
		scale = new BathroomScale(4, "Bathroom Scale", lightManufacturer);

		// 设置设备状态
		ac.setCurrTemp(25.0);
		ac.setTargetTemp(22.0);
		light.setBrightness(80);
		light.setColorTemp(4000);
		lock.setLocked(true);
		scale.setBodyMass(70.5);
		scale.setBatteryLevel(85);

		// 创建房间并添加设备
		livingRoom = new Room(1, "Living Room", 25.5);
		livingRoom.addDevice(ac);
		livingRoom.addDevice(light);

		bedroom = new Room(2, "Bedroom", 18.0);
		bedroom.addDevice(lock);

		// 创建家庭并添加房间
		household.addRoom(livingRoom);
		household.addRoom(bedroom);

		// 创建用户
		admin = new User(1, "admin", "111111", "管理员", "admin@nwpu.edu.cn");

		household.addUser(admin);
		household.setAdmin(admin);
		// 注册用户

		regularUser = system.register("hxt", "123456", "航小天", "htx@nwpu.edu.cn");
		household.getUsers().forEach(System.out::println);

		// 创建自动化场景
		eveningScene = new AutomationScene(1, "晚安模式", "关闭灯光调节至事宜温度");

		DeviceAction lightAction = new DeviceAction("powerOff", "", light);
		DeviceAction acAction = new DeviceAction("setTemperature", "26.0", ac);

		eveningScene.addAction(lightAction);
		eveningScene.addAction(acAction);

		household.addAutoScene(eveningScene);

	}

	@After
	public void tearDown() {
		system = null;
		household = null;
	}

	@Test
	public void testHouseholdCreation() {
		assertNotNull(household);
		assertEquals(1, household.getHouseholdId());
		assertEquals("友谊西路127号", household.getAddress());
	}

	@Test
	public void testHouseholdRoomManagement() {
		// 测试添加房间
		assertEquals(2, household.getRooms().size());

		// 测试移除房间
		household.removeRoom(2);
		assertEquals(1, household.getRooms().size());

		// 测试获取房间
		Room room = household.getRooms().get(0);
		assertEquals(1, room.getRoomId());
		assertEquals("Living Room", room.getName());
		assertEquals(25.5, room.getArea(), 0.01);
	}

	@Test
	public void testHouseholdUserManagement() {
		// 测试添加用户
		assertEquals(2, household.getUsers().size());

		// 测试移除用户
		household.removeUser(2);
		assertEquals(1, household.getUsers().size());

		// 测试获取用户
		User user = household.getUsers().get(0);
		assertEquals(1, user.getUserId());
		assertEquals("管理员", user.getUserName());
		assertTrue(user.isAdmin());
	}

	@Test
	public void testHouseholdAutomationSceneManagement() {
		// 测试添加自动化场景
		assertEquals(1, household.getAutoScenes().size());

		// 测试移除自动化场景
		household.removeAutoScene(1);
		assertEquals(0, household.getAutoScenes().size());

		// 测试获取自动化场景
		household.addAutoScene(eveningScene);
		AutomationScene scene = household.getAutoScenes().get(0);
		assertEquals(1, scene.getSceneId());
		assertEquals("晚安模式", scene.getName());
	}

	@Test
	public void testHouseholdListAllDevices() {
		List<Device> devices = household.listAllDevices();
		assertEquals(3, devices.size()); // AC, Light, Lock

		// 验证设备类型
		boolean hasAC = false;
		boolean hasLight = false;
		boolean hasLock = false;

		for (Device device : devices) {
			if (device instanceof AirConditioner)
				hasAC = true;
			if (device instanceof LightBulb)
				hasLight = true;
			if (device instanceof SmartLock)
				hasLock = true;
		}

		assertTrue(hasAC);
		assertTrue(hasLight);
		assertTrue(hasLock);
	}

	@Test
	public void testRoomDeviceManagement() {
		// 测试添加设备
		assertEquals(2, livingRoom.getDevices().size());

		// 测试移除设备
		livingRoom.removeDevice(2);
		assertEquals(1, livingRoom.getDevices().size());

		// 测试获取设备
		Device device = livingRoom.getDevices().get(0);
		assertEquals(1, device.getDeviceId());
		assertEquals("Living Room AC", device.getName());
	}

	@Test
	public void testDevicePowerManagement() {
		// 测试设备电源状态
		assertFalse(ac.isPowerStatus());

		// 测试开机
		ac.powerOn();
		assertTrue(ac.isPowerStatus());

		// 测试关机
		ac.powerOff();
		assertFalse(ac.isPowerStatus());
	}

	@Test
	public void testAirConditionerTemperature() {
		// 测试当前温度
		assertEquals(25.0, ac.getCurrTemp(), 0.01);

		// 测试目标温度设置
		ac.setTargetTemp(20.0);
		assertEquals(20.0, ac.getTargetTemp(), 0.01);
	}

	@Test
	public void testLightBulbSettings() {
		// 测试亮度
		assertEquals(80, light.getBrightness());

		// 测试色温
		assertEquals(4000, light.getColorTemp());

		// 测试设置亮度
		light.setBrightness(50);
		assertEquals(50, light.getBrightness());

		// 测试设置色温
		light.setColorTemp(3000);
		assertEquals(3000, light.getColorTemp());
	}

	@Test
	public void testSmartLock() {
		// 测试锁状态
		assertTrue(lock.isLocked());

		// 测试解锁
		lock.setLocked(false);
		assertFalse(lock.isLocked());

		// 测试电池电量
		assertEquals(0, lock.getBatteryLevel()); // 初始值为0
		lock.setBatteryLevel(75);
		assertEquals(75, lock.getBatteryLevel());
	}

	@Test
	public void testBathroomScale() {
		// 测试体重
		assertEquals(70.5, scale.getBodyMass(), 0.01);

		// 测试电池电量
		assertEquals(85, scale.getBatteryLevel());

		// 测试设置体重
		scale.setBodyMass(72.0);
		assertEquals(72.0, scale.getBodyMass(), 0.01);

		// 测试设置电池电量
		scale.setBatteryLevel(90);
		assertEquals(90, scale.getBatteryLevel());
	}

	@Test
	public void testEnergyReporting() {
		// 测试能源报告接口
		assertTrue(ac instanceof EnergyReporting);
		assertTrue(light instanceof EnergyReporting);
		assertFalse(lock instanceof EnergyReporting);
		assertFalse(scale instanceof EnergyReporting);

		// 测试获取功率
		ac.powerOn();
		double acPower = ((EnergyReporting) ac).getPower();
		assertTrue(acPower > 0);

		light.powerOn();
		double lightPower = ((EnergyReporting) light).getPower();
		assertTrue(lightPower > 0);

		// 测试能源报告
		Date startTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // 24小时前
		Date endTime = new Date(); // 现在

		double acEnergy = ((EnergyReporting) ac).getReport(startTime, endTime);
		double lightEnergy = ((EnergyReporting) light).getReport(startTime, endTime);

		assertTrue(acEnergy >= 0);
		assertTrue(lightEnergy >= 0);
	}

	@Test
	public void testAutomationScene() {
		// 测试场景属性
		assertEquals(1, eveningScene.getSceneId());
		assertEquals("晚安模式", eveningScene.getName());
		assertEquals("关闭灯光调节至事宜温度", eveningScene.getDescription());

		// 测试场景动作
		List<DeviceAction> actions = eveningScene.getActions();
		assertEquals(2, actions.size());

		// 测试手动触发场景
		eveningScene.manualTrig(); // 应该执行两个动作
	}

	@Test
	public void testDeviceAction() {
		// 创建设备动作
		DeviceAction action = new DeviceAction("setTemperature", "20.0", ac);

		// 测试动作属性
		assertEquals("setTemperature", action.getCommand());
		assertEquals("20.0", action.getParameters());
		assertEquals(ac, action.getDevice());

		// 测试执行动作
		action.execute(); // 应该设置空调目标温度为20.0
		assertEquals(20.0, ac.getTargetTemp(), 0.01);
	}

	@Test
	public void testManufacturer() {
		// 测试制造商属性
		assertEquals(1, acManufacturer.getManufacturerId());
		assertEquals("AC Corp", acManufacturer.getName());
		assertEquals("WiFi, ZigBee", acManufacturer.getProtocols());

		// 测试设备管理
		assertEquals(0, acManufacturer.getDevices().size()); // 初始为空
		acManufacturer.addDevice(ac);
		assertEquals(1, acManufacturer.getDevices().size());

		acManufacturer.removeDevice(ac);
		assertEquals(0, acManufacturer.getDevices().size());
	}

	@Test
	public void testRunningLog() {
		// 创建运行日志
		Date now = new Date();
		RunningLog log = new RunningLog(now, "Device turned on", RunningLog.Type.INFO, "User initiated");

		// 测试日志属性
		assertEquals(now, log.getDateTime());
		assertEquals("Device turned on", log.getEvent());
		assertEquals(RunningLog.Type.INFO, log.getType());
		assertEquals("User initiated", log.getNote());
	}

	@Test
	public void testHomeSphereSystem() {
		// 测试系统功能
		system.login("hxt", "111111"); // 应该打印登录消息
		assertNull(system.getCurrentUser());
		
		system.login("hxt", "123456"); // 应该打印登录消息
		assertNotNull(system.getCurrentUser());

		// 测试显示功能
		system.displayUsers(); // 应该打印用户列表
		system.displayRooms(); // 应该打印房间列表
		system.displayDevices(); // 应该打印设备列表
		system.displayAutoScenes(); // 应该打印自动化场景列表

		// 测试能源报告
		Date startTime = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // 24小时前
		Date endTime = new Date(); // 现在
		system.displayEnergyReportings(startTime, endTime); // 应该打印能源报告

		// 测试手动触发场景
		system.manualTrigSceneById(1); // 应该触发场景

		system.logoff(); // 应该打印登出消息
		assertNull(system.getCurrentUser());
	}

}