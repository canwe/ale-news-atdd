require "appium_lib"

class AppiumWorld
end

caps = Appium.load_appium_txt file: File.expand_path('./', __FILE__), verbose: true
Appium::Driver.new(caps)
Appium.promote_appium_methods AppiumWorld

World do
  AppiumWorld.new
end

Before { $driver.start_driver }
After { $driver.driver_quit }
